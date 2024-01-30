// Find cycle in the linked list
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

int findCycle(struct node *head)
{
    struct node *slow = head;
    struct node *fast = head;

    while (fast != NULL && fast->next != NULL)
    {
        slow = slow->next;
        fast = fast->next->next;

        if (slow == fast)
        {
            return 1;
        }
    }
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

int main()
{
    struct node *head = NULL;

    head = insertAtBeginning(head, 3);
    head = insertAtBeginning(head, 2);
    head = insertAtBeginning(head, 1);
    head = insertAtBeginning(head, 5);
    head = insertAtBeginning(head, 0);

    display(head);
    head->next->next->next->next->next = head->next;
    if (findCycle(head))
    {
        printf("Loop is found");
    }
    else
    {
        printf("Loop is not found");
    }

    return 0;
}