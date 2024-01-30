package Oops_programs.Operator_Overloading;

public class ComplexNumber {
    public double real;
    public double imaginary;

    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public ComplexNumber add(double real, double imaginary) {
        double resultReal = this.real + real;
        double resultImaginary = this.imaginary + imaginary;
        return new ComplexNumber(resultReal, resultImaginary);
    }

    public static void main(String[] args) {

    }
}
