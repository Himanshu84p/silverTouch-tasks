public class PalindromeCheck {

    public static boolean isPalindrome(String str) {
        str = str.toLowerCase().replaceAll("[^a-z0-9]", "");

        int left = 0;
        int right = str.length() - 1;

        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    public static void main(String[] args) {
        String palindromeStr = "level";
        boolean result = isPalindrome(palindromeStr);
        System.out.println("Is \"" + palindromeStr + "\" a palindrome? " + result);
    }
}
