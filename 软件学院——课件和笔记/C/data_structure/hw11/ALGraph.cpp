#include "stdio.h"
#include "ALGraph.h"
#include <iostream>
using namespace std;
Status CreateGraph( ALGraph &G ) {  // 算法7.1
    // 采用数组(邻接矩阵)表示法，构造图G。
	cout<<"input the type (0-digraph,1-directed net,2-undirected graph,3-undirected net)"<<endl;//元算法补充
    //scanf(&G.kind);  // 自定义输入函数，读入一个随机值
	scanf("%d",&G.kind);//输入图的类型
	switch (G.kind) {
        case  DG: return CreateDG(G);   //构造有向图G
        case  DN: return CreateDN(G);   // 构造有向网G
        case UDG: return CreateUDG(G);  // 构造无向图G
        case UDN: return CreateUDN(G);  // 构造无向网G
        default : return ERROR;
    }
} // CreateGraph
Status CreateDG(ALGraph &G) {// 
  // 采用邻接表表示法，构造有向图G。
  int i,j,k; 
  VertexType  v1,v2; 
  printf("G.vexnum:" );  scanf("%d",&G.vexnum);
  printf("G.arcnum:");   scanf("%d",&G.arcnum);   
  getchar();  /*** 加上此句getchar()!!! 吃回车 ***/
  // scanf("%d,%d,%d",&G.vexnum, &G.arcnum, &IncInfo);      
  for (i=0; i<G.vexnum; i++ ) { //录入各个顶点，并初始化顶点的第一条弧或边是指针为空
    printf("G.vertices[%d].data : ",i);
    scanf("%c",&G.vertices[i].data); 
    getchar();
	G.vertices[i].firstarc=NULL;
  } // 初始化图

  for (k=0; k<G.arcnum; ++k ) {  // 构造邻接表 
	  // 输入一条边依附的顶点及权值
    printf("v1 (char) : ");  scanf("%c", &v1);getchar();
    printf("v2 (char) : ");  scanf("%c", &v2);getchar();

   
    i = LocateVex(G, v1);  j = LocateVex(G, v2); // 确定v1和v2在G中位置
     //建立<i,j>    
    ArcNode* s= (ArcNode*)malloc(sizeof(ArcNode));
	s->adjvex = j;
	s->nextarc =G.vertices[i].firstarc;
	G.vertices[i].firstarc = s;
    // if (IncInfo) scanf(G.arcs[i][j].info); // 输入弧含有相关信息  
  }
  return OK;
} // CreateDG
Status CreateDN(ALGraph &G) {// 
  // 采用邻接表表示法，构造有向网G。
  int i,j,k,w; 
  VertexType  v1,v2; 
  printf("G.vexnum:" );  scanf("%d",&G.vexnum);
  printf("G.arcnum:");   scanf("%d",&G.arcnum);   
  getchar();  /*** 加上此句getchar()!!! 吃回车 ***/
  // scanf("%d,%d,%d",&G.vexnum, &G.arcnum, &IncInfo);      
  for (i=0; i<G.vexnum; i++ ) { //录入各个顶点，并初始化顶点的第一条弧或边是指针为空
    printf("G.vertices[%d].data : ",i);
    scanf("%c",&G.vertices[i].data); 
    getchar();
	G.vertices[i].firstarc=NULL;
  } // 初始化图

  for (k=0; k<G.arcnum; ++k ) {  // 构造邻接表 
	  // 输入一条边依附的顶点及权值
    printf("v1 (char): ");  scanf("%c", &v1);getchar();
    printf("v2 (char): ");  scanf("%c", &v2);getchar();
    printf("w (int): " );   scanf("%d", &w); getchar();   
   
    i = LocateVex(G, v1);  j = LocateVex(G, v2); // 确定v1和v2在G中位置
     //建立<i,j,w>    
    ArcNode* s= (ArcNode*)malloc(sizeof(ArcNode));
	s->adjvex = j;
	s->adj = w;
	s->nextarc =G.vertices[i].firstarc;
	G.vertices[i].firstarc = s;
    // if (IncInfo) scanf(G.arcs[i][j].info); // 输入弧含有相关信息
   
  }
  return OK;
} // CreateDN
Status CreateUDG(ALGraph &G) {// 
  // 采用邻接表表示法，构造无向图G。
  int i,j,k; 
  VertexType  v1,v2; 
  printf("G.vexnum :" );  scanf("%d",&G.vexnum);
  printf("G.arcnum :");   scanf("%d",&G.arcnum);   
  getchar();  /*** 加上此句getchar()!!! 吃回车 ***/
  // scanf("%d,%d,%d",&G.vexnum, &G.arcnum, &IncInfo);      
  for (i=0; i<G.vexnum; i++ ) { //录入各个顶点，并初始化顶点的第一条弧或边是指针为空
    printf("G.vertices[%d].data : ",i);
    scanf("%c",&G.vertices[i].data); 
    getchar();
	G.vertices[i].firstarc=NULL;
  } // 初始化图

  for (k=0; k<G.arcnum; ++k ) {  // 构造邻接表 
	  // 输入一条边依附的顶点及权值
    printf("v1 (char) : ");  scanf("%c", &v1);getchar();
    printf("v2 (char) : ");  scanf("%c", &v2);getchar();

   
    i = LocateVex(G, v1);  j = LocateVex(G, v2); // 确定v1和v2在G中位置
     //建立<i,j>    
    ArcNode* s= (ArcNode*)malloc(sizeof(ArcNode));
	s->adjvex = j;
	s->nextarc =G.vertices[i].firstarc;
	G.vertices[i].firstarc = s;

	 //建立<j,i> 
    ArcNode* t= (ArcNode*)malloc(sizeof(ArcNode));
	t->adjvex = i;
	t->nextarc =G.vertices[j].firstarc;
	G.vertices[j].firstarc = t;
    // if (IncInfo) scanf(G.arcs[i][j].info); // 输入弧含有相关信息  
  }
  return OK;
} // CreateUDG
Status CreateUDN(ALGraph &G) {// 
  // 采用邻接表表示法，构造无向网G。
  int i,j,k,w; 
  VertexType  v1,v2; 
  printf("G.vexnum :" );  scanf("%d",&G.vexnum);
  printf("G.arcnum :");   scanf("%d",&G.arcnum);   
  getchar();  /*** 加上此句getchar()!!! 吃回车 ***/
  // scanf("%d,%d,%d",&G.vexnum, &G.arcnum, &IncInfo);      
  for (i=0; i<G.vexnum; i++ ) { //录入各个顶点，并初始化顶点的第一条弧或边是指针为空
    printf("G.vertices[%d].data : ",i);
    scanf("%c",&G.vertices[i].data); 
    getchar();
	G.vertices[i].firstarc=NULL;
  } // 初始化图

  for (k=0; k<G.arcnum; ++k ) {  // 构造邻接表 
	  // 输入一条边依附的顶点及权值
    printf("v1 (char) : ");  scanf("%c", &v1);getchar();
    printf("v2 (char) : ");  scanf("%c", &v2);getchar();
    printf("w (int) : " );   scanf("%d", &w); getchar();   
   
    i = LocateVex(G, v1);  j = LocateVex(G, v2); // 确定v1和v2在G中位置
     //建立<i,j,w>    
    ArcNode* s= (ArcNode*)malloc(sizeof(ArcNode));
	s->adjvex = j;
	s->adj = w;
	s->nextarc =G.vertices[i].firstarc;
	G.vertices[i].firstarc = s;

	 //建立<j,i,w> 
    ArcNode* t= (ArcNode*)malloc(sizeof(ArcNode));
	t->adjvex = i;
	t->adj = w;
	t->nextarc =G.vertices[j].firstarc;
	G.vertices[j].firstarc = t;

    // if (IncInfo) scanf(G.arcs[i][j].info); // 输入弧含有相关信息
   
  }
  return OK;
} // CreateUDN
Status CreateInverseGraph(ALGraph &IG,ALGraph G){//构造G的逆邻接表InverseG简写为IG
	int i,j;
	IG.kind=G.kind;IG.vexnum=G.vexnum;IG.arcnum=G.arcnum;
	for(i=0;i<G.vexnum;i++){//初始化IG
		IG.vertices[i].data=G.vertices[i].data;
		IG.vertices[i].firstarc=NULL;
	}
	for(i=0;i<G.vexnum;i++){//根据G求IG
		ArcNode *p = G.vertices[i].firstarc;
		while(p){
			ArcNode *s=(ArcNode *)malloc(sizeof(ArcNode));
			s->adj = p->adj;
			s->adjvex =i;
			s->info=p->info;
			s->nextarc = IG.vertices[p->adjvex].firstarc;
			IG.vertices[p->adjvex].firstarc = s;
			p=p->nextarc;
		}
	}
	return OK;
}
int LocateVex(ALGraph G, VertexType u){//若图中存在顶点u，则返回其在图中的位置，否则返回-1
	for(int i=0;i<G.vexnum;i++){
		if(u == G.vertices[i].data) return i;
	}
	return -1;
}

void Output(ALGraph G){//输出图G
	int i;
	switch (G.kind) {
        case  DG: cout<<"digraph"<<endl;break;
        case  DN: cout<<"directed net"<<endl;break;
        case UDG: cout<<"undirected graph"<<endl;break;
        case UDN: cout<<"undirected net"<<endl;break;
        default : cout<<"ERROR"<<endl;
    }
	for( i=0;i<G.vexnum;i++){
		cout<<i <<"|"<<G.vertices[i].data<<"|->";
		ArcNode *p = G.vertices[i].firstarc;
		while(p){
			if(G.kind==DN||G.kind==UDN)//有向网，无向网要输出权重p->adj
				cout<<"|"<<p->adjvex<<","<<G.vertices[p->adjvex].data<<","<<p->adj<<"|->";
			else//有向图，无向图不需要输出p->adj
				cout<<"|"<<p->adjvex<<","<<G.vertices[p->adjvex].data<<"|->";
			p=p->nextarc;
		}
		cout<<"NULL"<<endl;
	}
}

Status TopologicalSort(ALGraph G) {  // 算法7.12
  // 有向图G采用邻接表存储结构。
  // 若G无回路，则输出G的顶点的一个拓扑序列并返回OK，否则ERROR。
  SqStack S;
  int count,k,i;
  ArcNode *p;
  char indegree[MAX_VERTEX_NUM];
  FindInDegree(G, indegree);   // 对各顶点求入度indegree[0..vernum-1]
  InitStack(S);
  for (i=0; i<G.vexnum; ++i)       // 建零入度顶点栈S
    if (!indegree[i]) Push(S, i);  // 入度为0者进栈
  count = 0;                       // 对输出顶点计数
  while (!StackEmpty(S)) {
    Pop(S, i); 
    printf("%d(%c) ",i, G.vertices[i].data);  ++count;  // 输出i号顶点并计数
    for (p=G.vertices[i].firstarc;  p;  p=p->nextarc) {
      k = p->adjvex;               // 对i号顶点的每个邻接点的入度减1
      if (!(--indegree[k])) Push(S, k);  // 若入度减为0，则入栈
    }
  }
  if (count<G.vexnum) return ERROR;      // 该有向图有回路
  else return OK;
} // TopologicalSort

void FindInDegree(ALGraph G, char indegree[MAX_VERTEX_NUM]){   // 对各顶点求入度indegree[0..vernum-1]
	ALGraph InvG;//G的逆邻接表
	CreateInverseGraph(InvG,G);//计算G的逆邻接表InvG
	for(int i=0;i<InvG.vexnum;i++){
		indegree[i]=0;//初始化第i个结点的入度为0
		ArcNode* p= InvG.vertices[i].firstarc;
		while(p){//根据逆邻接表计算各点入度
			indegree[i]++;
			p=p->nextarc;
		}
	}
}
int ve[MAX_VERTEX_NUM];//事件的最早发生时间
int vl[MAX_VERTEX_NUM];//事件的最迟发生时间
Status TopologicalOrder(ALGraph G, Stack &T) {  // 算法7.13
  // 有向网G采用邻接表存储结构，求各顶点事件的最早发生时间ve(全局变量)。
  // T为拓扑序列定点栈，S为零入度顶点栈。
  // 若G无回路，则用栈T返回G的一个拓扑序列，且函数值为OK，否则为ERROR。
  Stack S;int count=0,k;
  char indegree[40];
  ArcNode *p;
  InitStack(S);
  FindInDegree(G, indegree);  // 对各顶点求入度indegree[0..vernum-1]
  int j;
  for (j=0; j<G.vexnum; ++j)     // 建零入度顶点栈S
    if (indegree[j]==0) Push(S, j);  // 入度为0者进栈
  InitStack(T);//建拓扑序列顶点栈T
  count = 0;  
  for(int i=0; i<G.vexnum; i++) ve[i] = 0;  // 初始化
  while (!StackEmpty(S)) {
    Pop(S, j);  Push(T, j);  ++count;       // j号顶点入T栈并计数
    for (p=G.vertices[j].firstarc;  p;  p=p->nextarc) {
      k = p->adjvex;            // 对j号顶点的每个邻接点的入度减1
      if (--indegree[k] == 0) Push(S, k);   // 若入度减为0，则入栈
      //if (ve[j]+p->info > ve[k])  ve[k] = ve[j]+p->info;教材原来如此，用info存放权值，我改为如下，因为在图结构中增加了权值属性
	if (ve[j]+p->adj > ve[k])  ve[k] = ve[j]+p->adj;
    }//for  *(p->info)=dut(<j,k>)
  }//while
  if (count<G.vexnum) return ERROR;  // 该有向网有回路
  else return OK;
} // TopologicalOrder

Status CriticalPath(ALGraph G) {  // 算法7.14
  // G为有向网，输出G的各项关键活动。
  Stack T;
  int a,j,k,el,ee,dut;
  char tag;
  ArcNode *p;
  if (!TopologicalOrder(G, T)) return ERROR;
  for(a=0; a<G.vexnum; a++)
    vl[a] = ve[G.vexnum-1];    // 初始化顶点事件的最迟发生时间
  while (!StackEmpty(T))       // 按拓扑逆序求各顶点的vl值
    for (Pop(T, j), p=G.vertices[j].firstarc;  p;  p=p->nextarc) {
     // k=p->adjvex;  dut=p->info;     //dut<j,k>教材中如此，我改为如下
      k=p->adjvex;  dut=p->adj;     //dut<j,k>
      if (vl[k]-dut < vl[j]) vl[j] = vl[k]-dut;
    }
  for (j=0; j<G.vexnum; ++j)            // 求ee,el和关键活动
    for (p=G.vertices[j].firstarc;  p;  p=p->nextarc) {
      //k=p->adjvex;dut=p->info;   //教材中如此，我改为如下：
      k=p->adjvex;dut=p->adj;   
      ee = ve[j];  el = vl[k]-dut;
      tag = (ee==el) ? '*' : ' ';//tag=*表示关键活动，等于空表示不是关键活动
	  if(tag == '*')//输出关键活动
		  printf("%d(%c)---%d(%c)(dut=%d,ee=%d,el=%d,tag=%c)\n",j,G.vertices[j].data, k,G.vertices[k].data, dut, ee, el, tag);   // 输出关键活动
    }
  return OK;
} // CriticalPath