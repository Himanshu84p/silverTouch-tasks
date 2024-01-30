//Count words in a sentence
#include <stdio.h>
#include <string.h>

int main() {
    char sent[300];
    int count = 0;

    printf("Enter the sentence : ");
    gets(sent);
    int length = strlen(sent);
    for (int i = 0; i < length; i++)
    {
        char c = sent[i];
        if( c == ' ') {
            count++;
        }
    }
    
    printf("number of words in the sentence is : %d", count + 1);
    return 0;
}
