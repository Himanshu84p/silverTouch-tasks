import java.util.ArrayList;
import java.util.List;

class UndirectedGraph {
    private int vertices;
    private List<List<Integer>> adjacencyList;
    private int time = 0;

    public UndirectedGraph(int vertices) {
        this.vertices = vertices;
        this.adjacencyList = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) {
            this.adjacencyList.add(new ArrayList<>());
        }
    }

    public void addEdge(int source, int destination) {
        adjacencyList.get(source).add(destination);
        adjacencyList.get(destination).add(source);
    }

    public void findArticulationPoints() {
        boolean[] visited = new boolean[vertices];
        int[] disc = new int[vertices];
        int[] low = new int[vertices];
        int[] parent = new int[vertices];
        boolean[] isArticulationPoint = new boolean[vertices];

        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                dfs(i, visited, disc, low, parent, isArticulationPoint);
            }
        }

        System.out.println("Articulation Points:");
        for (int i = 0; i < vertices; i++) {
            if (isArticulationPoint[i]) {
                System.out.println(i);
            }
        }
    }

    private void dfs(int vertex, boolean[] visited, int[] disc, int[] low, int[] parent,
            boolean[] isArticulationPoint) {
        int children = 0;
        visited[vertex] = true;
        disc[vertex] = low[vertex] = ++time;

        for (int neighbor : adjacencyList.get(vertex)) {
            if (!visited[neighbor]) {
                children++;
                parent[neighbor] = vertex;
                dfs(neighbor, visited, disc, low, parent, isArticulationPoint);

                low[vertex] = Math.min(low[vertex], low[neighbor]);

                if (parent[vertex] == -1 && children > 1) {
                    isArticulationPoint[vertex] = true;
                }

                if (parent[vertex] != -1 && low[neighbor] >= disc[vertex]) {
                    isArticulationPoint[vertex] = true;
                }
            } else if (neighbor != parent[vertex]) {
                low[vertex] = Math.min(low[vertex], disc[neighbor]);
            }
        }
    }
}

public class ArticulationPoints {

    public static void main(String[] args) {
        int vertices = 7;
        UndirectedGraph graph = new UndirectedGraph(vertices);

        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(1, 6);
        graph.addEdge(3, 5);
        graph.addEdge(4, 5);

        graph.findArticulationPoints();
    }
}
