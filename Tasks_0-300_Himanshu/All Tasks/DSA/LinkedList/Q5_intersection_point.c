// Find intersection point
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

int findLength(struct node *head)
{
    int length = 0;
    struct node *current = head;
    while (current != NULL)
    {
        length++;
        current = current->next;
    }
    return length;
}

struct node *intersectionPoint(struct node *head1, struct node *head2)
{
    int l1 = findLength(head1);
    int l2 = findLength(head2);

    int diff = abs(l1 - l2);

    struct node *ptr1 = (l1 >= l2 ? head1 : head2);
    struct node *ptr2 = (l1 >= l2 ? head2 : head1);

    while (diff > 0)
    {
        ptr1 = ptr1->next;
        diff--;
    }

    while (ptr1 != NULL && ptr2 != NULL)
    {
        if (ptr1 == ptr2)
        {
            return ptr1;
        }
        ptr1 = ptr1->next;
        ptr2 = ptr2->next;
    }
    return NULL;
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
    struct node *head1 = NULL;

    head1 = insertAtBeginning(head1, 3);
    head1 = insertAtBeginning(head1, 2);
    head1 = insertAtBeginning(head1, 1);
    head1 = insertAtBeginning(head1, 5);
    head1 = insertAtBeginning(head1, 0);

    struct node *head2 = NULL;

    head2 = insertAtBeginning(head2, 6);
    head2 = insertAtBeginning(head2, 7);
    head2 = insertAtBeginning(head2, 8);

    head2->next->next->next = head1->next->next;

    struct node *intersect = intersectionPoint(head1, head2);
    if (intersect != NULL)
    {
        printf("Intersection at point %d", intersect->data);
    }
    else
    {
        printf("lists are not intersected");
    }

    return 0;
}