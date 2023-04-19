package waveIO;

import data.WaveObjectV1;

public interface WaveBytesBuilderV1 {
    byte[] createWaveBytes(WaveObjectV1 waveObject);
}
