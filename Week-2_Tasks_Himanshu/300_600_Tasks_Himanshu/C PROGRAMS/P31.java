import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class P31 {
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

    public static boolean isBipartite(ArrayList<Edge> graph[], int start, int[] color) {
        color[start] = 1;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (Edge edge : graph[current]) {
                if (color[edge.dest] == -1) {
                    color[edge.dest] = 1 - color[current];
                    queue.add(edge.dest);
                } else if (color[edge.dest] == color[current]) {
                    return false; // Graph is not bipartite
                }
            }
        }

        return true;
    }

    public static boolean isBipartite(ArrayList<Edge> graph[], int vertax) {
        int[] color = new int[vertax];
        for (int i = 0; i < vertax; i++) {
            if (color[i] == -1 && !isBipartite(graph, i, color)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String args[]) {
        int vertax = 4;
        ArrayList<Edge> graph[] = new ArrayList[vertax];
        createGraph(graph);

        boolean isBipartite = isBipartite(graph, vertax);

        if (isBipartite) {
            System.out.println("Graph is Bipartite.");
        } else {
            System.out.println("Graph is not Bipartite.");
        }
    }
}
