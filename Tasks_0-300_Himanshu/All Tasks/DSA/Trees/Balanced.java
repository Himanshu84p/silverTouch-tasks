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

    public boolean isBalanced(Node node) {
        if (node == null) {
            return true;
        }

        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);

        return Math.abs(leftHeight - rightHeight) <= 1 && isBalanced(node.left) && isBalanced(node.right);
    }

    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        } else {
            return Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        }
    }
}

public class Balanced {
    public static void main(String[] args) {

        BinaryTree balancedTree = new BinaryTree();
        balancedTree.root = new Node(1);
        balancedTree.root.left = new Node(2);
        balancedTree.root.right = new Node(3);
        balancedTree.root.left.left = new Node(4);
        balancedTree.root.left.right = new Node(5);

        boolean isBalanced = balancedTree.isBalanced(balancedTree.root);
        System.out.println("Is the tree balanced? " + isBalanced);

        BinaryTree unbalancedTree = new BinaryTree();
        unbalancedTree.root = new Node(1);
        unbalancedTree.root.left = new Node(2);
        unbalancedTree.root.left.left = new Node(3);

        isBalanced = unbalancedTree.isBalanced(unbalancedTree.root);
        System.out.println("Is the tree balanced? " + isBalanced);
    }
}
