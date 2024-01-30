//Factorization of a number
#include <stdio.h>

int main() {
    int number;

    printf("Enter the number: ");
    scanf("%d", &number);

    printf("Factors of number are : ");
    for (int i = 1; i <= number; i++)
    {
        if (number%i == 0)
        {
            printf(" %d ", i);
        }
    }
    
    return 0;
}