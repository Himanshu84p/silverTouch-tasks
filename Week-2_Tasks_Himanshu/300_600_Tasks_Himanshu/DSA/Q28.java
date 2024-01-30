import java.util.Random;

class Node {
    int value;
    Node[] forward;

    public Node(int value, int level) {
        this.value = value;
        this.forward = new Node[level + 1];
    }
}

public class Q28 {
    private static final int MAX_LEVEL = 16;
    private int level;
    private Node header;
    private Random random;

    public Q28() {
        this.level = 0;
        this.header = new Node(Integer.MIN_VALUE, MAX_LEVEL);
        this.random = new Random();
    }

    public boolean search(int target) {
        Node current = header;

        for (int i = level; i >= 0; i--) {
            while (current.forward[i] != null && current.forward[i].value < target) {
                current = current.forward[i];
            }
        }

        current = current.forward[0];

        return current != null && current.value == target;
    }

    public void insert(int value) {
        Node[] update = new Node[MAX_LEVEL + 1];
        Node current = header;

        for (int i = level; i >= 0; i--) {
            while (current.forward[i] != null && current.forward[i].value < value) {
                current = current.forward[i];
            }
            update[i] = current;
        }

        int newLevel = randomLevel();

        if (newLevel > level) {
            for (int i = level + 1; i <= newLevel; i++) {
                update[i] = header;
            }
            level = newLevel;
        }

        Node newNode = new Node(value, newLevel);

        for (int i = 0; i <= newLevel; i++) {
            newNode.forward[i] = update[i].forward[i];
            update[i].forward[i] = newNode;
        }
    }

    public void delete(int target) {
        Node[] update = new Node[MAX_LEVEL + 1];
        Node current = header;

        for (int i = level; i >= 0; i--) {
            while (current.forward[i] != null && current.forward[i].value < target) {
                current = current.forward[i];
            }
            update[i] = current;
        }

        current = current.forward[0];

        if (current != null && current.value == target) {
            for (int i = 0; i <= level; i++) {
                if (update[i].forward[i] != current) {
                    break;
                }
                update[i].forward[i] = current.forward[i];
            }

            while (level > 0 && header.forward[level] == null) {
                level--;
            }
        }
    }

    private int randomLevel() {
        int level = 0;
        while (level < MAX_LEVEL && random.nextDouble() < 0.5) {
            level++;
        }
        return level;
    }

    public void print() {
        for (int i = level; i >= 0; i--) {
            Node current = header.forward[i];
            System.out.print("Level " + i + ": ");
            while (current != null) {
                System.out.print(current.value + " ");
                current = current.forward[i];
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Q28 skipList = new Q28();

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

        System.out.println("Search 19: " + skipList.search(19));
        System.out.println("Search 8: " + skipList.search(8));

        skipList.delete(19);
        skipList.delete(17);
        System.out.println("After Deletion:");
        skipList.print();
    }
}
