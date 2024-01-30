public class Q76CirQueue {
    static class Queue {
        static int arr[];
        static int rear = -1;
        static int front = -1;
        static int size;

        Queue(int n) {
            this.size = n;
            arr = new int[n];
        }

        static boolean isEmpty() {
            return rear == -1 && front == -1;
        }

        static boolean isFull() {
            return (rear + 1) % size == front;
        }

        static void enqueue(int value) {
            if (isFull()) {
                System.out.println("queue is full!!");
                return;
            }
            if (front == -1) {
                front = 0;
            }
            rear = (rear + 1) % size;
            arr[rear] = value;
        }

        static int dequeue() {
            if (isEmpty()) {
                System.out.println("Queue is empty!!");
                return -1;
            }

            int result = arr[front];
            if (rear == front) {
                rear = front = -1;
            } else {
                front = (front + 1) % size;
            }
            return result;
        }

        static int peek() {
            if (isEmpty()) {
                System.out.println("Queue is empty!!");
                return -1;
            }
            return arr[front];
        }
    }

    public static void main(String[] args) {
        Queue q1 = new Queue(5);
        q1.enqueue(1);
        q1.enqueue(2);
        q1.enqueue(3);
        q1.enqueue(4);
        q1.enqueue(5);

        q1.dequeue();
        System.out.println(q1.peek());
        q1.dequeue();
        q1.enqueue(6);
        System.out.println(q1.peek());

        System.out.println("Original Queue : ");
        while (!q1.isEmpty()) {
            System.out.println(q1.peek());
            q1.dequeue();
        }
    }
}
