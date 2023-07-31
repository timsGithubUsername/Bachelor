package data;

import fourier.Complex;
import waveIO.ByteArrayTools;
import waveIO.JavaPCMTools;

import java.util.Arrays;
import java.util.Date;

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

        setPcmData();
        reduceChannels();
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
        if(magnitude == null) magnitude = new double[frequency.length];
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
    public void setPCMFromIFFT(Complex[] pcm) {
        int n = pcm.length;
        for(int i = 0; i < pcmData.length; i++) pcmData[i] = pcm[i].times(1.0/n).getReal();
        audioData = JavaPCMTools.calculateDataArray(pcmData, bitsPerSample);
    }

    private void reduceChannels(){
        double[] newPCMData = new double[pcmData.length/channels - pcmData.length/channels % channels];

        if(channels > 1){
            for(int i = 0; i < newPCMData.length; i++) {
                newPCMData[i] = pcmData[channels * i];
            }

            pcmData = newPCMData;
            audioData = JavaPCMTools.calculateDataArray(pcmData, bitsPerSample);

            byteRate = byteRate/channels;
            blockAlign = blockAlign/channels;
            channels = 1;
        }
    }

    @Override
    public void changeSpeed(double factor){
        sampleRate *= factor;
    }
}
