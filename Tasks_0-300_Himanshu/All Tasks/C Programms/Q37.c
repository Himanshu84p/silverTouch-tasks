//Binary search
#include <stdio.h> 

int main() {
    int arr[] = {1, 2, 3, 4, 5};
    int length = sizeof(arr) / sizeof(arr[0]);
    int n,low = 0,high = length-1;
    int mid = length/2;

    printf("Enter the number to search : ");
    scanf("%d", &n);
    
    while (low <= high)
    {
        mid  = low + (high - low)/2;

        if (arr[mid] == n)
        {
            printf("Match found!!");
            return 0;
        } else if (n > arr[mid])
        {
            low = mid +1;
        } else if (n < arr[mid])
        {
            high = mid -1;
        }
    }
    printf("Number is not in the list");   
    
    return 0;
}