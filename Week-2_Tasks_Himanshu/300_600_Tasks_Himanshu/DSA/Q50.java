import java.util.NoSuchElementException;

class Node {
    int data;
    Node xorPointer;

    public Node(int data) {
        this.data = data;
    }
}

public class Q50 {
    private Node head;

    public Q50() {
        this.head = null;
    }

    public void insert(int data) {
        Node newNode = new Node(data);

        if (head == null) {

            newNode.xorPointer = null;
        } else {

            newNode.xorPointer = XOR(null, head);

            head.xorPointer = XOR(newNode, head.xorPointer);
        }

        head = newNode;
    }

    public void printList() {
        Node current = head;
        Node prev = null;
        Node next;

        while (current != null) {

            System.out.print(current.data + " ");

            next = XOR(prev, current.xorPointer);
            prev = current;
            current = next;
        }
    }

    private Node XOR(Node a, Node b) {

        return new Node(0) {
            @Override
            public Node getXORPointer() {
                return new Node(a.data ^ b.data);
            }
        };
    }

    public static void main(String[] args) {
        Q50 xorList = new Q50();

        xorList.insert(1);
        xorList.insert(2);
        xorList.insert(3);

        System.out.print("XOR Linked List: ");
        xorList.printList();
    }
}
