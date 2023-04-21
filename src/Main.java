import data.WaveObjectV1;
import waveIO.FileReaderV1;
import waveIO.FileReaderV1Impl;

public class Main {
    public static void main(String[] args){
        FileReaderV1 fr = new FileReaderV1Impl();
        WaveObjectV1 wo;

        try {
           wo  = fr.read("C:\\Users\\timro\\IdeaProjects\\Bachelor\\rec\\C418-Minecraft.wav");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("");
    }
}
