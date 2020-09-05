#define TRUE 1
#define FALSE 0
#define OK 1
typedef int Status;
typedef char TElemType; //数据元素类型
typedef struct BiTNode
{ // 结点结构
    TElemType data;
    struct BiTNode *lchild, *rchild; // 左右孩子指针
} BiTNode, *BiTree;                  //树结构，结点结构
Status CreateBiTree(BiTree &T);      //根据输入的先缀表达式建树 表达式  -×+ a b c / d e
void PreOrder(BiTree T);             //先根遍历
void InOrder(BiTree T);              //中根遍历
void PostOrder(BiTree T);            //后根遍历
