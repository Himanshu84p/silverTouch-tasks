//Linear Search
#include <stdio.h>

int main() {
    int arr[] = {1, 2, 5, 3, 34, 21};
    int n;
    int length = sizeof(arr) / sizeof(arr[0]);
    
    printf("Enter the number to search : ");
    scanf("%d", &n);

    for (int i = 0; i < length; i++)
    {
        if (arr[i] == n)
        {
            printf("Element %d found in the array!!", n);
            return 0;
        }
    }
    printf("Element %d not found in the array!!", n);
    
    return 0;
}