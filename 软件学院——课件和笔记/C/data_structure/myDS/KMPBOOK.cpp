#include <iostream>
typedef unsigned char sstring[256];

void get_next(sstring t, int next[])
{
    int j = 1, k = 0;
    next[1] = 0;
    while (j < t[0])
    {
        if (k == 0 || t[j] == t[k])
        {
            j++;
            k++;
            next[j] = k;
        }
        else
            k = next[k];
    }
}
int next[256];
int KMP(sstring s, sstring t, int pos)
{
    int i = pos, j = 1;
    while (i <= s[0] && j <= t[0])
    {
        if (j == 0 || s[i] == t[j])
        {
            i++;
            j++;
        }
        else
            j = next[j];
    }
    if (j > t[0])
        return i - t[0];
    else
        return 0;
}