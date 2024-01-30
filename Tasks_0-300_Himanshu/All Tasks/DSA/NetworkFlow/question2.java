package NetworkFlow;

import java.util.*;

public class question2 {
    private static final int INF = Integer.MAX_VALUE;

    private int vertices;
    private int[][] residualGraph;

    public question2(int vertices) {
        this.vertices = vertices;
        this.residualGraph = new int[vertices][vertices];
    }

    public void addEdge(int source, int destination, int capacity) {
        residualGraph[source][destination] = capacity;
    }

    public int findMinCut(int source, int sink) {
        int[] parent = new int[vertices];
        int maxFlow = 0;

        while (bfs(source, sink, parent)) {
            int pathFlow = INF;

            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                pathFlow = Math.min(pathFlow, residualGraph[u][v]);
            }

            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                residualGraph[u][v] -= pathFlow;
                residualGraph[v][u] += pathFlow;
            }

            maxFlow += pathFlow;
        }

        List<Integer> minCut = new ArrayList<>();
        boolean[] visited = new boolean[vertices];
        dfs(source, visited);

        for (int i = 0; i < vertices; i++) {
            if (visited[i]) {
                for (int j = 0; j < vertices; j++) {
                    if (!visited[j] && residualGraph[i][j] > 0) {
                        minCut.add(i);
                        minCut.add(j);
                    }
                }
            }
        }

        System.out.println("Min Cut Edges:");
        for (int i = 0; i < minCut.size(); i += 2) {
            System.out.println(minCut.get(i) + " - " + minCut.get(i + 1));
        }

        return maxFlow;
    }

    private boolean bfs(int source, int sink, int[] parent) {
        boolean[] visited = new boolean[vertices];
        Arrays.fill(parent, -1);

        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source] = true;

        while (!queue.isEmpty()) {
            int u = queue.poll();

            for (int v = 0; v < vertices; v++) {
                if (!visited[v] && residualGraph[u][v] > 0) {
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }

        return visited[sink];
    }

    private void dfs(int u, boolean[] visited) {
        visited[u] = true;
        for (int v = 0; v < vertices; v++) {
            if (!visited[v] && residualGraph[u][v] > 0) {
                dfs(v, visited);
            }
        }
    }

    public static void main(String[] args) {
        int vertices = 6;
        question2 fordFulkerson = new question2(vertices);

        fordFulkerson.addEdge(0, 1, 16);
        fordFulkerson.addEdge(0, 2, 13);
        fordFulkerson.addEdge(1, 2, 10);
        fordFulkerson.addEdge(1, 3, 12);
        fordFulkerson.addEdge(2, 1, 4);
        fordFulkerson.addEdge(2, 4, 14);
        fordFulkerson.addEdge(3, 2, 9);
        fordFulkerson.addEdge(3, 5, 20);
        fordFulkerson.addEdge(4, 3, 7);
        fordFulkerson.addEdge(4, 5, 4);

        int source = 0;
        int sink = 5;

        int maxFlow = fordFulkerson.findMinCut(source, sink);
        System.out.println("Maximum Flow: " + maxFlow);
    }
}
