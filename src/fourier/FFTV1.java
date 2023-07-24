package fourier;

import data.FourierObjectV1;
import data.WaveObjectV1;

public interface FFTV1 {
    FourierObjectV1 fft(WaveObjectV1 wo);
    WaveObjectV1 ifft(FourierObjectV1 fo);
}
