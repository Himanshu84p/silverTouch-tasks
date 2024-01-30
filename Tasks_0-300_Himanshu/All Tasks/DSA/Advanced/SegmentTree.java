public class SegmentTree {
    private int[] tree;
    private int[] nums;

    public SegmentTree(int[] nums) {
        this.nums = nums;
        int n = nums.length;
        int height = (int) Math.ceil(Math.log(n) / Math.log(2));
        int maxSize = 2 * (int) Math.pow(2, height) - 1;
        tree = new int[maxSize];
        buildSegmentTree(0, 0, n - 1);
    }

    private void buildSegmentTree(int treeIndex, int start, int end) {
        if (start == end) {
            tree[treeIndex] = nums[start];
        } else {
            int mid = start + (end - start) / 2;
            int leftChild = 2 * treeIndex + 1;
            int rightChild = 2 * treeIndex + 2;

            buildSegmentTree(leftChild, start, mid);
            buildSegmentTree(rightChild, mid + 1, end);

            tree[treeIndex] = Math.min(tree[leftChild], tree[rightChild]);
        }
    }

    public int rangeMinimumQuery(int queryStart, int queryEnd) {
        return rangeMinimumQuery(0, 0, nums.length - 1, queryStart, queryEnd);
    }

    private int rangeMinimumQuery(int treeIndex, int start, int end, int queryStart, int queryEnd) {
        if (queryStart <= start && queryEnd >= end) {
            return tree[treeIndex];
        }

        if (queryEnd < start || queryStart > end) {
            return Integer.MAX_VALUE;
        }

        int mid = start + (end - start) / 2;
        int leftChild = 2 * treeIndex + 1;
        int rightChild = 2 * treeIndex + 2;

        int leftMin = rangeMinimumQuery(leftChild, start, mid, queryStart, queryEnd);
        int rightMin = rangeMinimumQuery(rightChild, mid + 1, end, queryStart, queryEnd);

        return Math.min(leftMin, rightMin);
    }

    public static void main(String[] args) {
        int[] nums = { 1, 3, 2, 7, 9, 11 };
        SegmentTree segmentTree = new SegmentTree(nums);

        System.out.println("Minimum element in range [1, 4]: " + segmentTree.rangeMinimumQuery(1, 4));
    }
}
