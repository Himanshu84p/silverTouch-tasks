import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Q23 {

    public static void main(String[] args) {
        String str1 = "abcabcaa";
        String str2 = "acbacba";

        List<String> lcsList = findAllLCS(str1, str2);

        System.out.println("All Longest Common Subsequences:");
        for (String lcs : lcsList) {
            System.out.println(lcs);
        }
    }

    public static List<String> findAllLCS(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();

        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        Set<String> lcsSet = new HashSet<>();
        backtrack(str1, str2, m, n, dp, "", lcsSet);

        List<String> lcsList = new ArrayList<>(lcsSet);
        Collections.sort(lcsList);

        return lcsList;
    }

    private static void backtrack(String str1, String str2, int i, int j, int[][] dp, String current,
            Set<String> lcsSet) {
        if (i == 0 || j == 0) {

            lcsSet.add(new StringBuilder(current).reverse().toString());
            return;
        }

        if (str1.charAt(i - 1) == str2.charAt(j - 1)) {

            backtrack(str1, str2, i - 1, j - 1, dp, current + str1.charAt(i - 1), lcsSet);
        } else {

            if (dp[i - 1][j] >= dp[i][j - 1]) {
                backtrack(str1, str2, i - 1, j, dp, current, lcsSet);
            }
            if (dp[i][j - 1] >= dp[i - 1][j]) {
                backtrack(str1, str2, i, j - 1, dp, current, lcsSet);
            }
        }
    }
}
