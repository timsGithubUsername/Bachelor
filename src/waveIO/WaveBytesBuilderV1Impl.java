package waveIO;

import data.WaveObjectV1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class WaveBytesBuilderV1Impl implements WaveBytesBuilderV1 {
    @Override
    public byte[] createWaveBytes(WaveObjectV1 waveObject) throws IOException {
        ByteArrayOutputStream waveObjectBytes = new ByteArrayOutputStream();

        //note: the size on pos 4 to 7 in RIFF Chunk has to bet set later
        waveObjectBytes.write(getRIFFChunk());
        waveObjectBytes.write(getFMTChunk());
        waveObjectBytes.write(getAudioData());

        return new byte[0];
    }

    private byte[] getRIFFChunk() {
        return null;
    }

    private byte[] getFMTChunk() {
        return null;
    }

    private byte[] getAudioData() {
        return null;
    }
}
