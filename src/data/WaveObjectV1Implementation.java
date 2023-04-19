package data;

public class WaveObjectV1Implementation implements WaveObjectV1 {
    private final String name;
    private final int channels;
    private final int sampleRate;
    private final int byteRate;
    private final int blockAlign;
    private final int bitsPerSecond;
    private final byte[] audioData;
    private final double[] javaPCM;

    public WaveObjectV1Implementation(String name, int channels, int sampleRate, int byteRate, int blockAlign,
    int bitsPerSecond, byte[] audioData, double[] javaPCM){
        this.name = name;
        this.channels = channels;
        this. sampleRate = sampleRate;
        this.byteRate = byteRate;
        this.blockAlign = blockAlign;
        this.bitsPerSecond = bitsPerSecond;
        this.audioData = audioData;
        this.javaPCM = javaPCM;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getChannels() {
        return channels;
    }

    @Override
    public int getSampleRate() {
        return sampleRate;
    }

    @Override
    public int getByteRate() {
        return byteRate;
    }

    @Override
    public int getBlockAlign() {
        return blockAlign;
    }

    @Override
    public int getBitsPerSecond() {
        return bitsPerSecond;
    }

    @Override
    public byte[] getAudioData() {
        return audioData;
    }

    @Override
    public double[] getJavaPCM() {
        return javaPCM;
    }
}
