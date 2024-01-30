import java.util.Arrays;

public class p80 {
    public static int[] parallelPrefixSum(int[] input) {
        int[] result = Arrays.copyOf(input, input.length);
        Arrays.parallelPrefix(result, (x, y) -> x + y);
        return result;
    }

    public static void main(String[] args) {
        int[] inputArray = { 1, 2, 3, 4, 5, 6, 7, 8 };

        System.out.println("Input Array: " + Arrays.toString(inputArray));

        int[] prefixSum = parallelPrefixSum(inputArray);

        System.out.println("Parallel Prefix Sum: " + Arrays.toString(prefixSum));
    }
}
