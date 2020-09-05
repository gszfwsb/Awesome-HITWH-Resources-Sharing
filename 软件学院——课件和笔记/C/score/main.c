#include <stdio.h>
#include <stdlib.h>

int main()
{
    char a[4][41];
    long *pl[4];
    int i;
    float *pf1[4],*pf2[4],*pf3[4];
    double *pd[4];
    for (i=0;i<4;i++)
        {
          pl[i]=(long*)a[i];
          pf1[i]=(float*)&a[i][8];
          pf2[i]=(float*)&a[i][16];
          pf3[i]=(float*)&a[i][24];
          pd[i]=(double*)&a[i][32];
        }
        printf("Input 4 students' numbers and scores:\n");
    for (i=0;i<4;i++)
    {
        scanf("%ld%f%f%f",pl[i],pf1[i],pf2[i],pf3[i]);
        *pd[i]=*pf1[i]+*pf2[i]+*pf3[i];
    }
    printf("number\t\tchinese\tmath\tEnglish\ttotal\n");
    for (i=0;i<4;i++)
    {
       printf("%ld\t%.0f\t%.0f\t%.0f\t%.0lf\n",*pl[i],*pf1[i],*pf2[i],*pf3[i],*pd[i]);
    }

    return 0;
}
