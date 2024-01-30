import java.util.PriorityQueue;
import java.util.HashMap;

class HuffmanNode implements Comparable<HuffmanNode> {
    char data;
    int frequency;
    HuffmanNode left, right;

    HuffmanNode(char data, int frequency) {
        this.data = data;
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }

    @Override
    public int compareTo(HuffmanNode node) {
        return this.frequency - node.frequency;
    }
}

public class P40 {

    public static void main(String[] args) {
        String inputText = "hello world";

        HashMap<Character, Integer> frequencyMap = buildFrequencyMap(inputText);

        HuffmanNode root = buildHuffmanTree(frequencyMap);

        HashMap<Character, String> huffmanCodes = generateHuffmanCodes(root);

        System.out.println("Original text: " + inputText);
        System.out.println("Huffman Codes:");
        for (char c : huffmanCodes.keySet()) {
            System.out.println(c + ": " + huffmanCodes.get(c));
        }

        String encodedText = encodeText(inputText, huffmanCodes);
        System.out.println("Encoded text: " + encodedText);

        String decodedText = decodeText(encodedText, root);
        System.out.println("Decoded text: " + decodedText);
    }

    private static HashMap<Character, Integer> buildFrequencyMap(String text) {
        HashMap<Character, Integer> frequencyMap = new HashMap<>();

        for (char c : text.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        return frequencyMap;
    }

    private static HuffmanNode buildHuffmanTree(HashMap<Character, Integer> frequencyMap) {
        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>();

        for (char c : frequencyMap.keySet()) {
            priorityQueue.offer(new HuffmanNode(c, frequencyMap.get(c)));
        }

        while (priorityQueue.size() > 1) {
            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();

            HuffmanNode mergedNode = new HuffmanNode('\0', left.frequency + right.frequency);
            mergedNode.left = left;
            mergedNode.right = right;

            priorityQueue.offer(mergedNode);
        }

        return priorityQueue.poll();
    }

    private static HashMap<Character, String> generateHuffmanCodes(HuffmanNode root) {
        HashMap<Character, String> huffmanCodes = new HashMap<>();
        generateCodesHelper(root, "", huffmanCodes);
        return huffmanCodes;
    }

    private static void generateCodesHelper(HuffmanNode node, String code, HashMap<Character, String> huffmanCodes) {
        if (node == null) {
            return;
        }

        if (node.data != '\0') {
            huffmanCodes.put(node.data, code);
        }

        generateCodesHelper(node.left, code + "0", huffmanCodes);
        generateCodesHelper(node.right, code + "1", huffmanCodes);
    }

    private static String encodeText(String text, HashMap<Character, String> huffmanCodes) {
        StringBuilder encodedText = new StringBuilder();

        for (char c : text.toCharArray()) {
            encodedText.append(huffmanCodes.get(c));
        }

        return encodedText.toString();
    }

    private static String decodeText(String encodedText, HuffmanNode root) {
        StringBuilder decodedText = new StringBuilder();
        HuffmanNode current = root;

        for (char bit : encodedText.toCharArray()) {
            if (bit == '0') {
                current = current.left;
            } else if (bit == '1') {
                current = current.right;
            }

            if (current.left == null && current.right == null) {
                decodedText.append(current.data);
                current = root;
            }
        }

        return decodedText.toString();
    }
}
