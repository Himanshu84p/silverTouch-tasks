public class p73 {
    int[][] sparseTable;

    // Constructor to build the sparse table
    public void RMQSparseTable(int[] arr) {
        int n = arr.length;
        int logN = (int) (Math.log(n) / Math.log(2)) + 1;
        sparseTable = new int[n][logN];

        for (int i = 0; i < n; i++) {
            sparseTable[i][0] = arr[i];
        }

        for (int j = 1; (1 << j) <= n; j++) {
            for (int i = 0; i + (1 << j) - 1 < n; i++) {
                sparseTable[i][j] = Math.min(sparseTable[i][j - 1], sparseTable[i + (1 << (j - 1))][j - 1]);
            }
        }
    }

    // Function to perform range minimum query using the sparse table
    public int query(int left, int right) {
        int k = (int) (Math.log(right - left + 1) / Math.log(2));
        return Math.min(sparseTable[left][k], sparseTable[right - (1 << k) + 1][k]);
    }

    public static void main(String[] args) {
        int[] arr = { 2, 5, 1, 3, 7, 4, 6, 8 };
        p73 rmq = new p73();
        rmq.RMQSparseTable(arr);

        // Example queries
        System.out.println("RMQ between index 1 and 5: " + rmq.query(1, 5));
        System.out.println("RMQ between index 2 and 6: " + rmq.query(2, 6));
    }
}
