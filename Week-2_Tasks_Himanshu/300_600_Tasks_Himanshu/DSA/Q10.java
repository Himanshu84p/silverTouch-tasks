import java.util.*;

public class Q10 {

    public static void main(String[] args) {
        int N1 = 2, Q1 = 1;
        int[][] queries1 = { { 1, 2 } };
        solveQueries(N1, Q1, queries1);

        int N2 = 4, Q2 = 2;
        int[][] queries2 = { { 1, 2 }, { 2, 4 } };
        solveQueries(N2, Q2, queries2);
    }

    public static void solveQueries(int N, int Q, int[][] queries) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        int[] componentSizes = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            graph.put(i, new ArrayList<>());
            componentSizes[i] = 1;
        }

        int minDifference = Integer.MAX_VALUE;

        for (int[] query : queries) {
            int u = query[0];
            int v = query[1];

            graph.get(u).add(v);
            graph.get(v).add(u);

            int difference = recalculateComponentSizes(graph, visited, componentSizes);
            minDifference = Math.min(minDifference, difference);

            System.out.println("Minimum difference after query: " + minDifference);
        }
    }

    private static int recalculateComponentSizes(Map<Integer, List<Integer>> graph, Set<Integer> visited,
            int[] componentSizes) {
        Arrays.fill(componentSizes, 0);
        visited.clear();
        int minDifference = Integer.MAX_VALUE;

        for (int node : graph.keySet()) {
            if (!visited.contains(node)) {
                int size = dfs(node, graph, visited, componentSizes);
                minDifference = Math.min(minDifference, size);
            }
        }

        return minDifference;
    }

    private static int dfs(int node, Map<Integer, List<Integer>> graph, Set<Integer> visited, int[] componentSizes) {
        visited.add(node);
        int size = 1;

        for (int neighbor : graph.get(node)) {
            if (!visited.contains(neighbor)) {
                size += dfs(neighbor, graph, visited, componentSizes);
            }
        }

        componentSizes[node] = size;
        return size;
    }
}
