package fourier;

public interface ComplexMutable {
    double getImaginary();
    void setImaginary(double imaginary);
    double getReal();
    void setReal(double real);
    double getMagnitude();

    ComplexMutable add(ComplexMutable number);
    ComplexMutable substract(ComplexMutable number);
    ComplexMutable add(double number);
    ComplexMutable substract(double number);
    ComplexMutable times(ComplexMutable number);
    ComplexMutable times(double number);

    ComplexMutable pow(int power);
}
