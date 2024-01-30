package Shape_Prac65;

public class Prac65 {
    public static void main(String[] args) {
        Drawing drawing = new Drawing();

        drawing.addShape(new Circle());
        drawing.addShape(new Rectangle());
        drawing.addShape(new Triangle());

        drawing.drawShapes();
    }
}
