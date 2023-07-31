import data.SoundObjectV1;
import fourier.DFTV1;
import fourier.DFTV1Impl;
import fourier.FFTV1;
import fourier.FFTV1Impl;
import graphics.PlotterV1;
import graphics.PlotterV1Impl;
import waveIO.FileReaderV1;
import waveIO.FileReaderV1Impl;
import waveIO.FileWriterV1;
import waveIO.FileWriterV1Impl;

public class Main {
    public static void main(String[] args){
        FileReaderV1 fr = new FileReaderV1Impl();
        FileWriterV1 fw = new FileWriterV1Impl();
        PlotterV1 plotter = new PlotterV1Impl();
        DFTV1 dft = new DFTV1Impl();
        FFTV1 fft = new FFTV1Impl();
        SoundObjectV1 so;

        try {
            so  = fr.read("C:\\Users\\timro\\IdeaProjects\\Bachelor\\rec\\C418-Minecraft.wav");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        so.setName("c418-test.wav");

        plotter.plotPCM(so);

        try {
            fw.write("C:\\Users\\timro\\IdeaProjects\\Bachelor\\rec\\", so);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("");
    }
}
