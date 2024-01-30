public class P53 {

    private static int kadane(int[] arr) {
        int maxSum = arr[0];
        int currentSum = arr[0];

        for (int i = 1; i < arr.length; i++) {
            currentSum = Math.max(arr[i], currentSum + arr[i]);
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    private static int maxCircularSum(int[] arr) {
        int totalSum = 0;
        int maxStraightSum = kadane(arr);

        for (int i = 0; i < arr.length; i++) {
            totalSum += arr[i];
            arr[i] = -arr[i];
        }


        int maxCircularSum = totalSum + kadane(arr);

        if (maxCircularSum == 0) {
            return maxStraightSum;
        }

        return Math.max(maxStraightSum, maxCircularSum);
    }

    public static void main(String[] args) {
        int[] arr = {1, -2, 3, -2};

        int result = maxCircularSum(arr);

        System.out.println("Maximum Subarray Sum Circular: " + result);
    }
}
