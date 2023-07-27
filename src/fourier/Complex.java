package fourier;

public interface Complex {
    double getImaginary();
    double getReal();
    double getMagnitude();

    Complex add(Complex number);
    Complex substract(Complex number);
    Complex add(double number);
    Complex substract(double number);
    Complex times(Complex number);
    Complex times(double number);

    Complex pow(int power);
}
