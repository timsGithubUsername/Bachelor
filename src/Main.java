import data.SoundObjectV1;
import graphics.PlotterV1;
import graphics.PlotterV1Impl;
import manipulation.*;
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
        PitchSamplets pitch = new PitchSamplets();
        PitchShift pitchShift = new PitchShift();


        try {
            //so  = fr.read("C:\\Users\\timro\\IdeaProjects\\Bachelor\\rec\\C418-Minecraft.wav");
            so  = fr.read("C:\\Users\\timro\\IdeaProjects\\Bachelor\\rec\\C-small.wav");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        so.setName("c-radex2.wav");
        //samplets = new HannWindows(so);
        //pf = new PeakFilter(samplets.getSamplets());
        //plotter.plotCoeff(samplets.getSamplets()[28]);
        fft.fft(so);
        plotter.plotCoeff(so);
        so = pitch.pitch(so, 1.2);

        //Complex[] freq = samplets.getSamplets()[10].getFrequency();
        //for(int i = 0; i < freq.length; i++) System.out.println(freq[i] + " <-> " + freq[freq.length - (i+1)]);


        try {
            fw.write("C:\\Users\\timro\\IdeaProjects\\Bachelor\\rec\\", so);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("");
    }

}
