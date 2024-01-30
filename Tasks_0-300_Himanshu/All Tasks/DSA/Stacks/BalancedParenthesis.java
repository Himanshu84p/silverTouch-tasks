package DSA.Stacks;

import java.util.Stack;

public class BalancedParenthesis {

    public static boolean isBalanced(String expression) {
        Stack<Character> stack = new Stack<>();

        for (char ch : expression.toCharArray()) {
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            } else if (ch == ')' || ch == ']' || ch == '}') {
                if (stack.isEmpty()) {
                    return false;
                }

                char top = stack.pop();
                if (ch == ')' && top != '(' || ch == '}' && top != '{' || ch == ']' && top != '[') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {

        String expression = "({[]})";
        String expression1 = "({[})";
        System.out.println("Expression is " + isBalanced(expression));
        System.out.println("Expression is " + isBalanced(expression1));
    }
}
