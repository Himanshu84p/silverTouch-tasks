import java.util.*;

public class p71 {
    public List<Integer> compress(String input) {
        Map<String, Integer> dictionary = new HashMap<>();
        List<Integer> result = new ArrayList<>();


        for (int i = 0; i < 256; i++) {
            dictionary.put("" + (char) i, i);
        }

        String current = "";
        for (char c : input.toCharArray()) {
            String combined = current + c;
            if (dictionary.containsKey(combined)) {
                current = combined;
            } else {
                result.add(dictionary.get(current));

        
                dictionary.put(combined, dictionary.size());
                current = "" + c;
            }
        }


        result.add(dictionary.get(current));

        return result;
    }

    public String decompress(List<Integer> compressed) {
        Map<Integer, String> dictionary = new HashMap<>();
        StringBuilder result = new StringBuilder();


        for (int i = 0; i < 256; i++) {
            dictionary.put(i, "" + (char) i);
        }

        int oldCode = compressed.get(0);
        result.append(dictionary.get(oldCode));

        for (int i = 1; i < compressed.size(); i++) {
            int currentCode = compressed.get(i);

            String entry;
            if (dictionary.containsKey(currentCode)) {
                entry = dictionary.get(currentCode);
            } else if (currentCode == dictionary.size()) {
                entry = dictionary.get(oldCode) + dictionary.get(oldCode).charAt(0);
            } else {
                throw new IllegalArgumentException("Invalid compressed data");
            }

            result.append(entry);

    
            dictionary.put(dictionary.size(), dictionary.get(oldCode) + entry.charAt(0));

            oldCode = currentCode;
        }

        return result.toString();
    }

    public static void main(String[] args) {
        p71 lzw = new p71();
        String input = "ABABABABA";

        List<Integer> compressed = lzw.compress(input);
        System.out.println("Compressed: " + compressed);

        String decompressed = lzw.decompress(compressed);
        System.out.println("Decompressed: " + decompressed);
    }
}
