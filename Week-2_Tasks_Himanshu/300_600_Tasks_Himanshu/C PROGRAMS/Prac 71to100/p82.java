import java.util.BitSet;
import java.util.Random;

public class p82 {
    private BitSet bitSet;
    private int size;
    private int numHashFunctions;

    
    public p82(int size, int numHashFunctions) {
        this.bitSet = new BitSet(size);
        this.size = size;
        this.numHashFunctions = numHashFunctions;
    }

    
    public void add(String element) {
        for (int i = 0; i < numHashFunctions; i++) {
            int hash = hash(element, i);
            bitSet.set(hash % size, true);
        }
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

    
    private int hash(String element, int seed) {
        Random random = new Random(seed);
        return (element.hashCode() ^ random.nextInt()) & Integer.MAX_VALUE;
    }

    public static void main(String[] args) {
        p82 bloomFilter = new p82(1000, 3);

        
        bloomFilter.add("apple");
        bloomFilter.add("banana");
        bloomFilter.add("orange");

        
        System.out.println("Contains 'apple': " + bloomFilter.contains("apple"));
        System.out.println("Contains 'grape': " + bloomFilter.contains("grape"));
    }
}
