package GeometricAlgorithms;


import java.util.*;

class Point {
    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class question1 {

    public List<Point> findConvexHull(Point[] points) {
        if (points.length < 3) {
            throw new IllegalArgumentException("Convex hull requires at least 3 points");
        }

        List<Point> convexHull = new ArrayList<>();

        
        Point startPoint = findStartPoint(points);

        
        Arrays.sort(points, (p1, p2) -> {
            double angle1 = Math.atan2(p1.y - startPoint.y, p1.x - startPoint.x);
            double angle2 = Math.atan2(p2.y - startPoint.y, p2.x - startPoint.x);
            if (angle1 < angle2) return -1;
            if (angle1 > angle2) return 1;
            return Double.compare(distanceSquared(startPoint, p1), distanceSquared(startPoint, p2));
        });

        Stack<Point> stack = new Stack<>();
        stack.push(startPoint);
        stack.push(points[1]);

        for (int i = 2; i < points.length; i++) {
            while (stack.size() > 1 && orientation(stack.get(stack.size() - 2), stack.peek(), points[i]) <= 0) {
                stack.pop();
            }
            stack.push(points[i]);
        }

        convexHull.addAll(stack);
        return convexHull;
    }

    private Point findStartPoint(Point[] points) {
        Point startPoint = points[0];
        for (int i = 1; i < points.length; i++) {
            if (points[i].y < startPoint.y || (points[i].y == startPoint.y && points[i].x < startPoint.x)) {
                startPoint = points[i];
            }
        }
        return startPoint;
    }

    private int orientation(Point p, Point q, Point r) {
        int val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);
        if (val == 0) return 0;  
        return (val > 0) ? 1 : -1; 
    }

    private double distanceSquared(Point p1, Point p2) {
        return Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2);
    }

    public static void main(String[] args) {
        Point[] points = {
                new Point(0, 3),
                new Point(1, 1),
                new Point(2, 2),
                new Point(4, 4),
                new Point(0, 0),
                new Point(1, 2),
                new Point(3, 1),
                new Point(3, 3)
        };

        question1 convexHull = new question1();
        List<Point> result = convexHull.findConvexHull(points);

        System.out.println("Convex Hull Points:");
        for (Point point : result) {
            System.out.println("(" + point.x + ", " + point.y + ")");
        }
    }
}
