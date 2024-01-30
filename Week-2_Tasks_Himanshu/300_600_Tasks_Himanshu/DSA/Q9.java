import java.util.*;

public class Q9 {

    public static void main(String[] args) {
        int N1 = 4;
        int[][] arr1 = { { 1, 2 }, { 2, 3 }, { 2, 4 }, { 2, 5 } };
        System.out.println("Maximum soldiers in a team: " + findMaxSoldiers(N1, arr1));

        int N2 = 8;
        int[][] arr2 = { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 3, 5 }, { 6, 7 }, { 7, 8 }, { 7, 9 }, { 7, 10 } };
        System.out.println("Maximum soldiers in a team: " + findMaxSoldiers(N2, arr2));

        int N3 = 3;
        int[][] arr3 = { { 1, 2 }, { 2, 3 }, { 3, 1 } };
        System.out.println("Maximum soldiers in a team: " + findMaxSoldiers(N3, arr3));
    }

    public static int findMaxSoldiers(int N, int[][] arr) {
        Map<Integer, List<Integer>> graph = buildGraph(N, arr);
        boolean[] visited = new boolean[N + 1];
        int maxSoldiers = 0;

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                int soldiersInTeam = dfs(graph, i, visited);
                maxSoldiers = Math.max(maxSoldiers, soldiersInTeam);
            }
        }

        return maxSoldiers;
    }

    private static Map<Integer, List<Integer>> buildGraph(int N, int[][] arr) {
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int[] connection : arr) {
            int u = connection[0];
            int v = connection[1];

            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            graph.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
        }

        return graph;
    }

    private static int dfs(Map<Integer, List<Integer>> graph, int node, boolean[] visited) {
        visited[node] = true;
        int soldiersInTeam = 1;

        if (graph.containsKey(node)) {
            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {
                    soldiersInTeam += dfs(graph, neighbor, visited);
                }
            }
        }

        return soldiersInTeam;
    }
}
