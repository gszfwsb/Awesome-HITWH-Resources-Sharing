#include "stdio.h"
#include "stdlib.h"
#include "stack_op.h" 
#include "math.h"//OVERFLOW的宏定义来自于此
Status InitStack (SqStack1 &S)
{// 构造一个空栈S
  S.base=(ElemType1*)malloc(STACK_INIT_SIZE*sizeof(ElemType1));
   if (!S.base) exit (OVERFLOW); //存储分配失败
   S.top = S.base;
   S.stacksize = STACK_INIT_SIZE;
   return OK;
}
 Status Push (SqStack1 &S, SElemType1 e) {//入栈
   if (S.top - S.base >= S.stacksize) {//栈满，追加存储空间
      S.base = (ElemType1 *) realloc ( S.base,
                (S.stacksize + STACKINCREMENT) * sizeof (ElemType1));
       if (!S.base) exit (OVERFLOW); //存储分配失败
       S.top = S.base + S.stacksize;
       S.stacksize += STACKINCREMENT;
   }
   *S.top++ = e;
    return OK;
}
 Status Pop (SqStack1 &S, SElemType1 &e) {//出栈
     // 若栈不空，则删除S的栈顶元素，
     // 用e返回其值，并返回OK；
     // 否则返回ERROR
    if (S.top == S.base) return ERROR;
    e = *--S.top;
    return OK;
}
 Status StackEmpty(SqStack1 S){//判栈空
	 if(S.base == S.top) return TRUE;
	 return FALSE;
 }

 Status GetTop(SqStack1 &S, SElemType1 &e){//获取栈顶元素1
	//若栈不空，则用e返回S的栈顶元素，并返回OK；否则返回ERROR
	if(S.top == S.base) return ERROR;
	e = *(S.top-1);
	return OK; 
 }

  SElemType1 GetTop(SqStack1 &S){//返回栈顶元素2
	//若栈不空，则用e返回S的栈顶元素，并返回OK；否则返回ERROR
	if(S.top == S.base) return NULL;
	SElemType1 e = *(S.top-1);
	return e; 
 }
