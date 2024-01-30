package DynamicGraphConnect84;

import java.util.HashMap;
import java.util.Map;

class UnionFind {
    private Map<Integer, Integer> parent;
    private Map<Integer, Integer> rank;

    public UnionFind() {
        this.parent = new HashMap<>();
        this.rank = new HashMap<>();
    }

    public void makeSet(int element) {
        if (!parent.containsKey(element)) {
            parent.put(element, element);
            rank.put(element, 0);
        }
    }

    public int find(int element) {
        if (element != parent.get(element)) {
            parent.put(element, find(parent.get(element)));
        }
        return parent.get(element);
    }

    public void union(int element1, int element2) {
        int root1 = find(element1);
        int root2 = find(element2);

        if (root1 != root2) {
            if (rank.get(root1) < rank.get(root2)) {
                parent.put(root1, root2);
            } else if (rank.get(root1) > rank.get(root2)) {
                parent.put(root2, root1);
            } else {
                parent.put(root1, root2);
                rank.put(root2, rank.get(root2) + 1);
            }
        }
    }
}

public class DynamicConnectivityGraph {
    private UnionFind unionFind;

    public DynamicConnectivityGraph() {
        this.unionFind = new UnionFind();
    }

    public void insertEdge(int vertex1, int vertex2) {
        unionFind.makeSet(vertex1);
        unionFind.makeSet(vertex2);

        unionFind.union(vertex1, vertex2);
    }

    public void deleteEdge(int vertex1, int vertex2) {
        if (unionFind.find(vertex1) == unionFind.find(vertex2)) {

            unionFind.union(vertex1, vertex2);
        }
    }

    public boolean isConnected(int vertex1, int vertex2) {
        return unionFind.find(vertex1) == unionFind.find(vertex2);
    }

    public static void main(String[] args) {
        DynamicConnectivityGraph graph = new DynamicConnectivityGraph();

        graph.insertEdge(1, 2);
        graph.insertEdge(2, 3);
        graph.insertEdge(4, 5);

        System.out.println("Is 1 connected to 3? " + graph.isConnected(1, 3));

        graph.insertEdge(1, 3);

        System.out.println("Is 1 connected to 3? " + graph.isConnected(1, 3));

        graph.deleteEdge(1, 3);

        System.out.println("Is 1 connected to 3? " + graph.isConnected(1, 3));
    }
}
