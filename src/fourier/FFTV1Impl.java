package fourier;

import data.FourierObjectV1;
import data.WaveFourierObjekt;
import data.WaveObjectV1;

public class FFTV1Impl implements FFTV1{
    @Override
    public FourierObjectV1 fft(WaveObjectV1 wo) {
        double[] data = fillArray(wo.getJavaPCM());
        Complex[] output = doFFT(data, true);
        double[] real, imaginary, magnitude;

        real = getReal(output);
        imaginary = getImaginary(output);
        magnitude = getMagnitude(output);

        return new WaveFourierObjekt(wo, real, imaginary, magnitude);
    }

    private double[] getMagnitude(Complex[] output) {
        double[] magnitude = new double[output.length];

        for(int i = 0; i < output.length; i++) magnitude[i] = output[i].getMagnitude();

        return magnitude;
    }

    private double[] getImaginary(Complex[] output) {
        double[] imaginary = new double[output.length];

        for(int i = 0; i < output.length; i++) imaginary[i] = output[i].getImaginary();

        return imaginary;
    }

    private double[] getReal(Complex[] output) {
        double[] real = new double[output.length];

        for(int i = 0; i < output.length; i++) real[i] = output[i].getReal();

        return real;
    }

    //mode true: fft, false: ifft
    private Complex[] doFFT(double[] data, boolean mode){
        if(data.length == 1) {
            return new Complex[]{new ComplexImpl(data[0], 0)};
        }

        Complex angle;
        if(mode) angle = new ComplexImpl(Math.cos(2 * Math.PI / data.length), Math.sin(2 * Math.PI / data.length));
        else angle = new ComplexImpl(Math.cos(-2 * Math.PI / data.length), Math.sin(-2 * Math.PI / data.length)).times(1.0/data.length);

        double[] dataEven = new double[data.length/2], dataOdd = new double[data.length/2];
        Complex[] outputEven, outputOdd, output = new ComplexImpl[data.length];

        for (int i = 0; i < data.length/2; i++){
            dataEven[i] = data[i*2];
            dataOdd[i] = data[i*2+1];
        }

        outputEven = doFFT(dataEven, mode);
        outputOdd = doFFT(dataOdd, mode);

        for(int i = 0; i < data.length/2; i++){
            output[i] = outputEven[i].add(outputOdd[i].times(angle.pow(i)));
            output[i + data.length / 2] = outputEven[i].substract(outputOdd[i].times(angle.pow(i)));
        }

        return output;
    }

    private double[] fillArray(double[] data){
        if(data.length <= 2) return data;

        Integer newLength = 2;

        while (newLength.compareTo(data.length) < 0){
            newLength *= 2;
        }

        double[] output = new double[newLength];

        for(int i = 0; i < data.length; i++) output[i] = data[i];
        return output;
    }

    @Override
    public WaveObjectV1 ifft(FourierObjectV1 fo) {
        return null;
    }
}
