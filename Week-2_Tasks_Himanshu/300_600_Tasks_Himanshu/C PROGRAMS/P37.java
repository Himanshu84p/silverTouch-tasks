public class P37 {

    public static int rodCuttingDP(int[] prices, int n) {
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            int maxVal = Integer.MIN_VALUE;
            for (int j = 1; j <= i; j++) {
                maxVal = Math.max(maxVal, prices[j - 1] + dp[i - j]);
            }
            dp[i] = maxVal;
        }

        return dp[n];
    }

    public static void main(String[] args) {
        int[] prices = {1, 5, 8, 9, 10, 17, 17, 20};

        int rodLength = 4;

        int maxTotalValue = rodCuttingDP(prices, rodLength);

        System.out.println("Maximum total value for rod length " + rodLength + ": " + maxTotalValue);
    }
}
