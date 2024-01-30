import java.util.Arrays;

public class P21 {

    public static String burrowsWheelerTransform(String text) {
        int length = text.length();
        String[] rotations = new String[length];

        for (int i = 0; i < length; i++) {
            rotations[i] = text.substring(i) + text.substring(0, i);
        }


        Arrays.sort(rotations);


        StringBuilder bwt = new StringBuilder();
        for (String rotation : rotations) {
            bwt.append(rotation.charAt(length - 1));
        }

        return bwt.toString();
    }

    public static String inverseBurrowsWheelerTransform(String bwt) {
        int length = bwt.length();
        char[] firstColumn = bwt.toCharArray();
        char[] sortedChars = Arrays.copyOf(firstColumn, length);

        Arrays.sort(sortedChars);

        StringBuilder originalString = new StringBuilder();
        int currentRow = Arrays.binarySearch(sortedChars, firstColumn[0]);

        for (int i = 0; i < length; i++) {
            originalString.insert(0, firstColumn[currentRow]);
            currentRow = Arrays.binarySearch(sortedChars, firstColumn[currentRow]);
        }

        return originalString.toString();
    }

    public static void main(String[] args) {
        String text = "banana";
        String bwtResult = burrowsWheelerTransform(text);
        String inverseResult = inverseBurrowsWheelerTransform(bwtResult);

        System.out.println("Original text: " + text);
        System.out.println("BWT result: " + bwtResult);
        System.out.println("Inverse BWT result: " + inverseResult);
    }
}