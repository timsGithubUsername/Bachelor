package waveIO;

import data.WaveObjectV1;
import data.WaveObjectV1Impl;

import java.util.Arrays;

public class WaveObjectBuilderV1Impl implements WaveObjectBuilderV1{

    /**
     * This Method creates an WaveObject base on a byte String and his name
     * @param data data array
     * @param name name of the WaveObject
     * @return The Wave Object corresponding to the data
     */
    @Override
    public WaveObjectV1 createWaveObject(byte[] data, String name) {
        //todo cut path to correct name
        return new WaveObjectV1Impl(name,
                getChannelsFromData(data),
                getSampleRateFromData(data),
                getByteRateFromData(data),
                getBlockAlignFromData(data),
                getBitsPerSampleFromData(data),
                getAudioData(data),
                calculateJavaPCM(data));
    }

    //with PCM-format, this information is on pos 22 to exclusive 24
    private int getChannelsFromData(byte[] data){
        return ByteArrayTools.getIntFromByteArray(Arrays.copyOfRange(data,22, 24));
    }
    //with PCM-Format, this information is on pos 24 to exclusive 28
    private int getSampleRateFromData(byte[] data){
        return ByteArrayTools.getIntFromByteArray(Arrays.copyOfRange(data,24, 28));
    }
    //with PCM-Format, this information is on pos 28 to exclusive 32
    private int getByteRateFromData(byte[] data){
        return ByteArrayTools.getIntFromByteArray(Arrays.copyOfRange(data,28, 32));
    }
    //with PCM-Format, this information is on pos 32 to exclusive 34
    private int getBlockAlignFromData(byte[] data){
        return ByteArrayTools.getIntFromByteArray(Arrays.copyOfRange(data,32, 34));
    }
    //with PCM-Format, this information is on pos 34 to exclusive 36
    private int getBitsPerSampleFromData(byte[] data){
        return ByteArrayTools.getIntFromByteArray(Arrays.copyOfRange(data,34, 36));
    }
    //with PCM-Format, this information is up on pos 44
    private byte[] getAudioData(byte[] data){
        return Arrays.copyOfRange(data, 44, data.length);
    }
    private double[] calculateJavaPCM(byte[] data){
        return JavaPCMTools.calculatePCMArray(data, getBitsPerSampleFromData(data));
    }

}
