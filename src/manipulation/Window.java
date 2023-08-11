package manipulation;

import data.SoundObjectV1;

interface Window {
    int getTaperLength();
    SoundObjectV1[] getSamplets();
    SoundObjectV1 getSoundObject();
    int getFullSize();
    int getOverlapFactor();
}
