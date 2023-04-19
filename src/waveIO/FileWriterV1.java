package waveIO;

import data.WaveObjectV1;

/**
 * FileReader writes a file wave file from a WaveObject or throws an exception
 */
public interface FileWriterV1 {
    void write(String path, WaveObjectV1 waveObject) throws Exception;
}
