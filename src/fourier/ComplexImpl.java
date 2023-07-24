package fourier;

public class ComplexImpl implements Complex{
    private double imaginary, real, magnitude;

    public ComplexImpl(double real, double imaginary){
        this.imaginary = imaginary;
        this.real = real;
        calcMagnitude();
    }
    public ComplexImpl(){
        this.imaginary = 0;
        this.real = 0;
        this.magnitude = 0;
    }

    private void calcMagnitude() {
        magnitude = Math.sqrt(Math.pow(imaginary, 2) + Math.pow(real, 2));
    }

    @Override
    public double getImaginary() {
        return imaginary;
    }

    @Override
    public double getReal() {
        return real;
    }

    @Override
    public double getMagnitude() {
        return magnitude;
    }

    @Override
    public Complex add(Complex number) {
        real += number.getReal();
        imaginary += number.getImaginary();
        calcMagnitude();

        return new ComplexImpl(real + number.getReal(), imaginary + number.getImaginary());
    }
    public Complex addMutable(Complex number) {
        real += number.getReal();
        imaginary += number.getImaginary();
        calcMagnitude();

        return this;
    }

    @Override
    public Complex substract(Complex number) {
        return new ComplexImpl(real - number.getReal(), imaginary - number.getImaginary());
    }
    public Complex substractMutable(Complex number) {
        real -= number.getReal();
        imaginary -= number.getImaginary();
        calcMagnitude();

        return this;
    }

    @Override
    public Complex add(double number) {
        return new ComplexImpl(real + number, imaginary);
    }

    @Override
    public Complex substract(double number) {
        return new ComplexImpl(real - number, imaginary);
    }

    @Override
    public Complex times(Complex number) {
        return new ComplexImpl(real * number.getReal() - imaginary * number.getImaginary(),
                imaginary * number.getReal() + real * number.getImaginary());
    }
    public Complex timesMutable(Complex number) {
        real = real * number.getReal() - imaginary * number.getImaginary();
        imaginary = imaginary * number.getReal() + real * number.getImaginary();
        calcMagnitude();

        return this;
    }

    @Override
    public Complex times(double number) {
        return new ComplexImpl(real * number,imaginary * number);
    }

    @Override
    public Complex pow(int power) {
        if(power == 0) return new ComplexImpl(1, 0) {
        };

        Complex output = new ComplexImpl(real, imaginary);

        for(int i = 1; i < power; i++){
            output.timesMutable(output);
        }

        return output;
    }
}
