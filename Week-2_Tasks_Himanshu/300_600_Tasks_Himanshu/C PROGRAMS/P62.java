public class P62 {

    public static int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();


        int[][] dp = new int[m + 1][n + 1];


        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }


        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]);
                }
            }
        }


        int i = m, j = n;
        StringBuilder operations = new StringBuilder();

        while (i > 0 || j > 0) {
            if (i > 0 && dp[i][j] == dp[i - 1][j] + 1) {

                operations.append("Delete ").append(word1.charAt(i - 1)).append(", ");
                i--;
            } else if (j > 0 && dp[i][j] == dp[i][j - 1] + 1) {

                operations.append("Insert ").append(word2.charAt(j - 1)).append(", ");
                j--;
            } else {

                if (i > 0 && j > 0 && dp[i][j] == dp[i - 1][j - 1]) {
                    if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
                        operations.append("Substitute ").append(word1.charAt(i - 1)).append(" with ")
                                .append(word2.charAt(j - 1)).append(", ");
                    }
                    i--;
                    j--;
                } else {
                    i--;
                    j--;
                }
            }
        }


        operations.reverse();

        System.out.println("Operations: " + operations.toString());

        return dp[m][n];
    }

    public static void main(String[] args) {
        String word1 = "kitten";
        String word2 = "sitting";

        int result = minDistance(word1, word2);

        System.out.println("Minimum Edit Distance: " + result);
    }
}
