class TreeNode {
    int value;
    TreeNode left, right;

    public TreeNode(int value) {
        this.value = value;
        this.left = this.right = null;
    }
}

public class Q46 {
    public static TreeNode constructCartesianTree(int[] arr) {
        return constructCartesianTree(arr, 0, arr.length - 1);
    }

    private static TreeNode constructCartesianTree(int[] arr, int start, int end) {
        if (start > end) {
            return null;
        }

        int minIndex = findMinIndex(arr, start, end);

        TreeNode root = new TreeNode(arr[minIndex]);

        root.left = constructCartesianTree(arr, start, minIndex - 1);
        root.right = constructCartesianTree(arr, minIndex + 1, end);

        return root;
    }

    private static int findMinIndex(int[] arr, int start, int end) {
        int minIndex = start;
        for (int i = start + 1; i <= end; i++) {
            if (arr[i] < arr[minIndex]) {
                minIndex = i;
            }
        }
        return minIndex;
    }

    public static void inOrderTraversal(TreeNode root) {
        if (root != null) {
            inOrderTraversal(root.left);
            System.out.print(root.value + " ");
            inOrderTraversal(root.right);
        }
    }

    public static void main(String[] args) {
        int[] arr = { 3, 2, 6, 1, 9 };
        TreeNode root = constructCartesianTree(arr);

        System.out.println("In-order traversal of the Cartesian tree:");
        inOrderTraversal(root);
    }
}
