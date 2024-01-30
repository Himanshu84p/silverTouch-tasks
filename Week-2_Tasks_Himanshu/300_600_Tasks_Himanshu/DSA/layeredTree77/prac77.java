package layeredTree77;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class prac77 {
    public static void main(String[] args) {
        Point[] points = {
                new Point(2, 3),
                new Point(4, 2),
                new Point(7, 5),
                new Point(8, 10),
                new Point(10, 12),
                new Point(12, 15)
        };

        LayeredRangeTree tree = new LayeredRangeTree();
        tree.build(points);

        List<Point> queryResult = tree.queryRange(5, 11, 3, 14);

        System.out.println("Query Result:");
        for (Point p : queryResult) {
            System.out.println("(" + p.x + ", " + p.y + ")");
        }
    }
}

class Point implements Comparable<Point> {
    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Point o) {
        return Integer.compare(x, o.x);
    }
}

class LayeredRangeTree {
    private Node root;

    static class Node {
        Point point;
        Node left, right;

        public Node(Point point) {
            this.point = point;
        }
    }

    public void build(Point[] points) {
        List<Point> sortedPoints = new ArrayList<>();
        Collections.addAll(sortedPoints, points);
        Collections.sort(sortedPoints);

        root = buildLayeredRangeTree(sortedPoints, 0, sortedPoints.size() - 1);
    }

    private Node buildLayeredRangeTree(List<Point> sortedPoints, int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = (start + end) / 2;
        Node node = new Node(sortedPoints.get(mid));
        node.left = buildLayeredRangeTree(sortedPoints, start, mid - 1);
        node.right = buildLayeredRangeTree(sortedPoints, mid + 1, end);

        return node;
    }

    public List<Point> queryRange(int x1, int x2, int y1, int y2) {
        List<Point> result = new ArrayList<>();
        queryRange(root, x1, x2, y1, y2, result);
        return result;
    }

    private void queryRange(Node node, int x1, int x2, int y1, int y2, List<Point> result) {
        if (node == null) {
            return;
        }

        if (node.point.x >= x1 && node.point.x <= x2 && node.point.y >= y1 && node.point.y <= y2) {
            result.add(node.point);
        }

        if (node.point.x >= x1) {
            queryRange(node.left, x1, x2, y1, y2, result);
        }

        if (node.point.x <= x2) {
            queryRange(node.right, x1, x2, y1, y2, result);
        }
    }

}
