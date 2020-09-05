#include "stack.h"
#define OK 1
#define ERROR 0
#define TRUE 1
#define FALSE 0
typedef int Status;
//图的邻接表存储结构
#define INFINITY INT_MAX //最大值 ∞
#define MAX_VERTEX_NUM 20//最大顶点个数
typedef int VRType;//顶点关系类型
typedef char  InfoType;
typedef char VertexType;//顶点类型
typedef enum{DG,DN,UDG,UDN}GraphKind;//{有向图，有向网，无向图，无向网}
typedef struct ArcNode {  //弧的结点结构
  int        adjvex;   // 该弧所指向的顶点的位置
  struct ArcNode  *nextarc; // 指向下一条弧的指针  
  VRType adj;  // 弧<v1,v2>的权值 这一条是照比教材后加的
  InfoType   *info;   // 该弧相关信息的指针
} ArcNode;
typedef struct VNode { //顶点的结点结构
  VertexType  data;   // 顶点信息
  ArcNode  *firstarc; // 指向第一条依附该顶点的弧
                   
  } VNode, AdjList[MAX_VERTEX_NUM];
typedef struct {  //图的结构定义
     AdjList  vertices;
     int      vexnum, arcnum; 
     int      kind;          // 图的种类标志
  } ALGraph;
Status CreateGraph( ALGraph &G );// 采用邻接表表示法，构造图G的框架。
Status CreateUDN(ALGraph &G);// 采用邻接表表示法，构造无向网G。
Status CreateUDG(ALGraph &G);//采用邻接表表示法，构造无向图。
Status CreateDN(ALGraph &G);// 采用邻接表表示法，构造有向网G。
Status CreateDG(ALGraph &G); // 采用邻接表表示法，构造有向图G。
Status CreateInverseGraph(ALGraph &IG,ALGraph G);//构造G的逆邻接表InverseG
int LocateVex(ALGraph G, VertexType u);//若图中存在顶点u，则返回其在图中的位置，否则返回-1
void Output(ALGraph G);//输出图G


Status TopologicalSort(ALGraph G);  // 算法7.12
  // 有向图G采用邻接表存储结构。
  // 若G无回路，则输出G的顶点的一个拓扑序列并返回OK，否则ERROR。

void FindInDegree(ALGraph G, char indegree[MAX_VERTEX_NUM]);   // 对各顶点求入度indegree[0..vernum-1]


Status TopologicalOrder(ALGraph G, Stack &T); // 算法7.13
  // 有向网G采用邻接表存储结构，求各顶点事件的最早发生时间ve(全局变量)。
  // T为拓扑序列定点栈，S为零入度顶点栈。
  // 若G无回路，则用栈T返回G的一个拓扑序列，且函数值为OK，否则为ERROR。

Status CriticalPath(ALGraph G); // 算法7.14
  // G为有向网，输出G的各项关键活动。