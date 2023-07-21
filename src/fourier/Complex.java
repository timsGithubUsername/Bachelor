package fourier;

public class Complex {
    private double imaginary, real, magnitude;

    public Complex(double real, double imaginary){
        this.imaginary = imaginary;
        this.real = real;
        this.magnitude = Math.sqrt(Math.pow(imaginary, 2) + Math.pow(real, 2));
    }
     public double getImaginary(){
        return imaginary;
     }
     public double getReal(){
        return real;
     }
     public double getMagnitude(){
        return magnitude;
     }

     public Complex add(Complex number){
        return new Complex(real + number.getReal(), imaginary + number.getImaginary());
     }
     public Complex add(double number){
        return new Complex(real+number, imaginary);
     }
     public Complex times(Complex number){
         //(ar+ai)(br+bi) = ar*br + ai*br + ar*bi + ai*bi
        return new Complex(real * number.getReal() - imaginary * number.getImaginary(),
                imaginary * number.getReal() + real * number.imaginary);
     }
     public Complex times(double number){
        return new Complex(real * number, imaginary * number);
     }
}
