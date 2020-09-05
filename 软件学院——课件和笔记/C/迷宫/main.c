#include  <stdio.h>
#include <stdlib.h>
#include <windows.h>
#include <string.h>
short b[8][8];
int a[8][8]={{1,2,1,1,1,1,1,1},
              {1,0,0,1,0,0,0,1},
              {1,1,0,0,0,1,1,1},
              {1,0,0,1,0,0,0,1},
              {1,1,1,1,0,1,1,1},
              {1,0,0,0,0,0,0,1},
              {1,0,1,0,1,0,0,1},
              {1,1,1,1,1,1,3,1}};
typedef struct node
{
    int rol;//行
    int col;//列
    struct node *next;
    struct node *prev;
}NODE;

void output(int rol,int col)//打印当前位置的地图
{ int i,j;
  int flag=1;
  for (i=0;i<8;i++)
  {
      for (j=0;j<8;j++)
      {   if (flag && i==rol && j==col)
          {
            printf("＠");
            flag=0;
            continue;
          }
              switch (a[i][j])
              {
              case 0:
              case 2:
              case 3:printf("  ");break;
              case 1:printf("█"); break;
              }
          }
           printf("\n");
      }

  }




void *create()
{ NODE *head;
  NODE *p,*pr;
  head=(NODE*)malloc(sizeof(NODE));
  head->prev=NULL;
  head->rol=0;
  head->col=1;
  pr=head;
  a[0][1]=0;
  while (1)
  {   p=(NODE*)malloc(sizeof(NODE));
          system("cls");

          output(pr->rol,pr->col);
          Sleep(500);
          if (a[pr->rol][pr->col]==3) return;
      if (a[pr->rol+1][pr->col]!=1 && !b[pr->rol+1][pr->col])
      {
          b[pr->rol+1][pr->col]=1;
          p->rol=pr->rol+1;
          p->col=pr->col;
          pr->next=p;
          p->prev=pr;
          pr=p;
          continue;
      }//向下
       if (a[pr->rol][pr->col+1]!=1 && !b[pr->rol][pr->col+1])
      {   b[pr->rol][pr->col+1]=1;
          p->rol=pr->rol;
          p->col=pr->col+1;
          pr->next=p;
          p->prev=pr;
          pr=p;
          continue;
      }//向右
       if (a[pr->rol][pr->col-1]!=1 && !b[pr->rol][pr->col-1])
      {
          b[pr->rol][pr->col-1]=1;
          p->rol=pr->rol;
          p->col=pr->col-1;
          pr->next=p;
          p->prev=pr;
          pr=p;
          continue;
      }//向左
       if (a[pr->rol-1][pr->col]!=1 && !b[pr->rol-1][pr->col])
      {
          b[pr->rol-1][pr->col]=1;
          p->rol=pr->rol-1;
          p->col=pr->col;
          pr->next=p;
          p->prev=pr;
          pr=p;
          continue;
      }

      pr=pr->prev;


  }
  pr->next=NULL;
  return head;
}




int main()
{
    printf("按任意键开始\n");
  getch();
  create();
  printf("\nheiheihei!!!");
     return 0;
}
