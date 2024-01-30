import java.util.Arrays;

class PersistentSegmentTree {
    static class Node {
        int sum;
        Node left, right;

        public Node(int sum, Node left, Node right) {
            this.sum = sum;
            this.left = left;
            this.right = right;
        }

        public Node(int sum) {
            this(sum, null, null);
        }
    }

    private Node[] versions;
    private int[] sourceArray;

    public PersistentSegmentTree(int[] arr) {
        this.sourceArray = Arrays.copyOf(arr, arr.length);
        this.versions = new Node[arr.length * 4];
        build(0, arr.length - 1, 0);
    }

    private void build(int left, int right, int version) {
        if (left == right) {
            versions[version] = new Node(sourceArray[left]);
            return;
        }

        int mid = left + (right - left) / 2;
        build(left, mid, 2 * version + 1);
        build(mid + 1, right, 2 * version + 2);

        versions[version] = new Node(versions[2 * version + 1].sum + versions[2 * version + 2].sum);
    }

    public int query(int version, int queryLeft, int queryRight) {
        return query(0, sourceArray.length - 1, queryLeft, queryRight, versions[version]);
    }

    private int query(int left, int right, int queryLeft, int queryRight, Node node) {
        if (queryRight < left || queryLeft > right) {
            return 0;
        }

        if (queryLeft <= left && queryRight >= right) {
            return node.sum;
        }

        int mid = left + (right - left) / 2;
        int leftSum = query(left, mid, queryLeft, queryRight, node.left);
        int rightSum = query(mid + 1, right, queryLeft, queryRight, node.right);

        return leftSum + rightSum;
    }

    public int update(int version, int index, int newValue) {
        versions = Arrays.copyOf(versions, versions.length + 1);

        return update(0, sourceArray.length - 1, index, newValue, versions[version]);
    }

    private Node update(int left, int right, int index, int newValue, Node node) {
        if (index < left || index > right) {
            return node;
        }

        if (left == right) {
            return new Node(newValue);
        }

        int mid = left + (right - left) / 2;
        Node newLeft = update(left, mid, index, newValue, node.left);
        Node newRight = update(mid + 1, right, index, newValue, node.right);

        return new Node(newLeft.sum + newRight.sum, newLeft, newRight);
    }
}

public class Q31 {

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5 };
        PersistentSegmentTree persistentSegmentTree = new PersistentSegmentTree(arr);

        int version1 = 0;

        int version2 = persistentSegmentTree.update(version1, 2, 10);

        System.out.println("Query Version 1: " + persistentSegmentTree.query(version1, 1, 4));

        System.out.println("Query Version 2: " + persistentSegmentTree.query(version2, 1, 4));
    }
}
