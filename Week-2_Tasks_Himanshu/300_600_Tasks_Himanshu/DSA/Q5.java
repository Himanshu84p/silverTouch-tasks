import java.util.Arrays;

class Graph {
    private int[] parent;
    private int[] size;
    private int smallestComponentSize;
    private int largestComponentSize;

    public Graph(int n) {
        parent = new int[n];
        size = new int[n];
        smallestComponentSize = n;
        largestComponentSize = 1;

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int find(int i) {
        if (parent[i] == i) {
            return i;
        }
        return parent[i] = find(parent[i]);
    }

    public void union(int i, int j) {
        int rootI = find(i);
        int rootJ = find(j);

        if (rootI != rootJ) {
            parent[rootI] = rootJ;
            size[rootJ] += size[rootI];

            smallestComponentSize = Math.min(smallestComponentSize, size[rootJ]);
            largestComponentSize = Math.max(largestComponentSize, size[rootJ]);
        }
    }

    public int getSmallestComponentSize() {
        return smallestComponentSize;
    }

    public int getLargestComponentSize() {
        return largestComponentSize;
    }
}

public class Q5 {

    public static void main(String[] args) {
        int n = 6;
        Graph graph = new Graph(n);

        System.out.println("Smallest Component Size: " + graph.getSmallestComponentSize());
        System.out.println("Largest Component Size: " + graph.getLargestComponentSize());

        graph.union(0, 1);
        System.out.println("Smallest Component Size: " + graph.getSmallestComponentSize());
        System.out.println("Largest Component Size: " + graph.getLargestComponentSize());

        graph.union(1, 2);
        System.out.println("Smallest Component Size: " + graph.getSmallestComponentSize());
        System.out.println("Largest Component Size: " + graph.getLargestComponentSize());

    }
}
