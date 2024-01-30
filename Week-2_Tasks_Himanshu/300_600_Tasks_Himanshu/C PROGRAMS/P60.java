public class P60 {

    private static boolean oracleFunction(boolean[] input) {

        int sum = 0;
        for (boolean bit : input) {
            if (bit) {
                sum++;
            }
        }
        return sum % 2 == 0;
    }


    private static int buhrmanMiltersenAlgorithm(int n) {
        int k = 0;
        boolean[] input = new boolean[n];

        while (!oracleFunction(input)) {
            k++;

            for (int i = 0; i < n; i++) {
                input[i] = !input[i];
            }
        }

        return k;
    }

    public static void main(String[] args) {
        int inputSize = 8;

        int quantumQueryComplexity = buhrmanMiltersenAlgorithm(inputSize);

        System.out.println("Quantum Query Complexity: " + quantumQueryComplexity);
    }
}
