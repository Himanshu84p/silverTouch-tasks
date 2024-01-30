import java.util.HashMap;
import java.util.Map;

public class Q6 {

    public static boolean isSatisfiable(String equation) {

        String[] parts = equation.split("=");
        String left = parts[0].trim();
        String right = parts[1].trim();

        boolean leftResult = evaluateExpression(left);
        boolean rightResult = evaluateExpression(right);

        return leftResult == rightResult;
    }

    private static boolean evaluateExpression(String expression) {

        Map<Character, Boolean> variableValues = new HashMap<>();

        expression = expression.replaceAll("\\s", "");

        return evaluateHelper(expression, variableValues);
    }

    private static boolean evaluateHelper(String expression, Map<Character, Boolean> variableValues) {

        if (expression.contains("=")) {
            String[] parts = expression.split("=");
            char variable = parts[0].charAt(0);
            boolean value = Boolean.parseBoolean(parts[1]);

            variableValues.put(variable, value);
            return value;
        }

        if (expression.startsWith("!")) {
            return !evaluateHelper(expression.substring(1), variableValues);
        } else if (expression.startsWith("(") && expression.endsWith(")")) {
            return evaluateHelper(expression.substring(1, expression.length() - 1), variableValues);
        } else {

            char variable = expression.charAt(0);
            return variableValues.getOrDefault(variable, false);
        }
    }

    public static void main(String[] args) {
        String equation1 = "a = true";
        String equation2 = "b = false";
        String equation3 = "a = !b";
        String equation4 = "(a = true) = b";
        String equation5 = "x = y";

        System.out.println(equation1 + " is satisfiable: " + isSatisfiable(equation1));
        System.out.println(equation2 + " is satisfiable: " + isSatisfiable(equation2));
        System.out.println(equation3 + " is satisfiable: " + isSatisfiable(equation3));
        System.out.println(equation4 + " is satisfiable: " + isSatisfiable(equation4));
        System.out.println(equation5 + " is satisfiable: " + isSatisfiable(equation5));
    }
}
