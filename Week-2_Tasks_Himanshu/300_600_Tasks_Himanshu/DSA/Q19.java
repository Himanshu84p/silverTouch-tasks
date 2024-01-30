import java.util.Arrays;

public class Q19 {

    public static void main(String[] args) {
        int[] arr1 = { 2, 1, 4, 5, 6 };
        int k1 = 3;
        System.out.println("Can be partitioned: " + canPartitionKSubsets(arr1, k1));

        int[] arr2 = { 2, 1, 5, 5, 6 };
        int k2 = 3;
        System.out.println("Can be partitioned: " + canPartitionKSubsets(arr2, k2));
    }

    public static boolean canPartitionKSubsets(int[] arr, int k) {
        int sum = Arrays.stream(arr).sum();
        if (sum % k != 0) {
            return false;
        }

        int targetSubsetSum = sum / k;
        int[] subsetSums = new int[k];
        Arrays.fill(subsetSums, targetSubsetSum);

        return canPartitionKSubsetsHelper(arr, 0, subsetSums, k);
    }

    private static boolean canPartitionKSubsetsHelper(int[] arr, int index, int[] subsetSums, int k) {
        if (index == arr.length) {

            for (int subsetSum : subsetSums) {
                if (subsetSum != 0) {
                    return false;
                }
            }
            return true;
        }

        for (int i = 0; i < k; i++) {
            if (subsetSums[i] >= arr[index]) {
                subsetSums[i] -= arr[index];
                if (canPartitionKSubsetsHelper(arr, index + 1, subsetSums, k)) {
                    return true;
                }
                subsetSums[i] += arr[index];
            }
        }

        return false;
    }
}
