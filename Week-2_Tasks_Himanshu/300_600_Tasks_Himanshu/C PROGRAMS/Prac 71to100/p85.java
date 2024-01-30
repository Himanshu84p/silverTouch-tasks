public class p85 {
    public static int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length;
        long[] prefixSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }

        return mergeSortAndCount(prefixSum, 0, n + 1, lower, upper);
    }

    private static int mergeSortAndCount(long[] prefixSum, int left, int right, int lower, int upper) {
        if (right - left <= 1) {
            return 0;
        }

        int mid = (left + right) / 2;
        int count = mergeSortAndCount(prefixSum, left, mid, lower, upper)
                + mergeSortAndCount(prefixSum, mid, right, lower, upper);

        int j = mid;
        int k = mid;
        int t = mid;

        long[] merged = new long[right - left];
        for (int i = left, r = 0; i < mid; i++, r++) {
            while (j < right && prefixSum[j] - prefixSum[i] < lower) {
                j++;
            }
            while (k < right && prefixSum[k] - prefixSum[i] <= upper) {
                k++;
            }

            while (t < right && prefixSum[t] < prefixSum[i]) {
                merged[r++] = prefixSum[t++];
            }
            merged[r] = prefixSum[i];
            count += k - j;
        }

        System.arraycopy(merged, 0, prefixSum, left, t - left);

        return count;
    }

    public static void main(String[] args) {
        int[] nums = { -2, 5, -1 };
        int lower = -2;
        int upper = 2;

        int result = countRangeSum(nums, lower, upper);

        System.out.println("Number of Range Sums in [" + lower + ", " + upper + "]: " + result);
    }
}
