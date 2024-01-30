interface Color {
    void fill();
}

class Red implements Color {
    @Override
    public void fill() {
        System.out.println("Filling with red color");
    }
}

class Blue implements Color {
    @Override
    public void fill() {
        System.out.println("Filling with blue color");
    }
}

abstract class Shape {
    protected Color color;

    public Shape(Color color) {
        this.color = color;
    }

    abstract void draw();
}

class Circle extends Shape {
    public Circle(Color color) {
        super(color);
    }

    @Override
    void draw() {
        System.out.print("Drawing Circle. ");
        color.fill();
    }
}

class Square extends Shape {
    public Square(Color color) {
        super(color);
    }

    @Override
    void draw() {
        System.out.print("Drawing Square. ");
        color.fill();
    }
}

public class Main {
    public static void main(String[] args) {

        Color red = new Red();
        Color blue = new Blue();

        Shape redCircle = new Circle(red);
        Shape blueSquare = new Square(blue);

        redCircle.draw();
        blueSquare.draw();
    }
}
