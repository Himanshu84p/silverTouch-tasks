package prac70;

import java.util.TreeSet;

class Node {
	int value;
	Node left, right;

	public Node(int value) {
		this.value = value;
		this.left = null;
		this.right = null;
	}
}

public class YFastTrie {
	private static final int MAX_BITS = 32;
	private Node root;
	private TreeSet<Integer> leaves;

	public YFastTrie() {
		this.root = null;
		this.leaves = new TreeSet<>();
	}

	public void insert(int key) {
		leaves.add(key);
		root = insert(root, key, MAX_BITS - 1);
	}

	private Node insert(Node node, int key, int bit) {
		if (node == null) {
			node = new Node(key);
		} else if (isBitSet(key, bit)) {
			node.right = insert(node.right, key, bit - 1);
		} else {
			node.left = insert(node.left, key, bit - 1);
		}
		return node;
	}

	public void delete(int key) {
		leaves.remove(key);
		root = delete(root, key, MAX_BITS - 1);
	}

	private Node delete(Node node, int key, int bit) {
		if (node != null) {
			if (isBitSet(key, bit)) {
				node.right = delete(node.right, key, bit - 1);
			} else {
				node.left = delete(node.left, key, bit - 1);
			}

			if (node.left == null && node.right == null && !leaves.contains(node.value)) {

				node = null;
			}
		}
		return node;
	}

	public boolean search(int key) {
		return leaves.contains(key);
	}

	public int successor(int key) {
		Integer successor = leaves.higher(key);
		return (successor != null) ? successor : -1;
	}

	public int predecessor(int key) {
		Integer predecessor = leaves.lower(key);
		return (predecessor != null) ? predecessor : -1;
	}

	private boolean isBitSet(int num, int bit) {
		return (num & (1 << bit)) != 0;
	}

	public static void main(String[] args) {
		YFastTrie yFastTrie = new YFastTrie();

		yFastTrie.insert(5);
		yFastTrie.insert(3);
		yFastTrie.insert(7);

		System.out.println("Search 5: " + yFastTrie.search(5));
		System.out.println("Search 4: " + yFastTrie.search(4));

		yFastTrie.delete(5);

		System.out.println("Search 5: " + yFastTrie.search(5));

		System.out.println("Successor of 3: " + yFastTrie.successor(3));
		System.out.println("Predecessor of 7: " + yFastTrie.predecessor(7));
	}
}
