#include <iostream>
#include "bitree.h"
#define OVERFLOW -1
using namespace std;
Status CreateBiTree(BiTree &T)
{
   TElemType ch;
   cin >> ch;
   if ((ch <= 'z' && ch >= 'a') || (ch <= 'Z' && ch >= 'A'))
   {
      if (!(T = (BiTNode *)malloc(sizeof(BiTNode))))
         exit(OVERFLOW);
      T->data = ch;     // 生成根结点
      T->lchild = NULL; // 构造左子树
      T->rchild = NULL; // 构造右子树
   }
   else
   {
      if (!(T = (BiTNode *)malloc(sizeof(BiTNode))))
         exit(OVERFLOW);
      T->data = ch;            // 生成根结点
      CreateBiTree(T->lchild); // 构造左子树
      CreateBiTree(T->rchild); // 构造右子树
   }
   return OK;
} // CreateBiTree 以字符串的形式输入表达式  -×+ a b c / d e

void PreOrder(BiTree T)
{ // 先序遍历二叉树
   if (T)
   {
      cout << T->data;     // 访问结点
      PreOrder(T->lchild); // 遍历左子树
      PreOrder(T->rchild); // 遍历右子树
   }
}
void InOrder(BiTree T)
{ // 中序遍历二叉树
   if (T)
   {
      InOrder(T->lchild); // 遍历左子树
      cout << T->data;    // 访问结点
      InOrder(T->rchild); // 遍历右子树
   }
}
void PostOrder(BiTree T)
{ // 后序遍历二叉树
   if (T)
   {
      PostOrder(T->lchild); // 遍历左子树
      PostOrder(T->rchild); // 遍历右子树
      cout << T->data;      // 访问结点
   }
}
