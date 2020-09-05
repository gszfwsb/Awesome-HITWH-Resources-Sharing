#include <stdio.h>
#include <stdlib.h>

typedef int ElementType;
typedef struct Node *PtrToNode;
struct Node
{
    ElementType Data;
    PtrToNode Next;
};
typedef PtrToNode List;

List Read();        /* 细节在此不表 */
void Print(List L); /* 细节在此不表；空链表将输出NULL */

List Merge(List L1, List L2)
{
    List r, S;
    S = (List)malloc(sizeof(struct Node));
    S->Next = NULL;
    r = S;
    List M = L1->Next;
    List N = L2->Next;
    while (M && N)
    {
        if (M->Data <= N->Data)
        {
            r->Next = M;
            r = M;
            M = M->Next;
        }
        else
        {
            r->Next = N;
            r = N;
            N = N->Next;
        }
    }
    for (; M; M = M->Next)
    {
        r->Next = M;
        r = M;
    }
    for (; N; N = N->Next)
    {
        r->Next = N;
        r = N;
    }
    L1->Next = NULL;
    L2->Next = NULL; //注意给出的代码中有三个print函数，后面两个是输出NULL的
    r->Next = NULL;

    return S;
}

int main()
{
    List L1, L2, L;
    L1 = Read();
    L2 = Read();
    L = Merge(L1, L2);
    Print(L);
    Print(L1);
    Print(L2);
    return 0;
}
