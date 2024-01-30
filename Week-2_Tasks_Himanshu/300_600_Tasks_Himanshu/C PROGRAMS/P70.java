import java.util.HashMap;
import java.util.Map;

public class P70 {

    public static int findShortestSubarray(int[] nums) {
        Map<Integer, Integer> count = new HashMap<>();
        Map<Integer, Integer> left = new HashMap<>();
        Map<Integer, Integer> right = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            count.put(nums[i], count.getOrDefault(nums[i], 0) + 1);

            if (!left.containsKey(nums[i])) {
                left.put(nums[i], i);
            }

            right.put(nums[i], i);
        }

        int maxDegree = 0;
        for (int num : count.keySet()) {
            maxDegree = Math.max(maxDegree, count.get(num));
        }

        int minLength = nums.length;
        for (int num : count.keySet()) {
            if (count.get(num) == maxDegree) {
                minLength = Math.min(minLength, right.get(num) - left.get(num) + 1);
            }
        }

        return minLength;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 3, 1, 4, 2};

        int minLength = findShortestSubarray(nums);
        System.out.println("The degree of the array is: " + minLength);
    }
}
