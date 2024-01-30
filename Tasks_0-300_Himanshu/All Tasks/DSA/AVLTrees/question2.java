class TreeNode2 {
    int val;
    TreeNode2 left, right;

    public TreeNode2(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

public class question2 {

    public TreeNode2 sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    private TreeNode2 sortedArrayToBST(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        int mid = left + (right - left) / 2;
        TreeNode2 root = new TreeNode2(nums[mid]);

        root.left = sortedArrayToBST(nums, left, mid - 1);
        root.right = sortedArrayToBST(nums, mid + 1, right);

        return root;
    }

    private void inorderTraversal(TreeNode2 root) {
        if (root != null) {
            inorderTraversal(root.left);
            System.out.print(root.val + " ");
            inorderTraversal(root.right);
        }
    }

    public static void main(String[] args) {
        question2 converter = new question2();

        int[] sortedArray = { -10, -3, 0, 5, 9 };

        TreeNode2 root = converter.sortedArrayToBST(sortedArray);

        System.out.println("Inorder traversal of the balanced BST:");
        converter.inorderTraversal(root);
    }
}
