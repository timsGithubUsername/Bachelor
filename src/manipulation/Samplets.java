package manipulation;

import data.SoundObjectV1;
import data.SoundObjectV1Impl;
import fourier.FFTV1;
import fourier.FFTV1Impl;

public class Samplets {

    private int sampletSize; //must be 2^n!!! max 16384 is good
    private double sampletTaperFactor;
    private Integer sampletTaperNumber;

    public Samplets(int sampletSize, SoundObjectV1 so){
        this.sampletSize = sampletSize;
        sampletTaperFactor = 1.0 / 4; //Do not Change careless, this magic number is use in fillSamplets - numberOfSamplets!!!
        sampletTaperNumber = (int) (sampletSize * sampletTaperFactor);

        fillSamplets(so);
    }

    public Samplets(SoundObjectV1 so){
        //constructor for testing!
        this.sampletSize = 8192;
        sampletTaperFactor = 1.0 / 4; //Do not Change
        sampletTaperNumber = (int) (sampletSize * sampletTaperFactor);

        fillSamplets(so);
    }
    FFTV1 fft = new FFTV1Impl();

    private SoundObjectV1[] samplets;

    public SoundObjectV1[] getSamplets() {return samplets;}
    public SoundObjectV1 getSoundObject() {
        SoundObjectV1 output = new SoundObjectV1Impl(samplets[0]);

        for (int i = 0; i < samplets.length; i++) fft.ifft(samplets[i]);

        output.setJavaPCM(joinSamplet());

        return output;
    }

    private void fillSamplets(SoundObjectV1 so) {
        Integer numberOfSamplets = (so.getJavaPCM().length - sampletTaperNumber) / (sampletSize - sampletTaperNumber);
        samplets = new SoundObjectV1[numberOfSamplets];

       // double percentagePerSamplet = 100.0 / numberOfSamplets, percentage = 0;
       // Integer oldPercentage = 0;

        for (int i = 0; i < numberOfSamplets; i++) {
            samplets[i] = new SoundObjectV1Impl(so);

            samplets[i].setJavaPCM(createSamplet(so.getJavaPCM(), i, numberOfSamplets));

            fft.fft(samplets[i]);

         //   percentage += percentagePerSamplet;
         //   if(percentage - (percentage % 1) > oldPercentage){
         //       oldPercentage = (int) percentage;
         //       System.out.println(oldPercentage);
         //   }
        }
    }

    private double[] createSamplet(double[] javaPCM, int sampletNumber, Integer numberOfSamplets) {
        double[] samplet = new double[sampletSize];
        int currentPosInPCMArray;

        for (int i = 0; i < sampletSize; i++) {
            currentPosInPCMArray = i + sampletNumber * sampletSize - sampletNumber * sampletTaperNumber;
            samplet[i] = javaPCM[currentPosInPCMArray];

            //smooth out at the edges
            //if its near the edges, signal gets flatten out
            if (sampletTaperNumber.compareTo(i) > 0) samplet[i] *= i / (double) sampletTaperNumber;
            if (sampletTaperNumber.compareTo(sampletSize - (i + 1)) > 0) samplet[i] *= (sampletSize - (i + 1)) / (double) sampletTaperNumber;
        }

        return samplet;
    }

    private double[] joinSamplet() {
        int numberOfSamplets = samplets.length;
        int samplesLength = numberOfSamplets * (sampletSize - sampletTaperNumber) + sampletTaperNumber;
        int maxValueInSamplet;

        double[] samples = new double[samplesLength];

        for (int sampletNumber = 0; sampletNumber < numberOfSamplets; sampletNumber++){

            //only the last samplet being transfered completly, the others are getting calculated
            if(sampletNumber == numberOfSamplets - 1) maxValueInSamplet = sampletSize;
            else maxValueInSamplet = sampletSize - sampletTaperNumber;

            for(int i = 0; i < maxValueInSamplet; i++){
                samples[sampletNumber * (sampletSize - sampletTaperNumber) + i] = samplets[sampletNumber].getJavaPCM()[i];

                //rejoin the edges
                //if its near the edges, signal gets flatten out
                //if (sampletNumber < numberOfSamplets - 1 && sampletTaperNumber.compareTo(sampletSize - i) > 0){
                //    samples[sampletNumber * sampletSize + i] += samplets[sampletNumber+1].getJavaPCM()[(i + sampletTaperNumber) - (sampletSize)];
                if (sampletNumber > 0 && sampletTaperNumber.compareTo(i) > 0) {
                    samples[sampletNumber * (sampletSize - sampletTaperNumber) + i] += samplets[sampletNumber - 1].getJavaPCM()[sampletSize-(sampletTaperNumber-i)];
                }
            }
        }

        return samples;
    }

}
