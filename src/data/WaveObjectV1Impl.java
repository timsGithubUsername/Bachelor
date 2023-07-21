package data;

public class WaveObjectV1Impl implements WaveObjectV1 {
    private String name;
    private final int channels;
    private final int sampleRate;
    private final int byteRate;
    private final int blockAlign;
    private final int bitsPerSecond;
    private final byte[] audioData;
    private final double[] javaPCM;

    //todo wird die audio data aus javaPVM berechnet
    public WaveObjectV1Impl(String name, int channels, int sampleRate, int byteRate, int blockAlign,
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
    public void setName(String name) {
        this.name = name;
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
    public int getBitsPerSample() {
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
