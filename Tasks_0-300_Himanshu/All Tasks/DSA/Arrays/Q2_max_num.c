// max num in array
#include <stdio.h>

int main()
{
    int n, max = 0;

    printf("Enter the number of elements to insert :");
    scanf("%d", &n);

    for (int i = 0; i < n; i++)
    {
        int arr[n];
        printf("Enter the %d element : ", i);
        scanf("%d", &arr[i]);
        if (arr[i] >= max)
        {
            max = arr[i];
        }
    }
    printf("Max num in array is : %d", max);
    return 0;
}