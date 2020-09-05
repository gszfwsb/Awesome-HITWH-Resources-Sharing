#include<stdio.h>
#include <time.h>
clock_t  start, stop; /* clock_t是clock()函数返回的变量类型 */
double  duration;  /* 记录被测函数运行时间，以秒为单位 */

#define MaxSize  100

typedef struct {
    int   N; /* 盘数  */
    char  A; /* 起始柱  */
    char  B; /* 借助柱  */
    char  C; /* 目标柱  */
} ElementType;  /* 汉诺塔问题结构类型 */

ElementType ERROR;

typedef struct {
    ElementType Data[MaxSize];
    int Top;
} Stack; /* 堆栈的标准定义 */

void Push( Stack *PtrS, ElementType item )
{   /* 入栈操作 */
    if ( PtrS->Top == MaxSize-1 ) {
        printf("堆栈满");
        return;
    }
    else {
        PtrS->Data[++(PtrS->Top)] = item;
        return;
    }
}

ElementType Pop( Stack *PtrS )
{
    if ( PtrS->Top == -1 ) {
        printf("堆栈空");
        return ERROR; /* ERROR是ElementType的特殊值，标志错误 */
    }
    else {
        PtrS->Top --;
        return ( PtrS->Data[PtrS->Top+1] );
    }
}


void Hanoi( int n )    /* 借助堆栈的非递归实现 */
{
    ElementType P, toPush;
    Stack S;
    
    /* 初始化 */
    P.N = n;  P.A='a';  P.B='b';  P.C='c';
    S.Top= -1;

    Push(&S, P);
    while ( S.Top != -1 ) { /* 当堆栈不空时 */
        P = Pop(&S);
        if ( P.N == 1)
            printf("%c -> %c\n", P.A, P.C);
        else {
            toPush.N = P.N - 1; toPush.A = P.B; toPush.B = P.A; toPush.C = P.C;
            Push( &S, toPush ); /* 将第二个待解子问题(n-1,b,a,c)入栈 */

            toPush.N = 1; toPush.A = P.A; toPush.B = P.B; toPush.C = P.C;
            Push( &S, toPush ); /* 将可直接求解的子问题(1,a,b,c)入栈 */

            toPush.N = P.N - 1; toPush.A = P.A; toPush.B = P.C; toPush.C = P.B;
            Push( &S, toPush ); /* 将第一个待解子问题(n-1,a,c,b)入栈 */
        }
    }
}
/*
void Move(int n, int start, int goal, int temp)
{
    if(n==0) return;
    Move(n-1, start, temp, goal);
    printf("%c -> %c\n", 'a'+start-1, 'a'+goal-1);
    Move(n-1, temp, goal, start);
}
*/
int main()
{
//    int i;
    int n;
    ERROR.N = -1; /* ERROR是ElementType的特殊值，标志错误 */
   
    scanf("%d", &n);
//    start = clock(); /* 开始计时 */
//    for (i=0; i<10; i++)
    Hanoi(n);
/*    Move(n, 1, 3, 2); */
//    stop = clock();     /* 停止计时 */
//    duration = ((double)(stop - start))/CLK_TCK; /* 计算运行时间 */
//    printf("duration=%lf\n", duration/10);

    return 0;
}
