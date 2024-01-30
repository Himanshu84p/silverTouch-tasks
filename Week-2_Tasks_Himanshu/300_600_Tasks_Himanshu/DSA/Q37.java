class RadixTreeNode {
    String key;
    RadixTreeNode[] children;

    public RadixTreeNode(String key) {
        this.key = key;
        this.children = new RadixTreeNode[26];
    }
}

public class Q37 {
    private RadixTreeNode root;

    public Q37() {
        this.root = new RadixTreeNode("");
    }

    public void insert(String key) {
        root = insert(root, key);
    }

    private RadixTreeNode insert(RadixTreeNode node, String key) {
        if (node == null) {
            return new RadixTreeNode(key);
        }

        String commonPrefix = getCommonPrefix(node.key, key);
        if (commonPrefix.isEmpty()) {

            int index = key.charAt(0) - 'a';
            node.children[index] = insert(node.children[index], key);
        } else if (commonPrefix.equals(key)) {

        } else if (commonPrefix.length() < node.key.length()) {

            String newNodeKey = node.key.substring(commonPrefix.length());
            RadixTreeNode newNode = new RadixTreeNode(newNodeKey);
            newNode.children = node.children;
            node.children = new RadixTreeNode[26];
            node.children[newNodeKey.charAt(0) - 'a'] = newNode;
            node.key = commonPrefix;
        } else {

            int index = key.charAt(commonPrefix.length()) - 'a';
            node.children[index] = insert(node.children[index], key.substring(commonPrefix.length()));
        }

        return node;
    }

    public boolean search(String key) {
        return search(root, key);
    }

    private boolean search(RadixTreeNode node, String key) {
        if (node == null) {
            return false;
        }

        String commonPrefix = getCommonPrefix(node.key, key);
        if (commonPrefix.isEmpty()) {

            int index = key.charAt(0) - 'a';
            return search(node.children[index], key);
        } else if (commonPrefix.equals(key)) {

            return true;
        } else {

            return search(node.children[commonPrefix.charAt(0) - 'a'], key.substring(commonPrefix.length()));
        }
    }

    private String getCommonPrefix(String str1, String str2) {
        int minLength = Math.min(str1.length(), str2.length());
        int commonLength = 0;

        for (int i = 0; i < minLength; i++) {
            if (str1.charAt(i) == str2.charAt(i)) {
                commonLength++;
            } else {
                break;
            }
        }

        return str1.substring(0, commonLength);
    }

    public static void main(String[] args) {
        Q37 radixTree = new Q37();

        radixTree.insert("apple");
        radixTree.insert("app");
        radixTree.insert("apricot");

        System.out.println("Search 'apple': " + radixTree.search("apple"));
        System.out.println("Search 'app': " + radixTree.search("app"));
        System.out.println("Search 'apricot': " + radixTree.search("apricot"));
        System.out.println("Search 'orange': " + radixTree.search("orange"));
    }
}
