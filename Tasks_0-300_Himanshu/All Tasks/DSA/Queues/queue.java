public class queue {
    static class Queue {
        static int arr[];
        static int rear = -1;
        static int size;

        Queue(int n) {
            this.size = n;
            arr = new int[n];
        }

        static boolean isEmpty() {
            return rear == -1;
        }

        static void enqueue(int value) {
            if (rear == size - 1) {
                System.out.println("queue is full!!");
                return;
            }
            rear++;
            arr[rear] = value;
        }

        static int dequeue() {
            if (isEmpty()) {
                System.out.println("Queue is empty!!");
                return -1;
            }

            int front = arr[0];
            for (int i = 0; i < rear; i++) {
                arr[i] = arr[i + 1];
            }
            rear--;
            return arr[0];
        }

        static int peek() {
            if (isEmpty()) {
                System.out.println("Queue is empty!!");
                return -1;
            }
            return arr[0];
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
    }
}
