#define OK 1
#define ERROR 0
#define TRUE 1
#define FALSE 0
typedef int Status;

//----- 栈的顺序存储表示 -----
#define  STACK_INIT_SIZE  100
#define  STACKINCREMENT   10
typedef  int SElemType, ElemType;
typedef struct {
  SElemType  *base;    
  SElemType  *top;  
  int  stacksize;    
} SqStack,Stack;
Status InitStack (SqStack &S);//初始化栈
Status Push (SqStack &S, SElemType e);//入栈
Status Pop (SqStack &S, SElemType &e);//出栈
Status StackEmpty(SqStack S);//判是否为空栈