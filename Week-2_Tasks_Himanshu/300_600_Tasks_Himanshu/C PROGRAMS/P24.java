import java.util.*;

class P24 {

    static class Edge {
        int from, to, capacity, flow;

        public Edge(int from, int to, int capacity) {
            this.from = from;
            this.to = to;
            this.capacity = capacity;
        }
    }

    static class FlowNetwork {
        int vertices;
        List<Edge>[] adjList;

        public FlowNetwork(int vertices) {
            this.vertices = vertices;
            adjList = new List[vertices];
            for (int i = 0; i < vertices; i++) {
                adjList[i] = new ArrayList<>();
            }
        }

        public void addEdge(int from, int to, int capacity) {
            Edge forwardEdge = new Edge(from, to, capacity);
            Edge backwardEdge = new Edge(to, from, 0); // Backward edge with initial flow 0
            forwardEdge.flow = backwardEdge.capacity; // Initializing flow in forward edge

            adjList[from].add(forwardEdge);
            adjList[to].add(backwardEdge);
        }
    }

    static int fordFulkerson(FlowNetwork graph, int source, int sink) {
        int maxFlow = 0;

        while (true) {
            boolean[] visited = new boolean[graph.vertices];
            int[] parent = new int[graph.vertices];
            Arrays.fill(parent, -1);

            Queue<Integer> queue = new LinkedList<>();
            queue.add(source);
            visited[source] = true;

            while (!queue.isEmpty()) {
                int current = queue.poll();

                for (Edge edge : graph.adjList[current]) {
                    if (!visited[edge.to] && edge.capacity - edge.flow > 0) {
                        queue.add(edge.to);
                        parent[edge.to] = current;
                        visited[edge.to] = true;

                        if (edge.to == sink) {
                            break;
                        }
                    }
                }
            }

            if (!visited[sink]) {
                break;
            }

            int minCapacity = Integer.MAX_VALUE;
            for (int current = sink; current != source; current = parent[current]) {
                int parentIndex = parent[current];
                for (Edge edge : graph.adjList[parentIndex]) {
                    if (edge.to == current) {
                        minCapacity = Math.min(minCapacity, edge.capacity - edge.flow);
                        break;
                    }
                }
            }

            for (int current = sink; current != source; current = parent[current]) {
                int parentIndex = parent[current];
                for (Edge edge : graph.adjList[parentIndex]) {
                    if (edge.to == current) {
                        edge.flow += minCapacity;
                        break;
                    }
                }
            }

            maxFlow += minCapacity;
        }

        return maxFlow;
    }

    public static void main(String[] args) {
        int vertices = 6;
        FlowNetwork graph = new FlowNetwork(vertices);

        graph.addEdge(0, 1, 16);
        graph.addEdge(0, 2, 13);
        graph.addEdge(1, 2, 10);
        graph.addEdge(1, 3, 12);
        graph.addEdge(2, 1, 4);
        graph.addEdge(2, 4, 14);
        graph.addEdge(3, 2, 9);
        graph.addEdge(3, 5, 20);
        graph.addEdge(4, 3, 7);
        graph.addEdge(4, 5, 4);

        int source = 0;
        int sink = 5;

        int maxFlow = fordFulkerson(graph, source, sink);
        System.out.println("Maximum Flow: " + maxFlow);
    }
}