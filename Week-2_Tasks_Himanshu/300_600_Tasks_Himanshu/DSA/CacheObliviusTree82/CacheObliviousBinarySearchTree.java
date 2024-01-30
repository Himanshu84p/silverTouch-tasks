package CacheObliviusTree82;

class CacheObliviousNode {
    int key;
    CacheObliviousNode left, right;

    public CacheObliviousNode(int key) {
        this.key = key;
        this.left = this.right = null;
    }
}

public class CacheObliviousBinarySearchTree {
    private CacheObliviousNode root;

    public CacheObliviousBinarySearchTree() {
        this.root = null;
    }

    public void insert(int key) {
        root = insert(root, key);
    }

    private CacheObliviousNode insert(CacheObliviousNode node, int key) {
        if (node == null) {
            return new CacheObliviousNode(key);
        }

        if (key < node.key) {
            node.left = insert(node.left, key);
        } else if (key > node.key) {
            node.right = insert(node.right, key);
        }

        return node;
    }

    public boolean search(int key) {
        return search(root, key);
    }

    private boolean search(CacheObliviousNode node, int key) {
        if (node == null) {
            return false;
        }

        if (key == node.key) {
            return true;
        } else if (key < node.key) {
            return search(node.left, key);
        } else {
            return search(node.right, key);
        }
    }

    public void inorder() {
        inorder(root);
    }

    private void inorder(CacheObliviousNode node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.key + " ");
            inorder(node.right);
        }
    }

    public static void main(String[] args) {
        CacheObliviousBinarySearchTree tree = new CacheObliviousBinarySearchTree();

        tree.insert(50);
        tree.insert(30);
        tree.insert(70);
        tree.insert(20);
        tree.insert(40);
        tree.insert(60);
        tree.insert(80);

        System.out.println("Inorder traversal:");
        tree.inorder();

        int searchKey = 40;
        System.out.println("\nSearch for key " + searchKey + ": " + tree.search(searchKey));
    }
}
