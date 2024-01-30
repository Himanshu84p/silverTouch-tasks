import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class P17 {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the message to encrypt: ");
        String originalMessage = scanner.nextLine().toUpperCase();

        Map<Character, Character> encryptionKey = generateEncryptionKey();

        String encryptedMessage = encryptMessage(originalMessage, encryptionKey);
        System.out.println("Encrypted Message: " + encryptedMessage);

        String decryptedMessage = decryptMessage(encryptedMessage, encryptionKey);
        System.out.println("Decrypted Message: " + decryptedMessage);

        scanner.close();
    }

    private static Map<Character, Character> generateEncryptionKey() {
        char[] shuffledAlphabet = ALPHABET.toCharArray();
        for (int i = 0; i < shuffledAlphabet.length; i++) {
            int randomIndex = (int) (Math.random() * (i + 1));
            char temp = shuffledAlphabet[i];
            shuffledAlphabet[i] = shuffledAlphabet[randomIndex];
            shuffledAlphabet[randomIndex] = temp;
        }

        Map<Character, Character> encryptionKey = new HashMap<>();
        for (int i = 0; i < ALPHABET.length(); i++) {
            encryptionKey.put(ALPHABET.charAt(i), shuffledAlphabet[i]);
        }

        return encryptionKey;
    }

    private static String encryptMessage(String message, Map<Character, Character> encryptionKey) {
        StringBuilder encryptedMessage = new StringBuilder();

        for (char originalChar : message.toCharArray()) {
            // Encrypt only letters, leave non-letter characters unchanged
            if (Character.isLetter(originalChar)) {
                char encryptedChar = encryptionKey.getOrDefault(originalChar, originalChar);
                encryptedMessage.append(encryptedChar);
            } else {
                encryptedMessage.append(originalChar);
            }
        }

        return encryptedMessage.toString();
    }

    private static String decryptMessage(String encryptedMessage, Map<Character, Character> encryptionKey) {
        // To decrypt, create a reverse mapping from the shuffled alphabet to the original alphabet
        Map<Character, Character> decryptionKey = new HashMap<>();
        for (Map.Entry<Character, Character> entry : encryptionKey.entrySet()) {
            decryptionKey.put(entry.getValue(), entry.getKey());
        }

        StringBuilder decryptedMessage = new StringBuilder();

        for (char encryptedChar : encryptedMessage.toCharArray()) {
            // Decrypt only letters, leave non-letter characters unchanged
            if (Character.isLetter(encryptedChar)) {
                char decryptedChar = decryptionKey.getOrDefault(encryptedChar, encryptedChar);
                decryptedMessage.append(decryptedChar);
            } else {
                decryptedMessage.append(encryptedChar);
            }
        }

        return decryptedMessage.toString();
    }
}
