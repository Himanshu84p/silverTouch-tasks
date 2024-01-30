package prac53;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DancingLinks {
	private ColumnNode header;
	private List<DancingNode> solution;

	public DancingLinks(boolean[][] matrix, String[] columnNames) {
		initialize(matrix, columnNames);
	}

	private void initialize(boolean[][] matrix, String[] columnNames) {
		int numRows = matrix.length;
		int numCols = matrix[0].length;

		// Create header nodes
		header = new ColumnNode("header");
		List<ColumnNode> columnNodes = new LinkedList<>();

		for (String columnName : columnNames) {
			ColumnNode columnNode = new ColumnNode(columnName);
			columnNodes.add(columnNode);
			header.linkRight(columnNode);
		}

		for (int i = 0; i < numRows; i++) {
			DancingNode prev = null;
			for (int j = 0; j < numCols; j++) {
				if (matrix[i][j]) {
					ColumnNode colNode = columnNodes.get(j);
					DancingNode newNode = new DancingNode(colNode);
					colNode.size++;
					if (prev == null) {
						prev = newNode;
					}
					newNode.linkDown(prev);
					prev = newNode;
				}
			}
		}

		solution = new LinkedList<>();
	}

	private void search(int k) {
		if (header.right == header) {
			handleSolution();
		} else {
			ColumnNode column = chooseColumnNodeHeuristic();
			column.cover();

			for (DancingNode row = column.down; row != column; row = row.down) {
				solution.add(row);

				for (DancingNode j = row.right; j != row; j = j.right) {
					j.column.cover();
				}

				search(k + 1);

				row = solution.remove(solution.size() - 1);
				column = row.column;

				for (DancingNode j = row.left; j != row; j = j.left) {
					j.column.uncover();
				}
			}

			column.uncover();
		}
	}

	private ColumnNode chooseColumnNodeHeuristic() {
		int minSize = Integer.MAX_VALUE;
		ColumnNode result = null;

		for (ColumnNode column = (ColumnNode) header.right; column != header; column = (ColumnNode) column.right) {
			if (column.size < minSize) {
				minSize = column.size;
				result = column;
			}
		}

		return result;
	}

	private void handleSolution() {

		for (DancingNode node : solution) {
			System.out.print(node.column.name + " ");
		}
		System.out.println();
	}

	public void solve() {
		search(0);
	}

	public static void main(String[] args) {

		boolean[][] matrix = { { true, false, true, false, false, true }, { true, false, false, true, false, false },
				{ false, true, true, false, false, false }, { false, true, false, true, true, false },
				{ false, false, false, true, false, true }, { false, false, true, false, true, true } };

		String[] columnNames = { "A", "B", "C", "D", "E", "F" };

		DancingLinks dancingLinks = new DancingLinks(matrix, columnNames);
		dancingLinks.solve();
	}
}
