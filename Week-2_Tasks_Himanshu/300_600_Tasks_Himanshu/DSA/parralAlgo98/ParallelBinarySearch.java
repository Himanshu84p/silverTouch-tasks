package parralAlgo98;

import java.util.Arrays;



public class ParallelBinarySearch {

    private static int localBinarySearch(int[] array, int target) {
        int low = 0;
        int high = array.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int midValue = array[mid];

            if (midValue == target) {
                return mid;
            } else if (midValue < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    public static int parallelBinarySearch(int[][] distributedArray, int target) {
        return Arrays.stream(distributedArray)
                .parallel()
                .map(array -> localBinarySearch(array, target))
                .filter(index -> index != -1)
                .findFirst()
                .orElse(-1);
    }

    public static void main(String[] args) {
        int[][] distributedArray = {
                {1, 3, 5, 7},
                {9, 11, 13, 15},
                {17, 19, 21, 23}
        };

        int target = 13;

        int result = parallelBinarySearch(distributedArray, target);

        if (result != -1) {
            System.out.println("Element " + target + " found at index " + result);
        } else {
            System.out.println("Element " + target + " not found.");
        }
    }
}
