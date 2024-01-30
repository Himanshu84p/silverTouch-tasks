public class HuffmanDecoding {

    public static void main(String[] args) {
        String encodedText = "1001011";
        HuffmanNode root = buildSampleHuffmanTree();

        String decodedText = decodeText(encodedText, root);
        System.out.println("Decoded Text: " + decodedText);
    }

    private static HuffmanNode buildSampleHuffmanTree() {
        HuffmanNode root = new HuffmanNode('\0', 0);
        root.left = new HuffmanNode('A', 0);
        root.right = new HuffmanNode('\0', 0);
        root.right.left = new HuffmanNode('B', 0);
        root.right.right = new HuffmanNode('C', 0);
        return root;
    }

    private static String decodeText(String encodedText, HuffmanNode root) {
        StringBuilder decodedText = new StringBuilder();
        HuffmanNode current = root;

        for (char bit : encodedText.toCharArray()) {
            if (bit == '0') {
                current = current.left;
            } else {
                current = current.right;
            }

            if (current.data != '\0') {
                decodedText.append(current.data);
                current = root;
            }
        }

        return decodedText.toString();
    }
}
