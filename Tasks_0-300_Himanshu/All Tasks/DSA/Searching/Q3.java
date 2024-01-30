import java.util.Arrays;

public class Q3 {

    private static int findFirstOccurrence(int[] array, int target) {
        int low = 0, high = array.length - 1, result = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (array[mid] == target) {
                result = mid;
                high = mid - 1;
            } else if (array[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return result;
    }

    private static int findLastOccurrence(int[] array, int target) {
        int low = 0, high = array.length - 1, result = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (array[mid] == target) {
                result = mid;
                low = mid + 1;
            } else if (array[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] sortedArray = { 1, 2, 2, 2, 3, 4, 4, 4, 5 };

        int targetElement = 2;

        int firstOccurrence = findFirstOccurrence(sortedArray, targetElement);

        int lastOccurrence = findLastOccurrence(sortedArray, targetElement);

        System.out.println("Array: " + Arrays.toString(sortedArray));
        System.out.println("Target Element: " + targetElement);
        System.out.println("First Occurrence Index: " + firstOccurrence);
        System.out.println("Last Occurrence Index: " + lastOccurrence);
    }
}
