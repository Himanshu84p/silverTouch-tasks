import java.util.Arrays;

public class KthSmallestLargestElement {

    public static int findKthSmallestElement(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[k - 1];
    }

    public static int findKthLargestElement(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    public static void main(String[] args) {
        int[] nums = { 3, 1, 4, 4, 2, 2, 1, 3, 3 };
        int k = 4;

        System.out.println(k + "th Smallest Element: " + findKthSmallestElement(nums, k));
        System.out.println(k + "th Largest Element: " + findKthLargestElement(nums, k));
    }
}
