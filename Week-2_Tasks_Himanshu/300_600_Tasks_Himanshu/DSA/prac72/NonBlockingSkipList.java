package prac72;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicMarkableReference;

class Node {
    final int key;
    final int topLevel;
    final AtomicMarkableReference<Node>[] next;

    public Node(int key, int topLevel) {
        this.key = key;
        this.topLevel = topLevel;
        this.next = new AtomicMarkableReference[topLevel + 1];
        for (int i = 0; i <= topLevel; i++) {
            this.next[i] = new AtomicMarkableReference<>(null, false);
        }
    }
}

public class NonBlockingSkipList {
    private static final int MAX_LEVEL = 10;
    private final Node head = new Node(Integer.MIN_VALUE, MAX_LEVEL);
    private final Node tail = new Node(Integer.MAX_VALUE, MAX_LEVEL);

    public NonBlockingSkipList() {
        for (int i = 0; i <= MAX_LEVEL; i++) {
            head.next[i].set(tail, false);
        }
    }

    public boolean contains(int key) {
        Node[] preds = new Node[MAX_LEVEL + 1];
        Node[] succs = new Node[MAX_LEVEL + 1];
        while (true) {
            int topLevel = find(key, preds, succs);
            Node pred, succ;
            boolean valid = true;
            for (int i = topLevel; i >= 0; i--) {
                pred = preds[i];
                succ = succs[i];
                if (!valid || !pred.next[i].getReference().equals(succ) || pred.next[i].isMarked()) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                return true;
            }
        }
    }

    public boolean add(int key) {
        int topLevel = randomLevel();
        Node[] preds = new Node[MAX_LEVEL + 1];
        Node[] succs = new Node[MAX_LEVEL + 1];
        while (true) {
            int foundLevel = find(key, preds, succs);
            if (foundLevel != -1) {
                Node nodeFound = succs[foundLevel];
                if (!nodeFound.next[0].isMarked()) {
                    while (true) {
                        int topLevelFound = nodeFound.topLevel;
                        if (nodeFound.next[topLevelFound].attemptMark(nodeFound.next[topLevelFound].getReference(),
                                true)) {
                            break;
                        }
                    }
                    continue;
                }
            }
            Node newNode = new Node(key, topLevel);
            for (int i = 0; i <= topLevel; i++) {
                newNode.next[i].set(succs[i], false);
            }
            Node pred;
            boolean valid = true;
            for (int i = topLevel; i >= 0; i--) {
                pred = preds[i];
                newNode.next[i].set(pred.next[i].getReference(), false);
                if (!valid || !pred.next[i].getReference().equals(succs[i]) || pred.next[i].isMarked()) {
                    valid = false;
                    break;
                }
                pred.next[i].set(newNode, false);
            }
            if (valid) {
                return true;
            }
        }
    }

    public boolean remove(int key) {
        Node[] preds = new Node[MAX_LEVEL + 1];
        Node[] succs = new Node[MAX_LEVEL + 1];
        Node nodeToRemove = null;
        while (true) {
            int foundLevel = find(key, preds, succs);
            if (foundLevel != -1) {
                nodeToRemove = succs[foundLevel];
            }
            if (nodeToRemove == null || nodeToRemove.key != key) {
                return false;
            }
            Node pred;
            boolean valid = true;
            for (int i = nodeToRemove.topLevel; i >= 0; i--) {
                pred = preds[i];
                if (!valid || !pred.next[i].getReference().equals(nodeToRemove) || pred.next[i].isMarked()) {
                    valid = false;
                    break;
                }
            }
            if (!valid) {
                continue;
            }
            for (int i = nodeToRemove.topLevel; i >= 0; i--) {
                Node succ = nodeToRemove.next[i].getReference();
                nodeToRemove.next[i].attemptMark(succ, true);
            }
            return true;
        }
    }

    private int find(int key, Node[] preds, Node[] succs) {
        int foundLevel = -1;
        Node pred = head;
        for (int level = MAX_LEVEL; level >= 0; level--) {
            Node curr = pred.next[level].getReference();
            while (curr.key < key) {
                pred = curr;
                curr = pred.next[level].getReference();
            }
            if (foundLevel == -1 && curr.key == key) {
                foundLevel = level;
            }
            preds[level] = pred;
            succs[level] = curr;
        }
        return foundLevel;
    }

    private int randomLevel() {
        int level = 0;
        while (ThreadLocalRandom.current().nextBoolean() && level < MAX_LEVEL) {
            level++;
        }
        return level;
    }

    public void printSkipList() {
        for (int i = MAX_LEVEL; i >= 0; i--) {
            Node current = head.next[i].getReference();
            System.out.print("Level " + i + ": ");
            while (current != null && current != tail) {
                System.out.print(current.key + " ");
                current = current.next[i].getReference();
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        NonBlockingSkipList skipList = new NonBlockingSkipList();

        skipList.add(3);
        skipList.add(1);
        skipList.add(4);
        skipList.add(2);
        skipList.add(5);

        System.out.println("Skip List after insertions:");
        skipList.printSkipList();

        System.out.println("Contains 2: " + skipList.contains(2));
        System.out.println("Contains 7: " + skipList.contains(7));

        skipList.remove(2);

        System.out.println("Skip List after removal of 2:");
        skipList.printSkipList();
    }
}
