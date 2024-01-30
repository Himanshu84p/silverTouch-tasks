#include <stdio.h>

int main() {
    float area,length,width;
    
    printf("Enter the length :");
    scanf("%f", &length);
    printf("Enter the width :");
    scanf("%f", &width);
    area = length * width;
    printf("Area of a Rectangle is %f", area);
    return 0;
}