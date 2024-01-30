public class SegmentTreeWithLazyPropagation {
    private int[] tree;
    private int[] lazy;
    private int[] nums;

    public SegmentTreeWithLazyPropagation(int[] nums) {
        this.nums = nums;
        int n = nums.length;
        int height = (int) Math.ceil(Math.log(n) / Math.log(2));
        int maxSize = 2 * (int) Math.pow(2, height) - 1;
        tree = new int[maxSize];
        lazy = new int[maxSize];
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

            tree[treeIndex] = tree[leftChild] + tree[rightChild];
        }
    }

    public void updateRange(int updateStart, int updateEnd, int delta) {
        updateRange(0, 0, nums.length - 1, updateStart, updateEnd, delta);
    }

    private void updateRange(int treeIndex, int start, int end, int updateStart, int updateEnd, int delta) {
        if (lazy[treeIndex] != 0) {
            tree[treeIndex] += (end - start + 1) * lazy[treeIndex];

            if (start != end) {
                lazy[2 * treeIndex + 1] += lazy[treeIndex];
                lazy[2 * treeIndex + 2] += lazy[treeIndex];
            }

            lazy[treeIndex] = 0;
        }

        if (updateStart > end || updateEnd < start) {
            return;
        }

        if (updateStart <= start && updateEnd >= end) {
            tree[treeIndex] += (end - start + 1) * delta;

            if (start != end) {
                lazy[2 * treeIndex + 1] += delta;
                lazy[2 * treeIndex + 2] += delta;
            }

            return;
        }

        int mid = start + (end - start) / 2;
        int leftChild = 2 * treeIndex + 1;
        int rightChild = 2 * treeIndex + 2;

        updateRange(leftChild, start, mid, updateStart, updateEnd, delta);
        updateRange(rightChild, mid + 1, end, updateStart, updateEnd, delta);

        tree[treeIndex] = tree[leftChild] + tree[rightChild];
    }

    public int queryRangeSum(int queryStart, int queryEnd) {
        return queryRangeSum(0, 0, nums.length - 1, queryStart, queryEnd);
    }

    private int queryRangeSum(int treeIndex, int start, int end, int queryStart, int queryEnd) {
        if (lazy[treeIndex] != 0) {
            tree[treeIndex] += (end - start + 1) * lazy[treeIndex];

            if (start != end) {
                lazy[2 * treeIndex + 1] += lazy[treeIndex];
                lazy[2 * treeIndex + 2] += lazy[treeIndex];
            }

            lazy[treeIndex] = 0;
        }

        if (queryStart > end || queryEnd < start) {
            return 0;
        }

        if (queryStart <= start && queryEnd >= end) {
            return tree[treeIndex];
        }

        int mid = start + (end - start) / 2;
        int leftChild = 2 * treeIndex + 1;
        int rightChild = 2 * treeIndex + 2;

        int leftSum = queryRangeSum(leftChild, start, mid, queryStart, queryEnd);
        int rightSum = queryRangeSum(rightChild, mid + 1, end, queryStart, queryEnd);

        return leftSum + rightSum;
    }

    public static void main(String[] args) {
        int[] nums = { 1, 3, 2, 7, 9, 11 };
        SegmentTreeWithLazyPropagation segmentTree = new SegmentTreeWithLazyPropagation(nums);

        System.out.println("Sum of elements in range [1, 4]: " + segmentTree.queryRangeSum(1, 4));

        segmentTree.updateRange(1, 4, 2);

        System.out.println("Sum of elements in range [1, 4] after update: " + segmentTree.queryRangeSum(1, 4));
    }
}
