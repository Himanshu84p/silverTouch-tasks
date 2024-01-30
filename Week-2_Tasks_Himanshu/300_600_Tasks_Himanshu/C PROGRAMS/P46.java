import java.util.ArrayList;
import java.util.List;

public class P46 {

    public static boolean isSubsetSum(int[] set, int target) {
        List<Integer> subset = new ArrayList<>();
        return isSubsetSum(set, target, 0, subset);
    }

    private static boolean isSubsetSum(int[] set, int target, int index, List<Integer> subset) {
        if (target == 0) {

            System.out.println("Subset with the target sum: " + subset);
            return true;
        }

        if (index == set.length) {

            return false;
        }

        if (isSubsetSum(set, target, index + 1, subset)) {
            return true;
        }

        subset.add(set[index]);
        if (isSubsetSum(set, target - set[index], index + 1, subset)) {
            return true;
        }
        subset.remove(subset.size() - 1);

        return false;
    }

    public static void main(String[] args) {
        int[] set = {3, 34, 4, 12, 5, 2};
        int target = 9;

        boolean result = isSubsetSum(set, target);

        if (result) {
            System.out.println("Subset with the target sum exists.");
        } else {
            System.out.println("Subset with the target sum does not exist.");
        }
    }
}
