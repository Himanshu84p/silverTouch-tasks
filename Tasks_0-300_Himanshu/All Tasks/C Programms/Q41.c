//matrix subtraction
#include <stdio.h>

int main() {
    int matrix1[3][3] = {{1,2,3}, {1,2,3},{1,2,3}};
    int matrix2[3][3] = {{1,2,3}, {1,2,3},{1,2,3}};
    int result[3][3];

    printf("Subtraction of the matrix is : \n");
    //calculate matrix
    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            result[i][j] = matrix1[i][j] - matrix2[i][j];
        }
        
    }
    //print matrix
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