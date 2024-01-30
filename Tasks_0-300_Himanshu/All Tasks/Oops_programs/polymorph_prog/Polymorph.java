package Oops_programs.polymorph_prog;

interface Shape {
    double calculateArea();
}

class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

class Rectangle implements Shape {
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
}

public class Polymorph {
    public static void main(String[] args) {
        Circle cir1 = new Circle(4);
        Rectangle rec1 = new Rectangle(5, 5);

        System.out.println("Area of circle is : " + cir1.calculateArea());
        System.out.println("Area of rectangle is : " + rec1.calculateArea());
    }
}
