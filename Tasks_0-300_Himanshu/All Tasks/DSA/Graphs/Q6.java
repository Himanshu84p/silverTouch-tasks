import java.util.*;

class Graph {
    private Map<Integer, List<Integer>> adjacencyList;

    public Graph() {
        this.adjacencyList = new HashMap<>();
    }

    public void addVertex(int vertex) {
        adjacencyList.put(vertex, new ArrayList<>());
    }

    public void addEdge(int source, int destination) {
        adjacencyList.get(source).add(destination);
    }

    public boolean hasCycle() {
        Set<Integer> visited = new HashSet<>();
        Set<Integer> currentlyVisited = new HashSet<>();

        for (int vertex : adjacencyList.keySet()) {
            if (!visited.contains(vertex) && hasCycleUtil(vertex, visited, currentlyVisited)) {
                return true;
            }
        }

        return false;
    }

    private boolean hasCycleUtil(int vertex, Set<Integer> visited, Set<Integer> currentlyVisited) {
        visited.add(vertex);
        currentlyVisited.add(vertex);

        for (int neighbor : adjacencyList.get(vertex)) {
            if (!visited.contains(neighbor)) {
                if (hasCycleUtil(neighbor, visited, currentlyVisited)) {
                    return true;
                }
            } else if (currentlyVisited.contains(neighbor)) {

                return true;
            }
        }

        currentlyVisited.remove(vertex);
        return false;
    }
}

public class Q6 {
    public static void main(String[] args) {
        Graph graph = new Graph();

        for (int i = 1; i <= 4; i++) {
            graph.addVertex(i);
        }

        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);
        graph.addEdge(3, 4);

        boolean hasCycle = graph.hasCycle();
        System.out.println("Does the graph have a cycle? " + hasCycle);
    }
}
