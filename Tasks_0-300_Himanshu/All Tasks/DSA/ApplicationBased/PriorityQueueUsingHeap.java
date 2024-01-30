import java.util.Arrays;

public class PriorityQueueUsingHeap {

    private int capacity = 10;
    private int size = 0;
    private int[] heap;

    public PriorityQueueUsingHeap() {
        heap = new int[capacity];
    }

    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    private int getLeftChildIndex(int index) {
        return 2 * index + 1;
    }

    private int getRightChildIndex(int index) {
        return 2 * index + 2;
    }

    private boolean hasParent(int index) {
        return getParentIndex(index) >= 0;
    }

    private boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) < size;
    }

    private boolean hasRightChild(int index) {
        return getRightChildIndex(index) < size;
    }

    private int getParent(int index) {
        return heap[getParentIndex(index)];
    }

    private int getLeftChild(int index) {
        return heap[getLeftChildIndex(index)];
    }

    private int getRightChild(int index) {
        return heap[getRightChildIndex(index)];
    }

    private void swap(int index1, int index2) {
        int temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }

    private void ensureCapacity() {
        if (size == capacity) {
            capacity *= 2;
            heap = Arrays.copyOf(heap, capacity);
        }
    }

    public int peek() {
        if (size == 0) {
            throw new IllegalStateException("Priority queue is empty");
        }
        return heap[0];
    }

    public void add(int value) {
        ensureCapacity();
        heap[size] = value;
        size++;
        heapifyUp();
    }

    public int poll() {
        if (size == 0) {
            throw new IllegalStateException("Priority queue is empty");
        }
        int root = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown();
        return root;
    }

    private void heapifyUp() {
        int index = size - 1;
        while (hasParent(index) && getParent(index) > heap[index]) {
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    private void heapifyDown() {
        int index = 0;
        while (hasLeftChild(index)) {
            int smallerChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index) && getRightChild(index) < getLeftChild(index)) {
                smallerChildIndex = getRightChildIndex(index);
            }

            if (heap[index] < heap[smallerChildIndex]) {
                break;
            } else {
                swap(index, smallerChildIndex);
            }

            index = smallerChildIndex;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        PriorityQueueUsingHeap priorityQueue = new PriorityQueueUsingHeap();

        priorityQueue.add(5);
        priorityQueue.add(3);
        priorityQueue.add(8);
        priorityQueue.add(1);

        System.out.println("Priority Queue Size: " + priorityQueue.size());

        while (!priorityQueue.isEmpty()) {
            System.out.println("Top Priority Element: " + priorityQueue.poll());
        }
    }
}
