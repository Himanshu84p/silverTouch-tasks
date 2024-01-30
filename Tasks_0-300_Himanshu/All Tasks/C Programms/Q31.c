// Check for anagrams
#include <stdio.h>
#include <string.h>

int areAnagrams(const char *str1, const char *str2)
{

    int len1 = strlen(str1);
    int len2 = strlen(str2);

    if (len1 != len2)
    {
        return 0;
    }

    char sortedStr1[len1 + 1];
    char sortedStr2[len2 + 1];

    strcpy(sortedStr1, str1);
    strcpy(sortedStr2, str2);

    for (int i = 0; i < len1 - 1; i++)
    {
        for (int j = i + 1; j < len1; j++)
        {
            if (sortedStr1[i] > sortedStr1[j])
            {
                char temp = sortedStr1[i];
                sortedStr1[i] = sortedStr1[j];
                sortedStr1[j] = temp;
            }
            if (sortedStr2[i] > sortedStr2[j])
            {
                char temp = sortedStr2[i];
                sortedStr2[i] = sortedStr2[j];
                sortedStr2[j] = temp;
            }
        }
    }

    if (strcmp(sortedStr1, sortedStr2) == 0)
    {
        return 1;
    }
    else
    {
        return 0;
    }
}

int main()
{

    const char *str1 = "listen";
    const char *str2 = "silent";

    if (areAnagrams(str1, str2))
    {
        printf("%s and %s are anagrams.\n", str1, str2);
    }
    else
    {
        printf("%s and %s are not anagrams.\n", str1, str2);
    }

    return 0;
}
