import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class SkipNode {
    int key;
    List<SkipNode> forward;

    public SkipNode(int key, int level) {
        this.key = key;
        this.forward = new ArrayList<>(level + 1);
        for (int i = 0; i <= level; i++) {
            this.forward.add(null);
        }
    }
}

public class Q44 {
    private static final int MAX_LEVEL = 16;
    private SkipNode head;
    private int maxLevel;

    public Q44() {
        this.head = new SkipNode(Integer.MIN_VALUE, MAX_LEVEL);
        this.maxLevel = 0;
    }

    private int randomLevel() {
        int level = 0;
        Random random = new Random();
        while (level < MAX_LEVEL && random.nextBoolean()) {
            level++;
        }
        return level;
    }

    public void insert(int key) {
        int level = randomLevel();

        if (level > maxLevel) {
            maxLevel = level;
        }

        SkipNode newNode = new SkipNode(key, level);
        SkipNode current = head;

        for (int i = maxLevel; i >= 0; i--) {
            while (current.forward.get(i) != null && current.forward.get(i).key < key) {
                current = current.forward.get(i);
            }
            if (i <= level) {
                newNode.forward.set(i, current.forward.get(i));
                current.forward.set(i, newNode);
            }
        }
    }

    public boolean search(int key) {
        SkipNode current = head;
        for (int i = maxLevel; i >= 0; i--) {
            while (current.forward.get(i) != null && current.forward.get(i).key < key) {
                current = current.forward.get(i);
            }
        }
        current = current.forward.get(0);

        return current != null && current.key == key;
    }

    public void delete(int key) {
        SkipNode current = head;
        SkipNode[] update = new SkipNode[MAX_LEVEL + 1];

        for (int i = maxLevel; i >= 0; i--) {
            while (current.forward.get(i) != null && current.forward.get(i).key < key) {
                current = current.forward.get(i);
            }
            update[i] = current;
        }

        current = current.forward.get(0);

        if (current != null && current.key == key) {
            for (int i = 0; i <= maxLevel; i++) {
                if (update[i].forward.get(i) != current) {
                    break;
                }
                update[i].forward.set(i, current.forward.get(i));
            }

            while (maxLevel > 0 && head.forward.get(maxLevel) == null) {
                maxLevel--;
            }
        }
    }

    public void printSkipGraph() {
        for (int i = maxLevel; i >= 0; i--) {
            SkipNode current = head;
            System.out.print("Level " + i + ": ");
            while (current.forward.get(i) != null) {
                System.out.print(current.forward.get(i).key + " ");
                current = current.forward.get(i);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Q44 skipGraph = new Q44();

        int[] keys = { 30, 10, 50, 5, 20, 40, 60 };
        for (int key : keys) {
            skipGraph.insert(key);
        }

        System.out.println("Skip Graph after insertions:");
        skipGraph.printSkipGraph();

        int searchKey = 40;
        boolean found = skipGraph.search(searchKey);
        System.out.println("Is " + searchKey + " present in the skip graph? " + found);

        int deleteKey = 20;
        skipGraph.delete(deleteKey);
        System.out.println("Skip Graph after deletion of key " + deleteKey + ":");
        skipGraph.printSkipGraph();
    }
}
