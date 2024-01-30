package sqrtDecom95;

public class SqrtDecomposition {

    private int[] arr;
    private int blockSize;
    private int[] blockSum;
    private int[] lazy;

    public SqrtDecomposition(int[] array) {
        arr = array;
        int n = arr.length;

        blockSize = (int) Math.ceil(Math.sqrt(n));
        int numBlocks = (n + blockSize - 1) / blockSize;

        blockSum = new int[numBlocks];
        lazy = new int[numBlocks];

        for (int i = 0; i < n; i++) {
            blockSum[i / blockSize] += arr[i];
        }
    }

    public void updateRange(int left, int right, int value) {
        int startBlock = left / blockSize;
        int endBlock = right / blockSize;

        for (int i = startBlock; i <= endBlock; i++) {
            lazy[i] += value;
        }

        for (int i = startBlock; i <= endBlock; i++) {
            int start = i * blockSize;
            int end = Math.min((i + 1) * blockSize - 1, arr.length - 1);
            blockSum[i] += (Math.min(end, right) - Math.max(start, left) + 1) * value;
        }
    }

    public int queryRange(int left, int right) {
        int startBlock = left / blockSize;
        int endBlock = right / blockSize;
        int result = 0;

        for (int i = startBlock; i <= endBlock; i++) {
            result += blockSum[i] + lazy[i] * blockSize;
        }

        for (int i = endBlock * blockSize; i <= right; i++) {
            result += arr[i] + lazy[endBlock];
        }

        return result;
    }

    public static void main(String[] args) {
        int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        SqrtDecomposition sqrtDecomposition = new SqrtDecomposition(array);

        sqrtDecomposition.updateRange(2, 8, 2);

        int queryResult = sqrtDecomposition.queryRange(1, 7);
        System.out.println("Query result for range [1, 7]: " + queryResult);
    }
}
