package fourier;

import data.FourierObjectV1;
import data.FourierObjectV1Impl;
import data.WaveObjectV1;

public class DFTV1Impl implements DFTV1{
    @Override
    public FourierObjectV1 dft(WaveObjectV1 wo) {
        //int dataLength = wo.getJavaPCM().length;
        int dataLength = 10000;
        double[] real = new double[dataLength], img = new double[dataLength], amplitude = new double[dataLength];

        Integer temp = 0;
        Integer newTemp = 0;
        System.out.println("Transform...");
        for(int output = 0; output < dataLength; output++) {//for each output
            newTemp = (100 * output) / dataLength;
            if(!temp.equals(newTemp)){
                System.out.println(temp+" %");
                temp = newTemp;
            }
            real[output] = 0;
            img[output] = 0;
            for(int input = 0; input < wo.getJavaPCM().length; input++){//do for every input
                real[output] += wo.getJavaPCM()[input] * Math.cos(2 * Math.PI * input * output / dataLength);
                img[output] -= wo.getJavaPCM()[input] * Math.sin(2 * Math.PI * input * output / dataLength);
            }
        }

        return new FourierObjectV1Impl(wo.getName() + "Fourier", real, img, calcAmplitude(real, img));
    }

    private double[] calcAmplitude(double[] real, double[] img){
        double[] output = new double[real.length];
        for(int i = 0; i < real.length; i++){
            output[i] = Math.sqrt(Math.pow(real[i], 2)+Math.pow(img[i], 2));
        }
        return output;
    }
}
