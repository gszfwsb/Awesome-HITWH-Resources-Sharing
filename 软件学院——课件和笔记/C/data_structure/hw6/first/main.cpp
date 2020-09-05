#include "bitree.h"
#include <iostream>
using namespace std;
int main()
{
    BiTree bt;
    CreateBiTree(bt);
    cout << "�ȸ�����" << endl;
    PreOrder(bt);
    cout << "\n�и�����" << endl;
    InOrder(bt); 
    cout << "\n�������" << endl;
    PostOrder(bt);
    cout << endl;
    system("pause");
    return 0;
}