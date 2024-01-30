public class P52 {


    private static int[] calculateLPS(String pattern) {
        int patternLength = pattern.length();
        int[] lps = new int[patternLength];
        int len = 0;
        int i = 1;

        while (i < patternLength) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }


    public static void search(String text, String pattern) {
        int textLength = text.length();
        int patternLength = pattern.length();
        int[] lps = calculateLPS(pattern);

        int i = 0;
        int j = 0;

        while (i < textLength) {
            if (pattern.charAt(j) == text.charAt(i)) {
                i++;
                j++;
            }

            if (j == patternLength) {
                System.out.println("Pattern found at index " + (i - j));
                j = lps[j - 1];
            } else if (i < textLength && pattern.charAt(j) != text.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
    }

    public static void main(String[] args) {
        String text = "ABABDABACDABABCABAB";
        String pattern = "ABABCABAB";

        System.out.println("Text: " + text);
        System.out.println("Pattern: " + pattern);
        System.out.println("Occurrences of the pattern:");

        search(text, pattern);
    }
}
