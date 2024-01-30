import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class P32 {
    static class Edge {
        int src;
        int dest;

        Edge(int src, int dest) {
            this.src = src;
            this.dest = dest;
        }
    }

    public static void createGraph(ArrayList<Edge> graph[]) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge(0, 2));
        graph[1].add(new Edge(1, 0));
        graph[2].add(new Edge(2, 3));
        graph[3].add(new Edge(3, 0));
    }

    public static int maxBipartiteMatching(ArrayList<Edge> graph[], int V) {
        int match[] = new int[V];
        Arrays.fill(match, -1);

        int result = 0;


        for (int u = 0; u < V; u++) {
            if (match[u] == -1) {

                if (bfs(graph, u, match)) {
                    result++;
                }
            }
        }
        return result;
    }


    private static boolean bfs(ArrayList<Edge> graph[], int u, int match[]) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(u);

        boolean visited[] = new boolean[graph.length];
        visited[u] = true;

        while (!queue.isEmpty()) {
            int v = queue.poll();

            for (Edge edge : graph[v]) {
                int w = edge.dest;

                if (!visited[w]) {
                    visited[w] = true;


                    if (match[w] == -1 || bfs(graph, match[w], match)) {
                        match[w] = v;
                        match[v] = w;
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public static void main(String[] args) {
        int vertax = 4;
        ArrayList<Edge> graph[] = new ArrayList[vertax];
        createGraph(graph);

        int maxMatching = maxBipartiteMatching(graph, vertax);
        System.out.println("Maximum Bipartite Matching: " + maxMatching);

    }

}
