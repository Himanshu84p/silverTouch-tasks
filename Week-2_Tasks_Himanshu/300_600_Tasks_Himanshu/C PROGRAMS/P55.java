public class P55 {

    public static int atoi(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        str = str.trim();
        if (str.isEmpty()) {
            return 0;
        }

        int sign = 1;
        int i = 0;


        if (str.charAt(0) == '-' || str.charAt(0) == '+') {
            sign = (str.charAt(0) == '-') ? -1 : 1;
            i++;
        }

        long result = 0;


        while (i < str.length() && Character.isDigit(str.charAt(i))) {
            result = result * 10 + (str.charAt(i) - '0');


            if (sign == 1 && result > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            } else if (sign == -1 && -result < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }

            i++;
        }

        return (int) (sign * result);
    }

    public static void main(String[] args) {
        String input1 = "42";
        String input2 = "   -42";
        String input3 = "4193 with words";
        String input4 = "words and 987";
        String input5 = "-91283472332";

        System.out.println("Input: \"" + input1 + "\", Output: " + atoi(input1));
        System.out.println("Input: \"" + input2 + "\", Output: " + atoi(input2));
        System.out.println("Input: \"" + input3 + "\", Output: " + atoi(input3));
        System.out.println("Input: \"" + input4 + "\", Output: " + atoi(input4));
        System.out.println("Input: \"" + input5 + "\", Output: " + atoi(input5));
    }
}
