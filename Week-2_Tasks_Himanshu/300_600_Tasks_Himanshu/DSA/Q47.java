import java.util.*;

class BipartiteGraph {
    private int leftSize;
    private int rightSize;
    private List<Integer>[] adjList;
    private int[] match;
    private int[] dist;

    public BipartiteGraph(int leftSize, int rightSize) {
        this.leftSize = leftSize;
        this.rightSize = rightSize;
        this.adjList = new List[leftSize];
        for (int i = 0; i < leftSize; i++) {
            this.adjList[i] = new ArrayList<>();
        }
        this.match = new int[rightSize];
        Arrays.fill(match, -1);
        this.dist = new int[leftSize];
    }

    public void addEdge(int left, int right) {
        adjList[left].add(right);
    }

    public int hopcroftKarp() {
        int result = 0;
        while (bfs()) {
            for (int left = 0; left < leftSize; left++) {
                if (match[left] == -1 && dfs(left)) {
                    result++;
                }
            }
        }
        return result;
    }

    private boolean bfs() {
        Arrays.fill(dist, -1);
        Queue<Integer> queue = new LinkedList<>();
        for (int left = 0; left < leftSize; left++) {
            if (match[left] == -1) {
                dist[left] = 0;
                queue.add(left);
            } else {
                dist[left] = Integer.MAX_VALUE;
            }
        }

        dist[rightSize] = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            int left = queue.poll();
            if (dist[left] < dist[rightSize]) {
                for (int right : adjList[left]) {
                    if (dist[match[right]] == Integer.MAX_VALUE) {
                        dist[match[right]] = dist[left] + 1;
                        queue.add(match[right]);
                    }
                }
            }
        }

        return dist[rightSize - 1] != -1;
    }

    private boolean dfs(int left) {
        if (left != -1) {
            for (int right : adjList[left]) {
                if (dist[match[right]] == dist[left] + 1 && dfs(match[right])) {
                    match[left] = right;
                    match[right] = left;
                    return true;
                }
            }
            dist[left] = -1;
            return false;
        }
        return true;
    }
}

public class Q47 {
    public static void main(String[] args) {
        int leftSize = 4;
        int rightSize = 4;

        BipartiteGraph graph = new BipartiteGraph(leftSize, rightSize);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);
        graph.addEdge(3, 3);

        int maxCardinalityMatching = graph.hopcroftKarp();

        System.out.println("Maximum Cardinality Matching: " + maxCardinalityMatching);
    }
}
