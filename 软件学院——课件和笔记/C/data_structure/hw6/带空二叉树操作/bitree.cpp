#include "stdio.h"
#include <iostream>
using namespace std;
#include "bitree.h"
Status CreateBiTree(BiTree &T)
{
   TElemType ch;
   //scanf(&ch);//输入ch
   cin >> ch;
   if (ch == '^')
      T = NULL; //课件中用空格表示空，这里用^表示空
   else
   {
      if (!(T = (BiTNode *)malloc(sizeof(BiTNode))))
         exit(OVERFLOW);
      T->data = ch;            // 生成根结点
      CreateBiTree(T->lchild); // 构造左子树
      CreateBiTree(T->rchild); // 构造右子树
   }
   return OK;
} // CreateBiTree 以字符串的形式  根 左子树 右子树   定义一棵二叉树,空树用^表示

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

//BiTNode *GoFarLeft(BiTree T, Stack *S){//原课件中的函数头，改为下面的  &S
BiTNode *GoFarLeft(BiTree T, Stack &S)
{ //找到树T的最左下结点
   if (!T)
      return NULL;
   while (T->lchild)
   {
      Push(S, T);
      T = T->lchild;
   }
   return T;
}

void InOrder_I(BiTree T)
{
   //Stack *S;
   Stack S;
   InitStack(S);               //初始化栈S
   BiTree t = GoFarLeft(T, S); // 找到最左下的结点
   while (t)
   {
      cout << t->data;
      if (t->rchild)
         t = GoFarLeft(t->rchild, S);
      else if (!StackEmpty(S)) // 栈不空时退栈
                               //t = Pop(S);
         Pop(S, t);
      else
         t = NULL; // 栈空表明遍历结束
   }               // while
} // Inorder_I
void CountLeaf(BiTree T, int &count)
{ //统计叶子节点的个数
   if (T)
   {
      if ((!T->lchild) && (!T->rchild))
         count++; // 对叶子结点计数
      CountLeaf(T->lchild, count);
      CountLeaf(T->rchild, count);
   } // if
} // CountLeaf
int Depth(BiTree T)
{ // 返回二叉树的深度
   int depthval, depthLeft, depthRight;
   if (!T)
      depthval = 0;
   else
   {
      depthLeft = Depth(T->lchild);
      depthRight = Depth(T->rchild);
      depthval = 1 + (depthLeft > depthRight ? depthLeft : depthRight);
   }
   return depthval;
}
