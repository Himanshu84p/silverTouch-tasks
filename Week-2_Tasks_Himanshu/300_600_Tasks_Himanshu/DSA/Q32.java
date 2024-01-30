import java.util.*;

class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    TrieNode fail;
    List<Integer> output = new ArrayList<>();
}

public class Q32 {
    private TrieNode root;
    private List<String> patterns; // Declare patterns at the class level

    public Q32(List<String> patterns) {
        this.patterns = patterns;
        buildTrie();
        buildFailureFunction();
    }

    private void buildTrie() {
        root = new TrieNode();

        for (int i = 0; i < patterns.size(); i++) {
            String pattern = patterns.get(i);
            TrieNode node = root;

            for (char c : pattern.toCharArray()) {
                node.children.putIfAbsent(c, new TrieNode());
                node = node.children.get(c);
            }

            node.output.add(i);
        }
    }

    private void buildFailureFunction() {
        Queue<TrieNode> queue = new LinkedList<>();

        for (TrieNode node : root.children.values()) {
            node.fail = root;
            queue.offer(node);
        }

        while (!queue.isEmpty()) {
            TrieNode currentNode = queue.poll();

            for (Map.Entry<Character, TrieNode> entry : currentNode.children.entrySet()) {
                char edge = entry.getKey();
                TrieNode child = entry.getValue();

                queue.offer(child);

                TrieNode failNode = currentNode.fail;
                while (failNode != null && !failNode.children.containsKey(edge)) {
                    failNode = failNode.fail;
                }

                if (failNode == null) {
                    child.fail = root;
                } else {
                    child.fail = failNode.children.get(edge);
                    child.output.addAll(child.fail.output);
                }
            }
        }
    }

    public List<Integer> search(String text) {
        List<Integer> result = new ArrayList<>();
        TrieNode node = root;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            while (node != null && !node.children.containsKey(c)) {
                node = node.fail;
            }

            if (node == null) {
                node = root;
            } else {
                node = node.children.get(c);

                for (int patternIndex : node.output) {
                    result.add(i - patterns.get(patternIndex).length() + 1);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        List<String> patterns = Arrays.asList("he", "she", "his", "hers");
        Q32 ahoCorasick = new Q32(patterns);

        String text = "hershehishers";
        List<Integer> matches = ahoCorasick.search(text);

        System.out.println("Pattern matches at indices: " + matches);
    }
}
