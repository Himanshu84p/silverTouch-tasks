import java.util.HashSet;
import java.util.Set;

public class P45 {

    public static boolean wordBreak(String s, Set<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        String s = "leetcode";
        Set<String> wordDict = new HashSet<>();
        wordDict.add("leet");
        wordDict.add("code");

        boolean result = wordBreak(s, wordDict);

        if (result) {
            System.out.println("The string can be segmented into dictionary words.");
        } else {
            System.out.println("The string cannot be segmented into dictionary words.");
        }
    }
}
