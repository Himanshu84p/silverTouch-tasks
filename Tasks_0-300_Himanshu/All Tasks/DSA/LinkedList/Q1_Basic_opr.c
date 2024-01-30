// linked list basic operation
#include <stdio.h>
#include <stdlib.h>
struct node
{
    int data;
    struct node *next;
};

struct node *insertAtBeginning(struct node *head, int value)
{

    struct node *newnode = (struct node *)malloc(sizeof(struct node));

    newnode->data = value;

    newnode->next = head;

    head = newnode;
    return head;
}

void display(struct node *head)
{
    struct node *current = head;
    while (current != NULL)
    {
        printf("%d --> ", current->data);
        current = current->next;
    }
}

void deleteAtBegin(struct node **head)
{
    if (*head == NULL)
    {
        printf("List already empty!!");
    }
    struct node *temp = *head;
    *head = (*head)->next;
    free(temp);
}

int main()
{
    struct node *head = NULL;

    head = insertAtBeginning(head, 3);
    head = insertAtBeginning(head, 2);
    head = insertAtBeginning(head, 1);

    display(head);
    deleteAtBegin(&head);
    printf("\nAfter deletion : \n");
    display(head);

    return 0;
}