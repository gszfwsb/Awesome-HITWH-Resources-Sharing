// 单链表就地逆置
#include <iostream>
using namespace std;
typedef int ElemType;
typedef struct LNode
{
	ElemType data;
	struct LNode *next;
} LNode, *LinkList;
void CreateList(LinkList &L, int n)
{
	//建立一个n个元素的线性表，头插法，最先录入的为表尾部，最后录入的为表头
	L = (LinkList)malloc(sizeof(LNode));
	L->next = NULL;
	for (LinkList p; n >= 1; n--)
	{
		p = (LinkList)malloc(sizeof(LNode));
		cout << "请输入第" << n << "个元素";
		cin >> p->data;
		p->next = L->next;
		L->next = p;
	}
}
void TraverseList(LinkList L)
{ //遍历
	LinkList p = L->next;
	while (p)
	{
		cout << p->data << " ";
		p = p->next;
	}
}
void ReverseList(LinkList &L)
{ //逆置
	//在此完善代码
	LinkList p = (LinkList)malloc(sizeof(LNode));
	p->next = NULL;
	LinkList pr;
	while (L->next)
	{
		p->data = L->next->data;
		pr = (LinkList)malloc(sizeof(LNode));
		pr->next = p;
		p = pr;
		L = L->next;
	}
	p->data = L->data;
	L = p;
}
int main()
{ //主函数
	LinkList L;
	CreateList(L, 5); //建立链表L,5个元素
	cout << "链表L=";
	TraverseList(L); //依次输出链表L

	cout << "\n"
		 << "L的逆置=";
	ReverseList(L);  //逆置链表L
	TraverseList(L); //依次输出链表L
	return 0;
}
