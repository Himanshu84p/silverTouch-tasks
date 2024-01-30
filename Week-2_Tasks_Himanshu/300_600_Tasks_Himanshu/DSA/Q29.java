class SegmentTree {
    private int[] segmentTree;
    private int[] lazy;
    private int size;

    public SegmentTree(int[] arr) {
        size = arr.length;
        segmentTree = new int[4 * size];
        lazy = new int[4 * size];
        buildSegmentTree(arr, 0, 0, size - 1);
    }

    private void buildSegmentTree(int[] arr, int index, int left, int right) {
        if (left == right) {
            segmentTree[index] = arr[left];
            return;
        }

        int mid = left + (right - left) / 2;
        buildSegmentTree(arr, 2 * index + 1, left, mid);
        buildSegmentTree(arr, 2 * index + 2, mid + 1, right);
        segmentTree[index] = Math.min(segmentTree[2 * index + 1], segmentTree[2 * index + 2]);
    }

    public int rangeQuery(int queryLeft, int queryRight) {
        return rangeQuery(0, 0, size - 1, queryLeft, queryRight);
    }

    private int rangeQuery(int index, int segmentLeft, int segmentRight, int queryLeft, int queryRight) {
        if (lazy[index] != 0) {
            propagateLazy(index, segmentLeft, segmentRight);
        }

        if (segmentLeft > queryRight || segmentRight < queryLeft) {
            return Integer.MAX_VALUE;
        }

        if (queryLeft <= segmentLeft && queryRight >= segmentRight) {
            return segmentTree[index];
        }

        int mid = segmentLeft + (segmentRight - segmentLeft) / 2;
        int leftChild = rangeQuery(2 * index + 1, segmentLeft, mid, queryLeft, queryRight);
        int rightChild = rangeQuery(2 * index + 2, mid + 1, segmentRight, queryLeft, queryRight);

        return Math.min(leftChild, rightChild);
    }

    public void rangeUpdate(int updateLeft, int updateRight, int value) {
        rangeUpdate(0, 0, size - 1, updateLeft, updateRight, value);
    }

    private void rangeUpdate(int index, int segmentLeft, int segmentRight, int updateLeft, int updateRight, int value) {
        if (lazy[index] != 0) {
            propagateLazy(index, segmentLeft, segmentRight);
        }

        if (updateLeft > segmentRight || updateRight < segmentLeft) {
            return;
        }

        if (updateLeft <= segmentLeft && updateRight >= segmentRight) {

            segmentTree[index] += value;
            if (segmentLeft != segmentRight) {
                lazy[2 * index + 1] += value;
                lazy[2 * index + 2] += value;
            }
            return;
        }

        int mid = segmentLeft + (segmentRight - segmentLeft) / 2;
        rangeUpdate(2 * index + 1, segmentLeft, mid, updateLeft, updateRight, value);
        rangeUpdate(2 * index + 2, mid + 1, segmentRight, updateLeft, updateRight, value);
        segmentTree[index] = Math.min(segmentTree[2 * index + 1], segmentTree[2 * index + 2]);
    }

    private void propagateLazy(int index, int segmentLeft, int segmentRight) {
        segmentTree[index] += lazy[index];

        if (segmentLeft != segmentRight) {
            lazy[2 * index + 1] += lazy[index];
            lazy[2 * index + 2] += lazy[index];
        }

        lazy[index] = 0;
    }
}

public class Q29 {

    public static void main(String[] args) {
        int[] arr = { 1, 3, 2, -2, 4, 5 };
        SegmentTree segmentTree = new SegmentTree(arr);

        System.out.println("Range Query [1, 4]: " + segmentTree.rangeQuery(1, 4));

        segmentTree.rangeUpdate(1, 3, 2);

        System.out.println("Range Query [1, 4] after Update: " + segmentTree.rangeQuery(1, 4));
    }
}
