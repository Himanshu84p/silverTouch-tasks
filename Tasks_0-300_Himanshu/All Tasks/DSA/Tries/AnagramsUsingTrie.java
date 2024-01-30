import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<String> findAnagrams(String input) {
        List<String> anagrams = new ArrayList<>();
        findAnagramsUtil(input, new StringBuilder(), this, anagrams);
        return anagrams;
    }

    private void findAnagramsUtil(String input, StringBuilder current, TrieNode node, List<String> anagrams) {
        if (node.isEndOfWord && input.length() == 0) {
            anagrams.add(current.toString());
        }

        for (int i = 0; i < 26; i++) {
            if (node.children[i] != null) {
                char ch = (char) ('a' + i);
                int index = input.indexOf(ch);

                if (index != -1) {
                    current.append(ch);
                    TrieNode nextNode = node.children[i];
                    findAnagramsUtil(input.substring(0, index) + input.substring(index + 1), current, nextNode,
                            anagrams);
                    current.deleteCharAt(current.length() - 1);
                }
            }
        }
    }
}

public class AnagramsUsingTrie {

    public static void main(String[] args) {
        TrieNode root = new TrieNode();
        String[] dictionary = { "listen", "silent", "enlist", "tea", "eat" };

        for (String word : dictionary) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String sortedWord = new String(chars);
            root.insert(sortedWord);
        }

        String inputWord = "silent";
        char[] inputChars = inputWord.toCharArray();
        Arrays.sort(inputChars);
        String sortedInput = new String(inputChars);

        List<String> anagrams = root.findAnagrams(sortedInput);

        System.out.println("Anagrams of '" + inputWord + "':");
        for (String anagram : anagrams) {
            System.out.println(anagram);
        }
    }
}
