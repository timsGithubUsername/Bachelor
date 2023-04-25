package data;

/**
 * WaveObject holds all the data needed to apply the Fourier transform or to create a wave file.
 */
public interface WaveObjectV1 {
    String getName();
    void setName(String name);
    int getChannels();
    int getSampleRate();
    int getByteRate();
    int getBlockAlign();
    int getBitsPerSample();
    byte[] getAudioData();
    double[] getJavaPCM();
}
