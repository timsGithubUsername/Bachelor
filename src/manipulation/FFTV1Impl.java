package manipulation;

import data.Complex;
import data.ComplexImpl;
import data.SoundObjectV1;
import manipulation.FFTV1;

public class FFTV1Impl implements FFTV1 {
    @Override
    public void fft(SoundObjectV1 so) {
        Complex[] data = fillSampleArrayWithZeros(doubleToComplexArray(so.getJavaPCM()));
        Complex[] output = doFFT(data, true);

        so.setFrequency(output);
    }

    @Override
    public void ifft(SoundObjectV1 so) {
        Complex[] data = fillFrequencyArrayWithZeros(so.getFrequency());
        Complex[] output = doFFT(data, false);

        so.setJavaPCM(calcPCM(output));
    }

    private double[] calcPCM(Complex[] complexPCM) {
        int n = complexPCM.length;
        double[] output = new double[n];

        for(int i = 0; i < n; i++) output[i] = complexPCM[i].times(1.0/n).getReal();

        return output;
    }

    //true fft, false ifft
    private Complex[] doFFT(Complex[] data, boolean mode){
        if(data.length == 1) {
            return new Complex[]{data[0]};
        }

        Complex angle;
        if(mode) angle = new ComplexImpl(Math.cos(2 * Math.PI / data.length), Math.sin(2 * Math.PI / data.length));
        else angle = new ComplexImpl(Math.cos(2 * Math.PI / data.length), -Math.sin(2 * Math.PI / data.length));


        Complex[] dataEven = new Complex[data.length/2], dataOdd = new Complex[data.length/2];
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

    private Complex[] fillSampleArrayWithZeros(Complex[] data){
        if(data.length <= 2) return data;
        Integer newLength = getNextFactor2(data);

        Complex[] output = new Complex[newLength];

        for(int i = 0; i < data.length; i++) output[i] = data[i];
        for(int i = data.length; i < newLength; i++) output[i] = new ComplexImpl();

        return output;
    }

    private Complex[] fillFrequencyArrayWithZeros(Complex[] data){
        if(data.length <= 2) return data;
        Integer newLength = getNextFactor2(data);

        Complex[] output = new Complex[newLength];

        for(int i = 0; i < data.length/2; i++) output[i] = data[i];
        for(int i = data.length/2; i < newLength - data.length/2; i++) output[i] = new ComplexImpl();
        for(int i = 1; i <= data.length/2; i++) output[newLength - i] = data[data.length - i];

        return output;
    }
    private Integer getNextFactor2(Complex[] data){
        Integer newLength = 2;
        while (newLength.compareTo(data.length) < 0){
            newLength *= 2;
        }
        return newLength;
    }
    private Complex[] doubleToComplexArray(double[] array){
        Complex[] complexArray = new ComplexImpl[array.length];

        for(int i = 0; i < array.length; i++) complexArray[i] = new ComplexImpl(array[i], 0);

        return complexArray;
    }
}
