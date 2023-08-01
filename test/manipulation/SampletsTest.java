package manipulation;

import data.SoundObjectV1;
import data.SoundObjectV1Impl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SampletsTest {
    private SoundObjectV1 so = new SoundObjectV1Impl(), soo = new SoundObjectV1Impl();
    Samplets s1;
    Samplets s2;
    Samplets s3;

    @BeforeEach
    public void before(){
        so.setJavaPCM(new double[]{1,2,1,0,
                1,2,1,0,
                1,2,1,0,
                1,2,1,0,
                1,2,1,0,});
        soo.setJavaPCM(new double[]{1,2,1,0,
                1,2,1,0,
                1,2,1,0,
                1,2,1,0,
                1,2,1,0,
                1,2,1,0,
                1,2,1,0,
                1,2,1,0,});


        s1 = new Samplets(4,so);
        s2 = new Samplets(8,so);
        s3 = new Samplets(16,soo);
    }

    @Test
    void getSamplets() {
        SoundObjectV1[] samplets1 = s1.getSamplets();
        SoundObjectV1[] samplets2 = s2.getSamplets();

        assertEquals(6, s1.getSamplets().length);
        assertArrayEquals(new double[]{0,2,1,0}, samplets1[0].getJavaPCM());
        assertArrayEquals(new double[]{0,1,2,0}, samplets1[1].getJavaPCM());
        assertArrayEquals(new double[]{0,0,1,0}, samplets1[2].getJavaPCM());
        assertArrayEquals(new double[]{0,1,0,0}, samplets1[3].getJavaPCM());
        assertArrayEquals(new double[]{0,2,1,0}, samplets1[4].getJavaPCM());
        assertArrayEquals(new double[]{0,1,2,0}, samplets1[5].getJavaPCM());

        assertEquals(3, s2.getSamplets().length);
        assertArrayEquals(new double[]{0,1,1,0,1,2,0.5,0}, samplets2[0].getJavaPCM());
        assertArrayEquals(new double[]{0,0,1,2,1,0,0.5,0}, samplets2[1].getJavaPCM());
        assertArrayEquals(new double[]{0,1,1,0,1,2,0.5,0}, samplets2[2].getJavaPCM());
    }

    @Test
    void getSoundObject() {
        SoundObjectV1 so1 = s1.getSoundObject();
        SoundObjectV1 so2 = s2.getSoundObject();
        SoundObjectV1 so3 = s3.getSoundObject();

        //for(int i = 0; i < so1.getJavaPCM().length; i++){
        //    System.out.println(so1.getJavaPCM()[i]);
        //}

        assertEquals(19, so1.getJavaPCM().length);
        assertEquals(20, so2.getJavaPCM().length);
        assertEquals(28, so3.getJavaPCM().length);

        assertArrayEquals(new double[]{0,1,1,0,1,2,0.5,0,1,2,1,0,0.5,1,1,0,1,2,0.5,0}, so2.getJavaPCM());
        assertArrayEquals(new double[]{0,2,1,0,1,2,0,0,1,0,1,0,0,2,1,0,1,2,0}, so1.getJavaPCM());
        assertArrayEquals(new double[]{0,0.5,0.5,0,1,2,1,0,1,2,1,0,0.75,1.5,0.75,0,1,2,1,0,1,2,1,0,0.75,1,0.25,0}, so3.getJavaPCM());


    }
}