import java.util.*;

public class Q3 {

    private static class Graph {
        private int V;
        private Map<Integer, List<Integer>> adjList;

        public Graph(int vertices) {
            V = vertices;
            adjList = new HashMap<>();
            for (int i = 0; i < V; i++) {
                adjList.put(i, new ArrayList<>());
            }
        }

        public void addEdge(int u, int v) {
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        public boolean isCyclic() {
            Set<Integer> visited = new HashSet<>();

            for (int vertex = 0; vertex < V; vertex++) {
                if (!visited.contains(vertex)) {
                    if (isCyclicUtil(vertex, -1, visited)) {
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean isCyclicUtil(int vertex, int parent, Set<Integer> visited) {
            visited.add(vertex);

            for (Integer neighbor : adjList.get(vertex)) {
                if (!visited.contains(neighbor)) {
                    if (isCyclicUtil(neighbor, vertex, visited)) {
                        return true;
                    }
                } else if (neighbor != parent) {

                    return true;
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(5);

        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(1, 3);
        graph.addEdge(3, 4);

        if (graph.isCyclic()) {
            System.out.println("The graph contains a cycle.");
        } else {
            System.out.println("The graph does not contain a cycle.");
        }
    }
}
