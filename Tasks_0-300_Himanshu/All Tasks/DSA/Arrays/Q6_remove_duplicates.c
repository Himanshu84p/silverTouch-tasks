#include <stdio.h>

int main()
{
    int n;

    printf("Enter the number of elements to insert :");
    scanf("%d", &n);
    int arr[n], temp[n];
    int j = 0;

    if (n == 0 || n == 1)
    {
        printf("array has nothing to do");
    }

    for (int i = 0; i < n; i++)
    {
        printf("Enter the %d element in sorted manner : ", i);
        scanf("%d", &arr[i]);
    }

    for (int i = 0; i < n - 1; i++)
    {
        if (arr[i] != arr[i + 1])
        {
            temp[j++] = arr[i];
        }
    }

    temp[j++] = arr[n - 1];

    for (int i = 0; i < j; i++)
    {
        arr[i] = temp[i];
    }

    printf("After removing the duplicates Array is : \n");
    for (int i = 0; i < j; i++)
    {
        printf("%d ", arr[i]);
    }

    return 0;
}