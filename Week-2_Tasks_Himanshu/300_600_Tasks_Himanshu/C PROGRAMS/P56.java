import java.util.Random;

class SkipNode {
    int value;
    SkipNode[] forward;

    public SkipNode(int value, int level) {
        this.value = value;
        this.forward = new SkipNode[level + 1];
    }
}

public class P56 {
    private static final int MAX_LEVEL = 16;
    private int level;
    private SkipNode head;
    private Random random;

    public P56() {
        this.level = 0;
        this.head = new SkipNode(Integer.MIN_VALUE, MAX_LEVEL);
        this.random = new Random();
    }


    private int randomLevel() {
        int level = 0;
        while (random.nextBoolean() && level < MAX_LEVEL) {
            level++;
        }
        return level;
    }


    public void insert(int value) {
        SkipNode[] update = new SkipNode[MAX_LEVEL + 1];
        SkipNode current = head;

        for (int i = level; i >= 0; i--) {
            while (current.forward[i] != null && current.forward[i].value < value) {
                current = current.forward[i];
            }
            update[i] = current;
        }

        int newLevel = randomLevel();

        if (newLevel > level) {
            for (int i = level + 1; i <= newLevel; i++) {
                update[i] = head;
            }
            level = newLevel;
        }

        SkipNode newNode = new SkipNode(value, newLevel);

        for (int i = 0; i <= newLevel; i++) {
            newNode.forward[i] = update[i].forward[i];
            update[i].forward[i] = newNode;
        }
    }

    // Search for a value in the skip list
    public boolean search(int value) {
        SkipNode current = head;

        for (int i = level; i >= 0; i--) {
            while (current.forward[i] != null && current.forward[i].value < value) {
                current = current.forward[i];
            }
        }

        current = current.forward[0];

        return current != null && current.value == value;
    }

    // Print the elements of the skip list
    public void print() {
        for (int i = level; i >= 0; i--) {
            SkipNode current = head.forward[i];
            System.out.print("Level " + i + ": ");
            while (current != null) {
                System.out.print(current.value + " ");
                current = current.forward[i];
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        P56 skipList = new P56();

        skipList.insert(3);
        skipList.insert(6);
        skipList.insert(7);
        skipList.insert(9);
        skipList.insert(12);
        skipList.insert(19);
        skipList.insert(17);
        skipList.insert(26);
        skipList.insert(21);
        skipList.insert(25);

        skipList.print();

        int searchValue = 17;
        boolean found = skipList.search(searchValue);

        System.out.println("Search for " + searchValue + ": " + (found ? "Found" : "Not found"));
    }
}
