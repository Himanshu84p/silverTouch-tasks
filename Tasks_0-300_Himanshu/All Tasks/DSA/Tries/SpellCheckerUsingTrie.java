import java.util.ArrayList;
import java.util.List;

class TrieNode {
    private final TrieNode[] children;
    private boolean isEndOfWord;

    public TrieNode() {
        this.children = new TrieNode[26];
        this.isEndOfWord = false;
    }

    public void insert(String word) {
        TrieNode current = this;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }
            current = current.children[index];
        }
        current.isEndOfWord = true;
    }

    public boolean search(String word) {
        TrieNode lastNode = getLastNode(word);
        return lastNode != null && lastNode.isEndOfWord;
    }

    private TrieNode getLastNode(String word) {
        TrieNode current = this;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (current.children[index] == null) {
                return null;
            }
            current = current.children[index];
        }
        return current;
    }
}

public class SpellCheckerUsingTrie {

    public static void main(String[] args) {
        TrieNode root = new TrieNode();
        String[] dictionary = { "apple", "banana", "orange", "grape", "pear" };

        for (String word : dictionary) {
            root.insert(word);
        }

        String wordToCheck = "oranje";
        boolean isSpelledCorrectly = root.search(wordToCheck);

        if (isSpelledCorrectly) {
            System.out.println("'" + wordToCheck + "' is spelled correctly.");
        } else {
            System.out.println("'" + wordToCheck + "' is misspelled.");
        }
    }
}
