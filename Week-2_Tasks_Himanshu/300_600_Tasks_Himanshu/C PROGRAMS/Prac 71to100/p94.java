import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class p94 {
    private Map<String, double[]> wordVectors;
    private int vectorSize;

    public p94(int vectorSize) {
        this.wordVectors = new HashMap<>();
        this.vectorSize = vectorSize;
    }

    public void train(List<String> corpus, int windowSize, int epochs, double learningRate) {
        for (int epoch = 0; epoch < epochs; epoch++) {
            for (String targetWord : corpus) {
                int targetIndex = corpus.indexOf(targetWord);

                for (int contextIndex = Math.max(0, targetIndex - windowSize); contextIndex <= Math
                        .min(corpus.size() - 1, targetIndex + windowSize); contextIndex++) {

                    if (contextIndex != targetIndex) {
                        String contextWord = corpus.get(contextIndex);
                        updateWordVectors(targetWord, contextWord, learningRate);
                    }
                }
            }
        }
    }

    private void updateWordVectors(String targetWord, String contextWord, double learningRate) {
        double[] targetVector = wordVectors.computeIfAbsent(targetWord, k -> new double[vectorSize]);
        double[] contextVector = wordVectors.computeIfAbsent(contextWord, k -> new double[vectorSize]);

        for (int i = 0; i < vectorSize; i++) {
            targetVector[i] += learningRate * contextVector[i];
            contextVector[i] += learningRate * targetVector[i];
        }
    }

    public double[] getWordVector(String word) {
        return wordVectors.get(word);
    }

    public static void main(String[] args) {
        p94 wordEmbeddings = new p94(50);

        List<String> corpus = List.of("king", "queen", "man", "woman", "is", "a", "the");

        wordEmbeddings.train(corpus, 2, 100, 0.01);

        String targetWord = "king";
        double[] vector = wordEmbeddings.getWordVector(targetWord);

        System.out.println("Word vector for '" + targetWord + "': " + arrayToString(vector));
    }

    private static String arrayToString(double[] array) {
        StringBuilder builder = new StringBuilder("[");
        for (double value : array) {
            builder.append(value).append(", ");
        }
        builder.setLength(builder.length() - 2);
        builder.append("]");
        return builder.toString();
    }
}
