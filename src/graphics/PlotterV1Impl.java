package graphics;

import data.FourierObjectV1;
import data.WaveFourierObjekt;
import data.WaveObjectV1;

import java.awt.image.BufferedImage;

public class PlotterV1Impl implements PlotterV1{
    int sizeX=5000, sizeY=500;
    @Override
    public BufferedImage plotPCM(WaveObjectV1 wo) { return PlotterPCM.plott(wo, sizeX, sizeY); }

    @Override
    public BufferedImage plotCoeff(FourierObjectV1 fo) {
        return PlotterFourierV2.plott((WaveFourierObjekt) fo,sizeX,sizeY);
    }

    @Override
    public void setSize(int x, int y) {
        sizeX = x;
        sizeY = y;
    }
}
