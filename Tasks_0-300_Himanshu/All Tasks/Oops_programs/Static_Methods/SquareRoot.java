package Oops_programs.Static_Methods;

public class SquareRoot {
    public static int number;

    public static void squareRoot() {
        double result = Math.sqrt(number);
        System.out.println("Square root is : " + result);
    }

    public static void main(String[] args) {
        SquareRoot.number = 9;
        SquareRoot.squareRoot();

    }
}
