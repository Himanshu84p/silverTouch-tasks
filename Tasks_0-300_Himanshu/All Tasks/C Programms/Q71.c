// roman to decimal
#include <stdio.h>

int romanToDecimal(char roman[])
{
    int decimal = 0;

    int romanCharacter(char ch)
    {
        switch (ch)
        {
        case 'i':
            return 1;
        case 'v':
            return 5;
        case 'x':
            return 10;
        case 'l':
            return 50;
        case 'c':
            return 100;
        case 'd':
            return 500;
        case 'm':
            return 1000;
        default:
            return 0;
        }
    }

    for (int i = 0; roman[i] != '\0'; i++)
    {
        int currentValue = romanCharacter(roman[i]);

        if (roman[i + 1] != '\0' && romanCharacter(roman[i + 1]) > currentValue)
        {
            decimal = decimal - currentValue;
        }
        else
            decimal = decimal + currentValue;
    }
    return decimal;
}
int main()
{
    char roman[] = "cm";
    int decimal = romanToDecimal(roman);

    printf("Decimal number : %d", decimal);

    return 0;
}