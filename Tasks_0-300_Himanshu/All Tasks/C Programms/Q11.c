#include <stdio.h>

int main() {
    int number,oneDigit, sum = 0;

    printf("Enter the number: ");
    scanf("%d", &number);

    while(number > 0) {
        oneDigit = number%10;
        sum = sum + oneDigit;
        number = number/10;
    }
    printf("%d", sum);

    return 0;

}