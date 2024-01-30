//Bridge

interface Color {
    public void fill(String color);
}

abstract class Shape {
    protected Color color;

    public Shape(Color color) {
        this.color = color;
    }

    public abstract void apply(String color);
}



//Implements Color
class Red implements Color {
    @Override
    public void fill(String Color){
        System.out.println("Color is : "+Color);
    }
}

class Blue implements Color {
   public void fill(String Color){
        System.out.println("Color is : "+Color);
    }
}


// Extends Shape 
class Rectangle extends Shape{
    public Rectangle(Color color){
        super(color);
    }

    public void apply(String rang){
        color.fill(rang);
    }
}

class Circle extends Shape{
public Circle(Color color){
        super(color);
    }

    public void apply(String rang){
        color.fill(rang);
    }

}

class Square extends Shape{
public Square(Color color){
        super(color);
    }

    public void apply(String rang){
        color.fill(rang);
    }
}

public class Prac3 {
    public static void main(String[] args) {
        Shape Rect = new Rectangle(new Red());
        System.out.print("Rectangle ");
        Rect.apply("Red");
        Shape Circle = new Circle(new Blue());
        System.out.print("Circle ");
        Circle.apply("Blue");
        Shape Square = new Square(new Red());
        System.out.print("Square ");
        Square.apply("Red");
    }
}
