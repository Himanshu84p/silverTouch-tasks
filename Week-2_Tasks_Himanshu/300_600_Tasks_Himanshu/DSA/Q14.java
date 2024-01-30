class UnionFind {
    private int[] parent;
    private int[] rank;

    public UnionFind(int size) {
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

                parent[rootX] = rootY;
                rank[rootY]++;
            }
        }
    }
}

public class Q14 {
    public static void main(String[] args) {
        UnionFind uf = new UnionFind(5);

        uf.union(0, 1);
        uf.union(2, 3);
        uf.union(1, 4);

        System.out.println(uf.find(0));
        System.out.println(uf.find(2));
    }
}
