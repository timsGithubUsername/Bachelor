package fourier;

import data.SoundObjectV1;

public class FFTV1Impl implements FFTV1{
    @Override
    public void fft(SoundObjectV1 so) {
        double[] data = fillArrayWithZeros(so.getJavaPCM());
        Complex[] output = doFFT(doubleToComplexArray(data), true);

        so.setFrequency(output);
    }

    @Override
    public void ifft(SoundObjectV1 so) {
        Complex[] output = doFFT(so.getFrequency(), false);

        so.setPCMFromIFFT(output);
    }

    //true fft, false ifft
    private Complex[] doFFT(Complex[] data, boolean mode){
        if(data.length == 1) {
            return new Complex[]{data[0]};
        }

        Complex angle;
        if(mode) angle = new ComplexImpl(Math.cos(2 * Math.PI / data.length), Math.sin(2 * Math.PI / data.length));
        else angle = new ComplexImpl(Math.cos(-2 * Math.PI / data.length), Math.sin(-2 * Math.PI / data.length));


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

    private double[] fillArrayWithZeros(double[] data){
        if(data.length <= 2) return data;

        Integer newLength = 2;

        while (newLength.compareTo(data.length) < 0){
            newLength *= 2;
        }

        double[] output = new double[newLength];

        for(int i = 0; i < data.length; i++) output[i] = data[i];
        return output;
    }

    private Complex[] doubleToComplexArray(double[] array){
        Complex[] complexArray = new ComplexImpl[array.length];

        for(int i = 0; i < array.length; i++) complexArray[i] = new ComplexImpl(array[i], 0);

        return complexArray;
    }
}
