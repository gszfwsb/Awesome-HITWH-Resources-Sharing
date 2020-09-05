#include "stdio.h"
#include "MGraph.h"
#include <iostream>
using namespace std;
Status visited[MAX_VERTEX_NUM];   /* 访问标志数组(全局量) */
Boolean (*VisitFunc)(VertexType); /* 函数变量 */
Status CreateGraph(MGraph &G)
{                                                                                                  // 算法7.1
                                                                                                   // 采用数组(邻接矩阵)表示法，构造图G。
  cout << "input the kind(0-digraph,1-directed network,2-undigraph,3-undirected network)" << endl; //原算法补充
                                                                                                   //scanf(&G.kind);  // 自定义输入函数，读入一个随机值
  scanf("%d", &G.kind);                                                                            //输入图的类型
  switch (G.kind)
  {
  case DG:
    return CreateDG(G); // 构造有向图G
  case DN:
    return CreateDN(G); // 构造有向网G
  case UDG:
    return CreateUDG(G); // 构造无向图G
  case UDN:
    return CreateUDN(G); // 构造无向网G，算法7.2
  default:
    return ERROR;
  }
} // CreateGraph

Status CreateDG(MGraph &G)
{
  // 采用数组（邻接矩阵）表示法，构造有向图G。
  int i, j, k;
  VertexType v1, v2;
  printf("G.vexnum :");
  scanf("%d", &G.vexnum);
  printf("G.arcnum :");
  scanf("%d", &G.arcnum);
  getchar(); /* 加上此句getchar()!!! 吃回车 */
             // scanf("%d,%d,%d",&G.vexnum, &G.arcnum, &IncInfo);
  printf("input messages(char):\n");
  for (i = 0; i < G.vexnum; i++)
  {
    printf("G.vexs[%d] : ", i);
    scanf("%c", &G.vexs[i]);
    getchar();
  }                              // 构造顶点向量
  for (i = 0; i < G.vexnum; ++i) // 初始化邻接矩阵
    for (j = 0; j < G.vexnum; ++j)
    {
      G.arcs[i][j].adj = 0; //{adj,info}
      G.arcs[i][j].info = NULL;
    }
  printf("input the vertex\n");
  for (k = 0; k < G.arcnum; ++k)
  { // 构造邻接矩阵
    printf("input arc %d:\n", k + 1);
    printf("arc from v1 (char) : ");
    scanf("%c", &v1);
    getchar();
    printf("arc to v2 (char) : ");
    scanf("%c", &v2);
    getchar();
    // printf("w (int) : " );   scanf("%d", &w); getchar();
    // 输入一条边依附的顶点及权值
    i = LocateVex(G, v1);
    j = LocateVex(G, v2);
    // 确定v1和v2在G中位置
    G.arcs[i][j].adj = 1; // 弧<v1,v2>的权值
    // if (IncInfo) scanf(G.arcs[i][j].info); // 输入弧含有相关信息
  }
  return OK;
} // CreateDG
Status CreateDN(MGraph &G)
{
  // 采用数组（邻接矩阵）表示法，构造有向网G。
  int i, j, k, w;
  VertexType v1, v2;
  printf("G.vexnum :");
  scanf("%d", &G.vexnum);
  printf("G.arcnum :");
  scanf("%d", &G.arcnum);
  getchar(); /* 加上此句getchar()!!! 吃回车 */
  // scanf("%d,%d,%d",&G.vexnum, &G.arcnum, &IncInfo);
  printf("input messages(char):\n");
  for (i = 0; i < G.vexnum; i++)
  {
    printf("G.vexs[%d] : ", i);
    scanf("%c", &G.vexs[i]);
    getchar();
  }                              // 构造顶点向量
  for (i = 0; i < G.vexnum; ++i) // 初始化邻接矩阵
    for (j = 0; j < G.vexnum; ++j)
    {
      G.arcs[i][j].adj = INFINITY; //{adj,info}
      G.arcs[i][j].info = NULL;
    }
  printf("input vertex and weight\n");
  for (k = 0; k < G.arcnum; ++k)
  { // 构造邻接矩阵
    printf("input arc %d:\n", k + 1);
    printf("arc from v1 (char) : ");
    scanf("%c", &v1);
    getchar();
    printf("arc to v2 (char) : ");
    scanf("%c", &v2);
    getchar();
    printf("w (int) : ");
    scanf("%d", &w);
    getchar();
    // 输入一条边依附的顶点及权值
    i = LocateVex(G, v1);
    j = LocateVex(G, v2);
    // 确定v1和v2在G中位置
    G.arcs[i][j].adj = w; // 弧<v1,v2>的权值
    // if (IncInfo) scanf(G.arcs[i][j].info); // 输入弧含有相关信息
  }
  return OK;
} // CreateDN

Status CreateUDG(MGraph &G)
{ //
  // 采用数组（邻接矩阵）表示法，构造无向图G。
  int i, j, k;
  VertexType v1, v2;
  printf("G.vexnum :");
  scanf("%d", &G.vexnum);
  printf("G.arcnum :");
  scanf("%d", &G.arcnum);
  getchar(); /*** 加上此句getchar()!!! 吃回车 ***/
  // scanf("%d,%d,%d",&G.vexnum, &G.arcnum, &IncInfo);
  printf("intput messages(char):\n");
  for (i = 0; i < G.vexnum; i++)
  {
    printf("G.vexs[%d] : ", i);
    scanf("%c", &G.vexs[i]);
    getchar();
  }                              // 构造顶点向量
  for (i = 0; i < G.vexnum; ++i) // 初始化邻接矩阵
    for (j = 0; j < G.vexnum; ++j)
    {
      G.arcs[i][j].adj = 0; //{adj,info}
      G.arcs[i][j].info = NULL;
    }
  printf("input vertex\n");
  for (k = 0; k < G.arcnum; ++k)
  { // 构造邻接矩阵
    printf("input arc %d:\n", k + 1);
    printf("arc from v1 (char) : ");
    scanf("%c", &v1);
    getchar();
    printf("arc to v2 (char) : ");
    scanf("%c", &v2);
    getchar();
    // printf("w (int) : " );   scanf("%d", &w); getchar();
    // 输入一条边依附的顶点及权值
    i = LocateVex(G, v1);
    j = LocateVex(G, v2);
    // 确定v1和v2在G中位置
    G.arcs[i][j].adj = 1; // 弧<v1,v2>的权值
    // if (IncInfo) scanf(G.arcs[i][j].info); // 输入弧含有相关信息
    G.arcs[j][i].adj = G.arcs[i][j].adj; // 置<v1,v2>的对称弧<v2,v1>
  }
  return OK;
} // CreateUDG
Status CreateUDN(MGraph &G)
{ //  算法 7.2
  // 采用数组（邻接矩阵）表示法，构造无向网G。
  int i, j, k, w;
  VertexType v1, v2;
  printf("G.vexnum :");
  scanf("%d", &G.vexnum);
  printf("G.arcnum :");
  scanf("%d", &G.arcnum);
  getchar(); /*** 加上此句getchar()!!! 吃回车 ***/
  // scanf("%d,%d,%d",&G.vexnum, &G.arcnum, &IncInfo);
  printf("input messages(char):\n");
  for (i = 0; i < G.vexnum; i++)
  {
    printf("G.vexs[%d] : ", i);
    scanf("%c", &G.vexs[i]);
    getchar();
  }                              // 构造顶点向量
  for (i = 0; i < G.vexnum; ++i) // 初始化邻接矩阵
    for (j = 0; j < G.vexnum; ++j)
    {
      G.arcs[i][j].adj = INFINITY; //{adj,info}
      G.arcs[i][j].info = NULL;
    }
  printf("input vertex and weight\n");
  for (k = 0; k < G.arcnum; ++k)
  { // 构造邻接矩阵
    // 输入每条边依附的顶点及权值
    printf("input arc %d:\n", k + 1);
    printf("arc from v1 (char) : ");
    scanf("%c", &v1);
    getchar();
    printf("arc to v2 (char) : ");
    scanf("%c", &v2);
    getchar();
    printf("weight (int) : ");
    scanf("%d", &w);
    getchar();

    i = LocateVex(G, v1);
    j = LocateVex(G, v2);
    // 确定v1和v2在G中位置
    G.arcs[i][j].adj = w; // 弧<v1,v2>的权值
    // if (IncInfo) scanf(G.arcs[i][j].info); // 输入弧含有相关信息
    G.arcs[j][i].adj = G.arcs[i][j].adj; // 置<v1,v2>的对称弧<v2,v1>
  }
  return OK;
} // CreateUDN
int LocateVex(MGraph G, VertexType u)
{ //若图中存在顶点u，则返回其在图中的位置，否则返回-1
  for (int i = 0; i < G.vexnum; i++)
  {
    if (u == G.vexs[i])
      return i;
  }
  return -1;
}
void Output(MGraph G)
{ //输出图G
  int i, j;
  switch (G.kind)
  {
  case DG:
    cout << "digrahG" << endl;
    break;
  case DN:
    cout << "direct networkG" << endl;
    break;
  case UDG:
    cout << "undigraphG" << endl;
    break;
  case UDN:
    cout << "undirect networkG" << endl;
    break;
  default:
    cout << "ERROR" << endl;
  }
  cout << "The MGraph G is:" << endl;
  cout << "   ";
  for (i = 0; i < G.vexnum; i++)
    cout << G.vexs[i] << "  ";
  cout << endl;
  for (i = 0; i < G.vexnum; i++)
    cout << "——";
  cout << endl;
  for (i = 0; i < G.vexnum; i++)
  {
    cout << G.vexs[i] << " |";
    for (j = 0; j < G.vexnum; j++)
    {
      if (G.arcs[i][j].adj == INFINITY)
        cout << "∞ ";
      else
        cout << G.arcs[i][j].adj << " ";
    }
    cout << endl;
  }
}
Status visit(VertexType v)
{
  printf("%c ", v);
  return OK;
}

void DFS(MGraph G, int v)
{ /* 从第v个顶点出发递归地深度优先遍历图G。算法7.5 */
  VertexType v1;
  int w;
  visited[v] = TRUE;    /* 设置访问标志为TRUE(已访问) */
  VisitFunc(G.vexs[v]); /* 访问第v个顶点 */
  v1 = GetVex(G, v);
  for (w = FirstAdjVex(G, v1); w >= 0; w = NextAdjVex(G, v1, GetVex(G, w)))
    if (!visited[w])
      DFS(G, w); /* 对v的尚未访问的序号为w的邻接顶点递归调用DFS */
}

void DFSTraverse(MGraph G, Status (*Visit)(VertexType))
{ /* 初始条件: 图G存在,Visit是顶点的应用函数。算法7.4 */
  /* 操作结果: 从第1个顶点起,深度优先遍历图G,并对每个顶点调用函数Visit */
  /*           一次且仅一次。一旦Visit()失败,则操作失败 */
  int v;
  VisitFunc = Visit; /* 使用全局变量VisitFunc,使DFS不必设函数指针参数 */
  for (v = 0; v < G.vexnum; v++)
    visited[v] = FALSE; /* 访问标志数组初始化(未被访问) */
  for (v = 0; v < G.vexnum; v++)
    if (!visited[v])
      DFS(G, v); /* 对尚未访问的顶点调用DFS */
  printf("\n");
}
VertexType GetVex(MGraph G, int v)
{ /* 初始条件: 图G存在，v是G中某个顶点的序号。操作结果: 返回v的值 */
  if (v >= G.vexnum || v < 0)
    exit(ERROR);
  return G.vexs[v];
}
int FirstAdjVex(MGraph G, VertexType v)
{ /* 初始条件: 图G存在,v是G中某个顶点 */
  /* 操作结果: 返回v的第一个邻接顶点的序号。若顶点在G中没有邻接顶点,则返回-1 */
  int i, j = 0, k;
  k = LocateVex(G, v);               /* k为顶点v在图G中的序号 */
  if (G.kind == DN || G.kind == UDN) /* 网 */
    j = INFINITY;
  for (i = 0; i < G.vexnum; i++)
    if (G.arcs[k][i].adj != j)
      return i;
  return -1;
}

int NextAdjVex(MGraph G, VertexType v, VertexType w)
{ /* 初始条件: 图G存在,v是G中某个顶点,w是v的邻接顶点 */
  /* 操作结果: 返回v的(相对于w的)下一个邻接顶点的序号, */
  /*           若w是v的最后一个邻接顶点,则返回-1 */
  int i, j = 0, k1, k2;
  k1 = LocateVex(G, v);              /* k1为顶点v在图G中的序号 */
  k2 = LocateVex(G, w);              /* k2为顶点w在图G中的序号 */
  if (G.kind == DN || G.kind == UDN) /* 网 */
    j = INFINITY;
  for (i = k2 + 1; i < G.vexnum; i++)
    if (G.arcs[k1][i].adj != j)
      return i;
  return -1;
}

void BFSTraverse(MGraph G, Status (*Visit)(VertexType))
{ /* 初始条件: 图G存在,Visit是顶点的应用函数。算法7.6 */
  /* 操作结果: 从第1个顶点起,按广度优先非递归遍历图G,并对每个顶点调用函数 */
  /*           Visit一次且仅一次。一旦Visit()失败,则操作失败。 */
  /*           使用辅助队列Q和访问标志数组visited */
  int v, u, w;
  VertexType u1;
  LinkQueue Q;
  for (v = 0; v < G.vexnum; v++)
    visited[v] = FALSE; /* 置初值 */
  InitQueue(Q);         /* 置空的辅助队列Q */
  for (v = 0; v < G.vexnum; v++)
    if (!visited[v]) /* v尚未访问 */
    {
      visited[v] = TRUE; /* 设置访问标志为TRUE(已访问) */
      Visit(G.vexs[v]);
      EnQueue(Q, v);         /* v入队列 */
      while (!QueueEmpty(Q)) /* 队列不空 */
      {
        DeQueue(Q, u); /* 队头元素出队并置为u */
        //strcpy(u1,*GetVex(G,u));
        u1 = GetVex(G, u);
        for (w = FirstAdjVex(G, u1); w >= 0; w = NextAdjVex(G, u1, GetVex(G, w)))
          if (!visited[w]) /* w为u的尚未访问的邻接顶点的序号 */
          {
            visited[w] = TRUE;
            Visit(G.vexs[w]);
            EnQueue(Q, w);
          }
      }
    }
  printf("\n");
}

int minimum(CE SZ, MGraph G) //计算辅助数组中，代价最小的元素编号
{                            /* 求closedge.lowcost的最小正值 */
  int i = 0, j, k, min;
  while (!SZ[i].lowcost)
    i++;
  min = SZ[i].lowcost; /* 第一个不为0的值 */
  k = i;
  for (j = i + 1; j < G.vexnum; j++)
    if (SZ[j].lowcost > 0)
      if (min > SZ[j].lowcost)
      {
        min = SZ[j].lowcost;
        k = j;
      }
  return k;
}

void MiniSpanTree_PRIM(MGraph G, VertexType u)
{ // 算法7.9
  // 用普里姆算法从第u个顶点出发构造网G的最小生成树T，输出T的各条边。
  // 记录从顶点集U到V－U的代价最小的边的辅助数组定义：
  //  struct {
  //      VertexType  adjvex;
  //      VRType     lowcost;
  //  } closedge[MAX_VERTEX_NUM];
  CE closedge;
  int i, j, k;
  k = LocateVex(G, u);
  for (j = 0; j < G.vexnum; ++j)
  { // 辅助数组初始化
    if (j != k)
    {
      closedge[j].adjvex = u;
      closedge[j].lowcost = G.arcs[k][j].adj;
    }
  }
  closedge[k].lowcost = 0; // 初始，U＝{u}
  for (i = 1; i < G.vexnum; ++i)
  {                           // 选择其余G.vexnum-1个顶点
    k = minimum(closedge, G); // 求出T的下一个结点：第k顶点
        // 此时closedge[k].lowcost =
        // MIN{ closedge[vi].lowcost | closedge[vi].lowcost>0, vi∈V-U }
    printf("%c--%c ", closedge[k].adjvex, G.vexs[k]); //输出生成树的边
    closedge[k].lowcost = 0;                          // 第k顶点并入U集
    for (j = 0; j < G.vexnum; ++j)
      if (G.arcs[k][j].adj < closedge[j].lowcost)
      {
        // 新顶点并入U后重新选择最小边
        // closedge[j] = { G.vexs[k], G.arcs[k][j].adj };
        closedge[j].adjvex = G.vexs[k];
        closedge[j].lowcost = G.arcs[k][j].adj;
      }
  }
} // MiniSpanTree

void ShortestPath_DIJ(MGraph G, int v0, PathMatrix &P, ShortPathTable &D)
{ // 算法7.15
  // 用Dijkstra算法求有向网G的v0顶点到其余顶点v的最短路径P[v]
  // 及其带权长度D[v]。
  // 若P[v][w]为TRUE，则w是从v0到v当前求得最短路径上的顶点。
  // final[v]为TRUE当且仅当v∈S,即已经求得从v0到v的最短路径。
  int i = 0, j, v, w, min;
  bool final[MAX_VERTEX_NUM];
  for (v = 0; v < G.vexnum; ++v)
  {
    final[v] = FALSE;
    D[v] = G.arcs[v0][v].adj;
    for (w = 0; w < G.vexnum; ++w)
      P[v][w] = FALSE; // 设空路径
    if (D[v] < INFINITY)
    {
      P[v][v0] = TRUE;
      P[v][v] = TRUE;
    } //v0到v有路可通。
      //目标是寻找V0到V的一条最短路，所以在v0到v肯定有路可通的情况下，先将P[v][v0],p[v][v]设置为true，即起点和终点置为true
      //因为不管v0到v有多少条路可走，肯定都要将上述两个值设置为true（只要在计算之前确认了v0-v有路(D[v]<INFINITY)，就可以这么做）
  }
  D[v0] = 0;
  final[v0] = TRUE; // 初始化，v0顶点属于S集
  //--- 开始主循环，每次求得v0到某个v顶点的最短路径，并加v到S集 ---
  for (i = 1; i < G.vexnum; ++i)
  {                 // 其余G.vexnum-1个顶点
    min = INFINITY; // 当前所知离v0顶点的最近距离
    for (w = 0; w < G.vexnum; ++w)
      if (!final[w]) // w顶点在V-S中
        if (D[w] < min)
        {
          v = w;
          min = D[w];
        }                          // w顶点离v0顶点更近，最后求得v是离v0最近的顶点
    final[v] = TRUE;               // 离v0顶点最近的v加入S集
    for (w = 0; w < G.vexnum; ++w) // 更新当前最短路径及距离
                                   //if (!final[w] && (min+G.arcs[v][w].adj)<D[w]) { //表明v0-经过v到w的路径长度比当前v0到w的路径长度要短
      if (!final[w] && ((double)min + G.arcs[v][w].adj) < D[w])
      { //表明v0-经过v到w的路径长度比当前v0到w的路径长度要短
        //这里将min++G.arcs[v][w].adj转换为doubule，防止G.arcs[v][w].adj 为INFINITY时候再加上一个min 出现整数溢出，导致结果为负的情况
        //if (!final[w] && G.arcs[v][w].adj!=INFINITY&&(min+G.arcs[v][w].adj<D[w])) {
        // 修改D[w]和P[w], w∈V-S
        D[w] = min + G.arcs[v][w].adj; //修正D[w]
        for (j = 0; j < G.vexnum; j++)
          P[w][j] = P[v][j]; //修正P[w],第v行赋值于第w行
                             //把v0到v的最短走法作为v0-w的最短走法的一部分赋值给v0-w的路径中，即p[w][0-vexnum]=p[v][0-vexnum])
        P[w][w] = TRUE;      // P[w] = P[v]+[w] ,在上一步基础上，修正路径的终点w的标志,即p[w][w]为TRUE
                             //（其中p[v]代表从v0到v的最短路径，上一步已经搞定，+[w]就是 让p[w][w]=TRUE）
      }                      //if
  }                          //for
} // ShortestPath_DIJ

void ShortestPath_FLOYD(MGraph G, PathMatrix P[], DistancMatrix &D)
{ //p是一个一维数组，同时每个元素又是一个二维数组，即三维数组
  // 算法7.16
  // 用Floyd算法求有向网G中各对顶点v和w之间的最短路径P[v][w]及其
  // 带权长度D[v][w]。若P[v][w][u]为TRUE，则u是从v到w当前求得最
  // 短路径上的顶点。

  int v, w, u, i;
  for (v = 0; v < G.vexnum; ++v) // 各对结点之间初始已知路径及距离
    for (w = 0; w < G.vexnum; ++w)
    {
      D[v][w] = G.arcs[v][w].adj; //初始化
      for (u = 0; u < G.vexnum; ++u)
        P[v][w][u] = FALSE;
      if (D[v][w] < INFINITY)
      {                                 //D[v][w] < INFINITY  表示 v到w有路可走
        P[v][w][v] = P[v][w][w] = TRUE; //P[v]中的v表示计算v到其他各个顶点的最短路径
                                        //p[v][w][v]  中的w表示计算v到w的最短路径 最后的v表示经过v，p[v][w][v]=true表示从v到达w的路径的起点为true
                                        //p[v][w][w]=true表示从v到达w的路径的终点为true
                                        //因为不管v到w有多少条路可走，肯定都要将上述两个值设置为true
      }                                 //if
    }                                   //for
                                        //下面是采用动态规划策略进行计算的过程，即在v和w的路径上动态加入u
  for (u = 0; u < G.vexnum; ++u)
    for (v = 0; v < G.vexnum; ++v)
      for (w = 0; w < G.vexnum; ++w)
        if ((double)D[v][u] + D[u][w] < D[v][w])
        {                              // 从v经u到w的一条路径更短,前面取double防止两个整数相加出现溢出
          D[v][w] = D[v][u] + D[u][w]; //修正距离
          for (i = 0; i < G.vexnum; ++i)
            P[v][w][i] = (P[v][u][i] || P[u][w][i]); //修正路径，或运算表示取从v到u的路径和从u到w的路径之和
        }                                            //if
} // ShortestPath_FLOYD
