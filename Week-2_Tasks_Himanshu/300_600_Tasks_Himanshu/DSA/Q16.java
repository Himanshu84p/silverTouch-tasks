class Node {
    int[] point;
    Node left, right;

    public Node(int[] point) {
        this.point = point;
        this.left = null;
        this.right = null;
    }
}

public class Q16 {
    private Node root;
    private int k;

    public Q16(int k) {
        this.root = null;
        this.k = k;
    }

    public void insert(int[] point) {
        root = insert(root, point, 0);
    }

    private Node insert(Node node, int[] point, int depth) {
        if (node == null) {
            return new Node(point);
        }

        int currentDimension = depth % k;

        if (point[currentDimension] < node.point[currentDimension]) {
            node.left = insert(node.left, point, depth + 1);
        } else {
            node.right = insert(node.right, point, depth + 1);
        }

        return node;
    }

    public int[] findNearestNeighbor(int[] target) {
        Node nearest = findNearestNeighbor(root, target, 0, null);

        return nearest != null ? nearest.point : null;
    }

    private Node findNearestNeighbor(Node node, int[] target, int depth, Node best) {
        if (node == null) {
            return best;
        }

        double currentDistance = distance(node.point, target);
        double bestDistance = (best != null) ? distance(best.point, target) : Double.MAX_VALUE;

        if (currentDistance < bestDistance) {
            best = node;
        }

        int currentDimension = depth % k;

        if (target[currentDimension] < node.point[currentDimension]) {
            best = findNearestNeighbor(node.left, target, depth + 1, best);
            if (target[currentDimension] + bestDistance >= node.point[currentDimension]) {
                best = findNearestNeighbor(node.right, target, depth + 1, best);
            }
        } else {
            best = findNearestNeighbor(node.right, target, depth + 1, best);
            if (target[currentDimension] - bestDistance <= node.point[currentDimension]) {
                best = findNearestNeighbor(node.left, target, depth + 1, best);
            }
        }

        return best;
    }

    private double distance(int[] point1, int[] point2) {
        double distanceSquared = 0;
        for (int i = 0; i < k; i++) {
            distanceSquared += Math.pow(point1[i] - point2[i], 2);
        }
        return Math.sqrt(distanceSquared);
    }

    public static void main(String[] args) {
        Q16 kdTree = new Q16(2);

        int[] point1 = { 2, 3 };
        int[] point2 = { 5, 4 };
        int[] point3 = { 9, 6 };
        int[] target = { 7, 5 };

        kdTree.insert(point1);
        kdTree.insert(point2);
        kdTree.insert(point3);

        int[] nearestNeighbor = kdTree.findNearestNeighbor(target);

        System.out.println("Nearest Neighbor to " + java.util.Arrays.toString(target) +
                ": " + java.util.Arrays.toString(nearestNeighbor));
    }
}
