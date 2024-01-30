//transpose of a matrix
#include <stdio.h>

int main() {
    int matrix[3][3] = {{1,2,3}, {1,2,3},{1,2,3}};
    int result[3][3];

    printf("Multiplication of the matrix is : \n");
    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            result[j][i] = matrix[i][j];
        }
        
    }

    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            printf("%d\t", result[i][j]);
        }
        printf("\n");
    }
    
    return 0;
}