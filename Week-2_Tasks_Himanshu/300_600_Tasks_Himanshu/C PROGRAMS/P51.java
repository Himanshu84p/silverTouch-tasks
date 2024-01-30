import java.util.Arrays;

class NetworkSimplex {

    static final int INF = Integer.MAX_VALUE / 2;

    static class Edge {
        int from, to, capacity, cost, flow;

        Edge(int from, int to, int capacity, int cost) {
            this.from = from;
            this.to = to;
            this.capacity = capacity;
            this.cost = cost;
            this.flow = 0;
        }
    }

    private int n;
    private int m;
    private Edge[] edges;
    private int[] head, nxt, to, capacity, cost, flow, pot, dist, parent;
    private boolean[] inQueue;

    NetworkSimplex(int n, int m) {
        this.n = n;
        this.m = m;
        this.edges = new Edge[m];
        this.head = new int[n];
        this.nxt = new int[m];
        this.to = new int[m];
        this.capacity = new int[m];
        this.cost = new int[m];
        this.flow = new int[m];
        this.pot = new int[n];
        this.dist = new int[n];
        this.parent = new int[n];
        this.inQueue = new boolean[n];
        Arrays.fill(head, -1);
    }

    void addEdge(int id, int from, int to, int capacity, int cost) {
        edges[id] = new Edge(from, to, capacity, cost);
        nxt[id] = head[from];
        head[from] = id;
    }

    int solve(int source, int sink, int requiredFlow) {
        int result = 0;
        Arrays.fill(flow, 0);
        Arrays.fill(pot, 0);
        Arrays.fill(inQueue, false);

        while (true) {
            bellmanFord(source);

            if (dist[sink] == INF) {
                break;
            }

            for (int i = 0; i < n; i++) {
                pot[i] += dist[i];
            }

            int cur = sink;
            int bottleneck = requiredFlow;

            while (cur != source) {
                int id = parent[cur];
                bottleneck = Math.min(bottleneck, edges[id].capacity - flow[id]);
                cur = edges[id].from;
            }

            cur = sink;

            while (cur != source) {
                int id = parent[cur];
                flow[id] += bottleneck;
                flow[id ^ 1] -= bottleneck;
                result += bottleneck * edges[id].cost;
                cur = edges[id].from;
            }

            requiredFlow -= bottleneck;
        }

        return result;
    }

    private void bellmanFord(int source) {
        Arrays.fill(dist, INF);
        Arrays.fill(parent, -1);

        dist[source] = 0;
        inQueue[source] = true;
        int[] queue = new int[n];
        int qt = 0;

        for (int i = 0; i < n; i++) {
            for (int v = 0; v < n; v++) {
                for (int id = head[v]; id != -1; id = nxt[id]) {
                    if (flow[id] < capacity[id] && dist[v] + pot[v] - pot[to[id]] + cost[id] < dist[to[id]]) {
                        dist[to[id]] = dist[v] + pot[v] - pot[to[id]] + cost[id];
                        parent[to[id]] = id;

                        if (!inQueue[to[id]]) {
                            inQueue[to[id]] = true;
                            queue[qt++] = to[id];

                            if (qt == n) {
                                qt = 0;
                            }
                        }
                    }
                }
            }

            inQueue[queue[i]] = false;
        }
    }

    public static void main(String[] args) {
        int n = 4;
        int m = 5;

        NetworkSimplex network = new NetworkSimplex(n, m);


        network.addEdge(0, 0, 1, 2, 1);
        network.addEdge(1, 0, 2, 1, 2);
        network.addEdge(2, 1, 2, 1, 1);
        network.addEdge(3, 1, 3, 1, 3);
        network.addEdge(4, 2, 3, 2, 2);

        int source = 0;
        int sink = 3;
        int requiredFlow = 3;

        int minCost = network.solve(source, sink, requiredFlow);

        System.out.println("Minimum Cost Flow: " + minCost);
    }
}
