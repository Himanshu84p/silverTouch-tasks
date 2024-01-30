package GeometricAlgorithms;

class Rectangle {
    int x1, y1, x2, y2;

    public Rectangle(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
}

public class question2 {

    public static boolean doRectanglesOverlap(Rectangle rect1, Rectangle rect2) {
        
        boolean xOverlap = (rect1.x1 < rect2.x2) && (rect1.x2 > rect2.x1);

        
        boolean yOverlap = (rect1.y1 < rect2.y2) && (rect1.y2 > rect2.y1);

        
        return xOverlap && yOverlap;
    }

    public static void main(String[] args) {
        Rectangle rect1 = new Rectangle(0, 0, 5, 5);
        Rectangle rect2 = new Rectangle(3, 3, 8, 8);

        boolean overlap = doRectanglesOverlap(rect1, rect2);

        if (overlap) {
            System.out.println("Rectangles overlap.");
        } else {
            System.out.println("Rectangles do not overlap.");
        }
    }
}

