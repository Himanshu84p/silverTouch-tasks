package rebBlackTree79;

class prac79 {
    public static void main(String[] args) {
        PersistentRedBlackTree tree = new PersistentRedBlackTree();

        tree.insert(10, 100);
        tree.insert(20, 200);
        tree.insert(30, 300);

        tree.insert(15, 150);
        tree.insert(25, 250);
        tree.insert(35, 350);

        System.out.println("Query at Version 1:");
        System.out.println("Key 10: " + tree.get(10, 1));
        System.out.println("Key 20: " + tree.get(20, 1));
        System.out.println("Key 30: " + tree.get(30, 1));

        System.out.println("\nQuery at Version 2:");
        System.out.println("Key 10: " + tree.get(10, 2));
        System.out.println("Key 15: " + tree.get(15, 2));
        System.out.println("Key 25: " + tree.get(25, 2));
        System.out.println("Key 35: " + tree.get(35, 2));
    }
}

class Node {
    int key;
    int value;
    int size;
    boolean isRed;
    Node left, right;

    public Node(int key, int value, boolean isRed) {
        this.key = key;
        this.value = value;
        this.isRed = isRed;
        this.size = 1;
    }
}

class PersistentRedBlackTree {
    private Node root;
    private int versionCounter;

    public PersistentRedBlackTree() {
        this.root = null;
        this.versionCounter = 0;
    }

    public void insert(int key, int value) {
        versionCounter++;
        root = insert(root, key, value);
        root.isRed = false;
    }

    private Node insert(Node node, int key, int value) {
        if (node == null) {
            return new Node(key, value, true);
        }

        if (key < node.key) {
            node.left = insert(node.left, key, value);
        } else if (key > node.key) {
            node.right = insert(node.right, key, value);
        } else {
            node.value = value;
            return node;
        }

        node.size = 1 + size(node.left) + size(node.right);

        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }

        return node;
    }

    public int get(int key, int version) {
        Node result = get(root, key, version);
        return result != null ? result.value : Integer.MIN_VALUE;
    }

    private Node get(Node node, int key, int version) {
        while (node != null) {
            int cmp = key - node.key;
            if (cmp < 0) {
                node = node.left;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                return node;
            }
        }
        return null;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.isRed = h.isRed;
        h.isRed = true;
        x.size = h.size;
        h.size = 1 + size(h.left) + size(h.right);
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.isRed = h.isRed;
        h.isRed = true;
        x.size = h.size;
        h.size = 1 + size(h.left) + size(h.right);
        return x;
    }

    private void flipColors(Node h) {
        h.isRed = true;
        h.left.isRed = false;
        h.right.isRed = false;
    }

    private int size(Node node) {
        return (node != null) ? node.size : 0;
    }

    private boolean isRed(Node node) {
        return (node != null && node.isRed);
    }
}
