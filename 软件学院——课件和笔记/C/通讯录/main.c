#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
typedef struct data
{
   char name[50];
  long long phonenumber;
}Student;

void list(Student* stu,int n)
{
    int i;
    for (i=0;i<n;i++)
        printf("%s\t%I64d\n",stu[i].name,stu[i].phonenumber);
}
int main()
{ int n;
  Student student[100];
  int i;
  char ch[100];
  printf("please input num\n");
  scanf("%d",&n);
  printf("please input their names and phone numbers:\n");
  for (i=0;i<n;i++)
  {
      scanf("%s%I64d",student[i].name,&student[i].phonenumber);
  }
  printf("input search name\n");
  getchar();
  gets(ch);
  if (isspace(ch[0])) list(student,n);
  else
  {
      for (i=0;i<n;i++)
      {
          if (strcmp(ch,student[i].name)==0)
          {
              printf("found hahaha!\n%s\t%I64d\n",student[i].name,student[i].phonenumber);
              break;
          }
      }
  }

    return 0;
}
