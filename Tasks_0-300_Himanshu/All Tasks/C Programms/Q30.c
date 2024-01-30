// String length calculate

#include <stdio.h>
#include <string.h>

int main () {
    char string[150];
    printf("Enter the string : ");
    gets(string);

    printf("Length of the string %s is %d", string, strlen(string));
    
    return 0;
}