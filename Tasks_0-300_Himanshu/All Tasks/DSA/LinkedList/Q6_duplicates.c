// remove duplicates
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

void removeDuplicates(struct node *head)
{
    if (head == NULL)
    {
        printf("List is empty");
        return;
    }

    struct node *current = head;
    struct node *runner;

    while (current != NULL)
    {
        runner = current;
        while (runner->next != NULL)
        {
            if (runner->next->data == current->data)
            {
                struct node *temp = runner->next;
                runner->next = runner->next->next;
                free(temp);
            }
            else
            {
                runner = runner->next;
            }
        }
        current = current->next;
    }
}

int main()
{
    struct node *head = NULL;

    head = insertAtBeginning(head, 3);
    head = insertAtBeginning(head, 2);
    head = insertAtBeginning(head, 1);
    head = insertAtBeginning(head, 4);
    head = insertAtBeginning(head, 2);

    printf("Before the removing :");
    display(head);

    printf("After the removing :");
    removeDuplicates(head);
    display(head);

    return 0;
}