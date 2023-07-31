package waveIO;

import data.SoundObjectV1;
import data.SoundObjectV1Impl;

import java.util.Arrays;

public class WaveObjectBuilderV1Impl implements WaveObjectBuilderV1 {
    private int pointer;
    private byte[] data;
    private byte[] audioDataTemp;
    private int bitsPerSampleTemp;

    /**
     * This Method creates an WaveObject base on a byte String and his name
     * @param data data array
     * @param name name of the WaveObject
     * @return The Wave Object corresponding to the data
     */
    @Override
    public SoundObjectV1 createWaveObject(byte[] data, String name) throws Exception {
        pointer = 0;
        this.data = data;

        //Pointer is at start from RIFF Chunk
        //verify RIFF format
        if (!checkIdentifier(RIFFIdentifier.RIFF.getName())) {
            throw new WrongFileFormatException();
        } else {
            goToChunk(RIFFIdentifier.FMT.getName());
        }

        //Pointer is at start from fmt Chunk
        //verify AudioFormat at pos 8 in this Chunk
        if (!checkFormat()) {
            throw new WrongAudioFormatException();
        }

        //Pointer is at pos 8 in this Chunk, to iterate in this Chunk, the pointer is incremented to fit the position
        //NumChannels       pos 10  +2
        //SampleRate        pos 12  +2
        //ByteRate          pos 16  +4
        //BlockAlign        pos 20  +4
        //BitsPerSample     pos 22  +4
        //
        //reach next chunk          +2
        //then get audio data
        return new SoundObjectV1Impl(name,
                getChannelsFromData(),
                getSampleRateFromData(),
                getByteRateFromData(),
                getBlockAlignFromData(),
                getBitsPerSampleFromData(),
                getAudioData());
    }

    private void setPointerToNextChunk() {
        if(checkIdentifier(RIFFIdentifier.RIFF.getName())) {
            pointer += 12;
        }
        else {
            pointer += 4; //go to size
            pointer += ByteArrayTools.getIntFromByteArray(Arrays.copyOfRange(data, pointer, pointer + 4)); //go to last 4 byte
            pointer += 4; //skip last 4 byte
        }
    }

    private boolean checkIdentifier(String expected) {
        return expected.equals(ByteArrayTools.getStringFromByteArray(Arrays.copyOfRange(data, pointer, pointer + 4)));
    }

    private void goToChunk(String chunkName){
        if (!checkIdentifier(chunkName)) {
            setPointerToNextChunk();
            goToChunk(chunkName);
        }
    }

    //AudioFormat Information is at pos 8 in fmt Chunk. AudioFormat 1 is PCM, what we need here
    private boolean checkFormat() {
        pointer += 8;
        return 1 == ByteArrayTools.getIntFromByteArray(Arrays.copyOfRange(data, pointer, pointer+2));
    }

    //with PCM-format, this information is on pos 10 to exclusive 12, pointer is at 8
    private int getChannelsFromData() {
        pointer += 2;
        return ByteArrayTools.getIntFromByteArray(Arrays.copyOfRange(data, pointer, pointer+2));
    }

    //with PCM-Format, this information is on pos 12 to exclusive 16, pointer is at 10
    private int getSampleRateFromData() {
        pointer += 2;
        return ByteArrayTools.getIntFromByteArray(Arrays.copyOfRange(data, pointer, pointer+4));
    }

    //with PCM-Format, this information is on pos 16 to exclusive 20, pointer is at 12
    private int getByteRateFromData() {
        pointer += 4;
        return ByteArrayTools.getIntFromByteArray(Arrays.copyOfRange(data, pointer, pointer+4));
    }

    //with PCM-Format, this information is on pos 20 to exclusive 22, pointer is at 16
    private int getBlockAlignFromData() {
        pointer += 4;
        return ByteArrayTools.getIntFromByteArray(Arrays.copyOfRange(data, pointer, pointer+2));
    }

    //with PCM-Format, this information is on pos 22 to exclusive 24, pointer is at 20
    private int getBitsPerSampleFromData() {
        pointer += 2;
        bitsPerSampleTemp = ByteArrayTools.getIntFromByteArray(Arrays.copyOfRange(data, pointer, pointer+2));

        return bitsPerSampleTemp;
    }

    //this information is on pos 8 to end of file, pointer is on 0
    private byte[] getAudioData() {
        pointer += 2;
        goToChunk(RIFFIdentifier.DATA.getName());
        pointer += 8;

        audioDataTemp = Arrays.copyOfRange(data, pointer, data.length);
        return audioDataTemp;
    }

    private static class WrongFileFormatException extends Exception {
    }

    private static final class WrongAudioFormatException extends Exception {
    }
}
