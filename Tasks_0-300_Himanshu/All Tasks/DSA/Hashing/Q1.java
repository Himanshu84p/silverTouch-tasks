import java.util.LinkedList;

class HashTable {
    private static final int TABLE_SIZE = 10;
    private LinkedList<Pair>[] table;

    public HashTable() {
        table = new LinkedList[TABLE_SIZE];
        for (int i = 0; i < TABLE_SIZE; i++) {
            table[i] = new LinkedList<>();
        }
    }

    private static class Pair {
        int key;
        String value;

        public Pair(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    private int hashFunction(int key) {
        return key % TABLE_SIZE;
    }

    public void insert(int key, String value) {
        int index = hashFunction(key);
        LinkedList<Pair> bucket = table[index];

        for (Pair pair : bucket) {
            if (pair.key == key) {
                pair.value = value;
                return;
            }
        }

        bucket.add(new Pair(key, value));
    }

    public String search(int key) {
        int index = hashFunction(key);
        LinkedList<Pair> bucket = table[index];

        for (Pair pair : bucket) {
            if (pair.key == key) {
                return pair.value;
            }
        }

        return null;
    }

    public void delete(int key) {
        int index = hashFunction(key);
        LinkedList<Pair> bucket = table[index];

        for (Pair pair : bucket) {
            if (pair.key == key) {
                bucket.remove(pair);
                return;
            }
        }
    }
}

public class Q1 {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable();

        hashTable.insert(1, "One");
        hashTable.insert(2, "Two");
        hashTable.insert(11, "Eleven");

        System.out.println("Value for key 2: " + hashTable.search(2));

        hashTable.delete(2);

        System.out.println("Value for key 2 after deletion: " + hashTable.search(2));
    }
}
