#include <bits/stdc++.h>
using namespace std;
struct polyNode
{
    int coef;
    int expon;
    polyNode *next;
};
void attachNode(int c, int e, polyNode *&pRear)
{
    polyNode *p = new polyNode[sizeof(struct polyNode)];
    p->coef = c;
    p->expon = e;
    p->next = nullptr;
    pRear->next = p;
    pRear = p;
}

polyNode *createNode()
{
    polyNode *p=nullptr, *pRear=nullptr, *prep=nullptr;
    int c, e;
    p = new polyNode[sizeof(struct polyNode)];
    pRear = p;
    while (cin >> c >> e)
        attachNode(c, e, pRear);
    prep = p;
    p = p->next;
    delete prep;
    return p;
}

polyNode *differentiation(polyNode *node)
{
    polyNode *p = node, *prep = nullptr;
    while (p->next)
    {
        p->coef = p->coef * p->expon--;
        prep = p;
        p = p->next;
    }
    if (p->expon) //判断指数是不是0，也就是最后一项是不是常数项
    //如果不是常数项
    {
        p->coef = p->coef * p->expon--;
    }
    //如果是常数项
    else 
    {
        if (prep) //如果前一项还有，表示这个多项式不是只有一项，那么常数项直接丢弃
        {
            delete p;
            prep->next = nullptr;
        }
        else
            p->coef = 0; //处理只有一项的情况。这种情况不能丢弃
    }
    return node;
}

void printNode(polyNode *p)
{
    int flag = 0;
    while (p)
    {
        if (!flag)
            flag = 1;
        else
            cout << ' ';
        cout << p->coef << ' ' << p->expon;
        p = p->next;
    }
    printf("\n");
}

int main()
{
    polyNode * p;
    p = createNode();
    p = differentiation(p);
    printNode(p);
    return 0;
}

