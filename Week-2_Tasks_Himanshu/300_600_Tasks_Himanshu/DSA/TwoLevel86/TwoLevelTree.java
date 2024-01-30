package TwoLevel86;

import java.util.HashMap;
import java.util.Map;

class Node {
    char ch;
    Map<Character, Node> children;
    boolean isEnd;

    public Node(char ch) {
        this.ch = ch;
        this.children = new HashMap<>();
        this.isEnd = false;
    }
}

public class TwoLevelTree {
    private Node root;

    public TwoLevelTree() {
        this.root = new Node('\0');
    }

    public void insert(String word) {
        Node current = root;

        for (char ch : word.toCharArray()) {
            current.children.putIfAbsent(ch, new Node(ch));
            current = current.children.get(ch);
        }

        current.isEnd = true;
    }

    public boolean search(String word) {
        Node current = root;

        for (char ch : word.toCharArray()) {
            if (!current.children.containsKey(ch)) {
                return false;
            }
            current = current.children.get(ch);
        }

        return current.isEnd;
    }

    public static void main(String[] args) {
        TwoLevelTree twoLevelTree = new TwoLevelTree();

        twoLevelTree.insert("apple");
        twoLevelTree.insert("banana");
        twoLevelTree.insert("orange");

        System.out.println("Is 'apple' present? " + twoLevelTree.search("apple"));
        System.out.println("Is 'grape' present? " + twoLevelTree.search("grape"));
    }
}
