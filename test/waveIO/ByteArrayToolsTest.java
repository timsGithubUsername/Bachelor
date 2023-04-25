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
        String case5 = "case 5 ";
        String case2 = "";
        String case3 = "ABCDEF";
        String case4 = "abcdef";

        assertEquals(case1, ByteArrayTools.getStringFromByteArray(case1.getBytes()));
        assertEquals(case5, ByteArrayTools.getStringFromByteArray(case5.getBytes()));
        assertEquals(case2, ByteArrayTools.getStringFromByteArray(case2.getBytes()));
        assertEquals(case3, ByteArrayTools.getStringFromByteArray(case3.getBytes()));
        assertNotEquals(case4, ByteArrayTools.getStringFromByteArray(case3.getBytes()));
    }

    @Test
    void getByteArrayFromInt() {
        byte[] case1b = {0x22, 0x56}; //22050
        int case1i = 22050;
        byte[] case2b = {22, 22, 1, 0}; //71190
        int case2i = 71190;
        byte[] case3b = {0, 0}; //0
        int case3i = 0;
        byte[] case4b = {(byte) 0xff,(byte) 0xff,(byte) 0xff, 0}; //16777215
        int case4i = 16777215;

        assertArrayEquals(case1b, ByteArrayTools.getByteArrayFromInt(case1i, 2));
        assertArrayEquals(case2b, ByteArrayTools.getByteArrayFromInt(case2i, 4));
        assertArrayEquals(case3b, ByteArrayTools.getByteArrayFromInt(case3i, 2));
        assertArrayEquals(case4b, ByteArrayTools.getByteArrayFromInt(case4i, 4));
    }

    @Test
    void getByteArrayFromString() {
        String case1 = "case1";
        String case5 = "case 5 ";
        String case2 = "";
        String case3 = "ABCDEF";
        String case4 = "abcdef";

        assertArrayEquals(case1.getBytes(), ByteArrayTools.getByteArrayFromString(case1));
        assertArrayEquals(case5.getBytes(), ByteArrayTools.getByteArrayFromString(case5));
        assertArrayEquals(case2.getBytes(), ByteArrayTools.getByteArrayFromString(case2));
        assertArrayEquals(case3.getBytes(), ByteArrayTools.getByteArrayFromString(case3));
    }
}