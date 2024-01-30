#include <stdio.h>
// Celsius to farenheit converion
int main() {

    float celsius,farenheit;

    printf("Enter the celsius:");
    scanf("%f", &celsius);
    farenheit = (celsius * 9/5) + 32;
    printf("Farenheit of celsius %f is %f ", celsius,farenheit);
}