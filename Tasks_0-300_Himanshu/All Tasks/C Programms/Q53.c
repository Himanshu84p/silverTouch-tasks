// GCD and lcm of array elements
#include <stdio.h>

int findGcd(int a, int b) {
    while (b != 0) {
        int temp = b;
        b = a % b;
        a = temp;
    }
    return a;
}

int findArrayGcd(int* arr, int len) {
    int gcd = arr[0];

    for (int i = 1; i < len; i++)
    {
        gcd = findGcd(gcd, arr[i]);
    }
    return gcd;
}

int findLcm(int a, int b) {
    int lcm = (a*b) / findGcd(a,b);
    return lcm;
}

int findArrayLcm(int* arr, int len) {
    int lcm = arr[0];

    for (int i = 1; i < len; i++)
    {
        lcm = findLcm(lcm, arr[i]);
    }
    return lcm;
}
int main()
{
    int arr[] = {1, 2, 4, 6};
    int n = sizeof(arr) / sizeof(arr[0]);

    int gcd = findArrayGcd(arr,n);
    printf("Gcd of array elements is : %d", gcd);

    int lcm = findArrayLcm(arr,n);
    printf("\nLcm of array elements is : %d", lcm);

    return 0;
}