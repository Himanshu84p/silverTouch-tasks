package prac69;

import java.util.ArrayDeque;
import java.util.Queue;

class ScapegoatTreeNode {
	int key;
	ScapegoatTreeNode left, right, parent;

	public ScapegoatTreeNode(int key) {
		this.key = key;
		this.left = null;
		this.right = null;
		this.parent = null;
	}
}

public class ScapegoatTree {
	private ScapegoatTreeNode root;
	private int size;
	private double alpha;

	public ScapegoatTree(double alpha) {
		this.root = null;
		this.size = 0;
		this.alpha = alpha;
	}

	public void insert(int key) {
		root = insert(root, key);
		size++;
	}

	private ScapegoatTreeNode insert(ScapegoatTreeNode node, int key) {
		if (node == null) {
			return new ScapegoatTreeNode(key);
		}

		if (key < node.key) {
			node.left = insert(node.left, key);
			node.left.parent = node;
		} else if (key > node.key) {
			node.right = insert(node.right, key);
			node.right.parent = node;
		}

		if (isUnbalanced(node)) {
			rebuildTree(node);
		}

		return node;
	}

	private boolean isUnbalanced(ScapegoatTreeNode node) {
		int subtreeSize = size(node);
		int parentSize = (node.parent != null) ? size(node.parent) : 0;
		return subtreeSize > alpha * parentSize;
	}

	private void rebuildTree(ScapegoatTreeNode node) {
		Queue<ScapegoatTreeNode> nodes = new ArrayDeque<>();
		collectNodes(node, nodes);

		ScapegoatTreeNode parent = node.parent;
		ScapegoatTreeNode[] nodeArray = nodes.toArray(new ScapegoatTreeNode[0]);
		ScapegoatTreeNode newSubtree = buildBalancedTree(nodeArray, 0, nodeArray.length - 1);

		if (parent == null) {
			root = newSubtree;
		} else if (parent.left == node) {
			parent.left = newSubtree;
		} else {
			parent.right = newSubtree;
		}

		if (newSubtree != null) {
			newSubtree.parent = parent;
		}
	}

	private void collectNodes(ScapegoatTreeNode node, Queue<ScapegoatTreeNode> nodes) {
		if (node == null) {
			return;
		}

		collectNodes(node.left, nodes);
		nodes.add(node);
		collectNodes(node.right, nodes);
	}

	private ScapegoatTreeNode buildBalancedTree(ScapegoatTreeNode[] nodes, int start, int end) {
		if (start > end) {
			return null;
		}

		int mid = (start + end) / 2;
		ScapegoatTreeNode node = nodes[mid];
		node.left = buildBalancedTree(nodes, start, mid - 1);
		node.right = buildBalancedTree(nodes, mid + 1, end);

		return node;
	}

	private int size(ScapegoatTreeNode node) {
		return (node != null) ? 1 + size(node.left) + size(node.right) : 0;
	}

	public void printInOrder() {
		printInOrder(root);
		System.out.println();
	}

	private void printInOrder(ScapegoatTreeNode node) {
		if (node != null) {
			printInOrder(node.left);
			System.out.print(node.key + " ");
			printInOrder(node.right);
		}
	}

	public static void main(String[] args) {
		ScapegoatTree tree = new ScapegoatTree(0.75);

		int[] keys = { 5, 3, 7, 2, 4, 6, 8 };
		for (int key : keys) {
			tree.insert(key);
			tree.printInOrder();
		}
	}
}
