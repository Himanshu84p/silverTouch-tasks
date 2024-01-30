//Bubble sort algo
#include <stdio.h>

void bubbleSort(int arr[], int n) {
    for (int i = 0; i < n -1; i++)
    {
        for (int j = 0; j < n-i-1; j++)
        {
            if(arr[j] > arr[j+1]) {
                int temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
            }
        }
        
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
    bubbleSort(array, len);  
    print(array, len); 
    return 0;

}