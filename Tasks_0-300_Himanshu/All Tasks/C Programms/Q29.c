//String Concatenation

#include <stdio.h> 
#include <string.h> 

int main() {
    char string1[300], string2[150];

    printf("Enter the string : ");
    gets(string1);
    printf("Enter the 2nd string : ");
    gets(string2);
    strcat(string1, string2);
    printf("Concatenation of the two string is  : %s ", string1);
    return 0;
}