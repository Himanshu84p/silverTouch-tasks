import java.util.HashMap;
import java.util.Map;

public class p81 {
    
    public static int countSubarraysWithSum(int[] nums, int targetSum) {
        int count = 0;
        int prefixSum = 0;
        Map<Integer, Integer> prefixSumCount = new HashMap<>();
        prefixSumCount.put(0, 1);

        for (int num : nums) {
            prefixSum += num;
            if (prefixSumCount.containsKey(prefixSum - targetSum)) {
                count += prefixSumCount.get(prefixSum - targetSum);
            }
            prefixSumCount.put(prefixSum, prefixSumCount.getOrDefault(prefixSum, 0) + 1);
        }

        return count;
    }

    public static void main(String[] args) {
        int[] nums = { 1, 2, 3, 4, 5 };
        int targetSum = 9;

        int result = countSubarraysWithSum(nums, targetSum);

        System.out.println("Count of Subarrays with Sum " + targetSum + ": " + result);
    }
}
