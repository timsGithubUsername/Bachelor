package manipulation;

import data.SoundObjectV1;

public class PeakFilter {

    private SoundObjectV1[] samplets;
    private boolean[][] signalPeaks;

    public PeakFilter(SoundObjectV1[] samplets){
        this.samplets = samplets;

        filterSignal();
    }

    public boolean[][] getSignalPeaks() {
        return signalPeaks;
    }

    private void filterSignal() {
        int lag = 10;
        int sampletLength = samplets[0].getMagnitude().length;
        double threshold = 3, average = 0;

        signalPeaks = new boolean[samplets.length][sampletLength];

        //for each samplet
        for(int samplet = 0; samplet < samplets.length; samplet++){

            //for each datapoint in fourier
            for(int i = lag; i < sampletLength; i++){
                average = 0;

                //calculate moving average
                for(int avg = 0; avg < lag; avg++){
                    average += samplets[samplet].getMagnitude()[i-avg];
                }

                average = average / lag;

                //if value is greater than average times threshold create signal
                if(samplets[samplet].getMagnitude()[i] > average * threshold){
                    signalPeaks[samplet][i] = true;
                }
            }
        }
    }
}
