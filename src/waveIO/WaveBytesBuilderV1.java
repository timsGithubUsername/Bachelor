package waveIO;

import data.SoundObjectV1;

import java.io.IOException;

/**
 * WaveBytesBuilder creates the bytecode of a wave file using a WaveObject
 */
public interface WaveBytesBuilderV1 {
    byte[] createWaveBytes(SoundObjectV1 soundObject) throws IOException;
}
