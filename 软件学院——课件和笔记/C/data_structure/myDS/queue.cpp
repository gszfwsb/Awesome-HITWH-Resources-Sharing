#include <bits/stdc++.h>
#define OK 1
typedef int ElemType, Status;
typedef struct QNode
{
    ElemType data;
    struct QNode *next;
} QNode, *QueuePtr;

typedef struct
{
    QueuePtr front;
    QueuePtr rear;
} LinkQueue;

Status InitQueue(LinkQueue &Q)
{
    Q.front = Q.rear = (QueuePtr)malloc(sizeof(QNode));
    if (!Q.front)
        exit(-1);
    Q.front->next = NULL;
    return OK;
}

Status EnQueue(LinkQueue &Q, ElemType e)
{
    QueuePtr q = (QueuePtr)malloc(sizeof(QNode));
    if (!q)
        exit(-1);
    q->data = e;
    q->next = NULL;
    Q.rear->next = q;
    Q.rear = q;
    return OK;
}

ElemType DeQueue(LinkQueue &Q)
{
    if (Q.front == Q.rear)
        return -1;
    QueuePtr p = Q.front->next;
    ElemType e = p->data;
    Q.front->next = p->next;
    if (Q.rear == p)
        Q.rear == Q.front;
    free(p);
    return OK;
}
