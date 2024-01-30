public class Q12 {

    public static void main(String[] args) {
        int N1 = 2;
        int[] arr1 = { 3, 0, 2, 1 };
        System.out.println("Minimum operations: " + minOperations(N1, arr1));

        int N2 = 3;
        int[] arr2 = { 1, 0, 3, 2, 4, 5 };
        System.out.println("Minimum operations: " + minOperations(N2, arr2));
    }

    public static int minOperations(int N, int[] arr) {
        int operations = 0;

        for (int i = 0; i < N - 1; i++) {
            if ((arr[i] % 2 == 0 && arr[i + 1] == arr[i] + 1) || (arr[i] % 2 == 1 && arr[i + 1] == arr[i] - 1)) {

                continue;
            } else {

                int temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
                operations++;
            }
        }

        return operations;
    }
}
