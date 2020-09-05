#include <stdio.h>
#include <stdlib.h>
struct DATA
{
    int ID;
    char name[11];
    char type;
    int length;
};

void Find(int n,struct DATA *p)

{
    int t,i;
    system("cls");
    for(i=0;i<n;i++)
    {
        (p+i)->ID=i+1;
        printf("请输入ID=%d的字段名称：",i+1);
        scanf("%s",(p+i)->name);
        fflush(stdin);
        printf("请选择字段类型(int n/string s)：");
        scanf("%c",&(p+i)->type);
        fflush(stdin);
        if((p+i)->type=='s')
        {
            printf("请输入字符串长度：");
            scanf("%d",&t);
            (p+i)->length=t+1;
        }
        else if((p+i)->type=='n')
        {
            (p+i)->length=4;
        }
        printf("一个字段属性输入完毕，按任意键继续\n");
        getch();
    }

}

int Search(int n,struct DATA *p)
{
    int sum=0,i;
    system("cls");
    printf("已建立记录的字段属性信息：\n");
    for(i=0;i<n;i++)
    {
        sum+=(p+i)->length;
        printf("字段ID：%d,字段名：%s,字段长度：%d,字段类型：%c\n",(p+i)->ID,(p+i)->name,(p+i)->length,(p+i)->type);
    }
    printf("所有字段所占空间为%d字节\n",sum);
    printf("按任意键录入信息");
    getch();
    return sum;
}

int Input(int n,int m,struct DATA *p,char *pc)

{
    system("cls");
    int i,j,t=0,count=0,a;
    for(j=0;j<m;j++)
    {
        count++;
        for(i=0;i<n;i++)
        {
            printf("%s=",(p+i)->name);
            if((p+i)->type=='s')
                scanf("%s",pc+t);
            else
                scanf("%d",pc+t);
            t+=(p+i)->length;
        }
        if(j==m-1) printf("记录全部录入完毕，按任意键浏览录入的信息");
        else printf("一条记录录入完毕，按任意键继续，按ESC键停止录入信息\n");
        a=getch();
        if(a==27) break;
    }
    return count;
}

void Print(int n,int count,struct DATA *p,char *pc)
{
    system("cls");
    printf("已经录入的信息：\n");
    int i,j,t=0;
    for(j=0;j<count;j++)
    {
        for(i=0;i<n;i++)
        {
            if((p+i)->type=='s')
                printf("%s:%s, ",(p+i)->name,pc+t);
            else
                printf("%s:%d",(p+i)->name,*(pc+t));
            t+=(p+i)->length;
        }
        printf("\n");
    }
}

FILE *pf;
void Write(int m,int n,int sum,struct DATA *p,char *pc,int count)
{
    char filename[10];
    int i,j;
    printf("请输入要创建的文件名：");
    scanf("%s",filename);
    pf=fopen(filename,"wb+");
    if(pf==NULL)
    {
        printf("创建失败！");
        exit(0);
    }
    char a=18;
    fwrite(&a,1,1,pf);
    fwrite(&m,4,1,pf);
    fwrite(&n,4,1,pf);
    fwrite(&sum,4,1,pf);
    fwrite(&count,4,1,pf);
    for(i=0;i<n;i++)
    {
        fwrite(&(p+i)->ID,4,1,pf);
        fwrite(&(p+i)->name,11,1,pf);
        fwrite(&(p+i)->type,1,1,pf);
        fwrite(&(p+i)->length,4,1,pf);
    }
    int t=0;
    for(j=0;j<count;j++)
    {
        for(i=0;i<n;i++)
        {
            fwrite(&(p+i)->name,11,1,pf);
            fwrite(&(p+i)->length,4,1,pf);
            fwrite(&(p+i)->type,1,1,pf);
            fwrite(pc+t,(p+i)->length,1,pf);
            t+=(p+i)->length;
        }
    }
    fclose(pf);
}

void Open()
{
    system("cls");
    int i,j;
    char filename[10];
    printf("请输入要查询的文件名：");
    scanf("%s",filename);
    pf=fopen(filename,"rb");
    if(pf==NULL)
    {
        printf("查找失败！");
        exit(0);
    }
    else printf("以下是从文件中读取的信息：\n");
    char out1,out3[11];
    int out2;
    fread(&out1,1,1,pf);
    printf("版本号：%d\n",out1);
    int m;
    fread(&m,4,1,pf);
    printf("记录数：%d\n",m);
    int n;
    fread(&n,4,1,pf);
    printf("字段数：%d\n",n);
    fread(&out2,4,1,pf);
    printf("每条记录的长度：%d\n",out2);
    int count;
    fread(&count,4,1,pf);
    printf("记录头长度：%d\n",n*24+13);
    printf("\n字段属性：\n");
    for(i=0;i<n;i++)
    {
        fread(&out2,4,1,pf);
        printf("字段ID：%-2d",out2);
        fread(&out3,11,1,pf);
        printf("字段名称：%-11s",out3);
        fread(&out1,1,1,pf);
        printf("字段类型：%-2c",out1);
        fread(&out2,4,1,pf);
        printf("字段长度：%-3d\n",out2);
    }
    printf("\n记录数据：\n");
    int t=0,length;
    char type;
    for(j=0;j<count;j++)
    {
        for(i=0;i<n;i++)
        {
            fread(&out3,11,1,pf);
            printf("%s：",out3);
            fread(&length,4,1,pf);
            fread(&type,1,1,pf);
            if(type=='s')
            {
                fread(&out3,length,1,pf);
                printf("%s  ",out3);
            }
            else
            {
                fread(&out2,length,1,pf);
                printf("%d  ",out2);
            }
        }
        printf("\n");
    }
    fclose(pf);
    printf("读取完毕，按任意键返回菜单");
    getch();
}

int main()
{
    int n,m,sum,count,a;
     while(1)
    {
        system("cls");
        printf("按下对应按键选择功能：\n");
        printf("[C]:创建记录 [L]:列出记录 [Q]:退出");
        a=getch();
        if(a=='c'||a=='C')
        {
            system("cls");
            printf("请输入记录的字段数：");
            scanf("%d",&n);
            printf("请输入记录条数：");
            scanf("%d",&m);
            struct DATA *p=calloc(n,sizeof(struct DATA));
            if(p==NULL)
            {
                printf("error！");
                return 0;
            }
            Find(n,p);
            sum=Search(n,p);
            char *pc=calloc(m,sum);
            if(pc==NULL)
            {
                printf("error！");
                return 0;
            }
            count=Input(n,m,p,pc);
            Print(n,count,p,pc);
            Write(m,n,sum,p,pc,count);
            free(p);
            free(pc);
        }
        else if(a=='l'||a=='L') Open();
        else if(a=='q'||a=='Q') break;

    }
 return 0;
}

