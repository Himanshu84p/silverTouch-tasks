//Print ASCII value of character
#include <stdio.h>

int main() {
    char c;
    printf("Enter the character: ");
    scanf("%c", &c);
    printf("ASCII value of %c is %d", c, c);

    return 0;
}