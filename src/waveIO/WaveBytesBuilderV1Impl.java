package waveIO;

import data.SoundObjectV1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class WaveBytesBuilderV1Impl implements WaveBytesBuilderV1 {
    private SoundObjectV1 soundObject;

    /**
     * This Method creates an Byte Array based on a WaveObject
     * @param soundObject The WaveObject, which should be transformed in a Wave Byte Array
     * @return A Byte Array corresponding to a Wave File
     * @throws IOException If something goes wrong with the ByteArrayOutputStreams. This shouldnt happen.
     */
    @Override
    public byte[] createWaveBytes(SoundObjectV1 soundObject) throws IOException {
        this.soundObject = soundObject;
        ByteArrayOutputStream waveObjectBytes = new ByteArrayOutputStream();
        byte[] output;

        waveObjectBytes.write(getRIFFChunk()); //note: the size on pos 4 to 7 in RIFF Chunk has to bet set later
        waveObjectBytes.write(getFMTChunk());
        waveObjectBytes.write(getDATAChunk());

        output = waveObjectBytes.toByteArray();

        setSizeInRIFFChunk(output);

        return output;
    }

    private void setSizeInRIFFChunk(byte[] output) {
        byte[] size = ByteArrayTools.getByteArrayFromInt(output.length - 8, 4); //ChunkID and Chunk Size of RIFF-Chunk dont count here

        System.arraycopy(size, 0, output, 4, size.length);
    }

    //note: the size on pos 4 to 7 in RIFF Chunk has to bet set later due to the file size isnt known now
    private byte[] getRIFFChunk() throws IOException {
        ByteArrayOutputStream riffChunk = new ByteArrayOutputStream();

        riffChunk.write(ByteArrayTools.getByteArrayFromString(RIFFIdentifier.RIFF.getName())); //ChunkID
        riffChunk.write(ByteArrayTools.getByteArrayFromInt(0,4)); //Chunk Size (whole file in this case)
        riffChunk.write(ByteArrayTools.getByteArrayFromString(RIFFIdentifier.WAVE.getName())); //Format

        return riffChunk.toByteArray();
    }

    private byte[] getFMTChunk() throws IOException {
        ByteArrayOutputStream fmtChunk = new ByteArrayOutputStream();

        fmtChunk.write(ByteArrayTools.getByteArrayFromString(RIFFIdentifier.FMT.getName()));  //ChunkID
        fmtChunk.write(ByteArrayTools.getByteArrayFromInt(16,4)); //Chunk Size (16 is the fix size for this Chunk with PCM)
        fmtChunk.write(ByteArrayTools.getByteArrayFromInt(1,2));  //AudioFormat (1 for PCM)
        fmtChunk.write(ByteArrayTools.getByteArrayFromInt(soundObject.getChannels(),2));  //Number of Channels
        fmtChunk.write(ByteArrayTools.getByteArrayFromInt(soundObject.getSampleRate(),4));  //Sample rate
        fmtChunk.write(ByteArrayTools.getByteArrayFromInt(soundObject.getByteRate(),4));  //Byte rate
        fmtChunk.write(ByteArrayTools.getByteArrayFromInt(soundObject.getBlockAlign(),2));  //Block align
        fmtChunk.write(ByteArrayTools.getByteArrayFromInt(soundObject.getBitsPerSample(),2));  //Block align

        return fmtChunk.toByteArray();
    }

    private byte[] getDATAChunk() throws IOException {
        ByteArrayOutputStream dataChunk = new ByteArrayOutputStream();

        dataChunk.write(ByteArrayTools.getByteArrayFromString(RIFFIdentifier.DATA.getName()));  //ChunkID
        dataChunk.write(ByteArrayTools.getByteArrayFromInt(soundObject.getAudioData().length,4)); //Chunk Size (= size of Audio Data)
        dataChunk.write(soundObject.getAudioData());  //Audio Data

        return dataChunk.toByteArray();
    }
}
