public class Q4 {

    private static int searchInRotatedArray(int[] array, int target) {
        int low = 0;
        int high = array.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (array[mid] == target) {
                return mid;
            }

            if (array[low] <= array[mid]) {

                if (array[low] <= target && target < array[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            else {

                if (array[mid] < target && target <= array[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] rotatedArray = { 4, 5, 6, 7, 0, 1, 2 };
        int targetElement = 0;

        int result = searchInRotatedArray(rotatedArray, targetElement);

        if (result != -1) {
            System.out.println("Element " + targetElement + " found at index " + result);
        } else {
            System.out.println("Element " + targetElement + " not found in the array");
        }
    }
}
