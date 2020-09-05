#include<stdio.h>

int main()

{

    float average=0,a[11],b[11],b1[11],c[11],c1[11];

    int i;

    printf("Please input the grade of students' number__ 1~10:\n");

    for (i=1;i<=10;i++)

    {

        scanf("%f",&a[i]);

        average+=a[i];

    }

    average/=10;

    int count1=0,count2=0;

    for (i=1;i<=10;i++)

    {

        if (a[i]>1.1*average) {count1++; b[count1]=a[i]; b1[count1]=i; }

        else if (a[i]>1.05*average) {count2++; c[count2]=a[i]; c1[count2]=i;}

    }

    for (i=1;i<=count1;i++)

    {

        printf("%2.0f号学生，成绩%4.2f分，一等奖\n",b1[i],b[i]);

    }

    printf("\n");

    for (i=1;i<=count2;i++)

    {

        printf("%2.0f号学生，成绩%4.2f分，二等奖\n",c1[i],c[i]);

    }

}
