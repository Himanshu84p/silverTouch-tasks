package Oops_programs.Method_overloading;

public class Calculator {
    public int add(int a, int b) {
        return a + b;
    }

    public int add(int a, int b, int c) {
        return a + b + c;
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();

        int sumOfTwoNumbers = calc.add(12, 23);
        int sumOfThreeNumbers = calc.add(12, 23, 34);

        System.out.println("Sum of two number 12 and 23 is " + sumOfTwoNumbers);
        System.out.println("Sum of three number 12,23 and 34 is " + sumOfThreeNumbers);
    }
}
