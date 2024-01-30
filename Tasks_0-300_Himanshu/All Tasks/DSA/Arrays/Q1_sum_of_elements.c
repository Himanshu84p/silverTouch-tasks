#include <stdio.h>

int main()
{
    int n, sum = 0;

    printf("Enter the number of elements to insert :");
    scanf("%d", &n);

    for (int i = 0; i < n; i++)
    {
        int arr[n];
        printf("Enter the %d element : ", i);
        scanf("%d", &arr[i]);
        sum += arr[i];
    }
    printf("Sum of elements is %d", sum);

    return 0;
}