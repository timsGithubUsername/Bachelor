package data;

public interface WaveObjectV1 {
    String getName();
    int getChannels();
    int getSampleRate();
    int getByteRate();
    int getBlockAlign();
    int getBitsPerSecond();
    byte[] getAudioData();
    double[] getJavaPCM();
}
