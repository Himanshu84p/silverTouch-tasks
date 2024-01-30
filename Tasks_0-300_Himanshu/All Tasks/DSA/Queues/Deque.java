class Node<T> {
    T data;
    Node<T> next;
    Node<T> prev;

    public Node(T data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

public class Deque<T> {
    private Node<T> front;
    private Node<T> rear;
    private int size;

    public Deque() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    public void addFront(T element) {
        Node<T> newNode = new Node<>(element);
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            newNode.next = front;
            front.prev = newNode;
            front = newNode;
        }
        size++;
    }

    public void addRear(T element) {
        Node<T> newNode = new Node<>(element);
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            newNode.prev = rear;
            rear = newNode;
        }
        size++;
    }

    public T removeFront() {
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }
        T data = front.data;
        front = front.next;
        if (front != null) {
            front.prev = null;
        } else {
            rear = null;
        }
        size--;
        return data;
    }

    public T removeRear() {
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }
        T data = rear.data;
        rear = rear.prev;
        if (rear != null) {
            rear.next = null;
        } else {
            front = null;
        }
        size--;
        return data;
    }

    public T peekFront() {
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }
        return front.data;
    }

    public T peekRear() {
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }
        return rear.data;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();

        deque.addFront(1);
        deque.addRear(2);
        deque.addFront(3);

        System.out.println("Deque: " + deque.peekFront() + " " + deque.peekRear());
        System.out.println("Size: " + deque.size());

        System.out.println("Remove front: " + deque.removeFront());
        System.out.println("Remove rear: " + deque.removeRear());

        System.out.println("Peek front: " + deque.peekFront());
        System.out.println("Peek rear: " + deque.peekRear());

        System.out.println("Size: " + deque.size());
    }
}
