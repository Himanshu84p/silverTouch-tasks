package prac57;

import java.util.Collections;
import java.util.PriorityQueue;

public class DoubleEndedPriorityQueue<T extends Comparable<T>> {
    private PriorityQueue<T> frontHeap;
    private PriorityQueue<T> rearHeap;

    public DoubleEndedPriorityQueue() {
        frontHeap = new PriorityQueue<>();
        rearHeap = new PriorityQueue<>(Collections.reverseOrder());
    }

    public void insertFront(T element) {
        frontHeap.offer(element);
    }

    public void insertRear(T element) {
        rearHeap.offer(element);
    }

    public T deleteFront() {
        if (frontHeap.isEmpty()) {
            throw new IllegalStateException("DEPQ is empty");
        }
        return frontHeap.poll();
    }

    public T deleteRear() {
        if (rearHeap.isEmpty()) {
            throw new IllegalStateException("DEPQ is empty");
        }
        return rearHeap.poll();
    }

    public T getFrontMin() {
        if (frontHeap.isEmpty()) {
            throw new IllegalStateException("DEPQ is empty");
        }
        return frontHeap.peek();
    }

    public T getRearMax() {
        if (rearHeap.isEmpty()) {
            throw new IllegalStateException("DEPQ is empty");
        }
        return rearHeap.peek();
    }

    public static void main(String[] args) {
        DoubleEndedPriorityQueue<Integer> depq = new DoubleEndedPriorityQueue<>();

        depq.insertFront(5);
        depq.insertFront(3);
        depq.insertRear(8);
        depq.insertRear(10);

        System.out.println("Front Min: " + depq.getFrontMin());
        System.out.println("Rear Max: " + depq.getRearMax());

        depq.deleteFront();
        depq.deleteRear();

        System.out.println("Front Min after deletion: " + depq.getFrontMin());
        System.out.println("Rear Max after deletion: " + depq.getRearMax());
    }
}