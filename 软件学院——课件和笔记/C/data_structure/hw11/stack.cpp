#include "stdio.h"
#include "stdlib.h"
#include "stack.h" 
#include "math.h"//OVERFLOW的宏定义来自于此
Status InitStack (SqStack &S)
{// 构造一个空栈S
  S.base=(ElemType*)malloc(STACK_INIT_SIZE*sizeof(ElemType));
   if (!S.base) exit (OVERFLOW); //存储分配失败
   S.top = S.base;
   S.stacksize = STACK_INIT_SIZE;
   return OK;
}
 Status Push (SqStack &S, SElemType e) {//入栈
   if (S.top - S.base >= S.stacksize) {//栈满，追加存储空间
      S.base = (ElemType *) realloc ( S.base,
                (S.stacksize + STACKINCREMENT) * sizeof (ElemType));
       if (!S.base) exit (OVERFLOW); //存储分配失败
       S.top = S.base + S.stacksize;
       S.stacksize += STACKINCREMENT;
   }
   *S.top++ = e;
    return OK;
}
 Status Pop (SqStack &S, SElemType &e) {//出栈
     // 若栈不空，则删除S的栈顶元素，
     // 用e返回其值，并返回OK；
     // 否则返回ERROR
    if (S.top == S.base) return ERROR;
    e = *--S.top;
    return OK;
}
 Status StackEmpty(SqStack S){//判栈空
	 if(S.base == S.top) return TRUE;
	 return FALSE;
 }


