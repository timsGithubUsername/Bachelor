package manipulation;

import data.SoundObjectV1;
import data.Complex;
import data.ComplexImpl;
import graphics.PlotterV1;
import graphics.PlotterV1Impl;

public class PitchSamplets {
    private HannWindows samplets;

    private double factor;

    public SoundObjectV1 pitch(SoundObjectV1 so, double factor) {
        this.samplets = new HannWindows(so);
        this.factor = factor;

        for(int samplet = 0; samplet < samplets.getSamplets().length; samplet++){
            shiftArray(samplet);
        }

        return samplets.getSoundObject();
    }

    private void shiftArray(int samplet) {
        Complex[] currentFrequenzy = samplets.getSamplets()[samplet].getFrequency();
        Complex[] newFrequenzy = createComplexArrayOfLength((int)(currentFrequenzy.length*factor));
        Complex phaseShift;
        double tValue = (2 * factor - 2) / samplets.getOverlapFactor();
        int newPosition;

        for(int i = 0; i < currentFrequenzy.length/2 && i * factor+0.5 < newFrequenzy.length/2; i++){
            newPosition = (int) (i*factor+0.5);
            phaseShift = new ComplexImpl(Math.cos(tValue*(newPosition - i)), -Math.sin(tValue*(newPosition - i)));

            newFrequenzy[newPosition] = currentFrequenzy[i].times(phaseShift);

        }

        for(int i = 0; i < (newFrequenzy.length/2-1);i++) newFrequenzy[newFrequenzy.length/2+(i+1)]=newFrequenzy[newFrequenzy.length/2-(i+1)].getConjugate();

        //fillCurrentFrequenzy(newFrequenzy, currentFrequenzy);
        samplets.getSamplets()[samplet].setFrequency(newFrequenzy);
    }

    private Complex[] createComplexArrayOfLength(int entrys) {
        Complex[] output = new Complex[entrys];

        for (int i = 0; i < entrys; i++) output[i] = new ComplexImpl();

        return output;
    }
}
