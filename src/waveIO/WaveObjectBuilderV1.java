package waveIO;

import data.WaveObjectV1;

/**
 * WaveObjectBuilder creates a WaveObject from a byte array
 */
public interface WaveObjectBuilderV1 {
    WaveObjectV1 createWaveObject(byte[] data, String name);
}
