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
            //createPeakPoints(samplet);
            //transformArray(samplet);
            shiftArray(samplet);
        }

        this.so = samplets.getSoundObject();
    }

    private void shiftArray(int samplet) {
        Complex[] currentFrequenzy = samplets.getSamplets()[samplet].getFrequency();
        Complex[] newFrequenzy = createComplexArrayOfLength(currentFrequenzy.length);

        double steps = (factor - 1);
        double currentStep = steps;
        int newFrequencyIndex = 0;

        for(int i = 0; i < newFrequenzy.length; i++){
            if(newFrequencyIndex >= newFrequenzy.length) break;
            newFrequenzy[newFrequencyIndex] = currentFrequenzy[i];
            newFrequencyIndex++;
            currentStep += steps;

            if(currentStep >= 1 && newFrequencyIndex < newFrequenzy.length) {
                currentStep -= 1;
                newFrequenzy[newFrequencyIndex] = currentFrequenzy[i];
                newFrequencyIndex++;
            }
        }
        //fillCurrentFrequenzy(newFrequenzy, currentFrequenzy);
        samplets.getSamplets()[samplet].setFrequency(newFrequenzy);
    }

    private void transformArray(int samplet) {
        Complex[] currentFrequenzy = samplets.getSamplets()[samplet].getFrequency();
        Complex[] newFrequenzy = createComplexArrayOfLength(currentFrequenzy.length / 2);

        int currentIndex = 0;

        for(int i = 0; i < peaks.size();){
            if(currentIndex >= newFrequenzy.length) break;
            System.out.print(currentIndex + " <-> ");//todo
            currentIndex = setIndexZeros(currentIndex, i == 0 ? 0 : peaks.get(i-1), peaks.get(i));
            System.out.print(currentIndex + " | ");//todo
            currentIndex = copyArrayPeak(newFrequenzy, currentIndex, currentFrequenzy, peaks.get(i), peaks.get(i+1));
            i+=2;
        }
        System.out.println("");
        fillCurrentFrequenzy(newFrequenzy, currentFrequenzy);
    }

    private int copyArrayPeak(Complex[] newFrequenzy, int currentIndex, Complex[] currentFrequenzy, Integer start, Integer end) {
        for(int i = start; i < end; i++){
            if(currentIndex >= newFrequenzy.length) break;
            newFrequenzy[currentIndex] = currentFrequenzy[i];
            currentIndex++;
        }
        return currentIndex;
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


    private void createPeakPoints(int samplet) {
        Complex[] currentFrequenzy = samplets.getSamplets()[samplet].getFrequency();

        int start = 0, end = 0;
        boolean inPeak = false;

        peaks = new ArrayList<Integer>();

        for (int i = 1; i < currentFrequenzy.length / 2; i++) {
            //if peak starts
            if (!inPeak && peakFilter.getSignalPeaks()[samplet][i]) {
                inPeak = peakFilter.getSignalPeaks()[samplet][i];

                start = i;
                peaks.add(start);
            }
            //if peak ends
            else if (inPeak && !peakFilter.getSignalPeaks()[samplet][i]) {
                inPeak = peakFilter.getSignalPeaks()[samplet][i];

                end = i;
                peaks.add(end);
            }
        }

        if(inPeak) peaks.add((currentFrequenzy.length / 2));
    }

    private Complex[] createComplexArrayOfLength(int entrys) {
        Complex[] output = new Complex[entrys];

        for (int i = 0; i < entrys; i++) output[i] = new ComplexImpl();

        return output;
    }
}
