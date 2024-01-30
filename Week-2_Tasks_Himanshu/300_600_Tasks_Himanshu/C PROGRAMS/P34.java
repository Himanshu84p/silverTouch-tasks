import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class P34 {
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

    public static int hopcroftKarp(ArrayList<Edge> graph[], int V) {
        int[] pairU = new int[V];
        int[] pairV = new int[V];
        int[] dist = new int[V];

        Arrays.fill(pairU, -1);
        Arrays.fill(pairV, -1);

        int matching = 0;


        while (bfs(graph, pairU, pairV, dist)) {
            for (int u = 0; u < V; u++) {
                if (pairU[u] == -1 && dfs(graph, u, pairU, pairV, dist)) {
                    matching++;
                }
            }
        }

        return matching;
    }

    private static boolean bfs(ArrayList<Edge> graph[], int[] pairU, int[] pairV, int[] dist) {
        Queue<Integer> queue = new LinkedList<>();

        for (int u = 0; u < pairU.length; u++) {
            if (pairU[u] == -1) {
                dist[u] = 0;
                queue.add(u);
            } else {
                dist[u] = Integer.MAX_VALUE;
            }
        }

        dist[-1] = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            int u = queue.poll();

            if (dist[u] < dist[-1]) {
                for (Edge edge : graph[u]) {
                    int v = edge.dest;
                    int w = pairV[v];

                    if (dist[w] == Integer.MAX_VALUE) {
                        dist[w] = dist[u] + 1;
                        queue.add(w);
                    }
                }
            }
        }

        return dist[-1] != Integer.MAX_VALUE;
    }


    private static boolean dfs(ArrayList<Edge> graph[], int u, int[] pairU, int[] pairV, int[] dist) {
        if (u != -1) {
            for (Edge edge : graph[u]) {
                int v = edge.dest;
                int w = pairV[v];

                if (dist[w] == dist[u] + 1 && dfs(graph, w, pairU, pairV, dist)) {
                    pairV[v] = u;
                    pairU[u] = v;
                    return true;
                }
            }

            dist[u] = Integer.MAX_VALUE;
            return false;
        }

        return true;
    }


    public static void main(String args[]) {
        int vertax = 4;
        ArrayList<Edge> graph[] = new ArrayList[vertax];
        createGraph(graph);

        int maxMatching = hopcroftKarp(graph, vertax);
        System.out.println("Maximum Bipartite Matching: " + maxMatching);
    }
}
