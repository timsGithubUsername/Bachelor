package waveIO;

import java.util.Arrays;

/**
 * This class provides methods to convert byte arrays into sample arrays and vice versa
 */
public class JavaPCMTools {

    /**
     * Calculate a array with samples based on the audio data and the frame size
     * @param audioData An Byte Array exclusive with the sample data
     * @param bitsPerSample size of an sample
     * @return double array with absolut sizes
     */
    public static double[] calculatePCMArray(byte[] audioData, int bitsPerSample) {
        int bytesPerSample = bitsPerSample / 8;
        double[] output = new double[audioData.length / bytesPerSample];

        fillPCMArray(audioData, output, bytesPerSample);

        return output;
    }

    //this method calculates a sample from all bytes for that sample
    private static void fillPCMArray(byte[] audioData, double[] output, int bytesPerSample) {
        int currentPosition;

        for (int sample = 0; sample < output.length; sample++) {
            currentPosition = sample * bytesPerSample;

            output[sample] = ByteArrayTools.getIntFromByteArray(Arrays.copyOfRange(audioData, currentPosition, currentPosition + bytesPerSample));
        }
    }

    public static byte[] calculateDataArray(double[] pcmData, int bitsPerSample){
        int bytesPerSample = bitsPerSample / 8;
        byte[] output = new byte[pcmData.length * bytesPerSample];

        fillByteArray(pcmData, output, bytesPerSample);

        return output;
    }

    private static void fillByteArray(double[] pcmData, byte[] output, int bytesPerSample) {
        int currentPosition;

        for (int sample = 0; sample < pcmData.length; sample++) {
            currentPosition = sample * bytesPerSample;

            ByteArrayTools.fillByteArrayWithArray(output, ByteArrayTools.getByteArrayFromInt((int) pcmData[sample],bytesPerSample), currentPosition);
        }
    }
}
