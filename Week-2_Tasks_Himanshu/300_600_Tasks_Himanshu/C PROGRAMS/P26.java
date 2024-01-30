import java.util.ArrayList;
import java.util.Stack;

public class P26 {

    public static class Edge {
        int src, dest;

        Edge(int src, int dest) {
            this.src = src;
            this.dest = dest;

        }
    }

    private static void createGraph(ArrayList<Edge>[] graph) {

        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge(0, 1));
        graph[0].add(new Edge(0, 2));
        graph[0].add(new Edge(0, 3));

        graph[1].add(new Edge(1, 0));
        graph[1].add(new Edge(1, 2));

        graph[2].add(new Edge(2, 0));
        graph[2].add(new Edge(2, 1));

        graph[3].add(new Edge(3, 0));
        graph[3].add(new Edge(3, 4));
        // graph[3].add(new Edge(3,5));

        graph[4].add(new Edge(4, 3));
        // graph[4].add(new Edge(4,5));

        // graph[5].add(new Edge(5, 3));
        // graph[5].add(new Edge(5, 4));
    }
    public static void findStronglyConnectedComponents(ArrayList<Edge> graph[], int vertexCount) {
        int[] ids = new int[vertexCount];
        int[] lowLinks = new int[vertexCount];
        boolean[] onStack = new boolean[vertexCount];
        Stack<Integer> stack = new Stack<>();
        int index = 0;

        for (int i = 0; i < vertexCount; i++) {
            if (ids[i] == 0) {
                tarjanSCCUtil(graph, i, ids, lowLinks, onStack, stack, index);
            }
        }
    }
    private static void tarjanSCCUtil(ArrayList<Edge>[] graph, int vertex, int[] ids, int[] lowLinks, boolean[] onStack, Stack<Integer> stack, int index) {
        ids[vertex] = index;
        lowLinks[vertex] = index;
        index++;
        stack.push(vertex);
        onStack[vertex] = true;

        for (Edge edge : graph[vertex]) {
            int neighbor = edge.dest;
            if (ids[neighbor] == 0) {
                tarjanSCCUtil(graph, neighbor, ids, lowLinks, onStack, stack, index);
                lowLinks[vertex] = Math.min(lowLinks[vertex], lowLinks[neighbor]);
            } else if (onStack[neighbor]) {
                lowLinks[vertex] = Math.min(lowLinks[vertex], ids[neighbor]);
            }
        }

        if (ids[vertex] == lowLinks[vertex]) {
            System.out.print("Strongly Connected Component: ");
            while (!stack.isEmpty()) {
                int componentVertex = stack.pop();
                onStack[componentVertex] = false;
                System.out.print(componentVertex + " ");
                if (componentVertex == vertex) {
                    break;
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int vertexCount = 5;
        ArrayList<Edge>[] graph = new ArrayList[vertexCount];

        createGraph(graph);

        findStronglyConnectedComponents(graph, vertexCount);
    }


}
