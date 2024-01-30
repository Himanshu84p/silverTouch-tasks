import java.util.ArrayList;
import java.util.List;

public class P43 {

    public static String findShortestSuperstring(String[] words) {
        List<String> remainingWords = new ArrayList<>();
        for (String word : words) {
            remainingWords.add(word);
        }

        while (remainingWords.size() > 1) {
            int[] indices = findBestOverlapIndices(remainingWords);
            int i = indices[0];
            int j = indices[1];
            String merged = mergeStrings(remainingWords.get(i), remainingWords.get(j));
            remainingWords.remove(i);
            remainingWords.remove(j - 1);
            remainingWords.add(merged);
        }

        return remainingWords.get(0);
    }

    private static int[] findBestOverlapIndices(List<String> words) {
        int n = words.size();
        int[] bestIndices = {0, 0};
        int bestOverlapLength = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int overlap = calculateOverlapLength(words.get(i), words.get(j));
                if (overlap > bestOverlapLength) {
                    bestOverlapLength = overlap;
                    bestIndices[0] = i;
                    bestIndices[1] = j;
                }
            }
        }

        return bestIndices;
    }

    private static int calculateOverlapLength(String a, String b) {
        int maxOverlap = Math.min(a.length(), b.length());
        for (int i = 1; i < maxOverlap; i++) {
            if (a.endsWith(b.substring(0, i))) {
                return i;
            }
        }
        return 0;
    }

    private static String mergeStrings(String a, String b) {
        int overlap = calculateOverlapLength(a, b);
        return a + b.substring(overlap);
    }

    public static void main(String[] args) {
        String[] strings = {"cat", "cats", "catsdogcats", "dog", "dogcatsdog", "hippopotamuses", "rat", "ratcatdogcat"};
        String shortestSuperstring = findShortestSuperstring(strings);

        System.out.println("Shortest Superstring: " + shortestSuperstring);
    }
}
