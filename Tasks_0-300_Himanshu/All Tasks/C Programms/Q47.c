//Hollow Pyramid pattern
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
            if (j == 1 || i == 1 || i == 5 || j == (2 * i) - 1)
            {
                printf("*");
            }
            else
            {
                printf(" ");
            }
        }
        printf("\n");
    }
    return 0;
}