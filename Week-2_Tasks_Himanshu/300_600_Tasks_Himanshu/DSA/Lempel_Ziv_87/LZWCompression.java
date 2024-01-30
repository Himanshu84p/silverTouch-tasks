package Lempel_Ziv_87;

import java.util.HashMap;
import java.util.Map;



public class LZWCompression {

    public static String compress(String input) {
        Map<String, Integer> dictionary = new HashMap<>();
        int dictionarySize = 256;

        for (int i = 0; i < 256; i++) {
            dictionary.put("" + (char) i, i);
        }

        StringBuilder compressed = new StringBuilder();
        StringBuilder currentPhrase = new StringBuilder();

        for (char c : input.toCharArray()) {
            currentPhrase.append(c);

            if (!dictionary.containsKey(currentPhrase.toString())) {
                compressed.append(dictionary.get(currentPhrase.substring(0, currentPhrase.length() - 1))).append(" ");

                if (dictionarySize < 4096) {
                    dictionary.put(currentPhrase.toString(), dictionarySize++);
                }

                currentPhrase = new StringBuilder("" + c);
            }
        }

        if (!currentPhrase.toString().equals("")) {
            compressed.append(dictionary.get(currentPhrase.toString()));
        }

        return compressed.toString();
    }

    public static void main(String[] args) {
        String original = "ABABABAABBABAABABAABABABABAABABAAB";
        System.out.println("Original: " + original);

        String compressed = compress(original);
        System.out.println("Compressed: " + compressed);
    }
}
