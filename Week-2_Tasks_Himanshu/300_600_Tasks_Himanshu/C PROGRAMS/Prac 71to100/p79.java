import java.util.PriorityQueue;

public class p79 {
    public int connectSticks(int[] sticks) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int stick : sticks) {
            minHeap.offer(stick);
        }

        int totalCost = 0;

        while (minHeap.size() > 1) {

            int stick1 = minHeap.poll();
            int stick2 = minHeap.poll();

            int combinedLength = stick1 + stick2;
            totalCost += combinedLength;

            minHeap.offer(combinedLength);
        }

        return totalCost;
    }

    public static void main(String[] args) {
        p79 minCost = new p79();
        int[] sticks = { 2, 4, 3 };

        int result = minCost.connectSticks(sticks);

        System.out.println("Minimum Cost to Connect Sticks: " + result);
    }
}
