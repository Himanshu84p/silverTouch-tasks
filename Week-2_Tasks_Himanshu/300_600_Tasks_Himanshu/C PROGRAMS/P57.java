public class P57 {

    public static int maxSumIncreasingSubsequence(int[] arr) {
        int n = arr.length;
        int[] msis = new int[n];


        for (int i = 0; i < n; i++) {
            msis[i] = arr[i];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && msis[i] < msis[j] + arr[i]) {
                    msis[i] = msis[j] + arr[i];
                }
            }
        }

        int maxSum = msis[0];
        for (int i = 1; i < n; i++) {
            maxSum = Math.max(maxSum, msis[i]);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        int[] arr = {1, 101, 2, 3, 100, 4, 5};

        int result = maxSumIncreasingSubsequence(arr);

        System.out.println("Maximum Sum Increasing Subsequence: " + result);
    }
}
