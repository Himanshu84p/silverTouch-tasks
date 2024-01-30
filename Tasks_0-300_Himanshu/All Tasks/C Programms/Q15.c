#include <stdio.h>

int main() {
    float area,radius;
    
    printf("Enter the radius :");
    scanf("%f", &radius);
    area = 3.14 * radius * radius;
    printf("Area of a circle is %f", area);
    return 0;
}