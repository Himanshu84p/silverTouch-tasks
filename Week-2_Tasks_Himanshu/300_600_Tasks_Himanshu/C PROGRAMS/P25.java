public class P25 {

    static boolean isSubsetSum(int[] set, int n, int sum) {
        boolean[][] dp = new boolean[n + 1][sum + 1];

        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (set[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {

                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - set[i - 1]];
                }
            }
        }

        return dp[n][sum];
    }

    public static void main(String[] args) {
        int[] set = {3, 34, 4, 12, 5, 2};
        int sum = 9;

        boolean result = isSubsetSum(set, set.length, sum);

        if (result) {
            System.out.println("Subset with sum " + sum + " exists.");
        } else {
            System.out.println("No subset with sum " + sum + " exists.");
        }
    }
}