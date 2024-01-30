//Selection sort algo
#include <stdio.h>

void selectionSort(int arr[], int n) {
    for (int i = 0; i < n -1; i++)
    {
        int minIndex = i;
        for (int j = i + 1; j < n; j++)
        {
            if (arr[j] < arr[minIndex])
            {
                minIndex = j;
            }
            
        }
        int temp = arr[i];
        arr[i] = arr[minIndex];
        arr[minIndex] = temp;
        
    }
    
}

void print(int arr[], int len) {
    for (int i = 0; i < len; i++)
    {
        printf("%d\n", arr[i]);
    }
    
}

int main() {
    int array[] = {10, 34, 23, 44, 45, 12};
    int len = sizeof(array) / sizeof(array[0]);

    // printf("%d\n", len);
    selectionSort(array, len);  
    print(array, len); 
    return 0;

}
