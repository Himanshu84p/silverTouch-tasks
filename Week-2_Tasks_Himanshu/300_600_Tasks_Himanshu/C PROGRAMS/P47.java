import java.util.Random;

class SkipListNode {
    int value;
    SkipListNode[] forward;

    SkipListNode(int value, int level) {
        this.value = value;
        this.forward = new SkipListNode[level + 1];
    }
}

public class P47 {
    private static final int MAX_LEVEL = 16;
    private int level;
    private SkipListNode head;
    private Random random;

    public P47() {
        this.level = 0;
        this.head = new SkipListNode(Integer.MIN_VALUE, MAX_LEVEL);
        this.random = new Random();
    }

    private int randomLevel() {
        int level = 0;
        while (level < MAX_LEVEL && random.nextDouble() < 0.5) {
            level++;
        }
        return level;
    }

    public void insert(int value) {
        SkipListNode[] update = new SkipListNode[MAX_LEVEL + 1];
        SkipListNode current = head;

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

        SkipListNode newNode = new SkipListNode(value, newLevel);
        for (int i = 0; i <= newLevel; i++) {
            newNode.forward[i] = update[i].forward[i];
            update[i].forward[i] = newNode;
        }
    }

    public boolean search(int value) {
        SkipListNode current = head;
        for (int i = level; i >= 0; i--) {
            while (current.forward[i] != null && current.forward[i].value < value) {
                current = current.forward[i];
            }
        }
        current = current.forward[0];
        return current != null && current.value == value;
    }

    public void printSkipList() {
        for (int i = level; i >= 0; i--) {
            SkipListNode current = head;
            System.out.print("Level " + i + ": ");
            while (current.forward[i] != null) {
                System.out.print(current.forward[i].value + " ");
                current = current.forward[i];
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        P47 skipList = new P47();

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

        skipList.printSkipList();

        int searchValue = 17;
        System.out.println("Search for " + searchValue + ": " + skipList.search(searchValue));

        int nonExistentValue = 10;
        System.out.println("Search for " + nonExistentValue + ": " + skipList.search(nonExistentValue));
    }
}
