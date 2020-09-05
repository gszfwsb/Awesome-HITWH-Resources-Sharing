#include <stdio.h>
#include <stdlib.h>
void add();
void reduction();
void instruction();
void E();
int main()
{

    int a,b,c;
    printf("                              10以内加减法辅助教学程序\n");
    printf("                              ******************************\n");
    printf("                              ******************************\n");
    printf("                              **                          **\n");
    printf("                              **       1加法练习          **\n");
    printf("                              **       2减法练习          **\n");
    printf("                              **       3程序说明          **\n");
    printf("                              **       4退出程序          **\n");
    printf("                              **                          **\n");
    printf("                              ******************************\n");
    printf("                              ******************************\n");
    printf("                      输入菜单项前的数字，选择功能，如数字超过菜单项前的数字将显示0：");
    scanf("%d",&c);
    switch(c)
    {
    case 1:add();break;
    case 2:reduction();break;
    case 3:instruction();break;
    case 4:return 0;
    default:
        E();break;
    }
}
void add(int b,int c)
{
    system("cls");
    printf("开始做咯^_^\n\n");
    int i,i2=0,i3=0,a1,p;
    for(i=0;i<=4;)
    {
        srand(time(NULL));
        a=rand()%10+1;
        b=rand()%10+1;
        if(a+b<=10)
        {
            printf("%d+%d=",b,c);
            scanf("%d",&a1);
            if(a1==b+c)
            {
                printf("正确^_^\n");
                i++;
                continue;
            }
            else if(a1!=b+c)
            {
                i2++;
                for(;;)
                {
                    printf("错误o(╥﹏╥)o，重新输入！\n");
                    printf("%d+%d=",b,c);
                    scanf("%d",&a1);
                    if(a1!=b+c)
                    {
                        i2++;
                        if(i2==3)
                        {
                            printf("你的正确率是%.f%%\n",100*(float)i/(i+i2+i3));
                            printf("按回车键继续");
                            getchar();
                            p=getchar();
                            if(p=='\n')
                            {
                              system("cls");
                              return main();
                            }


                        }
                    }
                    if(a1==b+c)
                    {
                        i3++;
                       printf("正确^_^\n");
                       break;
                    }
                }
                continue;
            }
        }
        else
        {
            continue;
        }
        return 0;
    }
    printf("你的正确率是%.2f%%",100*(float)i/(i+i2+i3));
    printf("按回车键继续\n");
    getchar();
    p=getchar();
    if(p=='\n');
    {
        system("cls");
        return main();
    }
}
void reduction(int a,int b)
{
    system("cls");
    printf("开始做咯\n\n");
    int i,i2=0,i3=0,a1,p;
    for(i=0;i<=4;)
    {
        srand(time(NULL));
        a=rand()%10+1;
        b=rand()%10+1;
        if(a>=b)
        {
            printf("%d-%d=",a,b);
            scanf("%d",&a1);
            if(a1==a-b)
            {
                printf("正确^_^\n");
                i++;
                continue;
            }
            else if(a1!=a-b)
            {
                i2++;
                for(;;)
                {
                    printf("错误o(╥﹏╥)o，重新输入！\n");
                    printf("%d-%d=",a,b);
                    scanf("%d",&a1);
                    if(a1!=a-b)
                    {
                        i2++;
                        if(i2==3)
                        {
                            printf("你的正确率是%.f%%\n",100*(float)i/(i+i2+i3));
                            printf("按回车键继续");
                            getchar();
                            p=getchar();
                            if(p=='\n')
                            {
                              system("cls");
                              return main();
                            }


                        }
                    }
                    if(a1==a-b)
                    {
                        i3++;
                       printf("正确^_^\n");
                       break;
                    }
                }
                continue;
            }
        }
        else
        {
            continue;
        }
        return 0;
    }
    printf("你的正确率是%.2f%%",100*(float)i/(i+i2+i3));
    printf("按回车键继续\n");
    getchar();
    p=getchar();
    if(p='\n');
    {
        system("cls");
        return main();
    }

}
void instruction()
{
    int a;
    system("cls");
    printf("你将做到加减法题目，其中为10以内加、减法的算式，加法时加数、和均不能超过10。减法时被减数、减数均不能大于10，且差不会出现负数。如果你输入答案错误则重得练习此题，且不会被判为正确，回答正确进行下一题。每组练习5题，每组练习完成之后进行正确率统计。\n");
    printf("请输出回车继续返回菜单。");
    getchar();
    a=getchar();
    if(a=='\n')
    {
        system("cls");
        return main();
    }

}
void E()
{
    int p;
    system("cls");
    printf("0\n");
    printf("按回车键继续！\n");
    getchar();
    p=getchar();
    if(p=='\n')
    {
        system("cls");
        return main();
    }
}
