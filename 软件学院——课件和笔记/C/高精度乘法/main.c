#include <stdio.h>
#include <string.h>
int main()
{
    char numberN[1500], numberM[1500];
    scanf("%s%s", numberN, numberM);
    int n = strlen(numberN), m = strlen(numberM);
    int a[n], b[m];
    int i, j;
    for (i = 0, j = n - 1; i < n; i++, j--) {
        a[i] = numberN[j] - '0';
    }
    for (i = 0, j = m - 1; i < m; i++, j--) {
        b[i] = numberM[j] - '0';
    }
    int c[3000]={0};
    for (i = 0; i < n; i++)
    {
        for (j = 0; j < m; j++)
        {
            c[i + j] += a[i] * b[j];
        }
    }
    for (i = 0; i < n + m; i++)
    {
        if (c[i] >= 10)
        {
            c[i + 1] += c[i] / 10;
            c[i] %= 10;
        }
    }
    for (j = 2999; j > 0; j--)
    {
        if (c[j] != 0)
        break;
    }
    for (i = j; i >= 0; i--)
    {
    printf("%d", c[i]);
    }
    printf("\n");
    return 0;
}
