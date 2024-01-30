class Node {
    int data;
    Node left;
    Node right;

    public Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

class BinaryTree {
    Node root;

    public BinaryTree() {
        this.root = null;
    }

    public boolean areIdentical(Node root1, Node root2) {

        if (root1 == null && root2 == null) {
            return true;
        }

        if (root1 == null || root2 == null) {
            return false;
        }

        return (root1.data == root2.data) &&
                areIdentical(root1.left, root2.left) &&
                areIdentical(root1.right, root2.right);
    }
}

public class Identical {
    public static void main(String[] args) {
        BinaryTree tree1 = new BinaryTree();
        tree1.root = new Node(1);
        tree1.root.left = new Node(2);
        tree1.root.right = new Node(3);
        tree1.root.left.left = new Node(4);
        tree1.root.left.right = new Node(5);

        BinaryTree tree2 = new BinaryTree();
        tree2.root = new Node(1);
        tree2.root.left = new Node(2);
        tree2.root.right = new Node(3);
        tree2.root.left.left = new Node(4);
        tree2.root.left.right = new Node(5);

        boolean identical = tree1.areIdentical(tree1.root, tree2.root);
        System.out.println("Are the trees identical? " + identical);

        BinaryTree tree3 = new BinaryTree();
        tree3.root = new Node(1);
        tree3.root.left = new Node(2);

        BinaryTree tree4 = new BinaryTree();
        tree4.root = new Node(1);
        tree4.root.right = new Node(2);

        identical = tree3.areIdentical(tree3.root, tree4.root);
        System.out.println("Are the trees identical? " + identical);
    }
}
