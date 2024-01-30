// Find the middle element
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

struct node *findMiddle(struct node *head)
{
    if (head == NULL)
    {
        printf("List is empty");
        return NULL;
    }
    struct node *slow = head;
    struct node *fast = head;

    while (fast != NULL && fast->next != NULL)
    {
        slow = slow->next;
        fast = fast->next->next;
    }

    return slow;
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
    struct node *middle = findMiddle(head);
    if (middle != NULL)
    {
        printf("\nMiddle element is %d", middle->data);
    }
    else
    {
        printf("list is empty can't find middle.");
    }

    return 0;
}