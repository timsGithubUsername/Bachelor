package fourier;

import data.FourierObjectV1;
import data.WaveObjectV1;

public class FFTV1Impl implements FFTV1{
    @Override
    public FourierObjectV1 fft(WaveObjectV1 wo) {
        return null;
    }

    private ComplexMutable[] doFFT(double[] data){
        if(data.length == 1) return new ComplexMutable[]{new ComplexMutableImpl(data[0], 0)};

        ComplexMutable angle = new ComplexMutableImpl(Math.cos(2 * Math.PI / data.length), Math.sin(2 * Math.PI / data.length));

        double[] dataEven = new double[data.length/2], dataOdd = new double[data.length/2];
        ComplexMutable[] outputEven, outputOdd, output = new ComplexMutable[data.length];

        for (int i = 0; i < data.length; i+=2){
            dataEven[i] = data[i];
            dataOdd[i+1] = data[i+1];
        }

        outputEven = doFFT(dataEven);
        outputOdd = doFFT(dataOdd);

        for(int i = 0; i < data.length/2; i++){
            output[i] = outputEven[i].add(angle.pow(i).times(outputOdd[i]));
            output[i + data.length/2] = outputEven[i].substract(angle.pow(i).times(outputOdd[i]));
        }

        return output;
    }

    @Override
    public WaveObjectV1 ifft(FourierObjectV1 fo) {
        return null;
    }
}
