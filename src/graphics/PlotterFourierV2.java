package graphics;

import data.SoundObjectV1;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class PlotterFourierV2 {
    /**
     * plott returns a buffered image with sizeX and sizeY of given FourierObject fo
     *
     * @param so    FourierObject
     * @param sizeX int, Length of Image
     * @param sizeY int, Height of Image
     * @return
     */
    public static BufferedImage plott(SoundObjectV1 so, int sizeX, int sizeY) {

        double[] plottData = new double[so.getMagnitude().length/2 > sizeX ? sizeX : so.getMagnitude().length/2 - 1];
        double stepFaktor = so.getMagnitude().length > sizeX ? (double) so.getMagnitude().length/2 / sizeX : 1;


        for (int i = 0; i < plottData.length; i++) {
            //plottData[i] = getHighestValue(Arrays.copyOfRange(so.getMagnitude(), (int) ((i+1)*stepFaktor), (int) ((i + 2)*stepFaktor)));
            plottData[i] = so.getFrequency()[i+1].getPhase();
        }

        //get the scale for y
        double scale = sizeY / getHighestValue(plottData);

        BufferedImage output = new BufferedImage(sizeX, sizeY, BufferedImage.TYPE_4BYTE_ABGR);

        //for each x and y plot if data is in range
        drawData(sizeX, sizeY, plottData, scale, output);

        drawGrid(sizeX, sizeY, stepFaktor, output, so);

        return output;
    }

    private static void drawData(int sizeX, int sizeY, double[] plottData, double scale, BufferedImage output) {
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                if (x >= plottData.length) {
                    output.setRGB(x, y, Color.red.getRGB());
                } else if (sizeY - y <= plottData[x] * scale * 2) {
                    output.setRGB(x, y, Color.gray.getRGB());
                } else {
                    output.setRGB(x, y, Color.white.getRGB());
                }
            }
        }
    }

    private static void drawGrid(int sizeX, int sizeY, double stepFaktor, BufferedImage output, SoundObjectV1 so) {
        Integer gridStep = (int)(100.0 * so.getMagnitude().length / (so.getSampleRate() * stepFaktor)); // * 1/faktor um zu skalieren und verschiebung zu zeigen
        Integer nextGrid = gridStep;

        //make grid
        for (int x = 0; x < sizeX; x++) {
            if (nextGrid.compareTo(x) < 0) {
                for (int y = 0; y < sizeY; y++) {
                    if (nextGrid % (10*gridStep) == 0) output.setRGB(x, y, Color.BLACK.getRGB());
                    else if (y % 4 == 0) output.setRGB(x, y, Color.BLACK.getRGB());
                }
                nextGrid += gridStep;
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
