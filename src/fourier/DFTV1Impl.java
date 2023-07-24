package fourier;

import data.*;

public class DFTV1Impl implements DFTV1{
    @Override
    public FourierObjectV1 dft(WaveObjectV1 wo) {
        int dataLength = wo.getJavaPCM().length;
        //int dataLength = 10000;
        double[] real = new double[dataLength], img = new double[dataLength], amplitude = new double[dataLength];

        for(int output = 0; output < dataLength; output++) {//for each output
            real[output] = 0;
            img[output] = 0;
            for(int input = 0; input < wo.getJavaPCM().length; input++){//do for every input
                real[output] += wo.getJavaPCM()[input] * Math.cos(2 * Math.PI * input * output / dataLength);
                img[output] -= wo.getJavaPCM()[input] * Math.sin(2 * Math.PI * input * output / dataLength);
            }
        }

        return new WaveFourierObjekt(wo.getName() + "Fourier", wo.getChannels(), wo.getSampleRate(),
                                    wo.getByteRate(), wo.getBlockAlign(), wo.getBitsPerSample(), wo.getAudioData(),
                                    wo.getJavaPCM(), real, img, calcAmplitude(real, img));
    }

    @Override
    public WaveObjectV1 idftReal(WaveFourierObjekt fo) {
        //x[n] = 1/N sum^N-1_k=0 X[k] exp(i 2 PI k n / N)
        //cos(x) + i*sin(x) = exp(i x)
        //=> x[n] = 1/N sum^N-1_k=0 x[k](cos(2 PI k n / N) + i * sin(2 PI k n / N)
        int length = fo.getAmplitude().length; //N
        double[] output = new double[length];
        double temp;
        ComplexMutable current = new ComplexMutableImpl(0,0);

        for(int n = 0; n < length; n++){
            temp = 0;

            for(int k = 0; k < length; k++) {
                current.setReal(fo.getReal()[k]);
                current.setImaginary(fo.getImg()[k]);
                temp += current.times(new ComplexMutableImpl(Math.cos(2 * Math.PI * k * n / length), Math.sin(2 * Math.PI * k * n / length))).getReal();
            }
            output[n] = temp / length;
        }

        return new WaveObjectV1Impl(fo.getName(), fo.getChannels(),fo.getSampleRate(),fo.getByteRate(),
                fo.getBlockAlign(),fo.getBitsPerSample(),fo.getAudioData(),output);
    }

    private double[] calcAmplitude(double[] real, double[] img){
        double[] output = new double[real.length];
        for(int i = 0; i < real.length; i++){
            output[i] = Math.sqrt(Math.pow(real[i], 2)+Math.pow(img[i], 2));
        }
        return output;
    }
}
