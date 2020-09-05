#include <cstdlib>
#include <cstdio>
#define OVERFLOW -1
#define ERROR -1
#define OK 1
#define STACK_INIT_SIZE 100
#define STACKINCREMENT 10

typedef int Status, SElemType;
typedef struct
{
    SElemType *base;
    SElemType *top;
    int stacksize;
} SqStack;

Status InitStack(SqStack &S)
{
    S.base = (SElemType *)malloc(STACK_INIT_SIZE * sizeof(SElemType));
    if (!S.base)
        exit(OVERFLOW);
    S.top = S.base;
    S.stacksize = STACK_INIT_SIZE;
    return OK;
}
Status Push(SqStack &S, SElemType e)
{
    if(S.top - S.base>=S.stacksize){
        S.base = (SElemType*)realloc(S.base,(S.stacksize+STACKINCREMENT)*sizeof(SElemType));
        if(!S.base) exit(OVERFLOW);
        S.top = S.base + S.stacksize;
        S.stacksize+=STACKINCREMENT;
    }
    *S.top++ = e;
    return OK;
}

SElemType Pop(SqStack &S){
    if (S.base == S.top) return ERROR;
    SElemType e = *(--S.top);
    return e;
}

int main(){
    SqStack stack;
    InitStack(stack);
    int n = 1348;
    while(n){
        Push(stack,n%8);
        n/=8;
    }
    int a;
    while((a = Pop(stack))!=ERROR){
        printf("%d\n",a);
    }
}