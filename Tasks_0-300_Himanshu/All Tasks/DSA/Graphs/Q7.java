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

    public boolean isBipartite() {
        Map<Integer, Integer> colorMap = new HashMap<>();
        for (int vertex : adjacencyList.keySet()) {
            if (!colorMap.containsKey(vertex) && !isBipartiteUtil(vertex, colorMap, 0)) {
                return false;
            }
        }
        return true;
    }

    private boolean isBipartiteUtil(int vertex, Map<Integer, Integer> colorMap, int color) {
        colorMap.put(vertex, color);
        for (int neighbor : adjacencyList.get(vertex)) {
            if (!colorMap.containsKey(neighbor)) {
                if (!isBipartiteUtil(neighbor, colorMap, 1 - color)) {
                    return false;
                }
            } else if (colorMap.get(neighbor) == color) {

                return false;
            }
        }
        return true;
    }
}

public class Q7 {
    public static void main(String[] args) {
        Graph graph = new Graph();

        for (int i = 1; i <= 5; i++) {
            graph.addVertex(i);
        }

        graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        graph.addEdge(2, 3);
        graph.addEdge(3, 5);

        boolean bipartite = graph.isBipartite();
        System.out.println("Is the graph bipartite? " + bipartite);
    }
}
