#define OK 1
#define ERROR 0
#define TRUE 1
#define FALSE 0
#define MAX_Info 20
typedef int Status;
/* 无向图的邻接多重表存储表示 */
#define MAX_VERTEX_NUM 20//顶点数量
#define MAX_NAME 20 //顶点值是20个字符
#define MAX_INFO 20 //边信息的最大长度20字符
typedef enum{unvisited,visited}VisitIf;
typedef char  InfoType;
typedef char  VertexType[MAX_NAME];//顶点类型

 typedef struct EBox
 {
   VisitIf mark; /* 访问标记 */
   int ivex,jvex; /* 该边依附的两个顶点的位置 */
   struct EBox *ilink,*jlink; /* 分别指向依附这两个顶点的下一条边 */
   InfoType *info; /* 该边信息指针 */
 }EBox;
 typedef struct
 {
   VertexType data;
   EBox *firstedge; /* 指向第一条依附该顶点的边 */
 }VexBox;
 typedef struct
 {
   VexBox adjmulist[MAX_VERTEX_NUM];
   int vexnum,edgenum; /* 无向图的当前顶点数和边数 */
 }AMLGraph;
Status LocateVex(AMLGraph G,VertexType u); /* 初始条件: 无向图G存在,u和G中顶点有相同特征 */
                                           /* 操作结果: 若G中存在顶点u,则返回该顶点在无向图中位置;否则返回-1 */
void MarkUnvizited(AMLGraph G); /* 置边的访问标记为未被访问 */
Status CreateGraph(AMLGraph &G); /* 采用邻接多重表存储结构,构造无向图G */
void Output(AMLGraph G); /* 输出无向图的邻接多重表G */
