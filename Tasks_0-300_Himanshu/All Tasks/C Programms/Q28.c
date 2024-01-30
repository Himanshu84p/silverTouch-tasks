// Calculate Vowels and Consonants in a string
#include <stdio.h>
#include <string.h>
#include <ctype.h>


int main() {
    char str[300];
    char c;
    int vowels = 0,consonants = 0,length;

    printf("Enter the string : ");
    gets(str);
    
    length = strlen(str);

    for (int i = 0; i < length ; i++)
    {
        c = tolower(str[i]);

        if (c == ' ')
        {
            continue;
        } else if (c == 'a' || c == 'e' ||c == 'i' ||c == 'o' || c == 'u')
        {
            vowels++;
        } else {
            consonants++;
        }
    }
    
    printf("Vowels are %d and Consonants are %d in given string %s ",vowels, consonants, str);
    return 0;
}