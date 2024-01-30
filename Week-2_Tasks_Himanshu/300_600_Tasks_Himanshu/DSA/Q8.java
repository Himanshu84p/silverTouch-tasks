import java.util.ArrayList;
import java.util.List;

public class Q8 {

    public static void main(String[] args) {
        String s1 = "AABBBCCCC";
        List<int[]> queries1 = List.of(new int[] { 1, 0 }, new int[] { 2, 1 }, new int[] { 1, 0 }, new int[] { 2, 2 },
                new int[] { 1, 3 });
        processQueries(s1, queries1);

        String s2 = "XXYYY";
        List<int[]> queries2 = List.of(new int[] { 1, 3 }, new int[] { 2, 3 }, new int[] { 1, 2 });
        processQueries(s2, queries2);
    }

    private static void processQueries(String s, List<int[]> queries) {
        for (int[] query : queries) {
            int type = query[0];
            int value = query[1];

            if (type == 1) {

                s = rotateSubstring(s, value);
            } else if (type == 2) {

                int length = longestContiguousSubstringLength(s.substring(value));
                System.out.println("Length of longest contiguous substring: " + length);
            }
        }
    }

    private static String rotateSubstring(String s, int index) {

        String substring = s.substring(index);
        String rotatedSubstring = substring.substring(1) + substring.charAt(0);
        return s.substring(0, index) + rotatedSubstring;
    }

    private static int longestContiguousSubstringLength(String substring) {
        int maxCount = 0;
        int currentCount = 1;

        for (int i = 1; i < substring.length(); i++) {
            if (substring.charAt(i) == substring.charAt(i - 1)) {
                currentCount++;
            } else {
                maxCount = Math.max(maxCount, currentCount);
                currentCount = 1;
            }
        }

        return Math.max(maxCount, currentCount);
    }
}
