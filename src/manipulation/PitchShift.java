package manipulation;

import data.SoundObjectV1;
import data.Complex;
import data.ComplexImpl;
import data.SoundObjectV1Impl;

public class PitchShift {
    FFTV1 fft = new FFTV1Impl();

    public SoundObjectV1 pitch(SoundObjectV1 so, double factor) {
        SoundObjectV1 newSo = new SoundObjectV1Impl(so);
        fft.fft(newSo);

        Complex[] currentFrequenzy = newSo.getFrequency();
        Complex[] newFrequenzy = createComplexArrayOfLength((int)(currentFrequenzy.length*factor));

        for(int i = 0; i < currentFrequenzy.length/2 && i * factor+0.5 < newFrequenzy.length/2; i++){
            newFrequenzy[(int)(i*factor+0.5)] = currentFrequenzy[i];
        }

        for(int i = 0; i < (currentFrequenzy.length/2-1);i++) newFrequenzy[currentFrequenzy.length/2+(i+1)]=currentFrequenzy[currentFrequenzy.length/2-(i+1)].getConjugate();

        newSo.setFrequency(newFrequenzy);
        fft.ifft(newSo);

        return newSo;
    }

    private Complex[] createComplexArrayOfLength(int entrys) {
        Complex[] output = new Complex[entrys];

        for (int i = 0; i < entrys; i++) output[i] = new ComplexImpl();

        return output;
    }
}
