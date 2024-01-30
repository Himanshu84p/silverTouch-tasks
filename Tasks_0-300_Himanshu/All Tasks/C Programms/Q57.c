// Stack implementation
#include <stdio.h>
#include <stdlib.h>

#define MAX_SIZE 10

struct Stack
{
    int arr[MAX_SIZE];
    int top;
};

void initializeStack(struct Stack *stack)
{
    stack->top = -1;
}

int isEmpty(struct Stack *stack)
{
    return (stack->top == -1);
}

int isFull(struct Stack *stack)
{
    return (stack->top == MAX_SIZE - 1);
}

void push(struct Stack *stack, int value)
{
    if (isFull(stack))
    {
        printf("Stack overflow. Cannot push %d.\n", value);
        return;
    }

    stack->arr[++stack->top] = value;
    printf("%d pushed onto the stack\n", value);
}

int pop(struct Stack *stack)
{
    if (isEmpty(stack))
    {
        printf("Stack underflow. Cannot pop.\n");
        return -1;
    }

    return stack->arr[stack->top--];
}

void displayStack(struct Stack *stack)
{
    if (isEmpty(stack))
    {
        printf("Stack is empty.\n");
        return;
    }

    printf("Stack: ");
    for (int i = 0; i <= stack->top; i++)
    {
        printf("%d ", stack->arr[i]);
    }
    printf("\n");
}

int main()
{

    struct Stack stack;
    initializeStack(&stack);

    push(&stack, 1);
    push(&stack, 2);
    push(&stack, 3);

    displayStack(&stack);

    int poppedValue = pop(&stack);
    if (poppedValue != -1)
    {
        printf("Popped element: %d\n", poppedValue);
    }

    displayStack(&stack);

    return 0;
}
