package fourier;

import data.FourierObjectV1;
import data.FourierObjectV1Impl;
import data.WaveObjectV1;

public class DFTV1Impl implements DFTV1{
    @Override
    public FourierObjectV1 dft(WaveObjectV1 wo) {
        int dataLength = wo.getJavaPCM().length;
        //int dataLength = 10000;
        double[] real = new double[dataLength], img = new double[dataLength], amplitude = new double[dataLength];

        //console solution to display progress.
        //todo solve this with gui
        Integer tempPercent = 0;
        Integer percent = 0;
        System.out.println("Transform...");

        for(int output = 0; output < dataLength; output++) {//for each output
            percent = (100 * output) / dataLength;
            if(!tempPercent.equals(percent)){
                System.out.println(tempPercent+" %");
                tempPercent = percent;
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

    @Override
    public WaveObjectV1 idft(FourierObjectV1 fo) {
        //x[n] = 1/N sum^N-1_k=0 X[k] exp(i 2 PI k n / N)
        return null;
    }

    private double[] calcAmplitude(double[] real, double[] img){
        double[] output = new double[real.length];
        for(int i = 0; i < real.length; i++){
            output[i] = Math.sqrt(Math.pow(real[i], 2)+Math.pow(img[i], 2));
        }
        return output;
    }
}
