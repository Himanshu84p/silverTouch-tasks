package DataStructure94;


public class SparseTable {

    private int[][] table;
    private int[] logTable;
    private int[] originalArray;

    public SparseTable(int[] array) {
        int n = array.length;
        int logN = log2(n) + 1;

        table = new int[n][logN];
        logTable = new int[n + 1];

        for (int i = 2; i <= n; i++) {
            logTable[i] = logTable[i / 2] + 1;
        }

        for (int i = 0; i < n; i++) {
            table[i][0] = array[i];
        }

        for (int j = 1; (1 << j) <= n; j++) {
            for (int i = 0; i + (1 << j) <= n; i++) {
                table[i][j] = Math.min(table[i][j - 1], table[i + (1 << (j - 1))][j - 1]);
            }
        }

        originalArray = array;
    }

    public int query(int left, int right) {
        int k = logTable[right - left + 1];
        return Math.min(table[left][k], table[right - (1 << k) + 1][k]);
    }

    public void update(int index, int newValue) {
        originalArray[index] = newValue;

        for (int j = 1; (1 << j) <= originalArray.length; j++) {
            for (int i = 0; i + (1 << j) <= originalArray.length; i++) {
                table[i][j] = Math.min(table[i][j - 1], table[i + (1 << (j - 1))][j - 1]);
            }
        }
    }

    private int log2(int n) {
        return 31 - Integer.numberOfLeadingZeros(n);
    }

    public static void main(String[] args) {
        int[] array = {2, 4, 1, 7, 3, 6, 9, 8};
        SparseTable sparseTable = new SparseTable(array);

        int queryResult = sparseTable.query(2, 5);
        System.out.println("Query result for range [2, 5]: " + queryResult);

        sparseTable.update(3, 0);
        queryResult = sparseTable.query(2, 5);
        System.out.println("Query result after updating index 3 to 0 for range [2, 5]: " + queryResult);
    }
}
