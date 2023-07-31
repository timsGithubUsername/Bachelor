package data;

import fourier.Complex;
import waveIO.ByteArrayTools;
import waveIO.JavaPCMTools;

import java.util.Arrays;

public class SoundObjectV1Impl implements SoundObjectV1{
    private String name;
    private Complex[] frequency;
    private double[] magnitude, pcmData;
    private byte[] audioData;
    private int channels, sampleRate, byteRate, blockAlign, bitsPerSample;

    public SoundObjectV1Impl(String name, int channels, int sampleRate, int byteRate, int blockAlign,
                            int bitsPerSecond, byte[] audioData){
        this.name = name;
        this.channels = channels;
        this.sampleRate = sampleRate;
        this.byteRate = byteRate;
        this.blockAlign = blockAlign;
        this.bitsPerSample = bitsPerSecond;
        this.audioData = audioData;
        this.pcmData = pcmData;

        reduceChannels();
        setPcmData();
    }

    private void setPcmData() {
        pcmData = JavaPCMTools.calculatePCMArray(audioData, bitsPerSample);
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
    public Complex[] getFrequency() {
        return frequency;
    }

    @Override
    public void setFrequency(Complex[] frequency) {
        this.frequency = frequency;
        calcMagnitude();
    }

    private void calcMagnitude() {
        for(int i = 0; i < frequency.length; i++) magnitude[i] = frequency[i].getMagnitude();
    }

    @Override
    public double[] getMagnitude() {
        return magnitude;
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
        return bitsPerSample;
    }

    @Override
    public byte[] getAudioData() {
        return audioData;
    }

    @Override
    public double[] getJavaPCM() {
        return pcmData;
    }

    @Override
    public void setPCMFromIFFT(double[] pcm) {
        for(int i = 0; i < pcmData.length; i++) pcmData[i] = pcm[i];
        audioData = JavaPCMTools.calculateDataArray(pcmData, bitsPerSample);
    }

    private void reduceChannels(){
        byte[] newAudioData = new byte[audioData.length/channels];

        if(channels > 1){
            for(int i = 0; i < newAudioData.length;) {
                if(i*channels+bitsPerSample >= audioData.length || i+bitsPerSample >= newAudioData.length) break;

                ByteArrayTools.fillByteArrayWithArray(newAudioData, Arrays.copyOfRange(audioData, i*channels, i*channels+bitsPerSample), i);
                i += bitsPerSample;
            }
            byteRate = byteRate/channels;
            blockAlign = blockAlign/channels;
            channels = 1;
            audioData = newAudioData;
        }
    }

    @Override
    public void changeSpeed(double factor){
        //todo
    }
}
