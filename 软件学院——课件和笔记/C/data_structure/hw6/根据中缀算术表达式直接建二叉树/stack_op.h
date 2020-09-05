#ifndef _STACK_OP_H
#define _STACK_OP_H
#define OK 1
#define ERROR 0
#define TRUE 1
#define FALSE 0
typedef int Status;
#define  STACK_INIT_SIZE  100
#define  STACKINCREMENT   10
typedef  char SElemType1, ElemType1;
typedef struct {
  SElemType1  *base;    
  SElemType1  *top;  
  int  stacksize;    
} SqStack1,Stack1;
Status InitStack (SqStack1 &S);
Status Push (SqStack1 &S, SElemType1 e);
Status Pop (SqStack1 &S, SElemType1 &e);
Status StackEmpty(SqStack1 S);
Status GetTop(SqStack1 &S, SElemType1 &e);
SElemType1 GetTop(SqStack1 &S);

#endif
