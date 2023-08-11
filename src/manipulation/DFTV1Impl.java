package manipulation;

import data.*;
import manipulation.DFTV1;

public class DFTV1Impl implements DFTV1 {
    @Override
    public void dft(SoundObjectV1 so) {
        int dataLength = so.getJavaPCM().length;

        Complex[] frequency = new Complex[dataLength];

        for(int output = 0; output < dataLength; output++) {//for each output
            frequency[output] = new ComplexImpl();
            for(int input = 0; input < so.getJavaPCM().length; input++){//do for every input
                frequency[output] = frequency[output].add(so.getJavaPCM()[input] * Math.cos(2 * Math.PI * input * output / dataLength)
                        - so.getJavaPCM()[input] * Math.sin(2 * Math.PI * input * output / dataLength));
            }
        }

        so.setFrequency(frequency);
    }

    @Override
    public void idft(SoundObjectV1 so) {
        //x[n] = 1/N sum^N-1_k=0 X[k] exp(i 2 PI k n / N)
        //cos(x) + i*sin(x) = exp(i x)
        //=> x[n] = 1/N sum^N-1_k=0 x[k](cos(2 PI k n / N) + i * sin(2 PI k n / N)
        int length = so.getFrequency().length; //N
        Complex[] output = new Complex[length];
        Complex temp;
        Complex current;

        for(int n = 0; n < length; n++){
            temp = new ComplexImpl();

            for(int k = 0; k < length; k++) {
                current = so.getFrequency()[k];
                temp.add(current.times(new ComplexImpl(Math.cos(2 * Math.PI * k * n / length), Math.sin(2 * Math.PI * k * n / length))));
            }
            output[n] = temp.times(1.0/length);
        }

        double[] outputReal = new double[output.length];
        for(int i = 0; i < outputReal.length; i++) outputReal[i] = output[i].getReal();

        so.setJavaPCM(outputReal);
    }
}
