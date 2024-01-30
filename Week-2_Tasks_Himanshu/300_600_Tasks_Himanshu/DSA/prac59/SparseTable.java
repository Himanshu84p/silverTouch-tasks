package prac59;

public class SparseTable {
	private int[][] sparseTable;
	private int[] logTable;
	private int[] array;

	public SparseTable(int[] inputArray) {
		int n = inputArray.length;
		int logN = (int) (Math.log(n) / Math.log(2)) + 1;

		sparseTable = new int[n][logN];
		logTable = new int[n + 1];
		array = inputArray;

		for (int i = 2; i <= n; i++) {
			logTable[i] = logTable[i >> 1] + 1;
		}

		for (int i = 0; i < n; i++) {
			sparseTable[i][0] = i;
		}

		for (int j = 1; (1 << j) <= n; j++) {
			for (int i = 0; i + (1 << j) - 1 < n; i++) {
				int x = sparseTable[i][j - 1];
				int y = sparseTable[i + (1 << (j - 1))][j - 1];
				sparseTable[i][j] = (array[x] <= array[y]) ? x : y;
			}
		}
	}

	public int query(int left, int right) {
		int k = logTable[right - left + 1];
		int x = sparseTable[left][k];
		int y = sparseTable[right - (1 << k) + 1][k];
		return (array[x] <= array[y]) ? x : y;
	}

	public static void main(String[] args) {
		int[] array = { 2, 5, 3, 1, 7, 6, 4, 8 };
		SparseTable sparseTable = new SparseTable(array);

		System.out.println("Minimum in range [1, 5]: " + array[sparseTable.query(1, 5)]);
		System.out.println("Minimum in range [2, 7]: " + array[sparseTable.query(2, 7)]);
		System.out.println("Minimum in range [0, 3]: " + array[sparseTable.query(0, 3)]);
	}
}
