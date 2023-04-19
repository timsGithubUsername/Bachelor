package waveIO;

/**
 * This class provides methods to convert byte arrays into integer, strings etc.
 */
public class ByteArrayTools {

    /**
     * Calculate a integer based on a little endian formatted byte array
     * @param data little endian formatted byte array
     * @return Number represented by that array
     */
    public static int getIntFromByteArray(byte[] data){
        int output = 0;

        //Each data field has a number of bytes in little endian format, hence this calculation.
        //0xff takes into account the fact that Java only knows signed bytes.
        for(int index = 0; index < data.length; index++){
            output += (data[index] & 0xff) * Math.pow(256, index);
        }

        return output;
    }

    /**
     * Calculate a string based on a byte array (char for char)
     * @param data the byte array
     * @return a string
     */
    public static String getStringFromByteArray(byte[] data){
        return new String(data);
    }
}
