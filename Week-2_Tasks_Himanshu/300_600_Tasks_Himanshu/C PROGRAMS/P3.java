import java.util.Scanner;

public class P3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the size of the square matrix: ");
        int size = scanner.nextInt();

        double[][] matrix = new double[size][size];

        // Input matrix elements
        System.out.println("Enter the elements of the matrix:");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = scanner.nextDouble();
            }
        }

        double determinant = calculateDeterminant(matrix);
        double trace = calculateTrace(matrix);

        System.out.println("Determinant: " + determinant);
        System.out.println("Trace: " + trace);

    }


    private static double calculateDeterminant(double[][] matrix) {
        double determinant = 1;

        for (int i = 0; i < matrix.length; i++) {
            determinant *= matrix[i][i];
        }

        return determinant;
    }

    private static double calculateTrace(double[][] matrix) {
        double trace = 0;

        for (int i = 0; i < matrix.length; i++) {
            trace += matrix[i][i];
        }

        return trace;
    }
}
