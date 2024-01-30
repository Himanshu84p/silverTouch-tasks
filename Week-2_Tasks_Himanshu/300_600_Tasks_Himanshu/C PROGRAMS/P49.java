import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Edge {
    int src, dest, weight;

    Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
}

class Subset {
    int parent, rank;

    Subset(int parent, int rank) {
        this.parent = parent;
        this.rank = rank;
    }
}

public class P49 {

    public static List<Edge> boruvkaMST(List<Edge> edges, int numVertices) {
        List<Edge> mst = new ArrayList<>();
        Subset[] subsets = new Subset[numVertices];

        for (int i = 0; i < numVertices; i++) {
            subsets[i] = new Subset(i, 0);
        }

        while (mst.size() < numVertices - 1) {
            int[] cheapest = new int[numVertices];

            Arrays.fill(cheapest, -1);

            for (Edge edge : edges) {
                int set1 = find(subsets, edge.src);
                int set2 = find(subsets, edge.dest);

                if (set1 != set2) {
                    if (cheapest[set1] == -1 || edge.weight < edges.get(cheapest[set1]).weight) {
                        cheapest[set1] = edge.dest;
                    }

                    if (cheapest[set2] == -1 || edge.weight < edges.get(cheapest[set2]).weight) {
                        cheapest[set2] = edge.src;
                    }
                }
            }

            for (int i = 0; i < numVertices; i++) {
                if (cheapest[i] != -1) {
                    int set1 = find(subsets, i);
                    int set2 = find(subsets, cheapest[i]);

                    if (set1 != set2) {
                        mst.add(edges.get(cheapest[i]));
                        union(subsets, set1, set2);
                    }
                }
            }
        }

        return mst;
    }

    private static int find(Subset[] subsets, int i) {
        if (subsets[i].parent != i) {
            subsets[i].parent = find(subsets, subsets[i].parent);
        }
        return subsets[i].parent;
    }

    private static void union(Subset[] subsets, int x, int y) {
        int rootX = find(subsets, x);
        int rootY = find(subsets, y);

        if (subsets[rootX].rank < subsets[rootY].rank) {
            subsets[rootX].parent = rootY;
        } else if (subsets[rootX].rank > subsets[rootY].rank) {
            subsets[rootY].parent = rootX;
        } else {
            subsets[rootX].parent = rootY;
            subsets[rootY].rank++;
        }
    }

    public static void main(String[] args) {
        int numVertices = 4;
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 10));
        edges.add(new Edge(0, 2, 6));
        edges.add(new Edge(0, 3, 5));
        edges.add(new Edge(1, 3, 15));
        edges.add(new Edge(2, 3, 4));

        List<Edge> mst = boruvkaMST(edges, numVertices);

        System.out.println("Minimum Spanning Tree (MST) edges:");
        for (Edge edge : mst) {
            System.out.println("Edge " + edge.src + " - " + edge.dest + " with weight " + edge.weight);
        }
    }
}
