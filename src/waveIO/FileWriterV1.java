package waveIO;

import data.WaveObjectV1;

public interface FileWriterV1 {
    void write(String path, WaveObjectV1 waveObject);
}
