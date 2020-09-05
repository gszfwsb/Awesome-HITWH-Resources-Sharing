#include <iostream>
#include <cstring>
#define NotFound -1

using namespace std;
typedef int Position;

void buildMatch(char *pattern, int *match)
{
    Position i, j;
    int m = strlen(pattern);
    match[0] = -1;
    for (j = 1; j < m; j++)
    {
        i = match[j - 1];
        while ((i>=0) && (pattern[i + 1] != pattern[j]))
            i = match[i];
        if (pattern[i + 1] == pattern[j])
            match[j] = i + 1;
        else
            match[j] = -1;
    }
}

Position KMP(char *str, char *pattern)
{
    int n = strlen(str);
    int m = strlen(pattern);
    Position s, p, *match;
    if (n < m)
        return NotFound;
    match = (Position *)malloc(sizeof(Position) * m);
    buildMatch(pattern, match);
    s = p = 0;
    while (s < n && p < m)
    {
        if (str[s] == pattern[p])
        {
            s++;
            p++;
        }
        else if (p > 0)
            p = match[p - 1] + 1;
        else
            s++;
    }
    return (p == m) ? (s - m) : NotFound;
}

int main()
{
    char str[] = "This is a simple pig";
    char pat[] = "simple";
    int pos = KMP(str, pat);
    cout << str + pos << endl;
}