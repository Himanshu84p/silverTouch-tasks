package NetworkFlow;

import java.util.*;

class question1 {
    private static final int NIL = 0;
    private static final int INF = Integer.MAX_VALUE;

    private List<List<Integer>> graph;
    private int[] pairU, pairV, dist;
    private int[] setU, setV;

    public question1(int u, int v) {
        graph = new ArrayList<>(u + 1);
        for (int i = 0; i <= u; i++) {
            graph.add(new ArrayList<>());
        }
        pairU = new int[u + 1];
        pairV = new int[v + 1];
        dist = new int[u + 1];
        setU = new int[u + 1];
        setV = new int[v + 1];
    }

    public void addEdge(int u, int v) {
        graph.get(u).add(v);
    }

    public int maxBipartiteMatching() {
        int matching = 0;
        while (bfs()) {
            for (int u = 1; u < pairU.length; u++) {
                if (pairU[u] == NIL && dfs(u)) {
                    matching++;
                }
            }
        }
        return matching;
    }

    private boolean bfs() {
        Queue<Integer> queue = new LinkedList<>();
        for (int u = 1; u < pairU.length; u++) {
            if (pairU[u] == NIL) {
                dist[u] = 0;
                queue.add(u);
            } else {
                dist[u] = INF;
            }
        }
        dist[NIL] = INF;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            if (dist[u] < dist[NIL]) {
                for (int v : graph.get(u)) {
                    if (dist[pairV[v]] == INF) {
                        dist[pairV[v]] = dist[u] + 1;
                        queue.add(pairV[v]);
                    }
                }
            }
        }

        return dist[NIL] != INF;
    }

    private boolean dfs(int u) {
        if (u != NIL) {
            for (int v : graph.get(u)) {
                if (dist[pairV[v]] == dist[u] + 1 && dfs(pairV[v])) {
                    pairV[v] = u;
                    pairU[u] = v;
                    return true;
                }
            }
            dist[u] = INF;
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int u = 4; 
        int v = 4; 

        question1 bipartiteMatching = new question1(u, v);

        
        bipartiteMatching.addEdge(1, 2);
        bipartiteMatching.addEdge(1, 3);
        bipartiteMatching.addEdge(2, 1);
        bipartiteMatching.addEdge(2, 3);
        bipartiteMatching.addEdge(3, 2);
        bipartiteMatching.addEdge(4, 2);

        int maxMatching = bipartiteMatching.maxBipartiteMatching();

        System.out.println("Maximum Bipartite Matching: " + maxMatching);
    }
}

