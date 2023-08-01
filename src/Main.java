import data.SoundObjectV1;
import fourier.*;
import graphics.PlotterV1;
import graphics.PlotterV1Impl;
import manipulation.ManipulateFFT;
import manipulation.PeakFilter;
import manipulation.PitchFourier;
import manipulation.Samplets;
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
        Samplets samplets;
        PeakFilter pf;
        PitchFourier pitch;


        try {
            //so  = fr.read("C:\\Users\\timro\\IdeaProjects\\Bachelor\\rec\\C418-Minecraft.wav");
            so  = fr.read("C:\\Users\\timro\\IdeaProjects\\Bachelor\\rec\\C-small.wav");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        so.setName("c-test.wav");
        //samplets = new Samplets(so);
        //pf = new PeakFilter(samplets.getSamplets());
        //plotter.plotCoeff(samplets.getSamplets()[28]);
        pitch = new PitchFourier(so, 1);
        so = pitch.getSoundobject();


        try {
            fw.write("C:\\Users\\timro\\IdeaProjects\\Bachelor\\rec\\", so);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("");
    }
}
