package graphics;

import data.SoundObjectV1;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

/**
 * class for plotting a pcm-wave
 */
public class PlotterPCM {
    /**
     * plott returns a buffered image with sizeX and sizeY of given WaveObject wo
     * @param so    WaveObject
     * @param sizeX int, Length of Image
     * @param sizeY int, Height of Image
     * @return
     */
    public static BufferedImage plott(SoundObjectV1 so, int sizeX, int sizeY) {
        //calculate the stepsize for x
        double stepFaktor = so.getJavaPCM().length > sizeX ? (double) so.getJavaPCM().length / sizeX : 1;

        //calculate Plott-Array (datapoints to plot)
        double[] plot = new double[so.getJavaPCM().length > sizeX ? sizeX : so.getJavaPCM().length];

        //fill Plott-Array
        if(so.getJavaPCM().length > sizeX) {
            for (int i = 0; i < plot.length; i++) {
                plot[i] = getWeightedAverageOf(Arrays.copyOfRange(so.getJavaPCM(), (int) (i * stepFaktor), (int) ((i + 1) * stepFaktor - 1)));
            }
        }
        else plot = so.getJavaPCM();

        //get the scale for y
        double scale = sizeY / getHighestValue(plot) / 2;

        BufferedImage output = new BufferedImage(sizeX, sizeY,BufferedImage.TYPE_4BYTE_ABGR);

        //for each x and y plot if data is in range
        for(int x = 0; x < plot.length; x++){
            for(int y = 0; y < sizeY / 2; y++){
                if(sizeY / 2 - y <= plot[x] * scale){
                    output.setRGB(x,y, Color.BLACK.getRGB());
                    output.setRGB(x,sizeY - (y + 1), Color.BLACK.getRGB());
                }
                else {
                    output.setRGB(x, y, Color.white.getRGB());
                    output.setRGB(x, sizeY - (y + 1), Color.white.getRGB());
                }
            }
        }

        drawGrid(sizeX, sizeY, output);

        return output;
    }
    private static void drawGrid(int sizeX, int sizeY, BufferedImage output) {
        Integer nextGrid = 100;

        //make grid
        for (int x = 0; x < sizeX; x++) {
            if (nextGrid.compareTo(x) < 0) {
                for (int y = 0; y < sizeY; y++) {
                    if (nextGrid % 1000 == 0) output.setRGB(x, y, Color.BLACK.getRGB());
                    else if (y % 4 == 0) output.setRGB(x, y, Color.BLACK.getRGB());
                }
                nextGrid += 100;
            }
        }
    }

    //
    private static double getHighestValue(double[] arr){
        double output = 0;
        double current;

        for(int n = 0; n < arr.length; n++){
            current = Math.abs(arr[n]);
            if(current > output) output = current;
        }

        return output;
    }
    private static double getWeightedAverageOf(double[] arr){
        double output = 0;

        for(int i = 0; i < arr.length; i++) output += arr[i] / (i + 1);

        return output / arr.length;
    }
}
