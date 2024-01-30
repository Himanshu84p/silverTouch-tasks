package prac62;

class UnionFind {
    private int[] parent;
    private int[] rank;

    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }
}

public class DynamicConnectivity {
    public static void main(String[] args) {
        int n = 5; // Number of elements
        UnionFind uf = new UnionFind(n);

        uf.union(0, 1);
        uf.union(1, 2);
        uf.union(3, 4);

        System.out.println("Connected(0, 2): " + (uf.find(0) == uf.find(4)));
        System.out.println("Connected(3, 4): " + (uf.find(3) == uf.find(4)));

        uf.union(1, 2);
    }
}
