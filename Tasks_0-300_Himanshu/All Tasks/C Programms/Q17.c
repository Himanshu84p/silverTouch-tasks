#include <stdio.h>

int main() {
    float area,base,height;
    
    printf("Enter the Base :");
    scanf("%f", &base);
    printf("Enter the width :");
    scanf("%f", &height);
    area = 0.5 *base * height;
    printf("Area of a Triangle is %f", area);
    return 0;
}