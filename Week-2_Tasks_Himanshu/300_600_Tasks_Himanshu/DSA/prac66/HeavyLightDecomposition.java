package prac66;

import java.util.ArrayList;
import java.util.List;

class HLDNode {
    int id;
    int depth;
    int parent;
    int chainHead;
    int positionInChain;

    HLDNode(int id, int depth, int parent) {
        this.id = id;
        this.depth = depth;
        this.parent = parent;
        this.chainHead = -1;
        this.positionInChain = -1;
    }
}

public class HeavyLightDecomposition {
    private int n;
    private List<List<Integer>> tree;
    private HLDNode[] nodes;
    private int[] subTreeSize;
    private int chainCount;
    private int chainSize;

    public HeavyLightDecomposition(int n) {
        this.n = n;
        tree = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            tree.add(new ArrayList<>());
        }
        nodes = new HLDNode[n];
        subTreeSize = new int[n];
        chainCount = 0;
        chainSize = 0;
    }

    public void addEdge(int u, int v) {
        tree.get(u).add(v);
        tree.get(v).add(u);
    }

    public void decompose() {
        dfs(0, -1, 0);

        chainCount = 0;
        chainSize = 0;

        hld(0, -1, -1);
    }

    private int dfs(int node, int parent, int depth) {
        nodes[node] = new HLDNode(node, depth, parent);
        subTreeSize[node] = 1;

        for (int child : tree.get(node)) {
            if (child != parent) {
                subTreeSize[node] += dfs(child, node, depth + 1);
            }
        }

        return subTreeSize[node];
    }

    private void hld(int node, int chainHead, int positionInChain) {
        nodes[node].chainHead = chainHead;
        nodes[node].positionInChain = positionInChain;

        if (positionInChain == -1) {
            chainSize = 0;
            chainCount++;
        }

        chainSize++;

        int heavyChild = -1;
        int maxSubTreeSize = -1;

        for (int child : tree.get(node)) {
            if (child != nodes[node].parent && (heavyChild == -1 || subTreeSize[child] > maxSubTreeSize)) {
                maxSubTreeSize = subTreeSize[child];
                heavyChild = child;
            }
        }

        if (heavyChild != -1) {
            hld(heavyChild, chainHead, nodes[node].positionInChain + 1);
        }

        for (int child : tree.get(node)) {
            if (child != nodes[node].parent && child != heavyChild) {
                hld(child, child, 0);
            }
        }
    }

    private void processChain(int chainHead, int positionInChain) {
        System.out.println("Processing chain starting at node " + chainHead + ", position " + positionInChain);

        int maxDepth = Integer.MIN_VALUE;
        for (int i = chainHead; i != -1; i = nodes[i].parent) {
            maxDepth = Math.max(maxDepth, nodes[i].depth);
        }
        System.out.println("Maximum depth in the chain: " + maxDepth);
    }

    public int query(int u, int v) {
        int result = 0;

        while (nodes[u].chainHead != nodes[v].chainHead) {
            if (nodes[nodes[u].chainHead].depth < nodes[nodes[v].chainHead].depth) {
                int temp = u;
                u = v;
                v = temp;
            }

            processChain(nodes[u].chainHead, nodes[u].positionInChain);

            u = nodes[nodes[u].chainHead].parent;
        }

        processChain(nodes[u].chainHead, nodes[u].positionInChain);

        return result;
    }

    public static void main(String[] args) {

        int n = 6;
        HeavyLightDecomposition hld = new HeavyLightDecomposition(n);

        hld.addEdge(0, 1);
        hld.addEdge(0, 2);
        hld.addEdge(1, 3);
        hld.addEdge(1, 4);
        hld.addEdge(2, 5);

        hld.decompose();

        int result = hld.query(3, 5);
        System.out.println("Result of query(3, 5): " + result);
    }
}
