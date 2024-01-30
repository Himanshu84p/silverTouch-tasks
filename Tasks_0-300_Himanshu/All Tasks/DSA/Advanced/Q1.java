class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isEndOfWord;

    TrieNode() {
        isEndOfWord = false;
        for (int i = 0; i < 26; i++) {
            children[i] = null;
        }
    }
}

public class Q1 {
    private TrieNode root;

    Q1() {
        root = new TrieNode();
    }

    void insert(String key) {
        TrieNode current = root;
        for (char c : key.toCharArray()) {
            int index = c - 'a';
            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }
            current = current.children[index];
        }
        current.isEndOfWord = true;
    }

    boolean search(String key) {
        TrieNode current = root;
        for (char c : key.toCharArray()) {
            int index = c - 'a';
            if (current.children[index] == null) {
                return false;
            }
            current = current.children[index];
        }
        return current.isEndOfWord;
    }

    public static void main(String[] args) {
        Q1 trie = new Q1();
        trie.insert("apple");
        System.out.println(trie.search("apple")); // Output: true
        System.out.println(trie.search("app")); // Output: false
        trie.insert("app");
        System.out.println(trie.search("app")); // Output: true
    }
}
