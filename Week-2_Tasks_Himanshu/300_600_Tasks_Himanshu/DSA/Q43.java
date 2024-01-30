class SplayNode {
    int key;
    SplayNode left, right, parent;

    public SplayNode(int key) {
        this.key = key;
        this.left = this.right = this.parent = null;
    }
}

public class Q43 {
    private SplayNode root;

    public Q43() {
        this.root = null;
    }

    private void zig(SplayNode x) {
        SplayNode p = x.parent;
        if (p.left == x) {
            p.left = x.right;
            if (x.right != null) {
                x.right.parent = p;
            }
            x.right = p;
        } else {
            p.right = x.left;
            if (x.left != null) {
                x.left.parent = p;
            }
            x.left = p;
        }
        x.parent = p.parent;
        p.parent = x;
        if (x.parent != null) {
            if (x.parent.left == p) {
                x.parent.left = x;
            } else {
                x.parent.right = x;
            }
        } else {
            root = x;
        }
    }

    private void zigZig(SplayNode x) {
        zig(x.parent);
        zig(x);
    }

    private void zigZag(SplayNode x) {
        zig(x);
        zig(x);
    }

    private void splay(SplayNode x) {
        while (x.parent != null) {
            if (x.parent.parent == null) {
                zig(x);
            } else if ((x.parent.left == x && x.parent.parent.left == x.parent) ||
                    (x.parent.right == x && x.parent.parent.right == x.parent)) {
                zigZig(x);
            } else {
                zigZag(x);
            }
        }
    }

    public void insert(int key) {
        SplayNode newNode = new SplayNode(key);
        if (root == null) {
            root = newNode;
        } else {
            SplayNode current = root;
            SplayNode parent = null;

            while (current != null) {
                parent = current;
                if (key < current.key) {
                    current = current.left;
                } else if (key > current.key) {
                    current = current.right;
                } else {

                    return;
                }
            }

            if (key < parent.key) {
                parent.left = newNode;
            } else {
                parent.right = newNode;
            }

            newNode.parent = parent;
            splay(newNode);
        }
    }

    public boolean search(int key) {
        SplayNode result = searchNode(root, key);
        if (result != null) {
            splay(result);
            return true;
        }
        return false;
    }

    private SplayNode searchNode(SplayNode node, int key) {
        if (node == null || node.key == key) {
            return node;
        }

        if (key < node.key) {
            return searchNode(node.left, key);
        } else {
            return searchNode(node.right, key);
        }
    }

    private void inorderTraversal(SplayNode node) {
        if (node != null) {
            inorderTraversal(node.left);
            System.out.print(node.key + " ");
            inorderTraversal(node.right);
        }
    }

    public void printTree() {
        System.out.print("Splay Tree (Inorder): ");
        inorderTraversal(root);
        System.out.println();
    }

    public static void main(String[] args) {
        Q43 splayTree = new Q43();

        int[] keys = { 50, 30, 70, 20, 40, 60, 80 };
        for (int key : keys) {
            splayTree.insert(key);
        }

        int searchKey = 40;
        boolean found = splayTree.search(searchKey);
        System.out.println("Is " + searchKey + " present in the tree? " + found);

        splayTree.printTree();
    }
}
