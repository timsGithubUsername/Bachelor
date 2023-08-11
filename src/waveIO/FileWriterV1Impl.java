package waveIO;

import data.SoundObjectV1;

import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

public class FileWriterV1Impl implements FileWriterV1{

    WaveBytesBuilderV1 waveBytesBuilder = new WaveBytesBuilderV1Impl();

    @Override
    public void write(String path, SoundObjectV1 soundObjectV1) throws Exception {
        try {
            Files.write(Paths.get(path+soundObjectV1.getName()), waveBytesBuilder.createWaveBytes(soundObjectV1));
        } catch (NoSuchFileException e) {
            Files.write(Paths.get(".\\"+soundObjectV1.getName()), waveBytesBuilder.createWaveBytes(soundObjectV1));
        }

    }
}
