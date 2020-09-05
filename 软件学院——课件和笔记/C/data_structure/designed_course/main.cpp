#include <iostream>
#include <cstring>
#include <cstdio>
#include <cstdlib>
using namespace std;

const int MAXN = 11;
const int MAXM = 2001;
long long dp[MAXN][MAXM];
int main()
{
    int t, n, m;
    scanf("%d",&t);
    int cnt = 1;
    while (t--)
    {
        scanf("%d%d",&n,&m);
        memset(dp, 0, sizeof(dp));
        for (int i = 1; i <= m; i++)
            dp[1][i] = 1; //长度为1的序列，只有一种方法
        for (int i = 2; i <= n; i++)
        {
            for (int j = 1; j <= m; j++)
            {
                for (int k = 1; k <= j / 2; k++)
                {
                    dp[i][j] += dp[i - 1][k];
                }
            }
        }
        long long sum = 0;
        for (int i = 1; i <= m; i++)
        {
            sum += dp[n][i];
        }
        cout << "Case " << cnt << ": n = " << n << ", m = " << m << ", # lists = " << sum << endl;
        cnt++;
    }
    return 0;
}