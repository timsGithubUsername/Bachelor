package manipulation;

import data.SoundObjectV1;
import fourier.Complex;
import fourier.ComplexImpl;
import fourier.FFTV1;
import fourier.FFTV1Impl;

public class ManipulateFFT {
    static FFTV1 fft = new FFTV1Impl();

    public static void changePitch(SoundObjectV1 so, double factor){
        if(so.getFrequency() == null) fft.fft(so);

        int length = (int) (so.getFrequency().length / factor);
        Complex[] newFrequency = new Complex[length];

        for(int i = 0; i < length; i++){
            if(i*factor < so.getFrequency().length){
                newFrequency[i] = so.getFrequency()[(int) (i*factor)];
            }
            else {
                System.out.println("!");
                newFrequency[i] = new ComplexImpl();
            }
        }

        so.setFrequency(newFrequency);
        so.setName(so.getName()+"_pitched_"+factor+"x.wav");

        fft.ifft(so);
    }

    public static void changeSpeedFFT(SoundObjectV1 so, double factor){
        so.setName(so.getName()+"_speed_"+factor+"x");
        changePitch(so, factor);
        so.changeSpeed(factor);
    }
}
