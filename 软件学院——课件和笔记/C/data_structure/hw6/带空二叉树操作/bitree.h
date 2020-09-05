#include "stack.h"
#define OK 1
#define ERROR 0
#define TRUE 1
#define FALSE 0
#define OVERFLOW -1
typedef int Status;
/*typedef char TElemType;
typedef struct BiTNode { // 结点结构
    TElemType      data;
    struct BiTNode  *lchild, *rchild; // 左右孩子指针                                  
} BiTNode, *BiTree;*/  //此处单独形成了一个存储结构头文件storage_tree.h，供bitree.h和stack.h共用
Status CreateBiTree(BiTree &T);//以字符串的形式  根 左子树 右子树   定义一棵二叉树,空树用^表示
void PreOrder (BiTree T);//先根遍历
void InOrder (BiTree T);//中根遍历
void PostOrder (BiTree T);//后根遍历
void visit(TElemType& e);//访问函数
void CountLeaf (BiTree T,  int& count);//统计叶子节点的个数
int Depth (BiTree T );// 返回二叉树的深度
BiTNode *GoFarLeft(BiTree T, Stack &S);//找到树T的最左下结点
void InOrder_I(BiTree T);//非递归中序遍历