package data;

public class WaveFourierObjekt implements FourierObjectV1, WaveObjectV1{
    private String name;

    public WaveFourierObjekt(String name, int channels, int sampleRate, int byteRate, int blockAlign,
                            int bitsPerSecond, byte[] audioData, double[] javaPCM,
                            double[] real,double[] img,double[] amplitude){
        this.name = name;
        this.channels = channels;
        this. sampleRate = sampleRate;
        this.byteRate = byteRate;
        this.blockAlign = blockAlign;
        this.bitsPerSecond = bitsPerSecond;
        this.audioData = audioData;
        this.javaPCM = javaPCM;
        this.real = real;
        this.img = img;
        this.amplitude = amplitude;
    }
    public WaveFourierObjekt(WaveObjectV1 wo, double[] real,double[] img,double[] amplitude){
        this.name = wo.getName();
        this.channels = wo.getChannels();
        this. sampleRate = wo.getSampleRate();
        this.byteRate = wo.getByteRate();
        this.blockAlign = wo.getBlockAlign();
        this.bitsPerSecond = wo.getBitsPerSample();
        this.audioData = wo.getAudioData();
        this.javaPCM = wo.getJavaPCM();
        this.real = real;
        this.img = img;
        this.amplitude = amplitude;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    private int channels;
    @Override
    public int getChannels() {
        return channels;
    }

    private int sampleRate;
    @Override
    public int getSampleRate() {
        return sampleRate;
    }

    private int byteRate;
    @Override
    public int getByteRate() {
        return byteRate;
    }

    private int blockAlign;
    @Override
    public int getBlockAlign() {
        return blockAlign;
    }

    private int bitsPerSecond;
    @Override
    public int getBitsPerSample() {
        return bitsPerSecond;
    }

    private byte[] audioData;
    @Override
    public byte[] getAudioData() {
        return audioData;
    }

    private double[] javaPCM;
    @Override
    public double[] getJavaPCM() {
        return javaPCM;
    }

    private double[] real;
    @Override
    public double[] getReal() {
        return real;
    }

    private double[] img;
    @Override
    public double[] getImg() {
        return img;
    }

    private double[] amplitude;
    @Override
    public double[] getAmplitude() {
        return amplitude;
    }
}
