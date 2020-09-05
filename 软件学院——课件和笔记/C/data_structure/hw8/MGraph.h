#include "Queue.h"
#define OK 1
#define ERROR 0
#define TRUE 1
#define FALSE 0
typedef int Status;
typedef int Boolean;
//-----图的数组(邻接矩阵)存储表示-----
#define INFINITY INT_MAX //最大值 ∞
#define MAX_VERTEX_NUM 20//最大顶点个数
typedef char InfoType;
typedef int VRType;//顶点关系类型,即矩阵的元素类型
typedef char VertexType;//顶点类型
typedef enum{DG,DN,UDG,UDN}GraphKind;//{有向图，有向网，无向图，无向网}
typedef struct ArcCell { // 弧的定义
     VRType  adj;    // VRType是顶点关系类型。
             // 对无权图，用1或0表示相邻否；
             // 对带权图，则为权值类型。
     InfoType  *info;  // 该弧相关信息的指针
} ArcCell,AdjMatrix[MAX_VERTEX_NUM][MAX_VERTEX_NUM];
typedef struct { // 图的定义
     VertexType vexs[MAX_VERTEX_NUM];   // 顶点信息                  
     AdjMatrix  arcs;      // 弧的信息                     
     int vexnum, arcnum;   // 顶点数，弧数      
     GraphKind   kind;     // 图的种类标志             
  } MGraph;
int LocateVex(MGraph G, VertexType u);//若图中存在顶点u，则返回其在图中的位置，否则返回-1
Status CreateGraph( MGraph &G );// 采用数组(邻接矩阵)表示法，构造图G的框架。
Status CreateUDN(MGraph &G);// 采用数组（邻接矩阵）表示法，构造无向网G。
Status CreateUDG(MGraph &G);//采用数组（邻接矩阵）表示法，构造无向图
Status CreateDN(MGraph &G);// 采用数组（邻接矩阵）表示法，构造有向网G。
Status CreateDG(MGraph &G);  // 采用数组（邻接矩阵）表示法，构造有向图G。
void Output(MGraph G);//输出图G


void DFSTraverse(MGraph G,Status(*Visit)(VertexType)); /* 初始条件: 图G存在,Visit是顶点的应用函数。算法7.4 */
   /* 操作结果: 从第1个顶点起,深度优先遍历图G,并对每个顶点调用函数Visit */
   /*           一次且仅一次。一旦Visit()失败,则操作失败 */
void DFS(MGraph G,int v); /* 从第v个顶点出发递归地深度优先遍历图G。算法7.5 */
Status visit(VertexType v);
int FirstAdjVex(MGraph G,VertexType v); /* 初始条件: 图G存在,v是G中某个顶点 */
   /* 操作结果: 返回v的第一个邻接顶点的序号。若顶点在G中没有邻接顶点,则返回-1 */
 int NextAdjVex(MGraph G,VertexType v,VertexType w); /* 初始条件: 图G存在,v是G中某个顶点,w是v的邻接顶点 */
   /* 操作结果: 返回v的(相对于w的)下一个邻接顶点的序号, */
VertexType GetVex(MGraph G,int v); /* 初始条件: 图G存在，v是G中某个顶点的序号。操作结果: 返回v的值 */
void BFSTraverse(MGraph G,Status(*Visit)(VertexType)); /* 初始条件: 图G存在,Visit是顶点的应用函数。算法7.6 */
   /* 操作结果: 从第1个顶点起,按广度优先非递归遍历图G,并对每个顶点调用函数 */
   /*           Visit一次且仅一次。一旦Visit()失败,则操作失败。 */
   /*           使用辅助队列Q和访问标志数组visited */
typedef struct closedge {
	VertexType  adjvex;
	VRType     lowcost;
}CE[MAX_VERTEX_NUM];// 记录从顶点集U到V－U的代价最小的边的辅助数组定义
int minimum(CE SZ,MGraph G);//计算辅助数组中，代价最小的元素编号
void MiniSpanTree_PRIM(MGraph G, VertexType u);// 用普里姆算法从第u个顶点出发构造网G的最小生成树T，输出T的各条边。
  // 记录从顶点集U到V－U的代价最小的边的辅助数组定义：
  //  struct {
  //      VertexType  adjvex;
  //      VRType     lowcost;
  //  } closedge[MAX_VERTEX_NUM];

typedef VRType ShortPathTable[MAX_VERTEX_NUM];
typedef bool PathMatrix[MAX_VERTEX_NUM][MAX_VERTEX_NUM];
void ShortestPath_DIJ(MGraph G,int v0,PathMatrix &P,ShortPathTable &D);
 // 算法7.15
  // 用Dijkstra算法求有向网G的v0顶点到其余顶点v的最短路径P[v]
  // 及其带权长度D[v]。
  // 若P[v][w]为TRUE，则w是从v0到v当前求得最短路径上的顶点。
  // final[v]为TRUE当且仅当v∈S,即已经求得从v0到v的最短路径。

typedef VRType DistancMatrix[MAX_VERTEX_NUM][MAX_VERTEX_NUM];
void ShortestPath_FLOYD(MGraph G, PathMatrix P[], DistancMatrix &D);
  // 算法7.16
  // 用Floyd算法求有向网G中各对顶点v和w之间的最短路径P[v][w]及其
  // 带权长度D[v][w]。若P[v][w][u]为TRUE，则u是从v到w当前求得最
  // 短路径上的顶点。