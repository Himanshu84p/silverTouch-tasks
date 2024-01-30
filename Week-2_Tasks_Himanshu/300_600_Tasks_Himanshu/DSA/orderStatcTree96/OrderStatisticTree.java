package orderStatcTree96;

public class OrderStatisticTree {

    static class Node {
        int key;
        int size;
        Node left, right;

        public Node(int key) {
            this.key = key;
            this.size = 1;
            this.left = this.right = null;
        }
    }

    private Node root;

    public OrderStatisticTree() {
        this.root = null;
    }

    public void insert(int key) {
        root = insert(root, key);
    }

    private Node insert(Node node, int key) {
        if (node == null) {
            return new Node(key);
        }

        node.size++;

        if (key < node.key) {
            node.left = insert(node.left, key);
        } else if (key > node.key) {
            node.right = insert(node.right, key);
        }

        return node;
    }

    public int getOrder(int key) {
        return getOrder(root, key);
    }

    private int getOrder(Node node, int key) {
        if (node == null) {
            return -1;
        }

        if (key == node.key) {
            return getSize(node.left) + 1;
        } else if (key < node.key) {
            return getOrder(node.left, key);
        } else {
            int rightOrder = getOrder(node.right, key);
            return (rightOrder != -1) ? getSize(node.left) + 1 + rightOrder : -1;
        }
    }

    public int getKthSmallest(int k) {
        return getKthSmallest(root, k);
    }

    private int getKthSmallest(Node node, int k) {
        if (node == null || k <= 0 || k > getSize(node)) {
            return -1;
        }

        int leftSize = getSize(node.left);

        if (k == leftSize + 1) {
            return node.key;
        } else if (k <= leftSize) {
            return getKthSmallest(node.left, k);
        } else {
            return getKthSmallest(node.right, k - leftSize - 1);
        }
    }

    private int getSize(Node node) {
        return (node != null) ? node.size : 0;
    }

    public static void main(String[] args) {
        OrderStatisticTree ost = new OrderStatisticTree();

        ost.insert(5);
        ost.insert(3);
        ost.insert(7);
        ost.insert(2);
        ost.insert(4);
        ost.insert(6);
        ost.insert(8);

        System.out.println("Order of 4: " + ost.getOrder(4));
        System.out.println("Order of 7: " + ost.getOrder(7));

        System.out.println("2nd smallest element: " + ost.getKthSmallest(2));
        System.out.println("4th smallest element: " + ost.getKthSmallest(4));
    }
}
