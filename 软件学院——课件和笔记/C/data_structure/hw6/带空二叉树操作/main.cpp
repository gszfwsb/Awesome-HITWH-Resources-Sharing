#include "bitree.h"
#include <iostream>
using namespace std;
int  main(){
	BiTree bt;//定义二叉树树bt
	cout<<"请先序输入一棵树，空树或空子树用^表示，如AB^C^^D^^"<<endl;
	CreateBiTree(bt);//先序建树  AB^C^^D^^
	cout<<"先序输出"<<endl;
	PreOrder(bt);//先序遍历
	cout<<"\n递归中序输出"<<endl;
	InOrder(bt);//递归中序遍历
	cout<<"\n非递归中序输出"<<endl;
	InOrder_I(bt);//非递归中序遍历
	cout<<"\n后序输出"<<endl;
	PostOrder(bt);//后序遍历
	cout<<"\n叶子节点的个数为：";
	int count=0;
	CountLeaf (bt,count);//统计叶子节点的个数
	cout<<count<<endl;
	cout<<"二叉树的深度为："<<Depth (bt);// 返回二叉树的深度
    cout<<endl;
	system("pause");
	return 0;
}