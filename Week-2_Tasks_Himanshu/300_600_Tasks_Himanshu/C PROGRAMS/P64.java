public class P64 {

    public static int minJumps(int[] arr) {
        int n = arr.length;

        if (n <= 1) {
            return 0;
        }

        if (arr[0] == 0) {
            return -1;
        }

        int maxReach = arr[0];
        int steps = arr[0];
        int jumps = 1;

        for (int i = 1; i < n; i++) {
            if (i == n - 1) {
                return jumps;
            }

            maxReach = Math.max(maxReach, i + arr[i]);
            steps--;

            if (steps == 0) {
                jumps++;

                if (i >= maxReach) {
                    return -1;
                }

                steps = maxReach - i;
            }
        }

        return -1;}

    public static void main(String[] args) {
        int[] arr = {2, 3, 1, 1, 2, 4, 2, 0, 1, 1};

        int result = minJumps(arr);

        if (result != -1) {
            System.out.println("Minimum number of jumps to reach the end: " + result);
        } else {
            System.out.println("Unable to reach the end.");
        }
    }
}
