#include <stdio.h>

int main() {
    int number,oneDigit, result = 0;

    printf("Enter the number: ");
    scanf("%d", &number);
    int temp = number;

    while(temp > 0) {
        oneDigit = temp%10;
        result = result*10 + oneDigit;
        temp = temp/10;
    }
    
    if (number == result) {
        printf("Number is Palindrome!!");
    }else {
        printf("Number is Not Palindrome!!");
    }
    
    return 0;

}