package waveIO;

import data.SoundObjectV1;

/**
 * WaveObjectBuilder creates a WaveObject from a byte array
 */
public interface WaveObjectBuilderV1 {
    SoundObjectV1 createWaveObject(byte[] data, String name) throws Exception;
}
