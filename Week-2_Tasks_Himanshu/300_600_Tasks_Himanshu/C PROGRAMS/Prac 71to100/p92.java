import java.util.Arrays;
import java.util.PriorityQueue;

public class p92 {
    static class Engineer {
        int speed;
        int efficiency;

        public Engineer(int speed, int efficiency) {
            this.speed = speed;
            this.efficiency = efficiency;
        }
    }

    public static int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        Engineer[] engineers = new Engineer[n];
        for (int i = 0; i < n; i++) {
            engineers[i] = new Engineer(speed[i], efficiency[i]);
        }

        Arrays.sort(engineers, (a, b) -> b.efficiency - a.efficiency);

        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);
        long speedSum = 0;
        long maxPerformance = 0;

        for (Engineer engineer : engineers) {
            if (minHeap.size() == k) {
                speedSum -= minHeap.poll();
            }
            speedSum += engineer.speed;
            minHeap.offer(engineer.speed);
            maxPerformance = Math.max(maxPerformance, speedSum * engineer.efficiency);
        }

        int mod = 1_000_000_007;
        return (int) (maxPerformance % mod);
    }

    public static void main(String[] args) {
        int n = 6;
        int[] speed = { 2, 10, 3, 1, 5, 8 };
        int[] efficiency = { 5, 4, 3, 9, 7, 2 };
        int k = 2;

        int result = maxPerformance(n, speed, efficiency, k);

        System.out.println("Maximum Performance of a Team: " + result);
    }
}
