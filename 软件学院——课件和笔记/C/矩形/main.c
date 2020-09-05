#include <stdio.h>
#include <stdlib.h>

int max(int a,int b)
{
    if (a>b) return a;
    else return b;
}
int min(int a,int b)
{
    if (a<b) return a;
    else return b;
}
int main()
{   int x[233],y[233];
    int x1,x2,y1,y2;
    int i,j,t,n,flag=1,count=0;
    scanf("%d",&t);
    for (i=0;i<t;i++)
    {
        scanf("%d",&n);
        for (j=0;j<n;j++)
        {
            scanf("%d%d",&x[j],&y[j]);
        }

        for (j=0;j<n-1;j++)
        {
            if (x[j]==x[j+1])
                count++;
        }
        if (count==n-1)
        {
            printf("YES\n");
            continue;
        }



        count=0;
        for (j=0;j<n-1;j++)
        {
            if (y[j]==y[j+1])
                count++;
        }
        if (count==n-1)
        {
            printf("YES\n");
            continue;
        }


        count=0;
        for (j=0;j<n-1;j++)
        {
            for (k=j+1;k<n;k++)
            {
                if (x[j]!=x[k+j]
            }
        }
        for (j=0;j<n;j++)
        {
            if (!((x[j]==x1 || x[j]==x2) && y[j]<=max(y1,y2) &&y[j]>=min(y1,y2) ))
            {

                    flag=0;break;
            }
                else if (!(y[j]==y1 || y[j]==y2) && x[j]<=max(x1,x2) &&x[j]>=min(x1,x2))
                     {
                      flag=0;break;
                     }
        }
        if (flag) printf("YES\n");
        else printf("NO\n");

}
   return 0;
}
