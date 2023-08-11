import data.SoundObjectV1;
import graphics.PlotterV1;
import graphics.PlotterV1Impl;
import manipulation.*;
import waveIO.FileReaderV1;
import waveIO.FileReaderV1Impl;
import waveIO.FileWriterV1;
import waveIO.FileWriterV1Impl;

import java.io.File;
import java.util.Scanner;

public class Main {
    static FileReaderV1 fr = new FileReaderV1Impl();
    static FileWriterV1 fw = new FileWriterV1Impl();
    static PlotterV1 plotter = new PlotterV1Impl();
    static DFTV1 dft = new DFTV1Impl();
    static FFTV1 fft = new FFTV1Impl();
    static SoundObjectV1 so;
    static PitchSamplets pitch = new PitchSamplets();
    static PitchShift pitchShift = new PitchShift();

    public static void main(String[] args) {
        if(args.length < 2) {
            System.out.println("Bitte geben folgende Parameter ein:\n\n" +
                    "String: Absoluter Pfad\n" +
                    "int: Faktor zum Warpen\n" +
                    "optional String: \"slow\" für den langsamen Warp\n\n" +
                    "Beispiel: warp mein\\pfad\\wave.wav 1.2\n" +
                    "Beispiel: warp mein\\anderer pfad\\wave.wav 1.2 slow\n");
            return;
        }

        String path = new File(args[0]).getParent();

        try {
            so = fr.read(args[0]);
        } catch (Exception e) {
            System.out.println("Datei konnte nicht gelesen werden. Ist die Datei ein WAVE-File in PCM Format?");
            return;
        }

        double factor;
        try{
            factor = Double.parseDouble(args[1]);
        } catch (Exception e) {
            System.out.println("Faktor sollte eine Zahl zwischen 0.1 und 2 sein!");
            return;
        }

        if(factor < 0.1 || factor > 2) {
            System.out.println("Faktor sollte zwischen 0.1 und 2 sein! Programm endet!");
            return;
        }

        if(args.length < 3){
            System.out.println("Starte Transformation");
            so = pitch.pitch(so, factor);
            so.setName(so.getName()+"-warped(Window)"+args[1]+"x.wav");
        } else {
            System.out.println("Starte Transformation ohne Fenstern. Dies dauert sehr lange für Audiodateien > 2s!");
            so = pitchShift.pitch(so, factor);
            so.setName(so.getName()+"-warped(Full)"+args[1]+"x.wav");
        }

        try {
            fw.write(path+"\\", so);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


       //try {
       //    //so  = fr.read("C:\\Users\\timro\\IdeaProjects\\Bachelor\\rec\\C418-Minecraft.wav");
       //    so  = fr.read("C:\\Users\\timro\\IdeaProjects\\Bachelor\\rec\\C-small.wav");

       //} catch (Exception e) {
       //    throw new RuntimeException(e);
       //}

       //so.setName("c-test.wav");
       ////samplets = new HannWindows(so);
       ////pf = new PeakFilter(samplets.getSamplets());
       ////plotter.plotCoeff(samplets.getSamplets()[28]);
       //so = pitch.pitch(so, Math.pow(2, 4./12));

       ////Complex[] freq = samplets.getSamplets()[10].getFrequency();
       ////for(int i = 0; i < freq.length; i++) System.out.println(freq[i] + " <-> " + freq[freq.length - (i+1)]);


       //try {
       //    fw.write("C:\\Users\\timro\\IdeaProjects\\Bachelor\\rec\\", so);
       //} catch (Exception e) {
       //    throw new RuntimeException(e);
       //}
       //System.out.println("")
}
