import java.util.Random;

class TreapNode {
    int key, priority;
    TreapNode left, right;

    public TreapNode(int key, int priority) {
        this.key = key;
        this.priority = priority;
        this.left = this.right = null;
    }
}

public class Q39 {
    private TreapNode root;
    private Random random;

    public Q39() {
        this.root = null;
        this.random = new Random();
    }

    // Perform a right rotation on the given node
    private TreapNode rightRotate(TreapNode y) {
        TreapNode x = y.left;
        TreapNode T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        return x;
    }

    // Perform a left rotation on the given node
    private TreapNode leftRotate(TreapNode x) {
        TreapNode y = x.right;
        TreapNode T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        return y;
    }

    // Insert a key into the Treap
    public void insert(int key) {
        root = insert(root, key);
    }

    private TreapNode insert(TreapNode root, int key) {
        if (root == null) {
            return new TreapNode(key, random.nextInt());
        }

        // Perform standard BST insert
        if (key < root.key) {
            root.left = insert(root.left, key);
        } else if (key > root.key) {
            root.right = insert(root.right, key);
        }

        // Perform rotations based on priority values
        if (root.left != null && root.left.priority > root.priority) {
            root = rightRotate(root);
        } else if (root.right != null && root.right.priority > root.priority) {
            root = leftRotate(root);
        }

        return root;
    }

    // Delete a key from the Treap
    public void delete(int key) {
        root = delete(root, key);
    }

    private TreapNode delete(TreapNode root, int key) {
        if (root == null) {
            return root;
        }

        // Perform standard BST delete
        if (key < root.key) {
            root.left = delete(root.left, key);
        } else if (key > root.key) {
            root.right = delete(root.right, key);
        } else {
            // Node with only one child or no child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // Node with two children, get the inorder successor (smallest
            // in the right subtree)
            root.key = minValue(root.right);

            // Delete the inorder successor
            root.right = delete(root.right, root.key);
        }

        return root;
    }

    private int minValue(TreapNode root) {
        int minValue = root.key;
        while (root.left != null) {
            minValue = root.left.key;
            root = root.left;
        }
        return minValue;
    }

    // Perform an inorder traversal of the Treap
    public void inorder() {
        inorder(root);
    }

    private void inorder(TreapNode root) {
        if (root != null) {
            inorder(root.left);
            System.out.print("(" + root.key + ", " + root.priority + ") ");
            inorder(root.right);
        }
    }

    public static void main(String[] args) {
        Q39 treap = new Q39();

        treap.insert(50);
        treap.insert(30);
        treap.insert(20);
        treap.insert(40);
        treap.insert(70);
        treap.insert(60);
        treap.insert(80);

        System.out.println("Inorder traversal after insertion:");
        treap.inorder();

        treap.delete(20);
        treap.delete(30);

        System.out.println("\nInorder traversal after deletion:");
        treap.inorder();
    }
}
