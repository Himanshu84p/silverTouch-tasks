import java.util.HashSet;
import java.util.Set;

public class p96 {
    public static int maximalNetworkRank(int n, int[][] roads) {
        int[] degree = new int[n];
        Set<String> roadSet = new HashSet<>();

        for (int[] road : roads) {
            degree[road[0]]++;
            degree[road[1]]++;
            roadSet.add(road[0] + "-" + road[1]);
            roadSet.add(road[1] + "-" + road[0]);
        }

        int maxRank = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int currentRank = degree[i] + degree[j];
                if (roadSet.contains(i + "-" + j) || roadSet.contains(j + "-" + i)) {
                    currentRank--;
                }
                maxRank = Math.max(maxRank, currentRank);
            }
        }

        return maxRank;
    }

    public static void main(String[] args) {
        int n = 5;
        int[][] roads = { { 0, 1 }, { 0, 3 }, { 1, 2 }, { 1, 4 }, { 2, 4 } };

        int result = maximalNetworkRank(n, roads);

        System.out.println("Maximal Network Rank: " + result);
    }
}
