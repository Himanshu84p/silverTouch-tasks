import java.util.Stack;

public class reverseString {

    public static String reverse(String str) {

        char[] reversedString = new char[str.length()];
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            stack.push(str.charAt(i));
        }

        int i = 0;
        while (!stack.isEmpty()) {
            reversedString[i++] = stack.pop();
        }

        return new String(reversedString);
    }

    public static void main(String[] args) {
        String res = reverse("Hello world!!");
        System.out.println(res);
    }
}
