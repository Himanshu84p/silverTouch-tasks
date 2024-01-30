import java.util.Arrays;

public class p90 {
    public static int minTaps(int n, int[] ranges) {
        int[] intervals = new int[n + 1];

        
        for (int i = 0; i <= n; i++) {
            int start = Math.max(0, i - ranges[i]);
            int end = Math.min(n, i + ranges[i]);
            intervals[start] = Math.max(intervals[start], end);
        }

        int taps = 0;
        int currentEnd = 0;
        int nextEnd = 0;

        for (int i = 0; i <= n; i++) {
            nextEnd = Math.max(nextEnd, intervals[i]);

            if (i == currentEnd) {
                if (nextEnd == currentEnd) {
                    return -1; 
                }

                taps++;
                currentEnd = nextEnd;
            }
        }

        return taps;
    }

    public static void main(String[] args) {
        int n = 5;
        int[] ranges = { 3, 4, 1, 1, 0, 0 };

        int result = minTaps(n, ranges);

        System.out.println("Minimum Number of Taps to Water the Garden: " + result);
    }
}
