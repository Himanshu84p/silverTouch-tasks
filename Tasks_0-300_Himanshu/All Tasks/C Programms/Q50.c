// Diamond pattern
#include <stdio.h>

int main()
{
    int n;
    printf("Enter the number of row : ");
    scanf("%d", &n);

    for (int i = 1; i <= n; i++)
    {
        for (int k = 5; k > i; k--)
        {
            printf(" ");
        }
        for (int j = 1; j <= (2 * i) - 1; j++)
        {
            printf("*");
        }
        printf("\n");
    }
    for (int j = n - 1; j >= 1; j--)
    {
        for (int i = 1; i <= n - j; i++)
        {
            printf(" ");
        }
        for (int k = 1; k <= (2 * j) - 1; k++)
        {
            printf("*");
        }
        printf("\n");
    }
    return 0;
}