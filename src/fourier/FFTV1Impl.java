package fourier;

import data.SoundObjectV1;

public class FFTV1Impl implements FFTV1{
    @Override
    public void fft(SoundObjectV1 so) {
        double[] data = fillArrayWithZeros(so.getJavaPCM());
        Complex[] output = doFFT(data);

        so.setFrequency(output);
    }

    @Override
    public void ifft(SoundObjectV1 so) {
        double[] output = doIFFT(so.getFrequency());

        so.setPCMFromIFFT(output);
    }

    private double[] doIFFT(Complex[] data) {
        if(data.length == 1) {
            return new double[]{data[0].getReal()};
        }

        Complex angle = new ComplexImpl(Math.cos(-2 * Math.PI / data.length), Math.sin(-2 * Math.PI / data.length)).times(1.0/data.length);

        Complex[] dataEven = new Complex[data.length/2], dataOdd = new Complex[data.length/2];
        double[] outputEven, outputOdd, output = new double[data.length];

        for (int i = 0; i < data.length/2; i++){
            dataEven[i] = data[i*2];
            dataOdd[i] = data[i*2+1];
        }

        outputEven = doIFFT(dataEven);
        outputOdd = doIFFT(dataOdd);

        for(int i = 0; i < data.length/2; i++){
            output[i] = outputEven[i] + angle.pow(i).times(outputOdd[i]).getReal();
            output[i + data.length / 2] = outputEven[i] - angle.pow(i).times(outputOdd[i]).getReal();
        }

        return output;
    }

    //mode true: fft, false: ifft
    private Complex[] doFFT(double[] data){
        if(data.length == 1) {
            return new Complex[]{new ComplexImpl(data[0], 0)};
        }

        Complex angle = new ComplexImpl(Math.cos(2 * Math.PI / data.length), Math.sin(2 * Math.PI / data.length));

        double[] dataEven = new double[data.length/2], dataOdd = new double[data.length/2];
        Complex[] outputEven, outputOdd, output = new ComplexImpl[data.length];

        for (int i = 0; i < data.length/2; i++){
            dataEven[i] = data[i*2];
            dataOdd[i] = data[i*2+1];
        }

        outputEven = doFFT(dataEven);
        outputOdd = doFFT(dataOdd);

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
}
