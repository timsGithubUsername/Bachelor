package waveIO;

import data.SoundObjectV1;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReaderV1Impl implements FileReaderV1{
    WaveObjectBuilderV1 waveObjectBuilder = new WaveObjectBuilderV1Impl();

    /**
     * Creates a WaveObject from file path for use in this programm
     * @param path the location of the wave file
     * @return WaveObject for further use
     * @throws Exception
     */
    @Override
    public SoundObjectV1 read(String path) throws Exception {
        byte[] fileBytes = Files.readAllBytes(Paths.get(path));
        File audioFile = new File(path);

        return waveObjectBuilder.createWaveObject(fileBytes, audioFile.getName());
    }
}
