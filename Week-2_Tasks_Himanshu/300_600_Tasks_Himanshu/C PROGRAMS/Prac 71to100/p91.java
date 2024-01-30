import java.util.BitSet;

public class p91 {
    private BitSet bitSet;
    private int size;
    private int numHashFunctions;
    private int elementsCount;
    private static final double RESIZE_FACTOR = 2.0;

    public p91(int initialSize, int numHashFunctions) {
        this.bitSet = new BitSet(initialSize);
        this.size = initialSize;
        this.numHashFunctions = numHashFunctions;
        this.elementsCount = 0;
    }

    public void add(String element) {
        if (contains(element)) {
            return; 
        }

        if ((double) elementsCount / size >= 0.75) {
            resize();
        }

        for (int i = 0; i < numHashFunctions; i++) {
            int hash = hash(element, i);
            bitSet.set(hash % size, true);
        }

        elementsCount++;
    }

    public boolean contains(String element) {
        for (int i = 0; i < numHashFunctions; i++) {
            int hash = hash(element, i);
            if (!bitSet.get(hash % size)) {
                return false; 
            }
        }
        return true; 
    }

    private void resize() {
        int newSize = (int) (size * RESIZE_FACTOR);
        BitSet newBitSet = new BitSet(newSize);

        for (int i = 0; i < size; i++) {
            if (bitSet.get(i)) {
                for (int j = 0; j < numHashFunctions; j++) {
                    int hash = hash(Integer.toString(i), j);
                    newBitSet.set(hash % newSize, true);
                }
            }
        }

        bitSet = newBitSet;
        size = newSize;
    }

    private int hash(String element, int seed) {
        int hash = 0;
        for (char c : element.toCharArray()) {
            hash = seed * hash + c;
        }
        return Math.abs(hash);
    }

    public static void main(String[] args) {
        p91 bloomFilter = new p91(10, 3);

        bloomFilter.add("apple");
        bloomFilter.add("banana");
        bloomFilter.add("orange");

        System.out.println("Contains 'apple': " + bloomFilter.contains("apple")); 
        System.out.println("Contains 'grape': " + bloomFilter.contains("grape")); 
    }
}
