package graphics;

import data.FourierObjectV1;
import data.WaveObjectV1;

import java.awt.image.BufferedImage;

public interface PlotterV1 {

    BufferedImage plotPCM(WaveObjectV1 wo);

    BufferedImage plotCoeff(FourierObjectV1 fo);

    void setSize(int x, int y);
}
