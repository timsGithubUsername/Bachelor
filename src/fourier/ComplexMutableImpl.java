package fourier;

public class ComplexMutableImpl implements ComplexMutable {

    private double imaginary, real, magnitude;

    public ComplexMutableImpl(double real, double imaginary){
        this.imaginary = imaginary;
        this.real = real;
        calcMagnitude();
    }

    private void calcMagnitude() {
        magnitude = Math.sqrt(Math.pow(imaginary, 2) + Math.pow(real, 2));
    }

    @Override
    public double getImaginary() {
        return imaginary;
    }

    public void setImaginary(double imaginary){
        this.imaginary = imaginary;
        calcMagnitude();
    }
    @Override
    public double getReal() {
        return real;
    }

    public void setReal(double real){
        this.real = real;
        calcMagnitude();
    }

    @Override
    public double getMagnitude() {
        return magnitude;
    }

    @Override
    public ComplexMutable add(ComplexMutable number) {
        real += number.getReal();
        imaginary += number.getImaginary();
        calcMagnitude();

        return this;
    }

    @Override
    public ComplexMutable substract(ComplexMutable number) {
        real -= number.getReal();
        imaginary -= number.getImaginary();
        calcMagnitude();

        return this;
    }

    @Override
    public ComplexMutable add(double number) {
        real += number;
        calcMagnitude();

        return this;
    }

    @Override
    public ComplexMutable substract(double number) {
        real -= number;
        calcMagnitude();

        return this;
    }

    @Override
    public ComplexMutable times(ComplexMutable number) {
        real = real * number.getReal() - imaginary * number.getImaginary();
        imaginary = imaginary * number.getReal() + real * number.getImaginary();
        calcMagnitude();

        return this;
    }

    @Override
    public ComplexMutable times(double number) {
        real = real * number;
        imaginary = imaginary * number;
        calcMagnitude();

        return this;
    }

    @Override
    public ComplexMutable pow(int power) {
        if(power == 0){
            real = 1;
            imaginary = 0;
            calcMagnitude();

            return this;
        }

        ComplexMutable base = this;

        for(int i = 1; i < power; i++){
            this.times(base);
        }

        return this;
    }
}
