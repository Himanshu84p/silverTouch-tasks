//Perfect number check
#include <stdio.h>

int main() {
    int number,sum = 0,remainder;

    printf("Enter the number to check perfect or not: ");
    scanf("%d", &number);

    for (int i = 1; i <= number/2; i++)
    {
        remainder = number%i;
        if(remainder == 0) {
            sum = sum + i;
        }
    }
    if (number == sum)
    {
        printf("Given number %d is perfect number", number);
    }else {
        printf("Given number %d is not a perfect number", number);
    }

    return 0;
}