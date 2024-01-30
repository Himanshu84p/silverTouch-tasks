import java.util.*;

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

    public void addEdge(int from, int to) {
        adjList.get(from).add(to);
    }

    public List<List<Integer>> getTranspose() {
        List<List<Integer>> transpose = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) {
            transpose.add(new ArrayList<>());
        }

        for (int i = 0; i < vertices; i++) {
            for (int neighbor : adjList.get(i)) {
                transpose.get(neighbor).add(i);
            }
        }

        return transpose;
    }

    public void fillOrder(int vertex, boolean[] visited, Stack<Integer> stack) {
        visited[vertex] = true;

        for (int neighbor : adjList.get(vertex)) {
            if (!visited[neighbor]) {
                fillOrder(neighbor, visited, stack);
            }
        }

        stack.push(vertex);
    }

    public void DFSUtil(int vertex, boolean[] visited, List<Integer> component) {
        visited[vertex] = true;
        component.add(vertex);

        for (int neighbor : adjList.get(vertex)) {
            if (!visited[neighbor]) {
                DFSUtil(neighbor, visited, component);
            }
        }
    }

    public List<List<Integer>> findSCCs() {
        Stack<Integer> stack = new Stack<>();

        boolean[] visited = new boolean[vertices];
        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                fillOrder(i, visited, stack);
            }
        }

        List<List<Integer>> sccList = new ArrayList<>();

        List<List<Integer>> transpose = getTranspose();

        Arrays.fill(visited, false);

        while (!stack.isEmpty()) {
            int vertex = stack.pop();

            if (!visited[vertex]) {
                List<Integer> component = new ArrayList<>();
                DFSUtil(vertex, visited, component);
                sccList.add(component);
            }
        }

        return sccList;
    }
}

public class StronglyConnectedComponents {

    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(1, 3);
        graph.addEdge(3, 4);

        List<List<Integer>> sccList = graph.findSCCs();

        System.out.println("Strongly Connected Components:");
        for (List<Integer> component : sccList) {
            System.out.println(component);
        }
    }
}
