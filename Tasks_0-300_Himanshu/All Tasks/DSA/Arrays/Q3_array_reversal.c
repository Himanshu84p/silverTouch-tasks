// array reversal
#include <stdio.h>

int main()
{
    int n;

    printf("Enter the number of elements to insert :");
    scanf("%d", &n);

    int arr[n];
    for (int i = 0; i < n; i++)
    {
        printf("Enter the %d element : ", i);
        scanf("%d", &arr[n - i - 1]);
    }

    printf("Reverse array is : \n");
    for (int i = 0; i < n; i++)
    {
        printf("%d ", arr[i]);
    }

    return 0;
}