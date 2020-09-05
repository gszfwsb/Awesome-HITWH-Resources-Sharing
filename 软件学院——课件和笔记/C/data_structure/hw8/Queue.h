#define OK 1
#define ERROR 0
#define TRUE 1
#define FALSE 0
#include "math.h"
typedef int Status;
/* 单链队列－－队列的链式存储结构 */
typedef int QElemType;
typedef struct QNode
 {
   QElemType data;
   struct QNode *next;
 }QNode,*QueuePtr;

 typedef struct
 {
   QueuePtr front,rear; /* 队头、队尾指针 */
 }LinkQueue;

Status InitQueue(LinkQueue &Q);/* 构造一个空队列Q */
Status DestroyQueue(LinkQueue &Q);/* 销毁队列Q(无论空否均可) */
Status ClearQueue(LinkQueue &Q); /* 将Q清为空队列 */
Status QueueEmpty(LinkQueue Q); /* 若Q为空队列,则返回TRUE,否则返回FALSE */
int QueueLength(LinkQueue Q); /* 求队列的长度 */
Status GetHead(LinkQueue Q,QElemType &e) ; /* 若队列不空,则用e返回Q的队头元素,并返回OK,否则返回ERROR */
Status EnQueue(LinkQueue &Q,QElemType e);/* 插入元素e为Q的新的队尾元素 */
Status DeQueue(LinkQueue &Q,QElemType &e);/* 若队列不空,删除Q的队头元素,用e返回其值,并返回OK,否则返回ERROR */
Status QueueTraverse(LinkQueue Q,void(*vi)(QElemType));/* 从队头到队尾依次对队列Q中每个元素调用函数vi()。一旦vi失败,则操作失败 */