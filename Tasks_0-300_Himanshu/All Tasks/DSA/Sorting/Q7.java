public class Q7 {

    public static void countingSort(int[] array) {
        int n = array.length;

        int max = array[0];
        for (int i = 1; i < n; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }

        int[] count = new int[max + 1];

        for (int i = 0; i < n; i++) {
            count[array[i]]++;
        }

        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
        }

        int[] result = new int[n];

        for (int i = n - 1; i >= 0; i--) {
            result[count[array[i]] - 1] = array[i];
            count[array[i]]--;
        }

        System.arraycopy(result, 0, array, 0, n);
    }

    public static void main(String[] args) {
        int[] array = { 4, 2, 1, 5, 3, 5, 7, 2 };

        System.out.println("Original array: " + arrayToString(array));

        countingSort(array);

        System.out.println("Sorted array: " + arrayToString(array));
    }

    private static String arrayToString(int[] array) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i < array.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
