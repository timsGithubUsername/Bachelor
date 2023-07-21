package fourier;

import data.FourierObjectV1;
import data.WaveObjectV1;

public interface DFTV1 {
    public FourierObjectV1 dft(WaveObjectV1 wo);

    public WaveObjectV1 idftReal(FourierObjectV1 fo);
}
