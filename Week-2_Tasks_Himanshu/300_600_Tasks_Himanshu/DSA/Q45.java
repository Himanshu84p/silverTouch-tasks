import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class Graph {
    private int vertices;
    private List<List<Integer>> adjList;

    public Graph(int vertices) {
        this.vertices = vertices;
        this.adjList = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) {
            this.adjList.add(new LinkedList<>());
        }
    }

    public void addEdge(int u, int v) {
        adjList.get(u).add(v);
        adjList.get(v).add(u);
    }

    public boolean hasEulerianPath() {
        int oddDegreeCount = 0;
        for (int i = 0; i < vertices; i++) {
            if (adjList.get(i).size() % 2 != 0) {
                oddDegreeCount++;
            }
        }

        return oddDegreeCount == 0 || oddDegreeCount == 2;
    }

    private int dfs(int v, boolean[] visited) {
        visited[v] = true;
        int count = 1;
        for (int neighbor : adjList.get(v)) {
            if (!visited[neighbor]) {
                count += dfs(neighbor, visited);
            }
        }
        return count;
    }

    private boolean isConnected() {
        boolean[] visited = new boolean[vertices];
        int startVertex = -1;

        // Find the first non-zero degree vertex
        for (int i = 0; i < vertices; i++) {
            if (adjList.get(i).size() > 0) {
                startVertex = i;
                break;
            }
        }

        if (startVertex == -1) {
            // Graph has no edges
            return true;
        }

        int count = dfs(startVertex, visited);

        // Check if all non-zero degree vertices are visited
        for (int i = 0; i < vertices; i++) {
            if (adjList.get(i).size() > 0 && !visited[i]) {
                return false;
            }
        }

        return count == vertices;
    }

    public List<Integer> findEulerianPath() {
        List<Integer> path = new ArrayList<>();
        if (!hasEulerianPath() || !isConnected()) {
            return path; // No Eulerian path exists
        }

        int startVertex = findEulerianStartVertex();
        dfsEulerianPath(startVertex, path);

        return path;
    }

    private void dfsEulerianPath(int v, List<Integer> path) {
        for (int neighbor : adjList.get(v)) {
            if (isValidNextEdge(v, neighbor, path)) {
                path.add(neighbor);
                removeEdge(v, neighbor);
                dfsEulerianPath(neighbor, path);
            }
        }
    }

    private boolean isValidNextEdge(int u, int v, List<Integer> path) {
        if (adjList.get(u).size() == 1) {
            return true;
        }

        boolean[] visited = new boolean[vertices];
        int count1 = dfs(u, visited);

        removeEdge(u, v);

        Arrays.fill(visited, false);
        int count2 = dfs(u, visited);

        addEdge(u, v);

        return count1 == count2;
    }

    private void removeEdge(int u, int v) {
        adjList.get(u).remove(Integer.valueOf(v));
        adjList.get(v).remove(Integer.valueOf(u));
    }

    private int findEulerianStartVertex() {
        int startVertex = 0;

        for (int i = 0; i < vertices; i++) {
            if (adjList.get(i).size() % 2 != 0) {
                startVertex = i;
                break;
            }
        }

        return startVertex;
    }
}

public class Q45 {
    public static void main(String[] args) {
        Graph graph = new Graph(5);

        graph.addEdge(1, 0);
        graph.addEdge(0, 2);
        graph.addEdge(2, 1);
        graph.addEdge(0, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 0);

        List<Integer> eulerianPath = graph.findEulerianPath();

        if (eulerianPath.isEmpty()) {
            System.out.println("No Eulerian path exists.");
        } else {
            System.out.println("Eulerian Path:");
            for (int vertex : eulerianPath) {
                System.out.print(vertex + " ");
            }
        }
    }
}
