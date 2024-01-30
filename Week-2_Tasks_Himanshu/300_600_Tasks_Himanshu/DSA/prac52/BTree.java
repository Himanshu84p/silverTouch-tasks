package prac52;

import java.util.ArrayList;
import java.util.List;

public class BTree {
	private BTreeNode root;
	private int t;

	public BTree(int t) {
		this.root = null;
		this.t = t;
	}

	public void insert(int key) {
		if (root == null) {
			root = new BTreeNode(t, true);
			root.keys[0] = key;
			root.numKeys = 1;
		} else {
			if (root.numKeys == 2 * t - 1) {
				BTreeNode newRoot = new BTreeNode(t, false);
				newRoot.children[0] = root;
				splitChild(newRoot, 0);
				root = newRoot;
			}
			insertNonFull(root, key);
		}
	}

	private void insertNonFull(BTreeNode node, int key) {
		int i = node.numKeys - 1;

		if (node.leaf) {
			while (i >= 0 && key < node.keys[i]) {
				node.keys[i + 1] = node.keys[i];
				i--;
			}

			node.keys[i + 1] = key;
			node.numKeys++;
		} else {
			while (i >= 0 && key < node.keys[i]) {
				i--;
			}

			i++;

			if (node.children[i].numKeys == 2 * t - 1) {
				splitChild(node, i);

				if (key > node.keys[i]) {
					i++;
				}
			}

			insertNonFull(node.children[i], key);
		}
	}

	private void splitChild(BTreeNode parent, int index) {
		BTreeNode child = parent.children[index];
		BTreeNode newChild = new BTreeNode(t, child.leaf);

		parent.numKeys++;
		for (int i = parent.numKeys - 1; i > index; i--) {
			parent.keys[i] = parent.keys[i - 1];
		}
		parent.keys[index] = child.keys[t - 1];

		for (int i = parent.numKeys; i > index + 1; i--) {
			parent.children[i] = parent.children[i - 1];
		}
		parent.children[index + 1] = newChild;

		newChild.numKeys = t - 1;
		for (int i = 0; i < t - 1; i++) {
			newChild.keys[i] = child.keys[i + t];
		}

		child.numKeys = t - 1;
		parent.children[index] = child;
	}

	public List<Integer> rangeQuery(int low, int high) {
		List<Integer> result = new ArrayList<>();
		rangeQuery(root, low, high, result);
		return result;
	}

	private void rangeQuery(BTreeNode node, int low, int high, List<Integer> result) {
		if (node != null) {
			int i = 0;
			while (i < node.numKeys && low > node.keys[i]) {
				i++;
			}

			if (i < node.numKeys && low <= node.keys[i]) {

				rangeQuery(node.children[i], low, high, result);
			}

			while (i < node.numKeys && node.keys[i] <= high) {

				result.add(node.keys[i]);
				i++;
			}

			if (i < node.numKeys && high >= node.keys[i]) {
				rangeQuery(node.children[i], low, high, result);
			}
		}
	}

	public static void main(String[] args) {
		BTree bTree = new BTree(3);

		bTree.insert(10);
		bTree.insert(20);
		bTree.insert(5);
		bTree.insert(6);
		bTree.insert(12);
		bTree.insert(30);
		bTree.insert(7);

		List<Integer> result = bTree.rangeQuery(10, 20);

		System.out.println("Range Query Result: " + result);
	}
}
