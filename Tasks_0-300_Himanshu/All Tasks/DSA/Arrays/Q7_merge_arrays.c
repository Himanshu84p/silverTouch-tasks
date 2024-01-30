// merge two arrays

#include <stdio.h>

int main()
{
    int i = 0, j = 0, k = 0, n1, n2;

    printf("Enter the number of elements to insert in first array :");
    scanf("%d", &n1);
    int arr1[n1];
    for (int i = 0; i < n1; i++)
    {
        printf("Enter the %d element : ", i);
        scanf("%d", &arr1[i]);
    }

    printf("Enter the number of elements to insert int second array :");
    scanf("%d", &n2);
    int arr2[n2];

    for (int i = 0; i < n2; i++)
    {
        printf("Enter the %d element : ", i);
        scanf("%d", &arr2[i]);
    }

    int result[n1 + n2];
    while (i < n1 && j < n2)
    {
        if (arr1[i] < arr2[j])
        {
            result[k++] = arr1[i++];
        }
        else
        {
            result[k++] = arr2[j++];
        }
    }
    while (i < n1)
    {
        result[k++] = arr1[i++];
    }
    while (j < n2)
    {
        result[k++] = arr2[j++];
    }

    printf("After merging Array is : \n");
    for (int i = 0; i < n1 + n2; i++)
    {
        printf("%d ", result[i]);
    }

    return 0;
}