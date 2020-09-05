#include<iostream>
using namespace std;
 typedef struct CLinkList
 {
int data;
 struct CLinkList *next;
 }node;
 int main()
 {
     node *L,*r,*s;
       L = new node;
        r =L;
        int n = 41,i;
        int k = 3;
         for(i = 1;i<=n;i++)       //尾插法建立链表
          {
        s = new node;
          s->data = i;
            r->next = s;
             r= s;    }
             r->next =L->next;       //让最后一个结点指向第一个有数据结点
             node *p;    p = L->next;    delete L;   //删除第一个空的结点 ///模拟解决约瑟夫问题
               while(p->next != p)
                    //判断条件：因为最后肯定剩下一个人， 循环链表的最后一个数据的next还是他本身
    {        for(i = 1;i<k-1;i++)
      {            p = p->next;                         //每k个数死一个人
       }        cout<<p->next->data<<"->";        p->next = p->next->next;
            //将该节点从链表上删除。
             p = p->next;    }
              cout<<p->data<<endl;
              return 0;}
