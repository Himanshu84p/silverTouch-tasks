public class P28 {

    public static String longestPalindromicSubstring(String text) {
        String modifiedText = preprocessText(text);
        int[] p = new int[modifiedText.length()];
        int c = 0, r = 0;

        for (int i = 1; i < modifiedText.length() - 1; i++) {
            int mirror = 2 * c - i;

            if (i < r) {
                p[i] = Math.min(p[mirror], r - i);
            }

            while (modifiedText.charAt(i + (1 + p[i])) == modifiedText.charAt(i - (1 + p[i]))) {
                p[i]++;
            }

            if (i + p[i] > r) {
                c = i;
                r = i + p[i];
            }
        }

        int maxLen = 0;
        int centerIndex = 0;
        for (int i = 1; i < modifiedText.length() - 1; i++) {
            if (p[i] > maxLen) {
                maxLen = p[i];
                centerIndex = i;
            }
        }

        int start = (centerIndex - maxLen) / 2;
        int end = start + maxLen - 1;
        return text.substring(start, end + 1);
    }

    private static String preprocessText(String text) {
        StringBuilder modifiedText = new StringBuilder();
        modifiedText.append("$#");
        for (char ch : text.toCharArray()) {
            modifiedText.append(ch).append('#');
        }
        modifiedText.append('@');
        return modifiedText.toString();
    }

    public static void main(String[] args) {
        String text = "babad";
        String longestPalindrome = longestPalindromicSubstring(text);
        System.out.println("Longest Palindromic Substring: " + longestPalindrome);
    }
}
