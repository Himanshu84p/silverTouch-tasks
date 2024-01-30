import java.util.LinkedList;

public class FordFulkerson {

    private int vertices;
    private int[][] residualGraph;

    public FordFulkerson(int vertices) {
        this.vertices = vertices;
        this.residualGraph = new int[vertices][vertices];
    }

    public void addEdge(int source, int destination, int capacity) {
        residualGraph[source][destination] = capacity;
    }

    public int maxFlow(int source, int sink) {
        int maxFlow = 0;

        while (true) {
            boolean[] visited = new boolean[vertices];
            int pathFlow = findAugmentingPath(source, sink, Integer.MAX_VALUE, visited);

            if (pathFlow == 0) {
                break;
            }

            for (int v = sink; v != source; v = getParent(v, visited)) {
                int u = getParent(v, visited);
                residualGraph[u][v] -= pathFlow;
                residualGraph[v][u] += pathFlow;
            }

            maxFlow += pathFlow;
        }

        return maxFlow;
    }

    private int findAugmentingPath(int source, int sink, int flow, boolean[] visited) {
        visited[source] = true;

        if (source == sink) {
            return flow;
        }

        for (int i = 0; i < vertices; i++) {
            if (!visited[i] && residualGraph[source][i] > 0) {
                int minCapacity = Math.min(flow, residualGraph[source][i]);
                int pathFlow = findAugmentingPath(i, sink, minCapacity, visited);

                if (pathFlow > 0) {
                    return pathFlow;
                }
            }
        }

        return 0;
    }

    private int getParent(int vertex, boolean[] visited) {
        for (int i = 0; i < vertices; i++) {
            if (visited[i] && residualGraph[i][vertex] > 0) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int vertices = 6;
        FordFulkerson fordFulkerson = new FordFulkerson(vertices);

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

        int maxFlow = fordFulkerson.maxFlow(source, sink);
        System.out.println("Maximum Flow: " + maxFlow);
    }
}
