import java.util.Scanner;

public class P5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the value of n: ");
        int n = scanner.nextInt();

        System.out.print("Enter the value of r: ");
        int r = scanner.nextInt();

        long permutationResult = calculatePermutation(n, r);
        long combinationResult = calculateCombination(n, r);

        System.out.println("Permutation (" + n + "P" + r + "): " + permutationResult);
        System.out.println("Combination (" + n + "C" + r + "): " + combinationResult);


    }

    private static long calculatePermutation(int n, int r) {
        return factorial(n) / factorial(n - r);
    }

    // Function to calculate combination (nCr)
    private static long calculateCombination(int n, int r) {
        return factorial(n) / (factorial(r) * factorial(n - r));
    }

    private static long factorial(int num) {
        if (num == 0 || num == 1) {
            return 1;
        } else {
            return num * factorial(num - 1);
        }
    }
}
