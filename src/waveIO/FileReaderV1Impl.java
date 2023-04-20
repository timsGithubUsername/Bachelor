package waveIO;

import data.WaveObjectV1;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class FileReaderV1Impl implements FileReaderV1{
    WaveObjectBuilderV1 waveObjectBuilder = new WaveObjectBuilderV1Impl();

    /**
     * Creates a WaveObject from file path for use in this programm
     * @param path the location of the wave file
     * @return WaveObject for further use
     * @throws Exception //todo not specified. there should at least a distinction between wrong format and input failure
     */
    @Override
    public WaveObjectV1 read(String path) throws Exception {
        byte[] fileBytes = Files.readAllBytes(Paths.get(path));

        if(fileIsWaveFormat(fileBytes)) return waveObjectBuilder.createWaveObject(fileBytes, path);
        else throw new Exception("File is not in correct WAVE-Format!");
    }

    //This Method checks if the wave-file is 1. a wave file and 2. in the correct format
    //todo it is not checked whether the WAVE file is stored in PCM. This can lead to wrong results in the later program run!
    private boolean fileIsWaveFormat(byte[] fileBytes) {
        String expected = "WAVE";
        //At this point the format "WAVE" is stored in byte form for each WAVE file.
        byte[] current = Arrays.copyOfRange(fileBytes, 8,12);

        return expected.equals(ByteArrayTools.getStringFromByteArray(current));
    }
}
