#include <stdio.h>

int main() {
    int factorial = 1,inp;
    printf("Enter the number you want factorial : ");
    scanf("%d", &inp);

    for(int i = 1; i <= inp; i++) {
        factorial = factorial * i;
    }

    printf("Factorial of a %d is %d", inp, factorial);    
    return 0;

}