import java.util.HashMap;
import java.util.Map;

class SuffixTreeNode {
    Map<Character, SuffixTreeNode> children = new HashMap<>();
    int start;
    int end;

    public SuffixTreeNode() {
        this.start = 0;
        this.end = -1; // -1 indicates an internal node with no end index
    }
}

public class p76 {
    SuffixTreeNode root;

    // Constructor to build the suffix tree from a given string
    public p76(String text) {
        root = new SuffixTreeNode();
        buildSuffixTree(text);
    }

    // Function to build the suffix tree
    private void buildSuffixTree(String text) {
        for (int i = 0; i < text.length(); i++) {
            insertSuffix(root, text.substring(i), i);
        }
    }

    // Function to insert a suffix into the suffix tree
    private void insertSuffix(SuffixTreeNode node, String suffix, int index) {
        char firstChar = suffix.charAt(0);

        if (!node.children.containsKey(firstChar)) {
            node.children.put(firstChar, new SuffixTreeNode());
        }

        SuffixTreeNode child = node.children.get(firstChar);
        if (child.start == 0 && child.end == -1) {
            child.start = index;
        }

        if (suffix.length() > 1) {
            insertSuffix(child, suffix.substring(1), index);
        } else {
            child.end = index; // Leaf node, store the end index
        }
    }

    // Function to print the suffix tree
    public void printSuffixTree(SuffixTreeNode node, String text) {
        for (Map.Entry<Character, SuffixTreeNode> entry : node.children.entrySet()) {
            SuffixTreeNode child = entry.getValue();
            if (child.end == -1) {
                System.out.println(text.substring(child.start) + " (Internal Node)");
                printSuffixTree(child, text);
            } else {
                System.out.println(text.substring(child.start, child.end + 1));
            }
        }
    }

    public static void main(String[] args) {
        String text = "banana";
        p76 suffixTree = new p76(text);

        System.out.println("Suffix Tree for '" + text + "':");
        suffixTree.printSuffixTree(suffixTree.root, text);
    }
}
