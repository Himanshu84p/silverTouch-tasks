public class Q38 {
    private int[] BIT;
    private int size;

    public Q38(int n) {
        this.BIT = new int[n + 1];
        this.size = n;
    }

    public void update(int i, int delta) {
        i++;
        while (i <= size) {
            BIT[i] += delta;
            i += (i & -i);
        }
    }

    public int query(int i) {
        i++;
        int sum = 0;
        while (i > 0) {
            sum += BIT[i];
            i -= (i & -i);
        }
        return sum;
    }

    public int rangeQuery(int left, int right) {
        if (left == 0) {
            return query(right);
        } else {
            return query(right) - query(left - 1);
        }
    }

    public static void main(String[] args) {
        int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        Q38 fenwickTree = new Q38(array.length);

        for (int i = 0; i < array.length; i++) {
            fenwickTree.update(i, array[i]);
        }

        System.out.println("Prefix Sum at index 5: " + fenwickTree.query(5));
        System.out.println("Range Sum in [2, 7]: " + fenwickTree.rangeQuery(2, 7));

        fenwickTree.update(3, 10);

        System.out.println("Prefix Sum at index 5: " + fenwickTree.query(5));
        System.out.println("Range Sum in [2, 7]: " + fenwickTree.rangeQuery(2, 7));
    }
}
