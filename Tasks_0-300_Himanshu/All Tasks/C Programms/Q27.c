// Reverse a string
#include <stdio.h>
#include <string.h>

int main() {
    char str[200];

    printf("Enter the string : ");
    gets(str);

    int len = strlen(str); 
    for (int i = 0; i <= len/2 ; i++)
    {
        int temp = str[i];
        str[i] = str[len -i -1];
        str[len -i -1] = temp;
    }
    
    printf("%s", str);
    return 0;
}