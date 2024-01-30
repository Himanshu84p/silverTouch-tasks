class WeightedUnionFind {
    private int[] parent;
    private int[] rank;

    public WeightedUnionFind(int size) {
        parent = new int[size];
        rank = new int[size];

        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 0;
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
            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }

    public boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }
}

public class Q41 {
    public static void main(String[] args) {
        int size = 5;
        WeightedUnionFind uf = new WeightedUnionFind(size);

        uf.union(0, 1);
        uf.union(2, 3);
        uf.union(0, 4);

        System.out.println("Is 1 connected to 4? " + uf.isConnected(1, 4));
        System.out.println("Is 2 connected to 4? " + uf.isConnected(2, 4));
    }
}
