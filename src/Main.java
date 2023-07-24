import data.WaveObjectV1;
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
        DFTV1 dft = new DFTV1Impl();
        FFTV1 fft = new FFTV1Impl();
        WaveObjectV1 wo;

        try {
            wo  = fr.read("C:\\Users\\timro\\IdeaProjects\\Bachelor\\rec\\C-small.wav");
            //wo  = fr.read("C:\\Users\\timro\\IdeaProjects\\Bachelor\\rec\\C418-Minecraft.wav");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        PlotterV1 plotter = new PlotterV1Impl();

        plotter.plotPCM(wo);
        plotter.plotCoeff(fft.fft(wo));

  //      try {
  //          fw.write("C:\\Users\\timro\\IdeaProjects\\Bachelor\\rec\\", wo);
  //      } catch (Exception e) {
  //          throw new RuntimeException(e);
  //      }
  //      System.out.println("");
    }
}
