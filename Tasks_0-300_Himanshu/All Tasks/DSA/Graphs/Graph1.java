import java.util.*;

class Graph {
    private Map<Integer, List<Integer>> adjacencyList;

    public Graph() {
        this.adjacencyList = new HashMap<>();
    }

    public void addVertex(int vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            adjacencyList.put(vertex, new ArrayList<>());
            System.out.println("Vertex " + vertex + " added.");
        } else {
            System.out.println("Vertex " + vertex + " already exists.");
        }
    }

    public void addEdge(int source, int destination) {
        adjacencyList.computeIfAbsent(source, k -> new ArrayList<>()).add(destination);
        adjacencyList.computeIfAbsent(destination, k -> new ArrayList<>()).add(source);
        System.out.println("Edge added between " + source + " and " + destination + ".");
    }

    public void removeVertex(int vertex) {
        if (adjacencyList.containsKey(vertex)) {

            adjacencyList.remove(vertex);

            for (List<Integer> neighbors : adjacencyList.values()) {
                neighbors.remove(Integer.valueOf(vertex));
            }

            System.out.println("Vertex " + vertex + " removed.");
        } else {
            System.out.println("Vertex " + vertex + " not found.");
        }
    }

    public void removeEdge(int source, int destination) {
        if (adjacencyList.containsKey(source) && adjacencyList.containsKey(destination)) {

            adjacencyList.get(source).remove(Integer.valueOf(destination));
            adjacencyList.get(destination).remove(Integer.valueOf(source));

            System.out.println("Edge removed between " + source + " and " + destination + ".");
        } else {
            System.out.println("Edge not found between " + source + " and " + destination + ".");
        }
    }

    public void displayGraph() {
        for (Map.Entry<Integer, List<Integer>> entry : adjacencyList.entrySet()) {
            System.out.print(entry.getKey() + " -> ");
            System.out.println(entry.getValue());
        }
    }
}

public class Graph1 {
    public static void main(String[] args) {
        Graph graph = new Graph();

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);

        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 1);

        System.out.println("Initial Graph:");
        graph.displayGraph();

        graph.removeVertex(3);
        graph.removeEdge(1, 2);

        System.out.println("\nModified Graph:");
        graph.displayGraph();
    }
}
