package waveIO;

import data.WaveObjectV1;

/**
 * WaveBytesBuilder creates the bytecode of a wave file using a WaveObject
 */
public interface WaveBytesBuilderV1 {
    byte[] createWaveBytes(WaveObjectV1 waveObject);
}
