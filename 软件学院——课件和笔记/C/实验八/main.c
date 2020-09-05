#include <stdio.h>
#include <stdlib.h>
#include <string.h>
int main()
{
    FILE *st=fopen("strecord.txt","r");
    int i,j,n,a[5];
    char s[100];
    char str[20];
    FILE *st1,*st2,*stu;
    st1=fopen("stR1.txt","w+");
    st2=fopen("stR2.txt","w+");
    for (i=0;i<5;i++)
    {
        fscanf(st,"%d%s",&n,str);
        fprintf(st1,"%-3d %s  ",n,str);
        for (j=0;j<5;j++)
           {
             fscanf(st,"%d",&a[j]);
             fprintf(st1,"%-3d ",a[j]);
           }
           fprintf(st1,"\n");


        fscanf(st,"%d%s",&n,str);
        fprintf(st2,"%-3d %s  ",n,str);
        for (j=0;j<5;j++)
           {
             fscanf(st,"%d",&a[j]);
             fprintf(st2,"%-3d ",a[j]);
           }
           fprintf(st2,"\n");

    }

    fclose(st);
    rewind(st1);
    rewind(st2);
    stu=fopen("student.txt","w");
    for (i=0;i<5;i++)
    {
      fgets(s,100,st1);
      for (j=0;j<strlen(s);j++)
      {
          if (s[j]!='\n') fputc(s[j],stu);
      }
      fprintf(stu,"     ");
      fgets(s,100,st2);
      fprintf(stu,"%s\n",s);
    }
    fclose(stu);
    fclose(st1);
    fclose(st2);
    return 0;
}
