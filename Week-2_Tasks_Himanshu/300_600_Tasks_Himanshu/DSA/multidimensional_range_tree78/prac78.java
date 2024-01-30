package multidimensional_range_tree78;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class prac78 {
    private static String arrayToString(int[] arr) {
        StringBuilder result = new StringBuilder();
        for (int value : arr) {
            result.append(value).append(", ");
        }
        return result.substring(0, result.length() - 2);
    }

    public static void main(String[] args) {
        List<Point> points = new ArrayList<>();
        points.add(new Point(2, 3));
        points.add(new Point(5, 4));
        points.add(new Point(9, 6));
        points.add(new Point(4, 7));
        points.add(new Point(8, 1));
        points.add(new Point(7, 2));

        KDTree kdTree = new KDTree();
        kdTree.build(points);

        Point target = new Point(6, 3);
        int radius = 3;

        List<Point> result = kdTree.searchRange(target, radius);

        System.out.println("Points in Range:");
        for (Point p : result) {
            System.out.println("(" + arrayToString(p.coordinates) + ")");
        }
    }
}

class Point {
    int[] coordinates;

    public Point(int... coordinates) {
        this.coordinates = coordinates.clone();
    }
}

class KDNode {
    Point point;
    KDNode left, right;

    public KDNode(Point point) {
        this.point = point;
        this.left = null;
        this.right = null;
    }
}

class KDTree {
    private KDNode root;

    public void build(List<Point> points) {
        root = buildKDTree(points, 0, points.size() - 1, 0);
    }

    private KDNode buildKDTree(List<Point> points, int start, int end, int depth) {
        if (start > end) {
            return null;
        }

        int k = points.get(0).coordinates.length;
        int axis = depth % k;

        points.sort(Comparator.comparingInt(p -> p.coordinates[axis]));

        int mid = (start + end) / 2;
        KDNode node = new KDNode(points.get(mid));

        node.left = buildKDTree(points, start, mid - 1, depth + 1);
        node.right = buildKDTree(points, mid + 1, end, depth + 1);

        return node;
    }

    public List<Point> searchRange(Point target, int radius) {
        List<Point> result = new ArrayList<>();
        searchRange(root, target, radius, 0, result);
        return result;
    }

    private void searchRange(KDNode node, Point target, int radius, int depth, List<Point> result) {
        if (node == null) {
            return;
        }

        int k = target.coordinates.length;
        int axis = depth % k;

        int distance = squaredDistance(target, node.point);
        if (distance <= radius * radius) {
            result.add(node.point);
        }

        if (target.coordinates[axis] - radius <= node.point.coordinates[axis]) {
            searchRange(node.left, target, radius, depth + 1, result);
        }
        if (target.coordinates[axis] + radius >= node.point.coordinates[axis]) {
            searchRange(node.right, target, radius, depth + 1, result);
        }
    }

    private int squaredDistance(Point p1, Point p2) {
        int distance = 0;
        for (int i = 0; i < p1.coordinates.length; i++) {
            distance += (p1.coordinates[i] - p2.coordinates[i]) * (p1.coordinates[i] - p2.coordinates[i]);
        }
        return distance;
    }

}
