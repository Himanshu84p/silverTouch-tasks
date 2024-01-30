import java.util.PriorityQueue;

public class p97 {
    public static int kthSmallest(int[][] matrix, int k) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> matrix[a[0]][a[1]] - matrix[b[0]][b[1]]);

        for (int i = 0; i < Math.min(k, rows); i++) {
            minHeap.offer(new int[] { i, 0 });
        }

        int result = 0;

        while (k-- > 0) {
            int[] current = minHeap.poll();
            int row = current[0];
            int col = current[1];

            result = matrix[row][col];

            if (col < cols - 1) {
                minHeap.offer(new int[] { row, col + 1 });
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                { 1, 5, 9 },
                { 10, 11, 13 },
                { 12, 13, 15 }
        };

        int k = 8;

        int result = kthSmallest(matrix, k);

        System.out.println("Kth Smallest Element: " + result);
    }
}
