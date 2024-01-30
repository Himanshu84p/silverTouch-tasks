
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

class Point2 {
    double x, y;

    public Point2(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

class QuadtreeNode2 {
    private static final int CAPACITY = 4;

    private List<Point> points;
    private Rectangle bounds;
    private QuadtreeNode2[] children;

    public QuadtreeNode2(Rectangle bounds) {
        this.points = new ArrayList<>();
        this.bounds = bounds;
        this.children = new QuadtreeNode2[4];
    }

    public void insert(Point point) {
        if (!bounds.contains(point.x, point.y)) {
            return;
        }

        if (points.size() < CAPACITY) {
            points.add(point);
        } else {
            if (children[0] == null) {
                split();
            }

            for (int i = 0; i < 4; i++) {
                children[i].insert(point);
            }
        }
    }

    private void split() {
        int subWidth = bounds.width / 2;
        int subHeight = bounds.height / 2;
        int x = bounds.x;
        int y = bounds.y;

        children[0] = new QuadtreeNode2(new Rectangle(x + subWidth, y, subWidth, subHeight));
        children[1] = new QuadtreeNode2(new Rectangle(x, y, subWidth, subHeight));
        children[2] = new QuadtreeNode2(new Rectangle(x, y + subHeight, subWidth, subHeight));
        children[3] = new QuadtreeNode2(new Rectangle(x + subWidth, y + subHeight, subWidth, subHeight));

        for (Point p : points) {
            for (int i = 0; i < 4; i++) {
                if (children[i].bounds.contains(p.x, p.y)) {
                    children[i].insert(p);
                    break;
                }
            }
        }

        points.clear();
    }

    public List<Point> queryRange(Rectangle range) {
        List<Point> result = new ArrayList<>();

        if (!bounds.intersects(range)) {
            return result;
        }

        for (Point p : points) {
            if (range.contains(p.x, p.y)) {
                result.add(p);
            }
        }

        if (children[0] != null) {
            for (int i = 0; i < 4; i++) {
                result.addAll(children[i].queryRange(range));
            }
        }

        return result;
    }
}

public class question2 {
    public static void main(String[] args) {
        question1 quadtree = new question1(new Rectangle(0, 0, 100, 100));

        quadtree.insert(new Point(10, 10));
        quadtree.insert(new Point(20, 20));
        quadtree.insert(new Point(30, 30));
        quadtree.insert(new Point(40, 40));

        Rectangle queryRange = new Rectangle(5, 5, 15, 15);
        List<Point> result = quadtree.queryRange(queryRange);

        System.out.println("Points in the query range:");
        for (Point point : result) {
            System.out.println("(" + point.x + ", " + point.y + ")");
        }
    }
}
