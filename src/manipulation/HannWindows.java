package manipulation;

import data.SoundObjectV1;
import data.SoundObjectV1Impl;
import graphics.PlotterV1;
import graphics.PlotterV1Impl;

import java.util.ArrayList;

class HannWindows implements Window {
    private int sampletSize; //should be 2^n!!! max 16384 is good
    private double sampletTaperFactor;
    private Integer sampletTaperLength, fullSize, overlapFactor;
    private SoundObjectV1[] samplets;
    private FFTV1 fft = new FFTV1Impl();

    protected int getHopSize(){return sampletTaperLength;}

    protected HannWindows(int sampletSize, SoundObjectV1 so) {
        this.sampletSize = sampletSize;
        sampletTaperFactor = 0.5; //do not change careless! this factor is used in fillSamplets()!
        overlapFactor = 2;
        sampletTaperLength = (int) (sampletSize * sampletTaperFactor);

        fillSamplets(so);

        fullSize = samplets.length * sampletSize;
    }

    protected HannWindows(SoundObjectV1 so) {
        this.sampletSize = 2048;
        sampletTaperFactor = 0.5; //do not change careless! this factor is used in fillSamplets()!
        overlapFactor = 2;
        sampletTaperLength = (int) (sampletSize * sampletTaperFactor);

        fillSamplets(so);

        fullSize = samplets.length * sampletSize;
    }

    @Override
    public int getTaperLength() {
        return sampletTaperLength;
    }

    @Override
    public SoundObjectV1[] getSamplets() {
        return samplets;
    }

    @Override
    public SoundObjectV1 getSoundObject() {
        SoundObjectV1 output = new SoundObjectV1Impl(samplets[0]);

        for (int i = 0; i < samplets.length; i++) fft.ifft(samplets[i]);

        output.setJavaPCM(joinSamplet());

        return output;
    }
    @Override
    public int getFullSize() {
        return fullSize;
    }

    @Override
    public int getOverlapFactor() {
        return overlapFactor;
    }

    private void fillSamplets(SoundObjectV1 so) {
        Integer numberOfSamplets = (so.getJavaPCM().length / (sampletSize - sampletTaperLength)) - (overlapFactor - 1);
        samplets = new SoundObjectV1[numberOfSamplets];

        for (int i = 0; i < numberOfSamplets; i++) {
            samplets[i] = new SoundObjectV1Impl(so);

            samplets[i].setJavaPCM(createSamplet(so.getJavaPCM(), i));

            fft.fft(samplets[i]);
        }
    }

    private double[] createSamplet(double[] javaPCM, int sampletNumber) {
        double[] samplet = new double[sampletSize];
        int currentPosInPCMArray = sampletNumber * sampletSize - sampletNumber * sampletTaperLength;

        for (int i = 0; i < sampletSize; i++) {
            samplet[i] = javaPCM[currentPosInPCMArray + i] * hanFactor(i);
        }

        return samplet;
    }

    private double hanFactor(int i) {
        return (1.0 / 2) * (1 - Math.cos(2 * Math.PI * i / (sampletSize - 1.0)));
    }

    private double[] joinSamplet() {
        //recalculate after possible manipulation
        this.sampletSize = samplets[0].getFrequency().length;
        this.sampletTaperLength = (int) (sampletSize * sampletTaperFactor);

        int numberOfSamplets = samplets.length;
        int hopSize = sampletSize-sampletTaperLength;
        int sampleIndex = 0;
        double newSample = 0;

        ArrayList<Double> samples = new ArrayList<Double>();

        for (int sampletNumber = 0; sampletNumber < numberOfSamplets; sampletNumber++) {

            for (int sampletIndex = 0; sampletIndex < sampletSize; sampletIndex++) {
                if(samples.size() <= sampleIndex + sampletIndex) samples.add(Double.valueOf(0));

                newSample = samples.get(sampleIndex + sampletIndex)+samplets[sampletNumber].getJavaPCM()[sampletIndex];
                samples.set(sampleIndex + sampletIndex, newSample);
            }
            sampleIndex += hopSize;
        }

        double[] output = new double[samples.size()];

        for (int i = 0; i < output.length; i++) output[i] = samples.get(i);

        return output;
    }

}
