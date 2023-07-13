package data;

/**
 * FourierObject holds all the data from a Fourier Transformation to inverse the transformation or manipulate the data
 */
public interface FourierObjectV1{
    String getName();
    void setName(String name);
    double[] getReal();
    double[] getImg();
    double[] getAmplitude();
}
