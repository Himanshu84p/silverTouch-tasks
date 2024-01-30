// linked list implementation
#include <stdio.h>
#include <stdlib.h>

struct Node
{
    int data;
    struct Node *next;
};

struct Node *insertAtBeginning(struct Node *head, int value)
{
    struct Node *newNode = (struct Node *)malloc(sizeof(struct Node));
    newNode->data = value;

    newNode->next = head;

    head = newNode;

    return head;
}

void displayList(struct Node *head)
{
    struct Node *current = head;

    printf("Linked List: ");
    while (current != NULL)
    {
        printf("%d -> ", current->data);
        current = current->next;
    }
    printf("\n");
}

int main()
{
    // initialize empty list
    struct Node *head = NULL;

    head = insertAtBeginning(head, 3);
    head = insertAtBeginning(head, 2);
    head = insertAtBeginning(head, 1);

    displayList(head);

    return 0;
}
