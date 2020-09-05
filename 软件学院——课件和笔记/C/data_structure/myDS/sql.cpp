#include <cstdlib>
#include <cstdio>
#define OVERFLOW -1
#define ERROR -1
#define OK 1
#define LIST_INIT_SIZE 80
#define LISTINCREMENT 10

using namespace std;

typedef int ElemType, Status;
typedef struct
{
    ElemType *elem;
    int length;
    int listsize;
} SqList;

Status InitList(SqList &L)
{
    L.elem = (ElemType *)malloc(LIST_INIT_SIZE * sizeof(ElemType));
    if (!L.elem)
        exit(OVERFLOW);
    L.length = 0;
    L.listsize = LIST_INIT_SIZE;
    return OK;
}

int LocateElem_Sq(SqList L, ElemType e, Status (*compare)(ElemType, ElemType))
{
    int i = 1;
    ElemType *p = L.elem;
    while (i <= L.length && !(*compare)(*p++, e))
        ++i;
    if (i <= L.length)
        return i;
    return 0;
}

Status ListInsert_Sq(SqList &L, int i, ElemType e)
{
    if (i < 1 || i > L.length)
        return ERROR;
    if (L.length >= L.listsize)
    {
        ElemType *newBase = (ElemType *)realloc(L.elem, (L.listsize + LISTINCREMENT) * sizeof(ElemType));
        if (!newBase)
            exit(OVERFLOW);
        L.elem = newBase;
        L.listsize += LISTINCREMENT;
    }
    ElemType *q = &(L.elem[i - 1]);
    for (ElemType *p = &(L.elem[L.length - 1]); p >= q; p--)
    {
        *(p + 1) = *p;
    }
    *q = e;
    L.length++;
    return OK;
}

Status ListDelete_Sq(SqList &L, int i,ElemType &e){
    if (i<1 || i>L.length) return ERROR;
    ElemType *p = &(L.elem[i-1]);
    ElemType e = *p;
    ElemType *q = L.elem+L.length-1;
    for (++p;p<=q;++p){
        *(p-1)=*p;
    }
    L.length--;
    return OK;
}



































