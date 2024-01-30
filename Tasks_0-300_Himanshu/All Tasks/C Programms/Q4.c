#include <stdio.h>

int main () {
    int a = 13;
    int b = 161;
    int c = 131;

    if(a >= c && a >= b) {
        printf("%d is the greatest ", a);
        return 0;
    } else if (c >= b && c >= a) {
        printf("%d is the greatest ", c);
        return 0;
    } else {
        printf("%d is the greatest ", b);
        return 0;
    }
    
}