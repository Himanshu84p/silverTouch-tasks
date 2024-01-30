import java.util.BitSet;

public class Q49 {
    private int size;
    private int[] counters;

    public Q49(int size) {
        this.size = size;
        this.counters = new int[size];
    }

    public void add(String element) {
        int hash1 = element.hashCode() % size;
        int hash2 = Math.abs(element.hashCode() % size);

        counters[hash1]++;
        counters[hash2]++;
    }

    public boolean contains(String element) {
        int hash1 = element.hashCode() % size;
        int hash2 = Math.abs(element.hashCode() % size);

        return counters[hash1] > 0 && counters[hash2] > 0;
    }

    public void remove(String element) {
        if (contains(element)) {
            int hash1 = element.hashCode() % size;
            int hash2 = Math.abs(element.hashCode() % size);

            counters[hash1]--;
            counters[hash2]--;
        }
    }

    public static void main(String[] args) {
        Q49 filter = new Q49(100);

        filter.add("apple");
        filter.add("orange");
        filter.add("banana");

        System.out.println(filter.contains("apple"));
        System.out.println(filter.contains("grape"));

        filter.remove("apple");
        System.out.println(filter.contains("apple"));
    }
}
