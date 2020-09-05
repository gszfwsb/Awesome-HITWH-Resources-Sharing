#define OK 1
#define ERROR 0
#define TRUE 1
#define FALSE 0
#define MAX_Info 20
typedef int Status;

/* 有向图的十字链表存储表示 */
#define MAX_VERTEX_NUM 20  //顶点数量
#define MAX_VERTEX_NAME 20 //顶点值是20个字符
typedef char InfoType;
typedef char VertexType[MAX_VERTEX_NAME]; //顶点类型
typedef struct ArcBox
{
  int tailvex, headvex;         /* 该弧的尾和头顶点的位置  A→B  A端为弧尾，B端为弧头*/
  struct ArcBox *hlink, *tlink; /* 分别为弧头相同和弧尾相同的弧的链域 */
  InfoType *info;               /* 该弧相关信息的指针(可无) */
} ArcBox;                       /* 弧结点 */
typedef struct
{
  VertexType data;
  ArcBox *firstin, *firstout; /* 分别指向该顶点第一条入弧和出弧 */
} VexNode;                    /* 顶点结点 */
typedef struct
{
  VexNode xlist[MAX_VERTEX_NUM]; /* 表头向量(数组) */
  int vexnum, arcnum;            /* 有向图的当前顶点数和弧数 */
} OLGraph;
Status CreateDG(OLGraph &G);               /* 采用十字链表存储表示,构造有向图G。算法7.3 */
Status LocateVex(OLGraph G, VertexType u); /* 返回顶点u在有向图G中的位置(序号),如不存在则返回-1 */
void Output(OLGraph G);
