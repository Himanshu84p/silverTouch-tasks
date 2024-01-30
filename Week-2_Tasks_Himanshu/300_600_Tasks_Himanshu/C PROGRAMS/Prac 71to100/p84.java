import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class p84 {
    static class Node {
        int value;
        List<Node> neighbors;

        public Node(int value) {
            this.value = value;
            this.neighbors = new ArrayList<>();
        }

        public void addNeighbor(Node neighbor) {
            this.neighbors.add(neighbor);
        }
    }

    static class BFSTask extends RecursiveAction {
        Node node;

        BFSTask(Node node) {
            this.node = node;
        }

        @Override
        protected void compute() {
            System.out.println("Visiting Node " + node.value);

            List<BFSTask> subtasks = new ArrayList<>();
            for (Node neighbor : node.neighbors) {
                BFSTask task = new BFSTask(neighbor);
                subtasks.add(task);
                task.fork();
            }

            for (BFSTask task : subtasks) {
                task.join();
            }
        }
    }

    public static void parallelBFS(Node startNode) {
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new BFSTask(startNode));
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        node1.addNeighbor(node2);
        node1.addNeighbor(node3);
        node2.addNeighbor(node4);

        parallelBFS(node1);
    }
}
