#include <stdio.h>

int main() {
    int num1 = 0, num2 = 1,num3, inp;
    printf("Enter the total count to which you want to print: ");
    scanf("%d", &inp);
    printf("\n%d %d", num1,num2);
    for (int i = 2; i < inp; i++)
    {
        num3 = num1 + num2;
        printf(" %d", num3);
        num1 = num2;
        num2 = num3;
    }
    
    return 0;
}