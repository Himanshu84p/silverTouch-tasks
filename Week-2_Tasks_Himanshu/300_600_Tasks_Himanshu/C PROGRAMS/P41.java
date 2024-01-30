import java.util.Arrays;

public class P41 {

    public static int longestIncreasingSubsequence(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        int[] lis = new int[n];
        Arrays.fill(lis, 1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j] && lis[i] < lis[j] + 1) {
                    lis[i] = lis[j] + 1;
                }
            }
        }

        int maxLIS = 0;
        for (int length : lis) {
            maxLIS = Math.max(maxLIS, length);
        }

        return maxLIS;
    }

    public static void main(String[] args) {
        int[] nums = {10, 22, 9, 33, 21, 50, 41, 60, 80};
        int result = longestIncreasingSubsequence(nums);

        System.out.println("Length of Longest Increasing Subsequence: " + result);
    }
}
