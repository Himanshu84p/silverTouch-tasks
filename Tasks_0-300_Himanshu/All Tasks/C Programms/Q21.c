//Armstrong Number Check

#include <stdio.h>

int main() {
    int number, inpNum,remainder,sum;
    printf("Enter the number: ");
    scanf("%d",  &number);

    inpNum = number;

    while(number>0) {
        remainder = number%10;
        sum = sum + (remainder*remainder*remainder);
        number = number/10;
    } 

    if(sum == inpNum) {
        printf("Given number %d is armstrong.", inpNum);
    } else {
        printf("Given number %d is not armstrong number.", inpNum);
    }
    return 0;
}
