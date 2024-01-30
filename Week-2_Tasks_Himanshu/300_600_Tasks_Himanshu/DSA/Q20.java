import java.util.Arrays;

public class Q20 {

    public static void main(String[] args) {
        int[][] matrix1 = {
                { 1, 0, 1, 1, 1 },
                { 1, 1, 1, 0, 1 },
                { 0, 0, 0, 0, 1 },
                { 0, 0, 0, 0, 0 },
                { 1, 1, 1, 1, 1 }
        };
        System.out.println("Longest route in matrix1: " + findLongestRoute(matrix1));

        int[][] matrix2 = {
                { 1, 0, 0, 1, 0 },
                { 1, 1, 0, 1, 1 },
                { 0, 1, 1, 1, 0 },
                { 1, 0, 0, 1, 0 },
                { 1, 1, 1, 1, 1 }
        };
        System.out.println("Longest route in matrix2: " + findLongestRoute(matrix2));
    }

    public static int findLongestRoute(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] memo = new int[rows][cols];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        int longestRoute = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 1) {
                    longestRoute = Math.max(longestRoute, dfs(matrix, i, j, memo));
                }
            }
        }

        return longestRoute;
    }

    private static int dfs(int[][] matrix, int i, int j, int[][] memo) {
        int rows = memo.length;
        int cols = memo[0].length;

        if (i < 0 || i >= rows || j < 0 || j >= cols || matrix[i][j] == 0) {
            return 0;
        }

        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        int up = dfs(matrix, i - 1, j, memo);
        int down = dfs(matrix, i + 1, j, memo);
        int left = dfs(matrix, i, j - 1, memo);
        int right = dfs(matrix, i, j + 1, memo);

        memo[i][j] = 1 + Math.max(Math.max(up, down), Math.max(left, right));
        return memo[i][j];
    }
}
