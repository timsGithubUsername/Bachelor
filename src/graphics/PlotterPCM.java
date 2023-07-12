package graphics;

import data.WaveObjectV1;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class PlotterPCM {
    public static BufferedImage plott(WaveObjectV1 wo, int sizeX, int sizeY) {
        double[] javaPCM = wo.getJavaPCM();

        //calculate the stepsize for x
        int stepsize = javaPCM.length / sizeX;
        //get the scale for y
        int scale = (int) (getHighestValue(javaPCM) / sizeY) / 2;

        BufferedImage output = new BufferedImage(sizeX, sizeY,BufferedImage.TYPE_4BYTE_ABGR);

        double currentValue;

        for(int x = 0; x < sizeX; x++){
            currentValue = getAverageOf(Arrays.copyOfRange(javaPCM, x * stepsize, (x + 1) * stepsize - 1));

            for(int y = 0; y < sizeY; y++){
                if (currentValue > 0 && y >= sizeY/2) {
                    if (sizeY / 2 - y < currentValue * scale) output.setRGB(x,y, Color.BLACK.getRGB());
                }
                if (currentValue <= 0) {
                    if (y > currentValue * scale) output.setRGB(x,y, Color.BLACK.getRGB());
                }
            }
        }
    //todo image is black
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

    private static double getAverageOf(double[] arr){
        double output = 0;

        for (double d:arr) output += d;

        return output / arr.length;
    }
}
