#include <stdio.h>

int main() {
    int decimalNum =0, binaryNum, remainder, base = 1;

    printf("Enter the binary number: ");
    scanf("%d", &binaryNum);

    while(binaryNum > 0) {
        remainder = binaryNum % 10;
        decimalNum = decimalNum + remainder * base;
        binaryNum = binaryNum/10;
        base = base * 2;        
    }

    printf("Decimal number is : %d", decimalNum);
}