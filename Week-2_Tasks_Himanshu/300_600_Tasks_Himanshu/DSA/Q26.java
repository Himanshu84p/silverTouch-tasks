import java.util.Random;

public class Q26 {

    public static void main(String[] args) {

        double[] probabilities = { 0.2, 0.3, 0.5 };
        int[] values = { 1, 2, 3 };

        int generatedNumber = generateRandomNumber(probabilities, values);
        System.out.println("Generated Number: " + generatedNumber);
    }

    public static int generateRandomNumber(double[] probabilities, int[] values) {
        if (probabilities.length != values.length || probabilities.length == 0) {
            throw new IllegalArgumentException("Invalid input arrays");
        }

        double[] cumulativeProbabilities = new double[probabilities.length];
        cumulativeProbabilities[0] = probabilities[0];
        for (int i = 1; i < probabilities.length; i++) {
            cumulativeProbabilities[i] = cumulativeProbabilities[i - 1] + probabilities[i];
        }

        double randomValue = Math.random();

        for (int i = 0; i < cumulativeProbabilities.length; i++) {
            if (randomValue <= cumulativeProbabilities[i]) {
                return values[i];
            }
        }

        throw new RuntimeException("Error in random number generation");
    }
}
