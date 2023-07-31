package fourier;

import data.SoundObjectV1;

public interface DFTV1 {
    public void dft(SoundObjectV1 so);

    public void idftReal(SoundObjectV1 so);
}
