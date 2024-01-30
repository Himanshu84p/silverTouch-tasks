class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

class BinaryTree {
    TreeNode root;

    public BinaryTree() {
        this.root = null;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode leftAncestor = lowestCommonAncestor(root.left, p, q);
        TreeNode rightAncestor = lowestCommonAncestor(root.right, p, q);

        if (leftAncestor != null && rightAncestor != null) {
            return root;
        }

        return (leftAncestor != null) ? leftAncestor : rightAncestor;
    }
}

public class Ancestor {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.root = new TreeNode(3);
        tree.root.left = new TreeNode(5);
        tree.root.right = new TreeNode(1);
        tree.root.left.left = new TreeNode(6);
        tree.root.left.right = new TreeNode(2);
        tree.root.right.left = new TreeNode(0);
        tree.root.right.right = new TreeNode(8);
        tree.root.left.right.left = new TreeNode(7);
        tree.root.left.right.right = new TreeNode(4);

        TreeNode p = tree.root.left;
        TreeNode q = tree.root.right;

        TreeNode lca = tree.lowestCommonAncestor(tree.root, p, q);
        System.out.println("Lowest Common Ancestor: " + lca.val);
    }
}
