import java.util.HashSet;
import java.util.Set;

public class Q18 {

    public static void main(String[] args) {
        String input1 = "ilikesamsungmobile";
        Set<String> dictionary1 = new HashSet<>();
        dictionary1.add("i");
        dictionary1.add("like");
        dictionary1.add("sam");
        dictionary1.add("sung");
        dictionary1.add("samsung");
        dictionary1.add("mobile");

        System.out.println("Word Break for input1: " + wordBreak(input1, dictionary1));

        String input2 = "ilikeicecreamandmango";
        Set<String> dictionary2 = new HashSet<>();
        dictionary2.add("i");
        dictionary2.add("like");
        dictionary2.add("ice");
        dictionary2.add("cream");
        dictionary2.add("and");
        dictionary2.add("mango");

        System.out.println("Word Break for input2: " + wordBreak(input2, dictionary2));
    }

    public static boolean wordBreak(String s, Set<String> wordDict) {
        return wordBreakHelper(s, wordDict, 0);
    }

    private static boolean wordBreakHelper(String s, Set<String> wordDict, int start) {
        int n = s.length();

        if (start == n) {

            return true;
        }

        for (int end = start + 1; end <= n; end++) {
            String currentWord = s.substring(start, end);

            if (wordDict.contains(currentWord) && wordBreakHelper(s, wordDict, end)) {
                return true;
            }
        }

        return false;
    }
}
