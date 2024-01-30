import java.util.BitSet;
import java.util.function.Function;

public class P33<T> {

    private BitSet bitSet;
    private int size;
    private int[] hashFunctions;

    public P33(int size, int numHashFunctions) {
        this.size = size;
        this.bitSet = new BitSet(size);
        this.hashFunctions = new int[numHashFunctions];
        generateHashFunctions();
    }

    public void add(T element) {
        for (int hashFunction : hashFunctions) {
            int index = hash(element, hashFunction) % size;
            bitSet.set(index, true);
        }
    }

    public boolean contains(T element) {
        for (int hashFunction : hashFunctions) {
            int index = hash(element, hashFunction) % size;
            if (!bitSet.get(index)) {
                return false;
            }
        }
        return true;
    }

    private void generateHashFunctions() {
        for (int i = 0; i < hashFunctions.length; i++) {
            hashFunctions[i] = (int) (Math.random() * Integer.MAX_VALUE);
        }
    }

    private int hash(T element, int hashFunction) {
        return Math.abs(element.hashCode() ^ hashFunction);
    }

    public static void main(String[] args) {
        P33<String> bloomFilter = new P33<>(100, 3);

        bloomFilter.add("apple");
        bloomFilter.add("banana");
        bloomFilter.add("cherry");

        System.out.println("Contains 'apple': " + bloomFilter.contains("apple"));     // true
        System.out.println("Contains 'orange': " + bloomFilter.contains("orange"));   // false (may have false positive)
        System.out.println("Contains 'cherry': " + bloomFilter.contains("cherry"));   // true
    }
}
