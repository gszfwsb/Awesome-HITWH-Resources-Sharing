#include <stdio.h>
#include <stdlib.h>
int max(int a, int b)
{
    return a > b ? a : b;
}
int f(int *a, int left, int right)
{
    if (left == right)
        return a[left] ? a[left] : 0;
    int mid = (right + left) / 2;
    int leftValue = f(a, left, mid);
    int rightValue = f(a, mid + 1, right);
    int maxValue = max(leftValue, rightValue);
    int midLeftValue = 0;
    int midRightValue = 0;
    int l = 0;
    int r = 0;
    for (int i = mid; i >= left; i--)
    {
        l += a[i];
        if (l > midLeftValue)
        {
            midLeftValue = l;
        }
    }
    for (int i = mid + 1; i <= right; i++)
    {
        r += a[i];
        if (r > midRightValue)
        {
            midRightValue = r;
        }
    }
    return max(maxValue, midLeftValue + midRightValue);
}
int main()
{
    int n;
    printf("Please input the number n:");
    scanf("%d", &n);
    int *a = malloc(n * sizeof(int));
    printf("Please input the %d digits:",n);
    for (int i = 0; i < n; i++)
    {
        scanf("%d", &a[i]);
    }
    printf("The max sum of the subsequences is: %d\n", f(a, 0, n - 1));
    free(a);
    return 0;
}

