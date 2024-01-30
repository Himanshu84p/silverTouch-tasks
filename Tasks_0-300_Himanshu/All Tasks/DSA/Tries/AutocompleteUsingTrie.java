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

    public List<String> autocomplete(String prefix) {
        List<String> suggestions = new ArrayList<>();
        TrieNode lastNode = getLastNode(prefix);

        if (lastNode != null) {
            autocompleteUtil(prefix, lastNode, suggestions);
        }

        return suggestions;
    }

    private void autocompleteUtil(String prefix, TrieNode node, List<String> suggestions) {
        if (node.isEndOfWord) {
            suggestions.add(prefix);
        }

        for (char ch = 'a'; ch <= 'z'; ch++) {
            TrieNode child = node.children[ch - 'a'];
            if (child != null) {
                autocompleteUtil(prefix + ch, child, suggestions);
            }
        }
    }

    private TrieNode getLastNode(String prefix) {
        TrieNode current = this;
        for (char ch : prefix.toCharArray()) {
            int index = ch - 'a';
            if (current.children[index] == null) {
                return null;
            }
            current = current.children[index];
        }
        return current;
    }
}

public class AutocompleteUsingTrie {

    public static void main(String[] args) {
        TrieNode root = new TrieNode();
        String[] words = { "apple", "app", "apricot", "banana", "bat" };

        for (String word : words) {
            root.insert(word);
        }

        String prefix = "ap";
        List<String> autocompleteSuggestions = root.autocomplete(prefix);

        System.out.println("Autocomplete Suggestions for '" + prefix + "':");
        for (String suggestion : autocompleteSuggestions) {
            System.out.println(suggestion);
        }
    }
}
