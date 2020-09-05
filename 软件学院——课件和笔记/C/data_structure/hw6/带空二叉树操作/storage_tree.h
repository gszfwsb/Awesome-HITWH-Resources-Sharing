typedef char TElemType;
typedef struct BiTNode { // 结点结构
    TElemType      data;
    struct BiTNode  *lchild, *rchild; // 左右孩子指针                                  
} BiTNode, *BiTree;