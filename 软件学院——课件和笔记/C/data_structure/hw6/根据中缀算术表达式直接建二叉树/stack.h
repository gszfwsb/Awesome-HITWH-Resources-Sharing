#ifndef _STACK_H
#define _STACK_H
#define OK 1
#define ERROR 0
#define TRUE 1
#define FALSE 0
typedef int Status;
#define STACK_INIT_SIZE 100
#define STACKINCREMENT 10
typedef char TElemType;
typedef struct BiTNode
{ 
  TElemType data;
  struct BiTNode *lchild, *rchild; 
} BiTNode, *BiTree;
typedef BiTree SElemType, ElemType;
typedef struct
{
  SElemType *base;
  SElemType *top;
  int stacksize;
} SqStack, Stack;
Status InitStack(SqStack &S);           
Status Push(SqStack &S, SElemType e);    
Status Pop(SqStack &S, SElemType &e);    
Status StackEmpty(SqStack S);           
Status GetTop(SqStack &S, SElemType &e); 

#endif