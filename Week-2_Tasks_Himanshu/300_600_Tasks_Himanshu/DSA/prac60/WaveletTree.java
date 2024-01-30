package prac60;

import java.util.Arrays;

public class WaveletTree {
    private int[] arr;
    private int[][] tree;
    private int alphabetSize = 2;

    public WaveletTree(int[] array) {
        this.arr = array.clone();
        Arrays.sort(this.arr);
        int n = this.arr.length;
        int treeHeight = (int) Math.ceil(Math.log(n) / Math.log(2)) + 1;
        tree = new int[2 * treeHeight - 1][n];
        build(0, 0, n - 1, 0);
    }

    private void build(int node, int left, int right, int depth) {
        if (left == right) {
            return;
        }

        int mid = (left + right) / 2;
        int lowerCount = mid - left + 1;
        tree[node][left] = 0;

        for (int i = left; i <= mid; i++) {
            if (arr[i] == 0) {
                lowerCount--;
            }
        }

        int leftChild = 2 * node + 1;
        int rightChild = 2 * node + 2;

        for (int i = left; i <= right; i++) {
            if (arr[i] == 0) {
                tree[leftChild][i] = lowerCount--;
            } else {
                tree[rightChild][i] = mid - left + 1 - (i - left + 1 - tree[leftChild][i]);
            }
        }

        build(leftChild, left, mid, depth + 1);
        build(rightChild, mid + 1, right, depth + 1);
    }

    public int rangeCount(int start, int end) {
        return rangeCount(0, 0, arr.length - 1, start, end);
    }

    private int rangeCount(int node, int left, int right, int start, int end) {
        if (left == right) {
            return (arr[left] == 1) ? 1 : 0;
        }

        int mid = (left + right) / 2;
        int leftChild = 2 * node + 1;
        int rightChild = 2 * node + 2;

        if (end <= mid) {
            return rangeCount(leftChild, left, mid, start, end);
        } else if (start > mid) {
            return rangeCount(rightChild, mid + 1, right, start, end);
        }

        return rangeCount(leftChild, left, mid, start, mid) + rangeCount(rightChild, mid + 1, right, mid + 1, end);
    }

    public static void main(String[] args) {
        int[] array = { 1, 0, 1, 1, 0, 0, 1, 0 };
        WaveletTree waveletTree = new WaveletTree(array);

        int start = 2;
        int end = 6;
        int countOnes = waveletTree.rangeCount(start, end);
        System.out.println("Number of 1s in range [" + start + ", " + end + "]: " + countOnes);
    }
}
