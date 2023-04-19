package waveIO;

import data.WaveObjectV1;

public interface FileReaderV1 {
    WaveObjectV1 read(String path);
}
