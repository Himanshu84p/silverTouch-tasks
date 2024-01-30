// check for Magic square
#include <stdio.h>

int main()
{
    int arr[3][3] = {{4, 9, 2}, {3, 5, 7}, {8, 1, 6}};
    int size = 3;

    int sum = 0;
    int diagonalSum1 = 0;
    int diagonalSum2 = 0;

    // calculate for first row
    for (int i = 0; i < size; i++)
    {
        sum += arr[0][i];
    }

    // for all rows
    for (int i = 1; i < size; i++)
    {
        int rowSum = 0;
        for (int j = 0; j < size; j++)
        {
            rowSum += arr[i][j];
        }
        if (rowSum != sum)
        {
            printf("Not a magic matrix");
            return 0;
        }
    }

    // for all col
    for (int i = 0; i < size; i++)
    {
        int colSum = 0;
        for (int j = 0; j < size; j++)
        {
            colSum += arr[i][j];
        }
        if (colSum != sum)
        {
            printf("Not a magic matrix");
            return 0;
        }
    }
    // for diagonal
    for (int i = 0; i < size; i++)
    {
        diagonalSum1 = diagonalSum1 + arr[i][i];
    }
    for (int i = 0; i < size; i++)
    {
        diagonalSum2 = diagonalSum2 + arr[i][size - 1 - i];
    }

    if (sum == diagonalSum1 && sum == diagonalSum2)
    {
        printf("Matrix is a magic matrix");
        return 0;
    }
    else
    {
        printf("Not a magic matrix");
    }

    return 0;
}