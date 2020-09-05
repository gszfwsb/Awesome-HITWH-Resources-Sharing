#include "iostream"
#include "cstdlib"
#include "cstdio"
#define T 10000000
using namespace std;

int main()
{  long n,i,j;
   short a[T];
   bool flag;
    while ((scanf("%ld",&n))!=EOF && n!=0)
    {
        for (i=0;i<n;i++)
        {
            scanf("%hd",&a[i]);
        }
        for (i=0;i<n;i++)
        { flag=0;
            for (j=i+1;j<n;j++)
            {
                if (a[i]==a[j])
                {
                    flag=1;
                    break;
                }
            }
            if (!flag)
            {
                printf("%d",a[(int)i]);
                return 0;
            }
        }
    }

   // return 0;
}
