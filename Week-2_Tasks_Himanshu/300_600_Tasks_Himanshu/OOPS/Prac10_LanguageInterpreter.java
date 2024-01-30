import java.util.Scanner;

public class Prac10_LanguageInterpreter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a string:");
        String input = scanner.nextLine();
        scanner.close();

        String detectedLanguage = identifyLanguage(input);
        System.out.println("Detected Language: " + detectedLanguage);
    }

    public static String identifyLanguage(String input) {
        // Common English words
        String[] englishWords = {"hello", "world", "java", "language", "interpreter"};

        // Common Spanish words
        String[] spanishWords = {"hola", "mundo", "java", "lenguaje", "intérprete"};

       

        int englishCount = countCommonWords(input.toLowerCase(), englishWords);
        int spanishCount = countCommonWords(input.toLowerCase(), spanishWords);
      


        if (englishCount > spanishCount) {
            return "English";
        } else if (spanishCount > englishCount) {
            return "Spanish";
        } else {
            return "unknown";
        }
    }

    private static int countCommonWords(String input, String[] commonWords) {
        int count = 0;
        for (String word : commonWords) {
            if (input.contains(word)) {
                count++;
            }
        }
        return count;
    }
}
