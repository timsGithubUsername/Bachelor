package waveIO;

import data.WaveObjectV1;

public interface WaveObjectBuilderV1 {
    WaveObjectV1 createWaveObject(byte[] data);
}
