
interface Prototype {
    Prototype clone();

    void displayInfo();
}

class Circle implements Prototype {
    private int radius;

    public Circle(int radius) {
        this.radius = radius;
    }

    @Override
    public Prototype clone() {
        return new Circle(this.radius);
    }

    @Override
    public void displayInfo() {
        System.out.println("Circle with radius: " + radius);
    }
}

class Rectangle implements Prototype {
    private int width;
    private int height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public Prototype clone() {
        return new Rectangle(this.width, this.height);
    }

    @Override
    public void displayInfo() {
        System.out.println("Rectangle with width: " + width + " and height: " + height);
    }
}

public class Main {
    public static void main(String[] args) {

        Prototype circlePrototype = new Circle(5);
        Prototype rectanglePrototype = new Rectangle(10, 8);

        Prototype clonedCircle = circlePrototype.clone();
        Prototype clonedRectangle = rectanglePrototype.clone();

        System.out.println("Original Circle:");
        circlePrototype.displayInfo();

        System.out.println("Cloned Circle:");
        clonedCircle.displayInfo();

        System.out.println("Original Rectangle:");
        rectanglePrototype.displayInfo();

        System.out.println("Cloned Rectangle:");
        clonedRectangle.displayInfo();
    }
}
