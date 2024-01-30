import java.util.ArrayList;
import java.util.List;

public class p83 {
    
    public static List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();
        backtrack(result, num, target, "", 0, 0, 0);
        return result;
    }

    private static void backtrack(List<String> result, String num, int target, String currentExpression,
            int index, long currentResult, long previousOperand) {
        if (index == num.length()) {
            if (currentResult == target) {
                result.add(currentExpression);
            }
            return;
        }

        for (int i = index + 1; i <= num.length(); i++) {
            String substring = num.substring(index, i);
            if (substring.length() > 1 && substring.charAt(0) == '0') {
                break; 
            }

            long currentNumber = Long.parseLong(substring);

            if (index == 0) {
                backtrack(result, num, target, currentExpression + currentNumber, i, currentNumber, currentNumber);
            } else {
                backtrack(result, num, target, currentExpression + "+" + currentNumber, i,
                        currentResult + currentNumber, currentNumber);

                backtrack(result, num, target, currentExpression + "-" + currentNumber, i,
                        currentResult - currentNumber, -currentNumber);

                backtrack(result, num, target, currentExpression + "*" + currentNumber, i,
                        currentResult - previousOperand + previousOperand * currentNumber,
                        previousOperand * currentNumber);
            }
        }
    }

    public static void main(String[] args) {
        String num = "123";
        int target = 6;

        List<String> result = addOperators(num, target);

        System.out.println("Expressions to achieve target value " + target + ":");
        for (String expression : result) {
            System.out.println(expression);
        }
    }
}
