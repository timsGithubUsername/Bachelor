package graphics;

import data.FourierObjectV1;
import data.WaveObjectV1;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class PlotterFourier {
    /**
     * plott returns a buffered image with sizeX and sizeY of given FourierObject fo
     * @param fo    FourierObject
     * @param sizeX int, Length of Image
     * @param sizeY int, Height of Image
     * @return
     */
    public static BufferedImage plott(FourierObjectV1 fo, int sizeX, int sizeY) {
        //calculate the stepsize for x
        int stepsize = fo.getAmplitude().length / sizeX;

        double[] plottData = new double[sizeX];

        for(int i = 0; i < plottData.length; i++){
            plottData[i] = getHighestValue(Arrays.copyOfRange(fo.getAmplitude(),i,i+stepsize));
        }
        //get the scale for y
        double scale = sizeY / getHighestValue(plottData);

        BufferedImage output = new BufferedImage(sizeX, sizeY,BufferedImage.TYPE_4BYTE_ABGR);

        //for each x and y plot if data is in range
        for(int x = 0; x < sizeX; x++){
            for(int y = 0; y < sizeY; y++){
                if(sizeY - y <= plottData[x] * scale){
                    output.setRGB(x,y, Color.BLACK.getRGB());
                }
                else {
                    output.setRGB(x, y, Color.white.getRGB());
                }
            }
        }

        return output;
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
