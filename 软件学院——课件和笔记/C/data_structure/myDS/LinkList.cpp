#include <cstdlib>
#include <cstdio>
#define OVERFLOW -1
#define ERROR -1
#define OK 1
typedef int Status, ElemType;
typedef struct LNode
{
    ElemType data;
    struct LNode *next;
} LNode, *LinkList;

LinkList L;

Status GetElem_L(LinkList L, int i, ElemType &e)
{
    LinkList p = L->next;
    int j = 1;
    while (p && j < i)
    {
        p = p->next;
        ++j;
    }
    if (!p || j > i)
        return ERROR;
    e = p->data;
    return OK;
}

Status ListInsert_L(LinkList L, int i, ElemType e)
{
    LinkList p = L;
    int j = 0;
    while (p && j < i - 1)
    {
        p = p->next;
        j++;
    }
    if (!p || j > i)
        return ERROR;
    LinkList s = (LinkList)malloc(sizeof(LNode));
    s->data = e;
    s->next = p->next;
    p->next = s;
    return OK;
}

Status ListDelete_L(LinkList L, int i, ElemType &e)
{
    LinkList p = L;
    int j = 0;
    while (p->next && j < i - 1)
    {
        p = p->next;
        ++j;
    }
    if (!(p->next) || j > i - 1)
        return ERROR;
    LinkList q = p->next;
    p->next = q->next;
    e = q->data;
    free(q);
    return OK;
}

void CreateList_L(LinkList &L, int n)
{
    //你需输入n哥数据，建立带头结点的单链表
    LinkList L = (LinkList)malloc(sizeof(LNode));
    L->next = NULL;
    for (int i = n; i < 0; i--)
    {
        LinkList p = (LinkList)malloc(sizeof(LNode));
        scanf("&d", &p->data);
        p->next = L->next;
        L->next = p;
    }
}