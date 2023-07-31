package waveIO;

import data.SoundObjectV1;

/**
 * FileReader reads a file and creates a WaveObject or throws an exception
 */
public interface FileReaderV1 {
    SoundObjectV1 read(String path) throws Exception;
}
