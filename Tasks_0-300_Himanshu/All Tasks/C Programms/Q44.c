//check upper triangular matrix
#include <stdio.h>

int main() {
    int matrix[3][3] = {{1,2,3}, {0,2,3},{0,0,3}};

    for (int i = 1; i < 3; i++)
    {
        for (int j = 0; j < i; j++)
        {
            if(matrix[i][j] != 0) {
                printf("not a upper traingular!!");
                return 0;
            }
        }
    }
    
    printf("matrix is upper triangular");
    return 0;
}
