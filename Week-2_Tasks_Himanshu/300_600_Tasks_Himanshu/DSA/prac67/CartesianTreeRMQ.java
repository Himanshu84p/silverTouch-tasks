package prac67;

import java.util.Stack;

class CartesianTreeNode {
    int key;
    int index;
    CartesianTreeNode left, right;

    public CartesianTreeNode(int key, int index) {
        this.key = key;
        this.index = index;
        this.left = null;
        this.right = null;
    }
}

public class CartesianTreeRMQ {
    public static int[] findRMQ(int[] arr, int[][] queries) {
        int n = arr.length;
        CartesianTreeNode root = buildCartesianTree(arr);

        int[] result = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int start = queries[i][0];
            int end = queries[i][1];
            result[i] = rangeMinimumQuery(root, start, end).index;
        }

        return result;
    }

    private static CartesianTreeNode buildCartesianTree(int[] arr) {
        Stack<CartesianTreeNode> stack = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
            CartesianTreeNode node = new CartesianTreeNode(arr[i], i);

            while (!stack.isEmpty() && stack.peek().key > arr[i]) {
                node.left = stack.pop();
            }

            if (!stack.isEmpty()) {
                stack.peek().right = node;
            }

            stack.push(node);
        }

        while (stack.size() > 1) {
            stack.pop();
        }

        return stack.pop();
    }

    private static CartesianTreeNode rangeMinimumQuery(CartesianTreeNode root, int start, int end) {
        CartesianTreeNode result = null;

        if (root != null) {
            if (start <= root.index && root.index <= end) {
                result = root;
            }

            if (root.index > start) {
                CartesianTreeNode leftResult = rangeMinimumQuery(root.left, start, end);
                if (result == null || (leftResult != null && leftResult.key < result.key)) {
                    result = leftResult;
                }
            }

            if (root.index < end) {
                CartesianTreeNode rightResult = rangeMinimumQuery(root.right, start, end);
                if (result == null || (rightResult != null && rightResult.key < result.key)) {
                    result = rightResult;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] array = { 3, 1, 4, 2, 5, 6 };
        int[][] queries = { { 1, 3 }, { 2, 5 }, { 0, 5 } };

        int[] rmqResult = findRMQ(array, queries);

        System.out.println("RMQ Results:");
        for (int i = 0; i < rmqResult.length; i++) {
            System.out.println("Query " + (i + 1) + ": " + rmqResult[i]);
        }
    }
}
