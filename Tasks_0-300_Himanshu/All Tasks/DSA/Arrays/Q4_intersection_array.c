// array intersection
#include <stdio.h>

int main()
{
    int n1, n2;

    printf("Enter the number of elements to insert in 1st array :");
    scanf("%d", &n1);

    int arr1[n1];
    for (int i = 0; i < n1; i++)
    {
        printf("Enter the %d element : ", i);
        scanf("%d", &arr1[i]);
    }

    printf("Enter the number of elements to insert in 2nd array :");
    scanf("%d", &n2);

    int arr2[n2];
    int result[(n1 > n2 ? n1 : n2)];
    for (int i = 0; i < n2; i++)
    {
        printf("Enter the %d element : ", i);
        scanf("%d", &arr2[i]);
    }

    printf("Intersected array is : \n");
    for (int i = 0; i < n1; i++)
    {
        for (int j = 0; j < n2; j++)
        {
            if (arr1[i] == arr2[j])
            {
                result[i] = arr1[i];
                printf("%d ", result[i]);
            }
        }
    }

    return 0;
}