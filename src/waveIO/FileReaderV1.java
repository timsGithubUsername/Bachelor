package waveIO;

import data.WaveObjectV1;

/**
 * FileReader reads a file and creates a WaveObject or throws an exception
 */
public interface FileReaderV1 {
    WaveObjectV1 read(String path) throws Exception;
}
