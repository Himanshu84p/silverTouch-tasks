import java.util.BitSet;

public class Q30 {
    private int size;
    private BitSet bitSet;
    private int[] hashFunctions;

    public Q30(int size, int numHashFunctions) {
        this.size = size;
        this.bitSet = new BitSet(size);
        this.hashFunctions = new int[numHashFunctions];

        for (int i = 0; i < numHashFunctions; i++) {
            hashFunctions[i] = (int) (Math.random() * Integer.MAX_VALUE);
        }
    }

    public void add(String element) {
        for (int hashFunction : hashFunctions) {
            int index = hash(element, hashFunction) % size;
            bitSet.set(index, true);
        }
    }

    public boolean contains(String element) {
        for (int hashFunction : hashFunctions) {
            int index = hash(element, hashFunction) % size;
            if (!bitSet.get(index)) {
                return false;
            }
        }
        return true;
    }

    private int hash(String element, int hashFunction) {
        return (element.hashCode() ^ hashFunction) & Integer.MAX_VALUE;
    }

    public static void main(String[] args) {
        Q30 bloomFilter = new Q30(1000, 3);

        bloomFilter.add("apple");
        bloomFilter.add("banana");
        bloomFilter.add("orange");

        System.out.println("Contains 'apple': " + bloomFilter.contains("apple"));
        System.out.println("Contains 'grape': " + bloomFilter.contains("grape"));
    }
}
