package prac63;

import java.util.Arrays;

class PersistentSegmentTree {
	static class Node {
		long sum;
		Node left, right;

		Node(long sum, Node left, Node right) {
			this.sum = sum;
			this.left = left;
			this.right = right;
		}

		Node(long sum) {
			this(sum, null, null);
		}
	}

	private Node build(int[] arr, int start, int end) {
		if (start == end) {
			return new Node(arr[start]);
		}
		int mid = (start + end) / 2;
		return new Node(0, build(arr, start, mid), build(arr, mid + 1, end));
	}

	private Node update(Node root, int start, int end, int idx, int value) {
		if (start == end) {
			return new Node(value);
		}
		int mid = (start + end) / 2;
		if (idx <= mid) {
			return new Node(root.sum + value, update(root.left, start, mid, idx, value), root.right);
		} else {
			return new Node(root.sum + value, root.left, update(root.right, mid + 1, end, idx, value));
		}
	}

	private Node lazyUpdate(Node root, int start, int end, int left, int right, int value) {
		if (left > end || right < start) {
			return root;
		}
		if (start == end) {
			return new Node(root.sum + value);
		}
		int mid = (start + end) / 2;
		return new Node(root.sum + (long) (right - left + 1) * value,
				lazyUpdate(root.left, start, mid, left, right, value),
				lazyUpdate(root.right, mid + 1, end, left, right, value));
	}

	private long query(Node root, int start, int end, int left, int right) {
		if (root == null || left > end || right < start) {
			return 0;
		}
		if (left <= start && right >= end) {
			return root.sum;
		}
		int mid = (start + end) / 2;
		return query(root.left, start, mid, left, right) + query(root.right, mid + 1, end, left, right);
	}

	public static void main(String[] args) {
		int[] initialArray = { 1, 2, 3, 4, 5 };
		PersistentSegmentTree pst = new PersistentSegmentTree();
		Node[] versions = new Node[initialArray.length + 1];

		versions[0] = pst.build(initialArray, 0, initialArray.length - 1);

		for (int i = 1; i <= initialArray.length; i++) {
			versions[i] = pst.update(versions[i - 1], 0, initialArray.length - 1, i - 1, 10);
		}

		System.out.println("Query on version 0: " + pst.query(versions[0], 0, initialArray.length - 1, 1, 3));
		System.out.println("Query on version 2: " + pst.query(versions[2], 0, initialArray.length - 1, 0, 4));
	}
}
