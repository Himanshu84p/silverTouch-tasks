import java.util.*;

class Graph {
    private Map<Integer, Map<Integer, Integer>> adjacencyList;

    public Graph() {
        this.adjacencyList = new HashMap<>();
    }

    public void addVertex(int vertex) {
        adjacencyList.put(vertex, new HashMap<>());
    }

    public void addEdge(int source, int destination, int weight) {
        adjacencyList.get(source).put(destination, weight);
    }

    public Map<Integer, Integer> dijkstra(int source) {
        Map<Integer, Integer> distances = new HashMap<>();
        PriorityQueue<Node> minHeap = new PriorityQueue<>(Comparator.comparingInt(node -> node.distance));

        for (int vertex : adjacencyList.keySet()) {
            distances.put(vertex, Integer.MAX_VALUE);
        }

        distances.put(source, 0);
        minHeap.offer(new Node(source, 0));

        while (!minHeap.isEmpty()) {
            Node currentNode = minHeap.poll();
            int currentVertex = currentNode.vertex;

            for (Map.Entry<Integer, Integer> neighbor : adjacencyList.get(currentVertex).entrySet()) {
                int newDistance = distances.get(currentVertex) + neighbor.getValue();

                if (newDistance < distances.get(neighbor.getKey())) {
                    distances.put(neighbor.getKey(), newDistance);
                    minHeap.offer(new Node(neighbor.getKey(), newDistance));
                }
            }
        }

        return distances;
    }

    private static class Node {
        int vertex;
        int distance;

        public Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }
}

public class ShortestD {
    public static void main(String[] args) {
        Graph graph = new Graph();

        for (int i = 1; i <= 6; i++) {
            graph.addVertex(i);
        }

        graph.addEdge(1, 2, 2);
        graph.addEdge(1, 3, 4);
        graph.addEdge(2, 3, 1);
        graph.addEdge(2, 4, 7);
        graph.addEdge(3, 5, 3);
        graph.addEdge(4, 5, 2);
        graph.addEdge(4, 6, 5);
        graph.addEdge(5, 6, 1);

        int sourceVertex = 1;
        Map<Integer, Integer> shortestDistances = graph.dijkstra(sourceVertex);

        System.out.println("Shortest distances from vertex " + sourceVertex + ":");
        for (Map.Entry<Integer, Integer> entry : shortestDistances.entrySet()) {
            System.out.println("To vertex " + entry.getKey() + ": " + entry.getValue());
        }
    }
}
