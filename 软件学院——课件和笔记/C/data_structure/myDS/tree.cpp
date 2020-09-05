#include <cstdlib>
#include <cstdio>
#include <stack>
#include <iostream>
#define OVERFLOW -1
#define ERROR -1
#define OK 1
using namespace std;
typedef int Status;
typedef char TElemType;
typedef struct BiTreeNode
{
    TElemType data;
    struct BiTreeNode *leftChild, *rightChild;
} BiTreeNode, *BiTree;

Status CreateBiTree(BiTree &T)
{
    TElemType ch;
    cin >> ch;
    if ((ch <= 'z' && ch >= 'a') || (ch <= 'Z' && ch >= 'A'))
    {
        if (!(T = (BiTree)malloc(sizeof(BiTreeNode))))
            exit(OVERFLOW);
        T->data = ch;         // 生成根结点
        T->leftChild = NULL;  // 构造左子树
        T->rightChild = NULL; // 构造右子树
    }
    else
    {
        if (!(T = (BiTree)malloc(sizeof(BiTreeNode))))
            exit(OVERFLOW);
        T->data = ch;                // 生成根结点
        CreateBiTree(T->leftChild);  // 构造左子树
        CreateBiTree(T->rightChild); // 构造右子树
    }
    return OK;
}
void PreOrder(BiTree T)
{
    stack<BiTree> s;
    BiTree t = T;
    while (t || !s.empty())
    {
        while (t)
        {
            cout << t->data;
            s.push(t);
            t = t->leftChild;
        }
        if (!s.empty())
        {
            t = s.top();
            s.pop();
            t = t->rightChild;
        }
    }
}
void InOrder(BiTree T)
{
    stack<BiTree> s;
    BiTree t = T;
    while (t || !s.empty())
    {
        while (t)
        {
            s.push(t);
            t = t->leftChild;
        }
        if (!s.empty())
        {
            t = s.top();
            cout << t->data;
            s.pop();
            t = t->rightChild;
        }
    }
}
void PostOrder(BiTree T)
{
    if (!T)
        return;
    PostOrder(T->leftChild);
    PostOrder(T->rightChild);
    cout << T->data;
}

int GetDepth(BiTree T)
{
    if (!T->leftChild && !T->rightChild)
        return 1;
    int l = GetDepth(T->leftChild);
    int r = GetDepth(T->rightChild);
    return l > r ? (l + 1) : (r + 1);
}

BiTree CopyTree(BiTree T)
{
    if (!T)
        return NULL;
    BiTree l, r;
    if (T->leftChild)
        l = CopyTree(T->leftChild);
    else
        l = NULL;
    if (T->rightChild)
        r = CopyTree(T->rightChild);
    else
        r = NULL;
    BiTree newT = (BiTree)malloc(sizeof(BiTreeNode));
    newT->data = T->data;
    newT->leftChild = l;
    newT->rightChild = r;
    return newT;
}

int main()
{
    BiTree bt;
    CreateBiTree(bt);
    cout << GetDepth(bt);

    return 0;
}