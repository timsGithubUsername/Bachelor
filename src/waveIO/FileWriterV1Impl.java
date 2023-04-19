package waveIO;

import data.WaveObjectV1;

import java.nio.file.Files;
import java.nio.file.Paths;

public class FileWriterV1Impl implements FileWriterV1{

    WaveBytesBuilderV1 waveBytesBuilder = null; //todo

    @Override
    public void write(String path, WaveObjectV1 waveObject) throws Exception {
        Files.write(Paths.get(path+waveObject.getName()), waveBytesBuilder.createWaveBytes(waveObject));
    }
}
