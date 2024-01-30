// Rotate an array
#include <stdio.h>

int main()
{
    int n, k;

    printf("Enter the number of elements to insert :");
    scanf("%d", &n);

    int arr[n];
    for (int i = 0; i < n; i++)
    {
        printf("Enter the %d element : ", i);
        scanf("%d", &arr[i]);
    }

    printf("Enter the k position: ");
    scanf("%d", &k);

    printf("Rotated array is : \n");
    for (int i = k; i < n + k - 1; i++)
    {
        printf(" %d ", arr[i]);
        if (i >= n)
        {
            printf("%d", arr[n - i]);
        }
    }

    return 0;
}