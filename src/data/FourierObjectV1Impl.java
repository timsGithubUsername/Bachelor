package data;

public class FourierObjectV1Impl implements FourierObjectV1{
    private String name;
    private double[] real, img, amplitude;

    public FourierObjectV1Impl(String name,double[] real,double[] img,double[] amplitude){
        this.name = name;
        this.real = real;
        this.img = img;
        this.amplitude = amplitude;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public double[] getReal() {
        return real;
    }

    @Override
    public double[] getImg() {
        return img;
    }

    @Override
    public double[] getAmplitude() {
        return amplitude;
    }
}
