import java.util.Random;

public class P63 {

    private final int depth;
    private final int width;
    private final int[][] sketch;
    private final int[] hashA;
    private final int hashB;

    public P63(int depth, int width) {
        this.depth = depth;
        this.width = width;
        this.sketch = new int[depth][width];
        this.hashA = new int[depth];
        this.hashB = new Random().nextInt(Integer.MAX_VALUE);

        for (int i = 0; i < depth; i++) {
            hashA[i] = new Random().nextInt(Integer.MAX_VALUE);
        }
    }

    public void update(String element, int count) {
        for (int i = 0; i < depth; i++) {
            int hashValue = hash(element, i);
            sketch[i][hashValue] += count;
        }
    }

    public int estimate(String element) {
        int minCount = Integer.MAX_VALUE;

        for (int i = 0; i < depth; i++) {
            int hashValue = hash(element, i);
            minCount = Math.min(minCount, sketch[i][hashValue]);
        }

        return minCount;
    }

    private int hash(String element, int index) {
        long hashValue = (long) hashA[index] * element.hashCode() + hashB;
        hashValue %= Integer.MAX_VALUE;
        return (int) (hashValue % width);
    }

    public static void main(String[] args) {
        P63 countMinSketch = new P63(5, 100);

        countMinSketch.update("apple", 3);
        countMinSketch.update("banana", 5);
        countMinSketch.update("apple", 2);


        int countApple = countMinSketch.estimate("apple");
        int countBanana = countMinSketch.estimate("banana");

        System.out.println("Estimated count of 'apple': " + countApple);
        System.out.println("Estimated count of 'banana': " + countBanana);
    }
}
