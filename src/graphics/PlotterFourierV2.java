package graphics;

import data.FourierObjectV1;
import data.WaveFourierObjekt;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class PlotterFourierV2 {
    private static double maxFrequency = 20000;

    /**
     * plott returns a buffered image with sizeX and sizeY of given FourierObject fo
     *
     * @param fo    FourierObject
     * @param sizeX int, Length of Image
     * @param sizeY int, Height of Image
     * @return
     */
    public static BufferedImage plott(WaveFourierObjekt fo, int sizeX, int sizeY) {
        double[] plottData = new double[sizeX];
        int step = (int) (maxFrequency / sizeX);
        int binStep = step * fo.getAmplitude().length / fo.getSampleRate();

        for (int i = 0; i < plottData.length; i++) {
            plottData[i] = getHighestValue(Arrays.copyOfRange(fo.getAmplitude(),i,i+binStep));
        }

        //get the scale for y
        double scale = sizeY / getHighestValue(plottData);

        BufferedImage output = new BufferedImage(sizeX, sizeY, BufferedImage.TYPE_4BYTE_ABGR);

        //for each x and y plot if data is in range
        drawData(sizeX, sizeY, plottData, scale, output);

        drawGrid(sizeX, sizeY, step, output);

        return output;
    }

    private static void drawData(int sizeX, int sizeY, double[] plottData, double scale, BufferedImage output) {
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                if (sizeY - y <= plottData[x] * scale * 2) {
                    output.setRGB(x, y, Color.gray.getRGB());
                } else {
                    output.setRGB(x, y, Color.white.getRGB());
                }
            }
        }
    }

    private static void drawGrid(int sizeX, int sizeY, int step, BufferedImage output) {
        Integer nextGrid = 100;

        //make grid
        for (int x = 0; x < sizeX; x++) {
            if (nextGrid.compareTo(step * x) < 0) {
                for (int y = 0; y < sizeY; y++) {
                    if(nextGrid % 1000 == 0) output.setRGB(x, y, Color.BLACK.getRGB());
                    else if(y % 4 == 0) output.setRGB(x, y, Color.BLACK.getRGB());
                }
                nextGrid += 100;
            }
        }
    }

    //
    private static double getHighestValue(double[] arr) {

        double output = 0;
        double current;

        for (int n = 0; n < arr.length; n++) {
            current = Math.abs(arr[n]);
            if (current > output) output = current;
        }

        return output;
    }
}
