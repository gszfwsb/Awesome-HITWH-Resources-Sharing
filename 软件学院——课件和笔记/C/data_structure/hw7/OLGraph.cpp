#include <cstdio>
#include "OLGraph.h"
#include <cstring>
#include <iostream>
using namespace std;
Status CreateDG(OLGraph &G)
 { /* 采用十字链表存储表示,构造有向图G。算法7.3 */
   int i,j,k;
   VertexType v1,v2;
   int IncInfo=0;
   char str[MAX_Info];
   struct ArcBox *p;

   printf("input vertex,bow,1/0 with comma:");
   scanf("%d,%d,%d",&G.vexnum,&G.arcnum,&IncInfo);
   printf("input%dvalue(<%dchar):\n",G.vexnum,MAX_VERTEX_NAME);
   for(i=0;i<G.vexnum;++i)
   { /* 构造表头向量 */
     scanf("%s",G.xlist[i].data); /* 输入顶点值 */
     G.xlist[i].firstin=NULL; /* 初始化指针 */
     G.xlist[i].firstout=NULL;
   }
   printf("input%dbow:\n",G.arcnum);
   for(k=0;k<G.arcnum;++k)
   { /* 输入各弧并构造十字链表 */
	 printf("input%dmessages:",k+1);
     scanf("%s%s",v1,v2);
     i=LocateVex(G,v1); /* 确定v1和v2在G中的位置 */
     j=LocateVex(G,v2);
     p=(ArcBox *)malloc(sizeof(ArcBox)); /* 产生弧结点(假定有足够空间) */
     p->tailvex=i; /* 对弧结点赋值 */
     p->headvex=j;
     p->hlink=G.xlist[j].firstin; /* 完成在入弧和出弧链表表头的插入 */
     p->tlink=G.xlist[i].firstout;
     G.xlist[j].firstin=G.xlist[i].firstout=p;
     if(IncInfo)
     { /* 若弧含有相关信息，则输入 */
       printf("input messages(<%dchars): ",MAX_Info);
       scanf("%s",str);
       p->info=(InfoType *)malloc((strlen(str)+1)*sizeof(InfoType));//+1是把'\0'的空间也要算上
       strcpy(p->info,str);
     }
     else /* 弧不含有相关信息 */
       p->info=NULL;
   }
   return OK;
 }
Status LocateVex(OLGraph G,VertexType u)
 { /* 返回顶点u在有向图G中的位置(序号),如不存在则返回-1 */
   int i;
   for(i=0;i<G.vexnum;++i) /* 用循环查找该结点 */
     if(!strcmp(G.xlist[i].data,u))
       return i;
   return -1;
 }
void Output(OLGraph G)
 { /* 输出有向图G */
   int i;
   ArcBox *p;
   printf("%dvertexs,%dbows:\n",G.vexnum,G.arcnum);
   for(i=0;i<G.vexnum;i++)
   {
     printf("vertexs%s| inbows: ",G.xlist[i].data);
     p=G.xlist[i].firstin;
     while(p)
     {
       printf("%s ",G.xlist[p->tailvex].data);
       p=p->hlink;
     }
     printf("        outbows: ");
     p=G.xlist[i].firstout;
     while(p)
     {
       printf("%s ",G.xlist[p->headvex].data);
       if(p->info) /* 该弧有相关信息 */
         printf("messages(%s)",p->info);
       p=p->tlink;
     }
     printf("\n");
   }
 }