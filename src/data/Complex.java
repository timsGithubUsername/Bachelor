package data;

public interface Complex {
    double getImaginary();
    double getReal();
    double getMagnitude();
    double getPhase();

    Complex add(Complex number);
    Complex substract(Complex number);
    Complex add(double number);
    Complex substract(double number);
    Complex times(Complex number);
    Complex times(double number);

    Complex pow(int power);
    Complex getConjugate();

    static Complex ComplexPolar(double magnitude, double phase) {
        return new ComplexImpl(magnitude*Math.cos(phase), magnitude*Math.sin(phase));
    }
}
