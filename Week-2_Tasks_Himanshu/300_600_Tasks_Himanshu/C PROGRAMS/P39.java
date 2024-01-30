import java.util.LinkedList;
import java.util.Queue;

class TrieNode {
    char data;
    TrieNode[] children;
    TrieNode failureLink;
    TrieNode outputLink;
    boolean isEndOfWord;

    TrieNode(char data) {
        this.data = data;
        this.children = new TrieNode[26]; // Assuming English alphabet
        this.failureLink = null;
        this.outputLink = null;
        this.isEndOfWord = false;
    }
}

class P39 {

    private TrieNode root;

    public P39() {
        this.root = new TrieNode(' ');
    }

    private void insertPattern(String pattern) {
        TrieNode current = root;

        for (char c : pattern.toCharArray()) {
            int index = c - 'a';
            if (current.children[index] == null) {
                current.children[index] = new TrieNode(c);
            }
            current = current.children[index];
        }

        current.isEndOfWord = true;
    }


    private void buildLinks() {
        Queue<TrieNode> queue = new LinkedList<>();


        for (TrieNode child : root.children) {
            if (child != null) {
                queue.offer(child);
                child.failureLink = root;
            }
        }

        while (!queue.isEmpty()) {
            TrieNode current = queue.poll();

            for (int i = 0; i < 26; i++) {
                TrieNode child = current.children[i];
                if (child != null) {
                    queue.offer(child);

                    TrieNode failureLink = current.failureLink;
                    while (failureLink != null && failureLink.children[i] == null) {
                        failureLink = failureLink.failureLink;
                    }

                    if (failureLink != null) {
                        child.failureLink = failureLink.children[i];
                    } else {
                        child.failureLink = root;
                    }

                    if (child.failureLink.isEndOfWord) {
                        child.outputLink = child.failureLink;
                    } else {
                        child.outputLink = child.failureLink.outputLink;
                    }
                }
            }
        }
    }


    public void search(String text) {
        TrieNode current = root;

        for (char c : text.toCharArray()) {
            int index = c - 'a';

            while (current != null && current.children[index] == null) {
                current = current.failureLink;
            }

            if (current != null) {
                current = current.children[index];

                TrieNode outputNode = current;
                while (outputNode != null) {
                    if (outputNode.isEndOfWord) {
                        System.out.println("Pattern found at index: " + (c - text.length() + 1));
                    }
                    outputNode = outputNode.outputLink;
                }
            } else {
                current = root;
            }
        }
    }

    public static void main(String[] args) {
        P39 ahoCorasick = new P39();
        String[] patterns = {"he", "she", "his", "hers"};

        for (String pattern : patterns) {
            ahoCorasick.insertPattern(pattern);
        }

        ahoCorasick.buildLinks();

        String text = "ahishers";
        System.out.println("Text: " + text);
        System.out.println("Patterns found:");

        ahoCorasick.search(text);
    }
}
