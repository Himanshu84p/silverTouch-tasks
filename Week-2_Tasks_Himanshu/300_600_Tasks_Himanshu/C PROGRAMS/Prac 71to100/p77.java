import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class p77 {
    int[] subtreeSize;
    int[] sumOfDistances;
    List<List<Integer>> tree;

    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        tree = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            tree.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            tree.get(u).add(v);
            tree.get(v).add(u);
        }

        subtreeSize = new int[n];
        sumOfDistances = new int[n];

        dfs(0, -1); // Compute subtree sizes
        dfs2(0, -1); // Compute sum of distances

        return sumOfDistances;
    }

    // DFS to compute subtree sizes
    private void dfs(int node, int parent) {
        subtreeSize[node] = 1;
        for (int child : tree.get(node)) {
            if (child != parent) {
                dfs(child, node);
                subtreeSize[node] += subtreeSize[child];
            }
        }
    }

    // DFS to compute sum of distances
    private void dfs2(int node, int parent) {
        for (int child : tree.get(node)) {
            if (child != parent) {
                sumOfDistances[child] = sumOfDistances[node] - subtreeSize[child] + (tree.size() - subtreeSize[child]);
                dfs2(child, node);
            }
        }
    }

    public static void main(String[] args) {
        p77 solution = new p77 ();
        int n = 6;
        int[][] edges = { { 0, 1 }, { 0, 2 }, { 2, 3 }, { 2, 4 }, { 2, 5 } };

        int[] result = solution.sumOfDistancesInTree(n, edges);

        System.out.println("Sum of Distances from Each Node:");
        System.out.println(Arrays.toString(result));
    }
}
