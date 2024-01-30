// Matrix diagonal sum
#include <stdio.h>
int main()
{
    int arr[3][3] = {{1, 2, 3}, {1, 2, 3}, {1, 2, 3}};
    int n = 3;
    int sum1 = 0, sum2 = 0;

    for (int i = 0; i < n; i++)
    {
        sum1 = sum1 + arr[i][i];
    }
    for (int i = 0; i < n; i++)
    {
        sum2 = sum2 + arr[i][n - 1 - i];
    }

    printf("Sum of diagonal element is %d", sum1 + sum2);

    return 0;
}