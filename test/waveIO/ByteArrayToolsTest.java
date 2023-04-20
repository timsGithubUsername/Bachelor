package waveIO;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ByteArrayToolsTest {

    @Test
    void getIntFromByteArray() {
        byte[] case1 = {0x22, 0x56, 0, 0}; //22050
        byte[] case2 = {22, 22, 1, 0}; //71190
        byte[] case3 = {0, 0, 0, 0}; //0
        byte[] case4 = {(byte) 0xff,(byte) 0xff,(byte) 0xff, 0}; //16777215

        assertEquals(22050, ByteArrayTools.getIntFromByteArray(case1));
        assertEquals(71190, ByteArrayTools.getIntFromByteArray(case2));
        assertEquals(0, ByteArrayTools.getIntFromByteArray(case3));
        assertEquals(16777215, ByteArrayTools.getIntFromByteArray(case4));

    }

    @Test
    void getStringFromByteArray() {
        String case1 = "case1";
        String case2 = "";
        String case3 = "ABCDEF";
        String case4 = "abcdef";

        assertEquals(case1, ByteArrayTools.getStringFromByteArray(case1.getBytes()));
        assertEquals(case2, ByteArrayTools.getStringFromByteArray(case2.getBytes()));
        assertEquals(case3, ByteArrayTools.getStringFromByteArray(case3.getBytes()));
        assertNotEquals(case4, ByteArrayTools.getStringFromByteArray(case3.getBytes()));
    }
}