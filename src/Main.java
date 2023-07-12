import data.WaveObjectV1;
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
        WaveObjectV1 wo;

        try {
            wo  = fr.read("C:\\Users\\timro\\IdeaProjects\\Bachelor\\rec\\C418-Minecraft.wav");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        wo.setName("soundoutput_test.wav");

        PlotterV1 plotter = new PlotterV1Impl();

        plotter.plotPCM(wo);

  //      try {
  //          fw.write("C:\\Users\\timro\\IdeaProjects\\Bachelor\\rec\\", wo);
  //      } catch (Exception e) {
  //          throw new RuntimeException(e);
  //      }
  //      System.out.println("");
    }
}
