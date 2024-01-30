#include <stdio.h>

int main() {
    int number,oneDigit, result = 0;

    printf("Enter the number: ");
    scanf("%d", &number);

    while(number > 0) {
        oneDigit = number%10;
        result = result*10 + oneDigit;
        number = number/10;
    }
    printf("Reverse number is %d", result);

    return 0;

}