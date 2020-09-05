#include "stdio.h"
#include "stdlib.h"
#include "bitree.h"
using namespace std;
char OP[] = {'+', '-', '*', '/', '\0'};
SqStack1 S;
SqStack PTR;
void CrtExptree(BiTree &T, char exp[])
{
	InitStack(S);
	Push(S, '#');
	InitStack(PTR);
	char *p = exp;
	char ch = *p;
	char c;
	while (!(GetTop(S) == '#' && ch == '#'))
	{
		BiTree t;
		if (!IN(ch, OP) && ch != '#' && ch != '(' && ch != ')')
			CrtNode(t, ch); // 建叶子结点并入栈
		else
		{
			switch (ch)
			{
			case '(':
				Push(S, ch);
				break;
			case ')':
				Pop(S, c);
				while (c != '(')
				{
					CrtSubtree(t, c); // 建二叉树并入栈
					Pop(S, c);
				}
				break;
			default:
				while (GetTop(S, c) && c != '(' && precede(c, ch))
				{
					CrtSubtree(t, c);
					Pop(S, c);
				}
				if (ch != '#')
					Push(S, ch);
				break;
			} // switch
		}	 //else
		if (ch != '#')
		{
			p++;
			ch = *p;
		}
	} // while
	Pop(PTR, T);
} //以表达式字符串的形式建立一颗表达式二叉树,(a+b)*c-d/e
void CrtNode(BiTree &T, char ch) //建立叶子节点
{
	T = (BiTNode *)malloc(sizeof(BiTNode));
	T->data = ch;
	T->lchild = T->rchild = NULL;
	Push(PTR, T);
}
void CrtSubtree(BiTree &T, char c) //建立子树
{
	BiTree lc, rc;
	T = (BiTNode *)malloc(sizeof(BiTNode));
	T->data = c;
	Pop(PTR, rc);
	T->rchild = rc;
	Pop(PTR, lc);
	T->lchild = lc;
	Push(PTR, T);
}

void PreOrder(BiTree T)
{ // 先序遍历二叉树
	if (T)
	{
		cout << T->data;	 // 访问结点
		PreOrder(T->lchild); // 遍历左子树
		PreOrder(T->rchild); // 遍历右子树
	}
}
void InOrder(BiTree T)
{ // 中序遍历二叉树
	if (T)
	{
		InOrder(T->lchild); // 遍历左子树
		cout << T->data;	// 访问结点
		InOrder(T->rchild); // 遍历右子树
	}
}
void PostOrder(BiTree T)
{ // 后序遍历二叉树
	if (T)
	{
		PostOrder(T->lchild); // 遍历左子树
		PostOrder(T->rchild); // 遍历右子树
		cout << T->data;	  // 访问结点
	}
}
Status IN(char ch, char OP[])
{ //看ch是否属于OP，如果是返回TRUE，否则返回ERROR
	for (int i = 0; OP[i] != '\0'; i++)
	{
		if (ch == OP[i])
			return TRUE;
	}
	return FALSE;
}
Status precede(char c, char ch)
{ //比较运算符c和ch的优先级,c高于ch返回true，否则返回FALSE

	int i = 0, j = 0;
	int pre[5][5]; //优先级矩阵， +号对应0，-号对应1，*号对应2，/号对应3,#号对应4
	pre[0][0] = 1, pre[0][1] = 1, pre[0][2] = 0, pre[0][3] = 0, pre[0][4] = 1;
	pre[1][0] = 1, pre[1][1] = 1, pre[1][2] = 0, pre[1][3] = 0, pre[1][4] = 1;
	pre[2][0] = 1, pre[2][1] = 1, pre[2][2] = 1, pre[2][3] = 1, pre[2][4] = 1;
	pre[3][0] = 1, pre[3][1] = 1, pre[3][2] = 1, pre[3][3] = 1, pre[3][4] = 1;
	pre[4][0] = 0, pre[4][1] = 0, pre[4][2] = 0, pre[4][3] = 0, pre[4][4] = 0; //p[4][4]=0代表#和#相遇时候结果是0，程序执行完毕了
	switch (c)
	{
	case '+':
		i = 0;
		break;
	case '-':
		i = 1;
		break;
	case '*':
		i = 2;
		break;
	case '/':
		i = 3;
		break;
	case '#':
		i = 4;
		break;
	}
	switch (ch)
	{
	case '+':
		j = 0;
		break;
	case '-':
		j = 1;
		break;
	case '*':
		j = 2;
		break;
	case '/':
		j = 3;
		break;
	case '#':
		j = 4;
		break;
	}
	return pre[i][j];
}