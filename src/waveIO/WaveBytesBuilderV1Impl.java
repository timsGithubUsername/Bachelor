package waveIO;

import data.WaveObjectV1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class WaveBytesBuilderV1Impl implements WaveBytesBuilderV1 {
    private WaveObjectV1 waveObject;

    /**
     * This Method creates an Byte Array based on a WaveObject
     * @param waveObject The WaveObject, which should be transformed in a Wave Byte Array
     * @return A Byte Array corresponding to a Wave File
     * @throws IOException If something goes wrong with the ByteArrayOutputStreams. This shouldnt happen.
     */
    @Override
    public byte[] createWaveBytes(WaveObjectV1 waveObject) throws IOException {
        this.waveObject = waveObject;
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

        riffChunk.write(ByteArrayTools.getByteArrayFromString("RIFF")); //ChunkID
        riffChunk.write(ByteArrayTools.getByteArrayFromInt(0,4)); //Chunk Size (whole file in this case)
        riffChunk.write(ByteArrayTools.getByteArrayFromString("WAVE")); //Format

        return riffChunk.toByteArray();
    }

    private byte[] getFMTChunk() throws IOException {
        ByteArrayOutputStream fmtChunk = new ByteArrayOutputStream();

        fmtChunk.write(ByteArrayTools.getByteArrayFromString("fmt "));  //ChunkID
        fmtChunk.write(ByteArrayTools.getByteArrayFromInt(16,4)); //Chunk Size (16 is the fix size for this Chunk with PCM)
        fmtChunk.write(ByteArrayTools.getByteArrayFromInt(1,2));  //AudioFormat (1 for PCM)
        fmtChunk.write(ByteArrayTools.getByteArrayFromInt(waveObject.getChannels(),2));  //Number of Channels
        fmtChunk.write(ByteArrayTools.getByteArrayFromInt(waveObject.getSampleRate(),4));  //Sample rate
        fmtChunk.write(ByteArrayTools.getByteArrayFromInt(waveObject.getByteRate(),4));  //Byte rate
        fmtChunk.write(ByteArrayTools.getByteArrayFromInt(waveObject.getBlockAlign(),2));  //Block align
        fmtChunk.write(ByteArrayTools.getByteArrayFromInt(waveObject.getBitsPerSample(),2));  //Block align

        return fmtChunk.toByteArray();
    }

    private byte[] getDATAChunk() throws IOException {
        ByteArrayOutputStream dataChunk = new ByteArrayOutputStream();

        dataChunk.write(ByteArrayTools.getByteArrayFromString("data"));  //ChunkID
        dataChunk.write(ByteArrayTools.getByteArrayFromInt(waveObject.getAudioData().length,4)); //Chunk Size (= size of Audio Data)
        dataChunk.write(waveObject.getAudioData());  //Audio Data

        return dataChunk.toByteArray();
    }
}
