// Queue implementation
#include <stdio.h>
#define MAX_SIZE 100

int queue[MAX_SIZE];
int front = -1;
int rear = -1;

void enqueue(int el)
{
    if (rear == MAX_SIZE - 1)
    {
        printf("Queue is full");
        return;
    }
    if (front == -1)
    {
        front = 0;
    }
    rear++;
    queue[rear] = el;
}

int dequeue()
{
    if (front == -1 || front > rear)
    {
        printf("Queue is empty");
        return 0;
    }
    int el = queue[front];
    front++;
    return el;
}

int main()
{
    enqueue(342);
    enqueue(23);
    enqueue(12);

    printf("Elements inserted.\n");

    printf("%d is dequeued.\n", dequeue());
    printf("%d is dequeued.\n", dequeue());

    return 0;
}