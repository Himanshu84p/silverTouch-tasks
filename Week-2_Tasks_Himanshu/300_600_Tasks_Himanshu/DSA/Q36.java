class TreeNode {
    int key, height;
    TreeNode left, right;

    public TreeNode(int key) {
        this.key = key;
        this.height = 1;
        this.left = this.right = null;
    }
}

public class Q36 {
    private TreeNode root;

    private int height(TreeNode node) {
        if (node == null)
            return 0;
        return node.height;
    }

    private int balanceFactor(TreeNode node) {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }

    private TreeNode rightRotate(TreeNode y) {
        TreeNode x = y.left;
        TreeNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    private TreeNode leftRotate(TreeNode x) {
        TreeNode y = x.right;
        TreeNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    public void insert(int key) {
        root = insert(root, key);
    }

    private TreeNode insert(TreeNode node, int key) {
        if (node == null)
            return new TreeNode(key);

        if (key < node.key) {
            node.left = insert(node.left, key);
        } else if (key > node.key) {
            node.right = insert(node.right, key);
        } else {

            return node;
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = balanceFactor(node);

        if (balance > 1 && key < node.left.key) {
            return rightRotate(node);
        }

        if (balance < -1 && key > node.right.key) {
            return leftRotate(node);
        }

        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    public void delete(int key) {
        root = delete(root, key);
    }

    private TreeNode delete(TreeNode root, int key) {
        if (root == null)
            return root;

        if (key < root.key) {
            root.left = delete(root.left, key);
        } else if (key > root.key) {
            root.right = delete(root.right, key);
        } else {

            if (root.left == null || root.right == null) {
                TreeNode temp = (root.left != null) ? root.left : root.right;

                if (temp == null) {
                    temp = root;
                    root = null;
                } else {

                    root = temp;
                }
            } else {

                TreeNode temp = minValueNode(root.right);

                root.key = temp.key;

                root.right = delete(root.right, temp.key);
            }
        }

        if (root == null) {
            return root;
        }

        root.height = 1 + Math.max(height(root.left), height(root.right));

        int balance = balanceFactor(root);

        if (balance > 1 && balanceFactor(root.left) >= 0) {
            return rightRotate(root);
        }

        if (balance > 1 && balanceFactor(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        if (balance < -1 && balanceFactor(root.right) <= 0) {
            return leftRotate(root);
        }

        if (balance < -1 && balanceFactor(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    private TreeNode minValueNode(TreeNode node) {
        TreeNode current = node;

        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    public void inorder() {
        inorder(root);
        System.out.println();
    }

    private void inorder(TreeNode root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.key + " ");
            inorder(root.right);
        }
    }

    public static void main(String[] args) {
        Q36 avlTree = new Q36();

        avlTree.insert(10);
        avlTree.insert(20);
        avlTree.insert(30);
        avlTree.insert(40);
        avlTree.insert(50);
        avlTree.insert(25);

        System.out.println("Inorder traversal:");
        avlTree.inorder();

        avlTree.delete(30);

        System.out.println("Inorder traversal after deletion of 30:");
        avlTree.inorder();
    }
}
