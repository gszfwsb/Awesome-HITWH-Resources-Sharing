#include <stdio.h>
#include <stdlib.h>
#include <string.h>
typedef struct data
{
    int id;
    int length;
    char type;
    char name[11];
}DATA;

int main()
{DATA *pstart,*p;
 void *q,*pbegin;
 int *pn;
 char *ps,ch;
 int n,m,i,j,recordlen=0;
 printf("请输入字段数:\n");
 scanf("%d",&m);
 printf("请输入记录数:\n");
 scanf("%d",&n);
 system("cls");
 pstart=(DATA *)calloc(m,sizeof(DATA));
  p=pstart;
  for (i=0;i<m;i++,p++)
  {
      p->id=i+1;
      printf("请输入字段名称:\n");
      scanf(" %s",p->name);
      printf("请输入字段类型，输入n是int,s是string\n");
      scanf(" %c",&p->type);
     if (p->type=='s')
     {
         printf("请输入字符串长度\n");
         scanf("%d",&p->length);
         p->length++;
     }
     else p->length=4;
     printf("一个字段信息已经输入完毕!请按任意键继续\n\n");
     getchar();getch();
  }
  p=pstart;
  system("cls");
  printf("已建立记录的字段属性信息:\n");
  for (i=0;i<m;i++,p++)
  {
      recordlen+=p->length;
      printf("字段ID:%d,\t%s,\t%d,\t%c\n",i+1,p->name,p->length,p->type);
  }
  p=pstart;
  printf("%d\n",recordlen);
  printf("按任意键，可以录入信息:\n");
  getch();
  system("cls");

  pbegin=(char*)calloc(n,recordlen);
  q=pbegin;
  for (i=0;i<n;i++)
  { p=pstart;
     for (j=0;j<m;j++,p++)
     {
         printf("%s=",p->name);
      if (p->type=='n')
        {
             pn=(int*)q;
             scanf("%d",pn);
             getchar();
             q+=4;
        }
      if (p->type=='s')
      {
          ps=(char*)q;
          gets(ps);
          q+=p->length;
      }
     }
     printf("一条记录已经录入完毕!按任意键继续,ESC退出\n");
     ch=getch();
     if (ch==27)
     {
        printf("\n\n\n程序运行结束！哈哈哈哈哈哈~~~~~ 按任意键退出！");
         getch();
        return 0;
     }
 }
 //getchar();
 system("cls");
 printf("已经录入的信息:\n");
 q=pbegin;
 for (i=0;i<n;i++)
 { p=pstart;
      for (j=0;j<m;j++,p++)
     {
         if (p->type=='n')
        {
             pn=(int*)q;
             printf("%s:%d\t",p->name,*pn);
             q+=4;
        }
      if (p->type=='s')
      {
          ps=(char*)q;
          printf("%s:%s\t",p->name,ps);
          q+=p->length;
      }
     }
     printf("\n");
 }
 free(pstart);
 free(pbegin);
 printf("\n\n\n程序运行结束！哈哈哈哈哈哈~~~~~ 按任意键退出！");
 getch();
  return 0;
}

