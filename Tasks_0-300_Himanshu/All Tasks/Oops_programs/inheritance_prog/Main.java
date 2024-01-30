package Oops_programs.inheritance_prog;

class Shape {
    public double calculateArea() {
        return 0.0;
    }

    public double calculatePerimeter() {
        return 0.0;
    }
}

class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }
}

class Rectangle extends Shape {
    private double length;
    private double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public double calculateArea() {

        return length * width;
    }

    @Override
    public double calculatePerimeter() {

        return 2 * (length + width);
    }
}

public class Main {
    public static void main(String[] args) {
        Circle circle1 = new Circle(4);
        Rectangle rec1 = new Rectangle(5, 8);

        System.out.println(
                "Circle area is " + circle1.calculateArea() + " and perimeter is " + circle1.calculatePerimeter());
        System.out
                .println(
                        "Rectangle area is " + rec1.calculateArea() + " and perimeter is " + rec1.calculatePerimeter());
    }
}
