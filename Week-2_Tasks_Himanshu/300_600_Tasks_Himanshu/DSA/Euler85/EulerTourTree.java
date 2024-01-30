package Euler85;

import java.util.*;

public class EulerTourTree {

    static final int MAX = 1001;
    static int index = 0;

    static ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
    static int vis[] = new int[MAX];
    static int Euler[] = new int[2 * MAX];

    static void add_edge(int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    static void eulerTree(int u) {
        vis[u] = 1;
        Euler[index++] = u;

        for (int it : adj.get(u)) {
            if (vis[it] == 0) {
                eulerTree(it);
                Euler[index++] = u;
            }
        }
    }

    static void printEulerTour(int root, int N) {
        eulerTree(root);
        for (int i = 0; i < (2 * N - 1); i++)
            System.out.print(Euler[i] + " ");
    }

    public static void main(String[] args) {
        int N = 4;

        for (int i = 0; i <= N; i++)
            adj.add(new ArrayList<>());

        add_edge(1, 2);
        add_edge(2, 3);
        add_edge(2, 4);

        printEulerTour(1, N);
    }
}
