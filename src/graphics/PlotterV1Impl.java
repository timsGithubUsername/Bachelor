package graphics;

import data.SoundObjectV1;

import java.awt.image.BufferedImage;

public class PlotterV1Impl implements PlotterV1{
    int sizeX=5000, sizeY=500;
    @Override
    public BufferedImage plotPCM(SoundObjectV1 so) { return PlotterPCM.plott(so, sizeX, sizeY); }

    @Override
    public BufferedImage plotCoeff(SoundObjectV1 so) {
        return PlotterFourierV2.plott(so,sizeX,sizeY);
    }

    @Override
    public void setSize(int x, int y) {
        sizeX = x;
        sizeY = y;
    }
}
