package graphics;

import data.WaveObjectV1;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class PlotterPCM {
    public static BufferedImage plott(WaveObjectV1 wo, int sizeX, int sizeY) {
        //calculate the stepsize for x
        int stepsize = wo.getJavaPCM().length / sizeX;

        //calculate Plott-Array
        double[] plot = new double[sizeX];

        for(int i = 0; i < plot.length; i++){
            plot[i] = getWeightedAverageOf(Arrays.copyOfRange(wo.getJavaPCM(), i * stepsize, (i + 1) * stepsize - 1));
        }

        //get the scale for y
        double scale = sizeY / getHighestValue(plot) / 2;

        BufferedImage output = new BufferedImage(sizeX, sizeY,BufferedImage.TYPE_4BYTE_ABGR);

        for(int x = 0; x < sizeX; x++){

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
        return output;
    }

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

        for(int i = 0; i < arr.length; i++)
        output += arr[i] / (i + 1);

        return output / arr.length;
    }
}
