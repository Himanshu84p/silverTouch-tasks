package prac58;

import java.util.*;

public class BlossomAlgorithm {
	private static final int INF = Integer.MAX_VALUE;

	public static List<Edge> findMaximumMatching(List<List<Integer>> graph) {
		int n = graph.size();
		List<Integer> match = new ArrayList<>(Collections.nCopies(n, -1));

		for (int i = 0; i < n; i++) {
			if (match.get(i) == -1) {
				bfs(graph, match, i);
			}
		}

		List<Edge> matchingEdges = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			if (match.get(i) != -1) {
				matchingEdges.add(new Edge(i, match.get(i)));
			}
		}

		return matchingEdges;
	}

	private static void bfs(List<List<Integer>> graph, List<Integer> match, int start) {
		int n = graph.size();
		List<Integer> label = new ArrayList<>(Collections.nCopies(n, INF));
		Deque<Integer> queue = new ArrayDeque<>();
		int[] parent = new int[n];
		Arrays.fill(parent, -1);

		queue.offerLast(start);
		label.set(start, 0);

		while (!queue.isEmpty()) {
			int u = queue.pollFirst();

			for (int v : graph.get(u)) {
				if (label.get(v) == INF) {
					label.set(v, label.get(u) + 1);
					parent[v] = u;

					if (match.get(v) == -1) {
						augmentPath(match, parent, v);
						return;
					}

					queue.offerLast(match.get(v));
				} else if (label.get(v) % 2 == 0 && label.get(u) % 2 == 1) {
					blossom(parent, match, u, v);
				}
			}
		}
	}

	private static void augmentPath(List<Integer> match, int[] parent, int end) {
		int u = end;
		while (u != -1) {
			int v = parent[u];
			int w = match.get(v);
			match.set(v, u);
			match.set(u, v);
			u = w;
		}
	}

	private static void blossom(int[] parent, List<Integer> match, int u, int v) {
		int n = match.size();
		List<Boolean> inBlossom = new ArrayList<>(Collections.nCopies(n, false));

		while (u != -1 || v != -1) {
			if (v != -1) {
				u = findCommonAncestor(parent, match, u, v);
				inBlossom.set(u, true);
				u = parent[match.get(u)];
			}

			if (u != -1) {
				v = findCommonAncestor(parent, match, u, v);
				inBlossom.set(v, true);
				v = parent[match.get(v)];
			}
		}

		for (int i = 0; i < n; i++) {
			if (inBlossom.get(i)) {
				parent[i] = n;
			}
		}
	}

	private static int findCommonAncestor(int[] parent, List<Integer> match, int u, int v) {
		boolean[] inPath = new boolean[parent.length];

		while (u != -1) {
			inPath[u] = true;
			u = parent[match.get(u)];
		}

		while (!inPath[v]) {
			v = parent[match.get(v)];
		}

		return v;
	}

	public static void main(String[] args) {
		int n = 4;
		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			graph.add(new ArrayList<>());
		}

		// Add edges to the graph
		addEdge(graph, 0, 1);
		addEdge(graph, 0, 2);
		addEdge(graph, 1, 2);
		addEdge(graph, 2, 3);

		List<Edge> matching = findMaximumMatching(graph);
		System.out.println("Maximum Cardinality Matching:");
		for (Edge edge : matching) {
			System.out.println(edge.from + " -> " + edge.to);
		}
	}

	private static void addEdge(List<List<Integer>> graph, int u, int v) {
		graph.get(u).add(v);
		graph.get(v).add(u);
	}

	static class Edge {
		int from, to;

		public Edge(int from, int to) {
			this.from = from;
			this.to = to;
		}
	}
}
