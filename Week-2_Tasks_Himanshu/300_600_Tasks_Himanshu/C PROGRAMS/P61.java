import java.util.*;

public class P61 {

    public static List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }


        PriorityQueue<Integer> minHeap = new PriorityQueue<>(Comparator.comparingInt(frequencyMap::get));

        for (int num : frequencyMap.keySet()) {
            minHeap.add(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        List<Integer> topKFrequentElements = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            topKFrequentElements.add(minHeap.poll());
        }

        Collections.reverse(topKFrequentElements);

        return topKFrequentElements;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;

        List<Integer> result = topKFrequent(nums, k);

        System.out.println("Top " + k + " frequent elements: " + result);
    }
}
