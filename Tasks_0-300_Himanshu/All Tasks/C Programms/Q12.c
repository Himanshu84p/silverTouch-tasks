#include <stdio.h>

int main() {
    int binary[32];
    int number, remainder;
    int i=0;

    printf("Enter the number you want to convert into binary: ");
    scanf("%d", &number);

    while(number > 0) {
        binary[i] = number % 2;
        number = number/2;
        i++;
    }

    printf("Binary number is :");
    for (int j = i - 1; j >= 0; j--) 
    {
        printf("%d", binary[j]);
    }
    
    return 0;
}