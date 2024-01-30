package prac64;

class TSTNode {
    char data;
    boolean isEnd;
    TSTNode left, middle, right;

    TSTNode(char data) {
        this.data = data;
        this.isEnd = false;
        this.left = null;
        this.middle = null;
        this.right = null;
    }
}

public class TernarySearchTree {
    private TSTNode root;

    public void insert(String word) {
        root = insert(root, word.toCharArray(), 0);
    }

    private TSTNode insert(TSTNode node, char[] word, int index) {
        if (node == null) {
            node = new TSTNode(word[index]);
        }

        if (word[index] < node.data) {
            node.left = insert(node.left, word, index);
        } else if (word[index] > node.data) {
            node.right = insert(node.right, word, index);
        } else {
            if (index < word.length - 1) {
                node.middle = insert(node.middle, word, index + 1);
            } else {
                node.isEnd = true;
            }
        }
        return node;
    }

    public boolean search(String word) {
        return search(root, word.toCharArray(), 0);
    }

    private boolean search(TSTNode node, char[] word, int index) {
        if (node == null) {
            return false;
        }

        if (word[index] < node.data) {
            return search(node.left, word, index);
        } else if (word[index] > node.data) {
            return search(node.right, word, index);
        } else {
            if (index == word.length - 1) {
                return node.isEnd;
            } else {
                return search(node.middle, word, index + 1);
            }
        }
    }

    public static void main(String[] args) {
        TernarySearchTree tst = new TernarySearchTree();

        tst.insert("apple");
        tst.insert("banana");
        tst.insert("orange");

        System.out.println(tst.search("apple")); // true
        System.out.println(tst.search("orange")); // true
        System.out.println(tst.search("grape")); // false
    }
}
