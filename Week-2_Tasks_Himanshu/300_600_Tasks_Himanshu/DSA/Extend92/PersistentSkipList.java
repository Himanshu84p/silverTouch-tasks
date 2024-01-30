package Extend92;

import java.util.*;

public class PersistentSkipList {
    static class Node {
        int value;
        Node[] next;

        public Node(int value, int level) {
            this.value = value;
            this.next = new Node[level + 1];
        }
    }

    static class SkipList {
        Node head;
        int maxLevel;
        Random random;

        public SkipList() {
            this.head = new Node(Integer.MIN_VALUE, 0);
            this.maxLevel = 0;
            this.random = new Random();
        }

        public SkipList insert(int value, int version) {
            Node[] update = new Node[maxLevel + 1];
            Node current = head;

            for (int i = maxLevel; i >= 0; i--) {
                while (current.next[i] != null && current.next[i].value < value) {
                    current = current.next[i];
                }
                update[i] = current;
            }

            int level = randomLevel();
            if (level > maxLevel) {
                for (int i = maxLevel + 1; i <= level; i++) {
                    update[i] = head;
                }
                maxLevel = level;
            }

            Node newNode = new Node(value, level);
            for (int i = 0; i <= level; i++) {
                newNode.next[i] = update[i].next[i];
                update[i].next[i] = newNode;
            }

            return this;
        }

        public boolean search(int value, int version) {
            Node current = head;

            for (int i = maxLevel; i >= 0; i--) {
                while (current.next[i] != null && current.next[i].value < value) {
                    current = current.next[i];
                }
            }

            return current.next[0] != null && current.next[0].value == value;
        }

        private int randomLevel() {
            int level = 0;
            while (random.nextDouble() < 0.5 && level < maxLevel + 1) {
                level++;
            }
            return level;
        }
    }

    public static void main(String[] args) {
        SkipList version1 = new SkipList();
        version1.insert(3, 1).insert(6, 1).insert(7, 1).insert(9, 1);

        SkipList version2 = version1.insert(4, 2).insert(5, 2).insert(8, 2);

        System.out.println("Search 6 at version 1: " + version1.search(6, 1));
        System.out.println("Search 5 at version 1: " + version1.search(5, 1));

        System.out.println("Search 6 at version 2: " + version2.search(6, 2));
        System.out.println("Search 5 at version 2: " + version2.search(5, 2));
    }
}
