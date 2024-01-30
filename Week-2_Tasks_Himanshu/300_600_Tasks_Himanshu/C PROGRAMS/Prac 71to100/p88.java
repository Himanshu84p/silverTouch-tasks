import java.util.Stack;

class TreeNode {
    String value;
    TreeNode left;
    TreeNode right;

    public TreeNode(String value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}

public class p88 {
    public static TreeNode buildExpressionTree(String[] postfixExpression) {
        Stack<TreeNode> stack = new Stack<>();

        for (String token : postfixExpression) {
            TreeNode newNode = new TreeNode(token);

            if (isOperator(token)) {            
                TreeNode operand2 = stack.pop();
                TreeNode operand1 = stack.pop();
            
                newNode.left = operand1;
                newNode.right = operand2;
            }
        
            stack.push(newNode);
        }
    
        return stack.pop();
    }

    private static boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    public static void printExpressionTree(TreeNode root) {
        if (root != null) {
            printExpressionTree(root.left);
            System.out.print(root.value + " ");
            printExpressionTree(root.right);
        }
    }

    public static void main(String[] args) {    
        String[] postfixExpression = { "2", "3", "4", "*", "+" };
    
        TreeNode root = buildExpressionTree(postfixExpression);
    
        System.out.println("Infix Expression from Expression Tree:");
        printExpressionTree(root);
    }
}
