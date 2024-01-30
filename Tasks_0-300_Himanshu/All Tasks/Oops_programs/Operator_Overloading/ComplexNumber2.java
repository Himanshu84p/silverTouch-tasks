package Oops_programs.Operator_Overloading;

public class ComplexNumber2 {
    public double real;
    public double imaginary;

    public ComplexNumber2(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public void display() {
        System.out.println(real + " + " + imaginary + "i");
    }

    public ComplexNumber2 add(ComplexNumber2 num2) {
        double resultReal = this.real + num2.real;
        double resultImaginary = this.imaginary + num2.imaginary;
        return new ComplexNumber2(resultReal, resultImaginary);
    }

    public static void main(String[] args) {
        ComplexNumber2 num1 = new ComplexNumber2(4, 5);
        ComplexNumber2 num2 = new ComplexNumber2(2, 7);
        ComplexNumber2 result = num1.add(num2);

        System.out.println("Sum is :  ");
        result.display();
    }
}
