package waveIO;

import data.SoundObjectV1;

/**
 * FileReader writes a file wave file from a WaveObject or throws an exception
 */
public interface FileWriterV1 {
    void write(String path, SoundObjectV1 soundObjectV1) throws Exception;
}
