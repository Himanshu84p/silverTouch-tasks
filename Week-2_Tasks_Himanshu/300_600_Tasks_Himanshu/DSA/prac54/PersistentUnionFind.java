package prac54;

import java.util.Arrays;

public class PersistentUnionFind {
	private int[] parent;
	private int[] rank;
	private int[] version;

	public PersistentUnionFind(int n) {
		parent = new int[n];
		rank = new int[n];
		version = new int[n];
		Arrays.fill(version, Integer.MAX_VALUE);
		initialize();
	}

	private void initialize() {
		for (int i = 0; i < parent.length; i++) {
			parent[i] = i;
			rank[i] = 0;
		}
	}

	public int find(int v, int ver) {
		if (version[v] > ver) {
			return v;
		}
		return findInternal(v, ver);
	}

	private int findInternal(int v, int ver) {
		int root = v;
		while (version[root] <= ver) {
			root = parent[root];
		}

		// Apply path compression
		int current = v;
		while (current != root) {
			int next = parent[current];
			parent[current] = root;
			current = next;
		}

		return root;
	}

	public void union(int u, int v) {
		int rootU = find(u, Integer.MAX_VALUE);
		int rootV = find(v, Integer.MAX_VALUE);

		if (rootU != rootV) {
			if (rank[rootU] > rank[rootV]) {
				parent[rootV] = rootU;
			} else if (rank[rootU] < rank[rootV]) {
				parent[rootU] = rootV;
			} else {
				parent[rootV] = rootU;
				rank[rootU]++;
			}
		}
		System.out.println("Union - Parent: " + Arrays.toString(parent));
	}

	public void persist(int ver) {
		for (int i = 0; i < parent.length; i++) {
			if (version[i] == Integer.MAX_VALUE || version[i] >= ver) {
				version[i] = ver;
			}
		}
		System.out.println("Persist Version " + ver + " - Parent: " + Arrays.toString(parent));
	}

	public boolean isConnected(int u, int v, int ver) {
		return find(u, ver) == find(v, ver);
	}

	public void printState(int ver) {
		System.out.println(
				"Version " + ver + " - Parent: " + Arrays.toString(parent) + ", Rank: " + Arrays.toString(rank));
	}
}
