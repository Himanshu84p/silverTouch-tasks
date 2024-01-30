public class P22 {

    public static String findLCS(String str1, String str2, String str3) {
        int m = str1.length();
        int n = str2.length();
        int o = str3.length();

        int[][][] dp = new int[m + 1][n + 1][o + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k <= o; k++) {
                    if (i == 0 || j == 0 || k == 0) {
                        dp[i][j][k] = 0;
                    } else if (str1.charAt(i - 1) == str2.charAt(j - 1) && str1.charAt(i - 1) == str3.charAt(k - 1)) {
                        dp[i][j][k] = dp[i - 1][j - 1][k - 1] + 1;
                    } else {
                        dp[i][j][k] = Math.max(Math.max(dp[i - 1][j][k], dp[i][j - 1][k]), dp[i][j][k - 1]);
                    }
                }
            }
        }

        StringBuilder lcs = new StringBuilder();
        int i = m, j = n, k = o;

        while (i > 0 && j > 0 && k > 0) {
            if (str1.charAt(i - 1) == str2.charAt(j - 1) && str1.charAt(i - 1) == str3.charAt(k - 1)) {
                lcs.insert(0, str1.charAt(i - 1));
                i--;
                j--;
                k--;
            } else if (dp[i - 1][j][k] >= dp[i][j - 1][k] && dp[i - 1][j][k] >= dp[i][j][k - 1]) {
                i--;
            } else if (dp[i][j - 1][k] >= dp[i - 1][j][k] && dp[i][j - 1][k] >= dp[i][j][k - 1]) {
                j--;
            } else {
                k--;
            }
        }

        return lcs.toString();
    }

    public static void main(String[] args) {
        String str1 = "ABCBDAB";
        String str2 = "BDCAB";
        String str3 = "BADBCA";

        String lcs = findLCS(str1, str2, str3);
        System.out.println("Longest Common Subsequence: " + lcs);
    }
}