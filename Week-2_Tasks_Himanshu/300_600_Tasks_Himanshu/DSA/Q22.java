public class Q22 {

    public static void main(String[] args) {
        printAllPalindromicPartitions("nitin");
        System.out.println();

        printAllPalindromicPartitions("geeks");
    }

    public static void printAllPalindromicPartitions(String input) {
        int n = input.length();

        for (int mask = 0; mask < (1 << (n - 1)); mask++) {
            StringBuilder partition = new StringBuilder();
            int lastCut = 0;

            for (int i = 0; i < n - 1; i++) {
                if ((mask & (1 << i)) != 0) {

                    partition.append(input.substring(lastCut, i + 1)).append("|");
                    lastCut = i + 1;
                }
            }

            partition.append(input.substring(lastCut));

            if (isPalindrome(partition.toString())) {
                System.out.println(partition.toString());
            }
        }
    }

    private static boolean isPalindrome(String str) {
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
}
