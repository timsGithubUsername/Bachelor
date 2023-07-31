package fourier;

import data.SoundObjectV1;

public interface FFTV1 {
    void fft(SoundObjectV1 so);
    void ifft(SoundObjectV1 so);
}
