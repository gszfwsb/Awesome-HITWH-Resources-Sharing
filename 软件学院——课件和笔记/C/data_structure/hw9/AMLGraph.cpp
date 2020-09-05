#include "stdio.h"
#include "stdlib.h"
#include "string.h"
#include "AMLGraph.h"
Status LocateVex(AMLGraph G,VertexType u)
 { /* 初始条件: 无向图G存在,u和G中顶点有相同特征 */
   /* 操作结果: 若G中存在顶点u,则返回该顶点在无向图中位置;否则返回-1 */
   int i;
   for(i=0;i<G.vexnum;++i)
     if(strcmp(u,G.adjmulist[i].data)==0)
       return i;
   return -1;
 }
Status CreateGraph(AMLGraph &G)
 { /* 采用邻接多重表存储结构,构造无向图G */
   int i,j,k,l,IncInfo;
   char s[MAX_INFO];
   VertexType va,vb;
   EBox *p;
   printf("请输入无向图G的顶点数,边数,边是否含其它信息(是:1，否:0): ");
   scanf("%d,%d,%d",&G.vexnum,&G.edgenum,&IncInfo);
   printf("请输入%d个顶点的值(<%d个字符):\n",G.vexnum,MAX_NAME);
   for(i=0;i<G.vexnum;++i) /* 构造顶点向量 */
   {
     scanf("%s",G.adjmulist[i].data);
     G.adjmulist[i].firstedge=NULL;
   }
   printf("请顺序输入每条边的两个端点(以空格作为间隔):\n");
   for(k=0;k<G.edgenum;++k) /* 构造表结点链表 */
   {
     scanf("%s%s%*c",va,vb); /* %*c吃掉回车符 */
     i=LocateVex(G,va); /* 一端 */
     j=LocateVex(G,vb); /* 另一端 */
     p=(EBox*)malloc(sizeof(EBox));
     p->mark=unvisited; /* 设初值 */
     p->ivex=i;
     p->jvex=j;
     p->info=NULL;
     p->ilink=G.adjmulist[i].firstedge; /* 插在表头 */
     G.adjmulist[i].firstedge=p;
     p->jlink=G.adjmulist[j].firstedge; /* 插在表头 */
     G.adjmulist[j].firstedge=p;
     if(IncInfo) /* 边有相关信息 */
     {
       printf("请输入该弧的相关信息(<%d个字符)：",MAX_INFO);
       gets(s);
       l=strlen(s);
       if(l)
       {
         p->info=(char*)malloc((l+1)*sizeof(char));
         strcpy(p->info,s);
       }
     }
   }
   return OK;
 }
void MarkUnvizited(AMLGraph G){ /* 置边的访问标记为未被访问 */
   int i;
   EBox *p;
   for(i=0;i<G.vexnum;i++)
   {
     p=G.adjmulist[i].firstedge;
     while(p)
     {
       p->mark=unvisited;
       if(p->ivex==i)/* 边的i端与该顶点有关 */
         p=p->ilink;
       else
         p=p->jlink;/* 边的j端与该顶点有关 */
     }
   }
 }
void Output(AMLGraph G)
 { /* 输出无向图的邻接多重表G */
   int i;
   EBox *p;
   MarkUnvizited(G); /* 置边的访问标记为未被访问 */
   printf("%d个顶点：\n",G.vexnum);
   for(i=0;i<G.vexnum;++i)
     printf("%s ",G.adjmulist[i].data);
   printf("\n%d条边:\n",G.edgenum);
   for(i=0;i<G.vexnum;i++)
   {
     p=G.adjmulist[i].firstedge;
     while(p)
       if(p->ivex==i) /* 边的i端与该顶点有关 */
       {
         if(!p->mark) /* 只输出一次 */
         {
           printf("%s－%s ",G.adjmulist[i].data,G.adjmulist[p->jvex].data);
           p->mark=visited;
           if(p->info) /* 输出附带信息 */
             printf("相关信息: %s ",p->info);
         }
         p=p->ilink;
       }
       else /* 边的j端与该顶点有关 */
       {
         if(!p->mark) /* 只输出一次 */
         {
           printf("%s－%s ",G.adjmulist[p->ivex].data,G.adjmulist[i].data);
           p->mark=visited;
           if(p->info) /* 输出附带信息 */
             printf("相关信息: %s ",p->info);
         }
         p=p->jlink;
       }
     printf("\n");
   }
 }