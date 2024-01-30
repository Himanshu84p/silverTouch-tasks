//Insertion sort algorithm
#include <stdio.h>

void insertionSort(int arr[], int n) {
    for (int i = 1; i < n ; i++)
    {
        int current = arr[i];
        int j = i-1;
        while(arr[j] > current && j >= 0) {
            arr[j + 1] = arr[j];
            j--;
        }
        arr[j+1] = current; 
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
    insertionSort(array, len);  
    print(array, len); 
    return 0;

}
