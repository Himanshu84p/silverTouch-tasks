public class p75 {
    public int maxCoins(int[] nums) {
        int n = nums.length;

        int[] extendedNums = new int[n + 2];
        extendedNums[0] = extendedNums[n + 1] = 1;
        System.arraycopy(nums, 0, extendedNums, 1, n);

        int[][] dp = new int[n + 2][n + 2];

        for (int len = 2; len <= n + 1; len++) {
            for (int left = 0; left + len <= n + 1; left++) {
                int right = left + len;
                for (int last = left + 1; last < right; last++) {
                    dp[left][right] = Math.max(dp[left][right],
                            extendedNums[left] * extendedNums[last] * extendedNums[right] +
                                    dp[left][last] + dp[last][right]);
                }
            }
        }

        return dp[0][n + 1];
    }

    public static void main(String[] args) {
        p75 obj = new p75();
        int[] nums = { 3, 1, 5, 8 };

        System.out.println("Maximum coins: " + obj.maxCoins(nums));
    }
}
