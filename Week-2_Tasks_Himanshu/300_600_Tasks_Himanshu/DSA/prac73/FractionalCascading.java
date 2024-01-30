package prac73;

import java.util.Arrays;
import java.util.List;

public class FractionalCascading {
    private static final int EMPTY_MARKER = Integer.MIN_VALUE;

    private static void buildFractionalCascading(List<int[]> arrays, int[][] fc, int start, int end) {
        if (start > end) {
            return;
        }

        int mid = (start + end) / 2;
        int midValue = arrays.get(mid)[0];

        for (int[] array : arrays.get(mid + 1)) {
            int index = Arrays.binarySearch(arrays.get(mid), array[0]);
            int nextIndex = Arrays.binarySearch(arrays.get(mid + 1), array[0]);

            if (nextIndex >= 0) {
                fc[mid][index] = nextIndex;
            } else {
                fc[mid][-nextIndex - 1] = -1;
            }
        }

        int left = start;
        int right = end;

        while (left < right) {
            int nextMid = (left + right) / 2;

            if (arrays.get(nextMid)[0] <= midValue) {
                left = nextMid + 1;
            } else {
                right = nextMid;
            }
        }

        buildFractionalCascading(arrays, fc, start, left - 1);
        buildFractionalCascading(arrays, fc, left, end);
    }

    private static int fractionalCascadingSearch(List<int[]> arrays, int[][] fc, int target) {
        int level = 0;
        int index = Arrays.binarySearch(arrays.get(level), target);

        while (level < arrays.size() - 1 && index < 0) {
            level++;
            int nextLevelIndex = -index - 1;
            int nextArraySize = arrays.get(level).length;

            if (nextLevelIndex < nextArraySize) {
                index = fc[level][nextLevelIndex];
            } else {
                index = nextArraySize;
            }
        }

        return (index >= 0) ? arrays.get(level)[index] : EMPTY_MARKER;
    }

    public static void main(String[] args) {

        int[] array1 = { 1, 3, 5, 7, 9 };
        int[] array2 = { 2, 4, 6, 8, 10 };
        int[] array3 = { 3, 6, 9, 12, 15 };

        List<int[]> arrays = List.of(array1, array2, array3);

        int numArrays = arrays.size();
        int maxLength = arrays.stream().mapToInt(array -> array.length).max().orElse(0);
        int[][] fc = new int[numArrays][maxLength];

        buildFractionalCascading(arrays, fc, 0, numArrays - 1);

        int target1 = 6;
        int target2 = 8;

        int result1 = fractionalCascadingSearch(arrays, fc, target1);
        int result2 = fractionalCascadingSearch(arrays, fc, target2);

        System.out.println("Fractional Cascading Search for " + target1 + ": " + result1);
        System.out.println("Fractional Cascading Search for " + target2 + ": " + result2);
    }
}
