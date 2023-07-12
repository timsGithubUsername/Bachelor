package graphics;

import data.FourierObjectV1;
import data.WaveObjectV1;

import java.awt.image.BufferedImage;

public class PlotterV1Impl implements PlotterV1{
    int sizeX=500, sizeY=100;
    @Override
    public BufferedImage plotPCM(WaveObjectV1 wo) { return PlotterPCM.plott(wo, sizeX, sizeY); }

    @Override
    public BufferedImage plotCoeff(FourierObjectV1 fo) {
        return PlotterFourier.plott(fo,sizeX,sizeY);
    }

    @Override
    public void setSize(int x, int y) {
        sizeX = x;
        sizeY = y;
    }
}
