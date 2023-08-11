package data;

public interface SoundObjectV1 {
    String getName();
    void setName(String name);

    //fft
    Complex[] getFrequency();
    void setFrequency(Complex[] frequency);
    double[] getMagnitude();

    //pcm
    int getChannels();
    int getSampleRate();
    int getByteRate();
    int getBlockAlign();
    int getBitsPerSample();
    byte[] getAudioData();
    double[] getJavaPCM();

    //manipulation
    void setJavaPCM(double[] pcm);
}
