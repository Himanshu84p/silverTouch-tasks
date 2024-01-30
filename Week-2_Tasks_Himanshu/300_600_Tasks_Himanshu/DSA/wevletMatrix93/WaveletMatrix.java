package wevletMatrix93;

import java.util.Arrays;



public class WaveletMatrix {

    private int[][] matrix;
    private int[] starts;

    public WaveletMatrix(int[] arr) {
        int n = arr.length;
        int maxVal = Arrays.stream(arr).max().orElse(0) + 1;

        matrix = new int[log2(maxVal) + 1][n];
        starts = new int[log2(maxVal) + 1];

        build(arr, 0, n - 1, 0, maxVal - 1, 0);
    }

    private void build(int[] arr, int left, int right, int low, int high, int level) {
        if (low == high || left > right) {
            return;
        }

        int mid = (low + high) / 2;
        starts[level] = mid;

        int count = 0;
        for (int i = left; i <= right; i++) {
            if (arr[i] <= mid) {
                count++;
            }
        }

        int leftIndex = left;
        int rightIndex = mid + 1;

        for (int i = left; i <= right; i++) {
            if (arr[i] <= mid) {
                matrix[level][i] = 1;
                leftIndex++;
            } else {
                matrix[level][i] = 0;
                rightIndex++;
            }
        }

        build(arr, left, leftIndex - 1, low, mid, level + 1);
        build(arr, leftIndex, right, mid + 1, high, level + 1);
    }

    public int rank(int num, int index) {
        int result = 0;

        for (int level = 0; level < matrix.length; level++) {
            if (num <= starts[level]) {
                result += matrix[level][index];
                index = index - matrix[level][index];
            }
        }

        return result;
    }

    public int select(int num, int k) {
        int left = 0;
        int right = matrix[0].length - 1;

        for (int level = 0; level < matrix.length; level++) {
            int mid = (left + right) / 2;
            int count = rank(num, mid);

            if (count < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    private int log2(int n) {
        return (int) (Math.log(n) / Math.log(2));
    }

    public static void main(String[] args) {
        int[] arr = {2, 6, 3, 7, 1, 4, 5};

        WaveletMatrix waveletMatrix = new WaveletMatrix(arr);

        int rankResult = waveletMatrix.rank(4, 5);
        System.out.println("Rank of 4 at index 5: " + rankResult);

        int selectResult = waveletMatrix.select(4, 2);
        System.out.println("Select of 4 with k=2: " + selectResult);
    }
}
