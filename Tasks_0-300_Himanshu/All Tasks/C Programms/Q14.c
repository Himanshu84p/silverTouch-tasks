#include <stdio.h>

int main() {
    int num1, num2, operator;

    printf("Enter the 1st number:");
    scanf("%d", &num1);
    printf("Enter the 2nd number:");
    scanf("%d", &num2);

    printf("Choose the operator: \n 1.Addition 2.Substraction 3.Multiplication 4.Division \n");
    scanf("%d", &operator);

    switch (operator)
    {
    case 1:
        printf("Addition of %d and %d is : %d", num1,num2, num1+num2);
        break;

    case 2:
        printf("Subtraction of %d and %d is : %d", num1,num2, num1-num2);
        break;
    
    case 3:
        printf("Multiplication of %d and %d is : %d", num1,num2, num1*num2);
        break;
    
    case 4:
        printf("Divsion of %d and %d is : %d", num1,num2, num1/num2);
        break;
    
    default:
        break;
    }


}