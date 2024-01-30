#include <stdio.h>

int main() {
    int principalAmount,time;
    float interestRate,simpleInterest;
    
    printf("Enter the Principal Amount :");
    scanf("%d", &principalAmount);
    printf("Enter the time in years:");
    scanf("%d", &time);
    printf("Enter the interest rate per annum :");
    scanf("%f", &interestRate);
    simpleInterest = (principalAmount*interestRate*time) /100;
    printf("S.I. of amount %d is %f",principalAmount, simpleInterest);
    return 0;
}