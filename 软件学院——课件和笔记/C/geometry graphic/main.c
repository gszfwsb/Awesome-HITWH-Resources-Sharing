#include <stdio.h>
#include <stdlib.h>
enum type {line,rectangle,circle,ellipe,none};
typedef struct rl
{
          int x1;
          int x2;
          int y1;
          int y2;
}RL;

typedef struct cir
{
          int x;
          int y;
          int r;
}CIRCLE;

typedef struct Ellipse
{
          int x;
          int y;
          int a;
          int b;
}ELLIPSE;

typedef struct fig
{
    int ID;
    union Basicdata
    {
       RL lr;
       CIRCLE circle;
       ELLIPSE ellipse;
    }basicdata;
    int type;
}figure;

void Add(figure fig[],int n)
{  char ch;
    system("cls");
    printf("Line/Rectangle/Circle/Ellipse\n");
    printf("请输入图形首字母，不区分大小写,按ESC返回，输入完后按下回车\n");
    ch=getch();
    system("cls");
    while (ch!='L' && ch!='l' && ch!='R' && ch!='r' && ch!='C' && ch!='c' && ch!='E' && ch!='e' &&ch!=27)
        scanf("%c",&ch);
    if (ch==27) return;
    switch (ch)
    {
    case ('l'):
    case ('L'):printf("请输入直线端点的两个坐标:x1,x2,y1,y2并按回车\n");
               scanf("%d,%d,%d,%d",&fig[n].basicdata.lr.x1,&fig[n].basicdata.lr.x2,&fig[n].basicdata.lr.y1,&fig[n].basicdata.lr.y2);
               fig[n].type=line;
               system("cls");
               printf("一条直线已经被添加！请按任意键继续\n");
               getchar();
               getchar();
               break;
    case ('R'):
    case ('r'):printf("请输入矩形端点的两个坐标:x1,x2,y1,y2并按回车\n");
               scanf("%d,%d,%d,%d",&fig[n].basicdata.lr.x1,&fig[n].basicdata.lr.x2,&fig[n].basicdata.lr.y1,&fig[n].basicdata.lr.y2);
               fig[n].type=rectangle;
               system("cls");
               printf("一个矩形已经被添加！请按任意键继续\n");
               getchar();
               getchar();
               break;
    case ('C'):
    case ('c'):printf("请输入圆心端点的坐标和半径:x,y,r并按回车\n");
               scanf("%d,%d,%d",&fig[n].basicdata.circle.x,&fig[n].basicdata.circle.y,&fig[n].basicdata.circle.r);
               fig[n].type=circle;
               system("cls");
               printf("一个圆已经被添加！请按任意键继续\n");
               getchar();
               getchar();
               break;
    case ('E'):
    case ('e'):printf("请输入椭圆心的坐标和半长轴半短轴:x,y,a,b并按回车\n");
               scanf("%d,%d,%d,%d",&fig[n].basicdata.ellipse.x,&fig[n].basicdata.ellipse.y,&fig[n].basicdata.ellipse.a,&fig[n].basicdata.ellipse.b);
               fig[n].type=ellipe;
               system("cls");
               printf("一个椭圆已经被添加！请按任意键继续\n");
               getchar();
               getchar();
               break;
    }

}
void list(figure fig[],int n)
{
  int i;
  system("cls");
  for (i=0;i<n;i++)
  {

      switch (fig[i].type)
      {
          case(line):printf("ID= %d:直线:端点坐标为 x1=%d,y1=%d,x2=%d,y2=%d\n",fig[i].ID,fig[i].basicdata.lr.x1,fig[i].basicdata.lr.x2,fig[i].basicdata.lr.y1,fig[i].basicdata.lr.y2);
                     break;
          case(rectangle):printf("ID= %d:矩形:端点坐标为 x1=%d,y1=%d,x2=%d,y2=%d\n",fig[i].ID,fig[i].basicdata.lr.x1,fig[i].basicdata.lr.x2,fig[i].basicdata.lr.y1,fig[i].basicdata.lr.y2);
                           break;
          case(circle):printf("ID= %d:圆:圆心坐标为 x=%d,y=%d,半径为%d\n",fig[i].ID,fig[i].basicdata.circle.x,fig[i].basicdata.circle.y,fig[i].basicdata.circle.r);
                       break;
          case(ellipe):printf("ID= %d:圆:中心坐标为 x=%d,y=%d,半长轴为a=%d,半短轴为b=%d\n",fig[i].ID,fig[i].basicdata.ellipse.x,fig[i].basicdata.ellipse.y,fig[i].basicdata.ellipse.a,fig[i].basicdata.ellipse.b);
                       break;

      }
  }
  printf("\n请按任意键继续\n");
  getchar();

}
void Delete(figure fig[])
{   int r,n;
   printf("请输入要删除的ID号,否则返回\n");
    if (!(r=scanf("%d",&n))) return;
    fig[n].type=none;
    getchar();
    printf("ID是%d的图形已经被删除！\n",n);
    getchar();

}
int main()
{  figure fig[100];
   char ch;
   int i,count=0;
   for (i=0;i<100;i++) fig[i].ID=i; //初始化id
   while (1)
   {system("cls");
    printf("Add/List/Delete/Quit\n");
    printf("请输入首字母来执行功能输入，不区分大小写并按下回车\n");
    ch=getch();
    system("cls");
    switch (ch)
    {
      case ('a'):
      case ('A'):Add(fig,count++);
               break;
      case ('q'):
      case ('Q'):return 0;
      case ('l'):
      case ('L'):list(fig,count);break;
      case ('d'):
      case ('D'):Delete(fig);break;

    }

   }

    return 0;
}
