class Node {
    int data;
    Node next;
    Node forward;

    Node(int data) {
        this.data = data;
        this.next = null;
        this.forward = null;
    }
}

public class P38 {


    public static void schorrWaite(Node root) {
        Node current = root;

        while (current != null) {
            if (current.next != null && current.forward == null) {
                current.forward = current.next;
                current.next = null;
                current = current.forward;
            } else if (current.forward != null && current.forward != root) {
                Node next = current.forward;
                current.forward = root;
                root = current;
                current = next;
            } else {
                current.forward = root;
                root = current;
                current = current.next;
            }
        }
    }

    public static void printMarkedNodes(Node root) {
        Node current = root;

        while (current != null) {
            System.out.print(current.data + " ");
            current = current.forward;
        }

        System.out.println();
    }

    public static void main(String[] args) {
        // Sample graph: 1 -> 2 -> 3 -> 4 -> 5
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        schorrWaite(node1);

        System.out.println("Marked Nodes:");
        printMarkedNodes(node1);
    }
}
