//LCM of two numbers
#include <stdio.h>

int main() {
    int gcd, num1,num2,lcm;

    printf("Enter the 1st number: ");
    scanf("%d", &num1);
    printf("Enter the 2nd number: ");
    scanf("%d", &num2);

    for (int i = 1; i <= num1 && i <= num2; i++)
    {
        if(num1 % i == 0 && num2 % i == 0) {
            gcd = i;
        }
    }
    lcm = (num1 * num2) /gcd;
        
    printf("LCM of the number %d and %d is %d",num1,num2,lcm );
    return 0;
}