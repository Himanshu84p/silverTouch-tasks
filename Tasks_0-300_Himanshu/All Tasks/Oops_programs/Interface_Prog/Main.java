interface Drawable {
    void draw();
}

class Circle implements Drawable {
    @Override
    public void draw() {
        System.out.println("Circle class output!!");
    }
}

class Rectangle implements Drawable {
    @Override
    public void draw() {
        System.out.println("Rectangle class output!!!");
    }
}

public class Main {
    public static void main(String[] args) {
        Circle circle1 = new Circle();
        Rectangle rec1 = new Rectangle();

        circle1.draw();
        rec1.draw();

    }
}