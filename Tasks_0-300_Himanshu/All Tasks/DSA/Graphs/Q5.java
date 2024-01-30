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
        adjacencyList.get(destination).add(source);
    }

    public List<List<Integer>> findConnectedComponents() {
        Set<Integer> visited = new HashSet<>();
        List<List<Integer>> connectedComponents = new ArrayList<>();

        for (int vertex : adjacencyList.keySet()) {
            if (!visited.contains(vertex)) {
                List<Integer> component = new ArrayList<>();
                DFS(vertex, visited, component);
                connectedComponents.add(component);
            }
        }

        return connectedComponents;
    }

    private void DFS(int vertex, Set<Integer> visited, List<Integer> component) {
        visited.add(vertex);
        component.add(vertex);

        for (int neighbor : adjacencyList.get(vertex)) {
            if (!visited.contains(neighbor)) {
                DFS(neighbor, visited, component);
            }
        }
    }
}

public class Q5 {
    public static void main(String[] args) {
        Graph graph = new Graph();

        for (int i = 1; i <= 8; i++) {
            graph.addVertex(i);
        }

        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);
        graph.addEdge(4, 5);
        graph.addEdge(5, 6);
        graph.addEdge(7, 8);

        List<List<Integer>> connectedComponents = graph.findConnectedComponents();
        System.out.println("Connected Components:");
        for (List<Integer> component : connectedComponents) {
            System.out.println(component);
        }
    }
}
