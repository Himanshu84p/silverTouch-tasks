public class P68 {

    private int[] parent;
    private int[] rank;

    public P68(int size) {
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

    public static void main(String[] args) {
        P68 uf = new P68(5);
        uf.union(0, 1);
        uf.union(2, 3);
        uf.union(3, 4);

        System.out.println("Are 0 and 3 connected? " + (uf.find(0) == uf.find(3)));
        System.out.println("Are 1 and 4 connected? " + (uf.find(1) == uf.find(4)));
    }
}
