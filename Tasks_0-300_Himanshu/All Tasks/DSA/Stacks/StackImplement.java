//stack implementation
package DSA.Stacks;

public class StackImplement {
    private int maxSize;
    private int top;
    private int[] stackArray;

    public StackImplement(int size) {
        maxSize = size;
        stackArray = new int[maxSize];
        top = -1;
    }

    public boolean isFull() {
        return (top == maxSize - 1);
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public void push(int value) {
        if (isFull()) {
            System.out.println("Stack is full!! cannot perform push.");
        }
        stackArray[++top] = value;
        System.out.println("pushed value " + value);
    }

    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack is Empty !! cannot pop!!");
        }
        int poppedValue = stackArray[top--];
        System.out.println("popped value " + poppedValue);
        return poppedValue;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Stack is Empty !! cannot display!!");
        }
        for (int i = 0; i <= top; i++) {
            System.out.print(" " + stackArray[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {

        StackImplement stack = new StackImplement(4);

        stack.push(5);
        stack.push(3);
        stack.push(7);
        stack.push(1);

        stack.display();
        stack.pop();
        stack.display();

    }
}
