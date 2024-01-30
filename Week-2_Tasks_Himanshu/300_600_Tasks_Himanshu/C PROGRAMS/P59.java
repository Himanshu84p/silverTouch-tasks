import java.util.ArrayList;
import java.util.List;

public class P59 {

    public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();

        int start = 0;
        int end = 0;

        while (start < words.length) {
            int lineLength = words[start].length();
            end = start + 1;

            while (end < words.length && lineLength + 1 + words[end].length() <= maxWidth) {
                lineLength += 1 + words[end].length();
                end++;
            }

            StringBuilder line = new StringBuilder(words[start]);

            if (end == words.length || end - start == 1) {

                for (int i = start + 1; i < end; i++) {
                    line.append(' ').append(words[i]);
                }
                while (line.length() < maxWidth) {
                    line.append(' ');
                }
            } else {

                int spaces = maxWidth - lineLength + (end - start - 1);
                int spaceBetweenWords = spaces / (end - start - 1);
                int extraSpaces = spaces % (end - start - 1);

                for (int i = start + 1; i < end; i++) {
                    for (int j = 0; j < spaceBetweenWords; j++) {
                        line.append(' ');
                    }
                    if (extraSpaces > 0) {
                        line.append(' ');
                        extraSpaces--;
                    }
                    line.append(words[i]);
                }
            }

            result.add(line.toString());
            start = end;
        }

        return result;
    }

    public static void main(String[] args) {
        String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
        int maxWidth = 16;

        List<String> justifiedText = fullJustify(words, maxWidth);

        for (String line : justifiedText) {
            System.out.println(line);
        }
    }
}
