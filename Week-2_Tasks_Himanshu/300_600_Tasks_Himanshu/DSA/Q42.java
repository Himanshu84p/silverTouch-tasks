import java.util.PriorityQueue;
import java.util.HashMap;

class HuffmanNode implements Comparable<HuffmanNode> {
    char data;
    int frequency;
    HuffmanNode left, right;

    public HuffmanNode(char data, int frequency) {
        this.data = data;
        this.frequency = frequency;
        this.left = this.right = null;
    }

    @Override
    public int compareTo(HuffmanNode node) {
        return this.frequency - node.frequency;
    }
}

public class Q42 {

    private static HuffmanNode buildHuffmanTree(HashMap<Character, Integer> frequencyMap) {
        PriorityQueue<HuffmanNode> minHeap = new PriorityQueue<>();

        for (char key : frequencyMap.keySet()) {
            minHeap.add(new HuffmanNode(key, frequencyMap.get(key)));
        }

        while (minHeap.size() > 1) {
            HuffmanNode left = minHeap.poll();
            HuffmanNode right = minHeap.poll();

            HuffmanNode internalNode = new HuffmanNode('\0', left.frequency + right.frequency);
            internalNode.left = left;
            internalNode.right = right;

            minHeap.add(internalNode);
        }

        return minHeap.poll();
    }

    private static void generateHuffmanCodes(HuffmanNode root, String code, HashMap<Character, String> huffmanCodes) {
        if (root != null) {
            if (root.data != '\0') {
                huffmanCodes.put(root.data, code);
            }

            generateHuffmanCodes(root.left, code + "0", huffmanCodes);
            generateHuffmanCodes(root.right, code + "1", huffmanCodes);
        }
    }

    public static String encode(String input) {
        HashMap<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : input.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        HuffmanNode root = buildHuffmanTree(frequencyMap);

        HashMap<Character, String> huffmanCodes = new HashMap<>();
        generateHuffmanCodes(root, "", huffmanCodes);

        StringBuilder encodedString = new StringBuilder();
        for (char c : input.toCharArray()) {
            encodedString.append(huffmanCodes.get(c));
        }

        return encodedString.toString();
    }

    public static String decode(String encodedString, HuffmanNode root) {
        StringBuilder decodedString = new StringBuilder();
        HuffmanNode current = root;

        for (char bit : encodedString.toCharArray()) {
            if (bit == '0') {
                current = current.left;
            } else {
                current = current.right;
            }

            if (current.data != '\0') {
                decodedString.append(current.data);
                current = root;
            }
        }

        return decodedString.toString();
    }

    public static void main(String[] args) {
        String input = "huffman";
        String encodedString = encode(input);

        System.out.println("Original String: " + input);
        System.out.println("Encoded String: " + encodedString);

        HuffmanNode root = buildHuffmanTree(new HashMap<>());
        String decodedString = decode(encodedString, root);

        System.out.println("Decoded String: " + decodedString);
    }
}
