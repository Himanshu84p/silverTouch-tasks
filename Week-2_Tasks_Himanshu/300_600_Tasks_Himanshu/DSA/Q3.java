import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Graph {
    private int vertices;
    private List<List<Integer>> adjList;

    public Graph(int vertices) {
        this.vertices = vertices;
        this.adjList = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    public void addEdge(int source, int destination) {
        adjList.get(source).add(destination);
        adjList.get(destination).add(source);
    }

    public boolean hasCycle() {
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < vertices; i++) {
            if (!visited.contains(i) && hasCycleUtil(i, -1, visited)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasCycleUtil(int vertex, int parent, Set<Integer> visited) {
        visited.add(vertex);

        for (int neighbor : adjList.get(vertex)) {
            if (!visited.contains(neighbor)) {
                if (hasCycleUtil(neighbor, vertex, visited)) {
                    return true;
                }
            } else if (neighbor != parent) {

                return true;
            }
        }
        return false;
    }
}

public class Q3 {
    public static void main(String[] args) {
        Graph graph = new Graph(5);

        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(1, 3);
        graph.addEdge(3, 4);

        boolean hasCycle = graph.hasCycle();
        System.out.println("Does the graph have a cycle? " + hasCycle);
    }
}
