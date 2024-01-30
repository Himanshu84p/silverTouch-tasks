import java.util.Random;

public class P36 {

    private static final Random random = new Random();

    public static int floydRivestSelect(int[] array, int k) {
        return select(array, 0, array.length - 1, k);
    }

    private static int select(int[] array, int left, int right, int k) {
        while (right > left) {
            if (right - left > 600) {
                int n = right - left + 1;
                int i = k - left + 1;
                int z = (int) (Math.log(n) / Math.log(2));
                int s = 17 * z;
                int sd = 0;

                if (n > 16) {
                    sd = (int) (0.5 * Math.sqrt(z * i * (n - i) / n) * random.nextDouble() * (random.nextBoolean() ? 1 : -1));
                }

                int newLeft = Math.max(left, k - i * 2);
                int newRight = Math.min(right, k + (n - i) * 2);
                select(array, newLeft, newRight, k);

                int[] tempArray = new int[right - left + 1];
                for (int j = 0; j < tempArray.length; j++) {
                    tempArray[j] = array[left + j];
                }

                int[] partition = partition(tempArray, i - 1 + newLeft - left, i + newRight - left, sd);
                for (int j = left; j <= right; j++) {
                    array[j] = tempArray[j - left];
                }

                if (k <= partition[0]) {
                    right = left + partition[0] - 1;
                } else if (k <= partition[1]) {
                    return k;
                } else {
                    left = left + partition[1] + 1;
                }
            } else {
                for (int i = left + 1; i <= right; i++) {
                    if (array[i - 1] > array[i]) {
                        swap(array, i - 1, i);
                    }
                }
                return array[k];
            }
        }
        return array[k];
    }

    private static int[] partition(int[] array, int left, int right, double sd) {
        int i = left - 1;
        int j = right;
        int p = left - 1;
        int q = right;

        while (true) {
            while (array[++i] < array[right]) ;
            while (array[right] < array[--j]) {
                if (j == left) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            swap(array, i, j);
            if (array[i] == array[right]) {
                p++;
                swap(array, p, i);
            }
            if (array[j] == array[right]) {
                q--;
                swap(array, q, j);
            }
        }
        swap(array, i, right);

        j = i - 1;
        i = i + 1;
        for (int k = left; k <= p; k++, j--) {
            swap(array, k, j);
        }
        for (int k = right - 1; k >= q; k--, i++) {
            swap(array, k, i);
        }

        return new int[]{j + 1, i - 1};
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        int[] array = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5};

        int k = 5;
        int result = floydRivestSelect(array, k - 1);

        System.out.println("The " + k + "-th smallest element is: " + result);
    }
}
