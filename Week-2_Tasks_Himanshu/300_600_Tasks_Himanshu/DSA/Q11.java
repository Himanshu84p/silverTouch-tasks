import java.util.*;

class Edge implements Comparable<Edge> {
    int from, to, cost;

    public Edge(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.cost, other.cost);
    }
}

public class Q11 {

    public static void main(String[] args) {
        int N1 = 3;
        int[] wells1 = { 1, 2, 2 };
        int[][] pipes1 = { { 1, 2, 1 }, { 2, 3, 1 } };
        System.out.println("Minimum cost to provide water: " + minCost(N1, wells1, pipes1));

        int N2 = 4;
        int[] wells2 = { 1, 1, 1, 1 };
        int[][] pipes2 = { { 1, 2, 100 }, { 2, 3, 100 }, { 2, 4, 50 } };
        System.out.println("Minimum cost to provide water: " + minCost(N2, wells2, pipes2));
    }

    public static int minCost(int N, int[] wells, int[][] pipes) {
        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            edges.add(new Edge(0, i + 1, wells[i]));
        }

        for (int[] pipe : pipes) {
            edges.add(new Edge(pipe[0], pipe[1], pipe[2]));
        }

        Collections.sort(edges);

        int[] parent = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        int totalCost = 0;

        for (Edge edge : edges) {
            int rootFrom = findRoot(edge.from, parent);
            int rootTo = findRoot(edge.to, parent);

            if (rootFrom != rootTo) {
                parent[rootFrom] = rootTo;
                totalCost += edge.cost;
            }
        }

        return totalCost;
    }

    private static int findRoot(int node, int[] parent) {
        while (node != parent[node]) {
            parent[node] = parent[parent[node]];
            node = parent[node];
        }
        return node;
    }
}
