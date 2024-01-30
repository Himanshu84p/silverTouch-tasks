package prac51;

import java.util.Random;

public class SkipGraph {
	private SkipGraphNode head;
	private int maxLevel;
	private Random random;

	public SkipGraph() {
		this.head = new SkipGraphNode(Integer.MIN_VALUE, 0);
		this.maxLevel = 0;
		this.random = new Random();
	}

	public void insert(int key) {
		int level = generateRandomLevel();
		SkipGraphNode newNode = new SkipGraphNode(key, level);

		while (head.next.size() < level + 1) {
			head.next.add(null);
		}

		int currentLevel = maxLevel;
		SkipGraphNode current = head;

		while (currentLevel >= 0) {
			while (current.next.size() > currentLevel && current.next.get(currentLevel) != null
					&& current.next.get(currentLevel).key < key) {
				current = current.next.get(currentLevel);
			}

			if (currentLevel <= level) {
				while (current.next.size() <= currentLevel) {
					current.next.add(null);
				}
				newNode.next.set(currentLevel, current.next.get(currentLevel));
				current.next.set(currentLevel, newNode);
			}

			currentLevel--;

			if (currentLevel < 0 && newNode.next.get(0) != null && newNode.next.get(0).key > key) {
				maxLevel++;
			}
		}
	}

	public SkipGraphNode search(int key) {
		SkipGraphNode current = head;
		int currentLevel = maxLevel;

		while (currentLevel >= 0) {
			while (current.next.size() > currentLevel && current.next.get(currentLevel) != null
					&& current.next.get(currentLevel).key < key) {
				current = current.next.get(currentLevel);
			}

			currentLevel--;

			if (current.next.size() > 0 && current.next.get(0) != null && current.next.get(0).key == key) {
				return current.next.get(0);
			}
		}

		return null;
	}

	private int generateRandomLevel() {
		int level = 0;
		while (random.nextDouble() < 0.5 && level < maxLevel + 1) {
			level++;
		}
		return level;
	}

	public static void main(String[] args) {
		SkipGraph skipGraph = new SkipGraph();

		skipGraph.insert(10);
		skipGraph.insert(20);
		skipGraph.insert(5);
		skipGraph.insert(6);
		skipGraph.insert(12);

		int searchKey = 6;
		SkipGraphNode result = skipGraph.search(searchKey);

		if (result != null) {
			System.out.println("Key " + searchKey + " found in the skip graph.");
		} else {
			System.out.println("Key " + searchKey + " not found in the skip graph.");
		}
	}
}
