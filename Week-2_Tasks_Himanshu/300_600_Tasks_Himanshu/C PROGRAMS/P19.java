import java.util.Arrays;

public class P19 {
    public static void solveNQueens(int n) {
        int[] placement = new int[n];
        Arrays.fill(placement, -1);
        solve(placement, 0, n);
    }

    private static void solve(int[] placement, int row, int n) {
        if (row == n) {
            printSolution(placement);
        } else {
            for (int col = 0; col < n; col++) {
                if (isValid(placement, row, col)) {
                    placement[row] = col;
                    solve(placement, row + 1, n);
                    placement[row] = -1;
                }
            }
        }
    }

    private static boolean isValid(int[] placement, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (placement[i] == col || Math.abs(placement[i] - col) == Math.abs(i - row)) {
                return false;
            }
        }
        return true;
    }

    private static void printSolution(int[] placement) {
        int n = placement.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (placement[i] == j) {
                    System.out.print("Q ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int n = 8;
        solveNQueens(n);
    }
}
