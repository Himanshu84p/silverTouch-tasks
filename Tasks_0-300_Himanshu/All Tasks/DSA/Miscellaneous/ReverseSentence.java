public class ReverseSentence {

    public static String reverseSentence(String sentence) {
        String[] words = sentence.split("\\s+");
        StringBuilder reversedSentence = new StringBuilder();

        for (int i = words.length - 1; i >= 0; i--) {
            reversedSentence.append(words[i]).append(" ");
        }

        return reversedSentence.toString().trim();
    }

    public static void main(String[] args) {
        String sentence = "Hello World! Java is amazing.";
        String reversedSentence = reverseSentence(sentence);
        System.out.println("Original Sentence: " + sentence);
        System.out.println("Reversed Sentence: " + reversedSentence);
    }
}
