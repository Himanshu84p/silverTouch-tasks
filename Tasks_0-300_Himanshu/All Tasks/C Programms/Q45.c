//check lower triangular matrix
#include <stdio.h>

int main() {
    int matrix[3][3] = {{1,0,0}, {1,2,0},{1,2,3}};

    for (int i = 0; i < 3; i++)
    {
        for (int j = i + 1; j < 3; j++)
        {
            if(matrix[i][j] != 0) {
                printf("not a lower traingular!!");
                return 0;
            }
        }
    }
    
    printf("matrix is lower triangular");
    return 0;
}
