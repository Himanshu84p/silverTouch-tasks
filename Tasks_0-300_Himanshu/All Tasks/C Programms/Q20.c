#include <stdio.h>
// Farenheit to celsius converion
int main() {

    float celsius,farenheit;

    printf("Enter the farenheit:");
    scanf("%f", &farenheit);
    celsius = (farenheit -32)*5 /9;
    printf("Celsius of farenheit of %f is %f ",farenheit, celsius);
}