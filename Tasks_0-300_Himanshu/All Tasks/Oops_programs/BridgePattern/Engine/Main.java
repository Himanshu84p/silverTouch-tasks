package Engine;

interface DrawingAPI {
    public void DrawCircle(int rad);

    public void DrawRectangle(int side);
}

abstract class Shape {
    protected DrawingAPI drawingAPI;

    Shape(DrawingAPI drawingAPI) {
        this.drawingAPI = drawingAPI;
    }

    abstract void draw();
}

class DrawingAPI1 implements DrawingAPI {

    @Override
    public void DrawCircle(int rad) {
        System.out.println("API1 - Drawing Circle with Radius :" + rad);
    }

    @Override
    public void DrawRectangle(int Side) {
        System.out.println("API1 - Drawing Rectangle with Side :" + Side);
    }
}

class DrawingAPI2 implements DrawingAPI {
    @Override
    public void DrawCircle(int rad) {
        System.out.println("API2 - Drawing Circle with Radius :" + rad);
    }

    @Override
    public void DrawRectangle(int Side) {
        System.out.println("API2 - Drawing Rectangle with Side :" + Side);
    }
}

class Circle extends Shape {
    private int rad;

    public Circle(DrawingAPI drawingapi, int rad) {
        super(drawingapi);
        this.rad = rad;
    }

    @Override
    public void draw() {
        drawingAPI.DrawCircle(rad);
    }
}

class Rectangle extends Shape {
    private int side;

    public Rectangle(DrawingAPI drawingapi, int side) {
        super(drawingapi);
        this.side = side;
    }

    @Override
    public void draw() {
        drawingAPI.DrawRectangle(side);
    }
}

public class Main {
    public static void main(String[] args) {
        DrawingAPI API1 = new DrawingAPI1();
        DrawingAPI API2 = new DrawingAPI2();

        Shape circle = new Circle(API1, 5);
        Shape Rectangle = new Rectangle(API2, 15);

        circle.draw();
        Rectangle.draw();
    }
}