package manipulation;

import data.SoundObjectV1;
import fourier.Complex;
import fourier.ComplexImpl;

import java.util.ArrayList;

public class PitchFourier {
    private Samplets samplets;
    private PeakFilter peakFilter;

    private double factor;
    ArrayList<Integer> peaks;

    private SoundObjectV1 so;

    public SoundObjectV1 getSoundobject(){return so;}

    public PitchFourier(SoundObjectV1 so, double factor){
        samplets = new Samplets(so);
        peakFilter = new PeakFilter(samplets.getSamplets());
        this.factor = factor;

        for(int samplet = 0; samplet < samplets.getSamplets().length; samplet++){
            createPeakPoints(samplet);
            transformArray(samplet);
        }

        this.so = samplets.getSoundObject();
    }

    private void transformArray(int samplet) {
        Complex[] currentFrequenzy = samplets.getSamplets()[samplet].getFrequency();
        Complex[] newFrequenzy = createComplexArrayOfLength(currentFrequenzy.length / 2);

        int currentIndex = 0;

        for(int i = 2; i < peaks.size();){
            if(currentIndex >= newFrequenzy.length) break;
            currentIndex = copyArrayHalfPeak(newFrequenzy, currentIndex, currentFrequenzy, peaks.get(i-2), peaks.get(i-1));
            currentIndex = setIndexZeros(currentIndex, peaks.get(i-2), peaks.get(i));
            currentIndex = copyArrayHalfPeak(newFrequenzy, currentIndex, currentFrequenzy, peaks.get(i-1), peaks.get(i));

            i+=3;
        }

        fillCurrentFrequenzy(newFrequenzy, currentFrequenzy);
    }

    private void fillCurrentFrequenzy(Complex[] newFrequenzy, Complex[] currentFrequenzy) {
        for(int i = 0; i < newFrequenzy.length; i++){
            currentFrequenzy[i] = newFrequenzy[i];
            currentFrequenzy[currentFrequenzy.length-(i+1)] = newFrequenzy[i];
        }
    }

    private int setIndexZeros(int currentIndex, Integer peak1, Integer peak2) {
        int gap = peak2 - peak1;
        int toFill = (int) (gap * factor) - gap;

        currentIndex += toFill;

        return currentIndex;
    }

    private int copyArrayHalfPeak(Complex[] newFrequenzy, int currentIndex, Complex[] currentFrequenzy, Integer peak1, Integer peak2) {
        for(int i = peak1; i < peak2; i++){
            if(currentIndex >= newFrequenzy.length) break;
            newFrequenzy[currentIndex] = currentFrequenzy[i];
            currentIndex++;
        }
        return currentIndex;
    }


    private void createPeakPoints(int samplet) {
        Complex[] currentFrequenzy = samplets.getSamplets()[samplet].getFrequency();

        int start = 0, end = 0;
        boolean inPeak = false;

        peaks = new ArrayList<Integer>();
        peaks.add(0);

        for (int i = 1; i < currentFrequenzy.length / 2; i++) {
            //if peak starts
            if (!inPeak && peakFilter.getSignalPeaks()[samplet][i]) {
                inPeak = peakFilter.getSignalPeaks()[samplet][i];

                start = i;
                peaks.add(end + (start - end)/2);
            }
            //if peak ends
            else if (inPeak && !peakFilter.getSignalPeaks()[samplet][i]) {
                inPeak = peakFilter.getSignalPeaks()[samplet][i];

                end = i;
                peaks.add(start + (end - start)/2);
            }
        }

        if(!inPeak) peaks.add(((currentFrequenzy.length / 2) - end) / 2);
    }

    private Complex[] createComplexArrayOfLength(int entrys) {
        Complex[] output = new Complex[entrys];

        for (int i = 0; i < entrys; i++) output[i] = new ComplexImpl();

        return output;
    }
}
