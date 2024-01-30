package WAVLTree81;

class Node {
    int key, height, size;
    Node left, right;

    public Node(int key) {
        this.key = key;
        this.height = 1;
        this.size = 1;
    }
}

public class WAVLTree {
    Node root;

    public WAVLTree() {
        this.root = null;
    }

    int height(Node node) {
        return (node == null) ? 0 : node.height;
    }

    int size(Node node) {
        return (node == null) ? 0 : node.size;
    }

    void update(Node node) {
        if (node != null) {
            node.height = 1 + Math.max(height(node.left), height(node.right));
            node.size = 1 + size(node.left) + size(node.right);
        }
    }

    Node rotateRight(Node y) {
        Node x = y.left;
        Node T = x.right;

        x.right = y;
        y.left = T;

        update(y);
        update(x);

        return x;
    }

    Node rotateLeft(Node x) {
        Node y = x.right;
        Node T = y.left;

        y.left = x;
        x.right = T;

        update(x);
        update(y);

        return y;
    }

    Node rebalanceInsert(Node root) {
        if (root == null)
            return null;

        update(root);

        int balance = height(root.left) - height(root.right);

        if (balance > 1) {
            if (root.left != null && height(root.left.left) >= height(root.left.right)) {

                root = rotateRight(root);
            } else {

                root.left = rotateLeft(root.left);
                root = rotateRight(root);
            }
        } else if (balance < -1) {
            if (root.right != null && height(root.right.right) >= height(root.right.left)) {

                root = rotateLeft(root);
            } else {

                root.right = rotateRight(root.right);
                root = rotateLeft(root);
            }
        }

        return root;
    }

    public void insert(int key) {
        root = insert(root, key);
    }

    Node insert(Node root, int key) {
        if (root == null)
            return new Node(key);

        if (key < root.key)
            root.left = insert(root.left, key);
        else if (key > root.key)
            root.right = insert(root.right, key);
        else
            return root;

        update(root);

        root = rebalanceInsert(root);

        return root;
    }

    public void inorder() {
        inorder(root);
    }

    private void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.key + " ");
            inorder(root.right);
        }
    }

    public static void main(String[] args) {
        WAVLTree tree = new WAVLTree();

        tree.insert(50);
        tree.insert(30);
        tree.insert(70);
        tree.insert(20);
        tree.insert(40);
        tree.insert(60);
        tree.insert(80);

        System.out.println("Inorder traversal:");
        tree.inorder();
    }
}
