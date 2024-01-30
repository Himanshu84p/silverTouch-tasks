class Node {
    int[] point;
    Node left, right;

    public Node(int[] point) {
        this.point = point;
        this.left = null;
        this.right = null;
    }
}

public class Q15 {
    private Node root;
    private int k;

    public Q15(int k) {
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

    public boolean search(int[] target) {
        return search(root, target, 0);
    }

    private boolean search(Node node, int[] target, int depth) {
        if (node == null) {
            return false;
        }

        if (java.util.Arrays.equals(node.point, target)) {
            return true;
        }

        int currentDimension = depth % k;

        if (target[currentDimension] < node.point[currentDimension]) {
            return search(node.left, target, depth + 1);
        } else {
            return search(node.right, target, depth + 1);
        }
    }

    public static void main(String[] args) {
        Q15 kdTree = new Q15(2);

        int[] point1 = { 2, 3 };
        int[] point2 = { 5, 4 };
        int[] point3 = { 9, 6 };
        int[] target1 = { 5, 4 };
        int[] target2 = { 1, 2 };

        kdTree.insert(point1);
        kdTree.insert(point2);
        kdTree.insert(point3);

        System.out.println("Search for target1: " + kdTree.search(target1));
        System.out.println("Search for target2: " + kdTree.search(target2));
    }
}
