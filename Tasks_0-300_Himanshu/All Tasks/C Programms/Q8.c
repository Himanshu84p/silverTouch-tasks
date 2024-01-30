#include <stdio.h>

int main() {
    int inp, result = 1;

    printf("Enter the number you want to check: ");
    scanf("%d", &inp);

    if(inp == 0 || inp == 1) {
        result = 0;
    }

    for(int i = 2; i <= inp/2 ; i++) {
        if(inp % i == 0) {
            result = 0;
            break;
        }
    }

    if(result) {
        printf("%d is a Prime number.", inp);
    } else {
        printf("%d is not a prime number", inp);
    }
    return 0;
}