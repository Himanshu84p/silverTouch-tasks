import java.util.ArrayList;
import java.util.List;

class Node {
    int parent;
    int weight;
    List<Integer> children;

    public Node(int weight) {
        this.parent = -1;
        this.weight = weight;
        this.children = new ArrayList<>();
    }
}

public class Q13 {

    public static void main(String[] args) {
        int numberOfNodes1 = 7;
        int[] weights1 = { 1, 2, 6, 4, 2, 0, 3 };
        int[][] edges1 = { { 1, 2 }, { 1, 3 }, { 2, 4 }, { 2, 5 }, { 4, 6 }, { 6, 7 } };
        dsuOnTrees(numberOfNodes1, weights1, edges1);

        int numberOfNodes2 = 6;
        int[] weights2 = { 2, 4, 0, 2, 2, 6 };
        int[][] edges2 = { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 5 }, { 1, 6 } };
        dsuOnTrees(numberOfNodes2, weights2, edges2);
    }

    public static void dsuOnTrees(int numberOfNodes, int[] weights, int[][] edges) {
        Node[] nodes = new Node[numberOfNodes + 1];

        for (int i = 1; i <= numberOfNodes; i++) {
            nodes[i] = new Node(weights[i - 1]);
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            int rootU = find(u, nodes);

            int rootV = find(v, nodes);

            if (rootU != rootV) {

                nodes[rootU].parent = rootV;
                nodes[rootV].children.add(rootU);

                System.out.println("Subtree sum after processing edge (" + u + ", " + v + "): " +
                        nodes[rootV].weight);
            }
        }
    }

    private static int find(int node, Node[] nodes) {
        if (nodes[node].parent == -1) {
            return node;
        } else {

            nodes[node].parent = find(nodes[node].parent, nodes);
            return nodes[node].parent;
        }
    }
}
