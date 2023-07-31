package graphics;

import data.SoundObjectV1;

import java.awt.image.BufferedImage;

/**
 * Plotter generates graphics of the waves or the Fourier-Transformation
 */
public interface PlotterV1 {

    BufferedImage plotPCM(SoundObjectV1 so);

    BufferedImage plotCoeff(SoundObjectV1 so);

    void setSize(int x, int y);
}
