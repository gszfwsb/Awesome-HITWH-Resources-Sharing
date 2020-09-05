#include "stdio.h"
#include "MGraph.h"
#include <iostream>
int main(){
	MGraph G;
	CreateGraph( G );
	Output(G);
	printf("DFS:\n");
    DFSTraverse(G,visit);
	printf("BFS:\n");
    BFSTraverse(G,visit);
	if(G.kind==UDN){//计算无向网的最小生成树
		printf("PRIM u:\n");
		VertexType u;
		scanf("%c",&u);
		printf("edges\n");
		MiniSpanTree_PRIM(G,u);
	}
	if(G.kind==DN){//迪杰斯特拉计算有向网的最短路径
		printf("dijstra u:\n");
		VertexType u;
		scanf("%c",&u);
		PathMatrix P;
		ShortPathTable D;
		ShortestPath_DIJ( G,LocateVex(G,u),P,D);//调用迪杰斯特拉算法计算
		printf("outcome：\n");
		printf("the shortest distance：\n");
		for(int k=0;k<G.vexnum;k++)
			printf("D[%c-%c]= %d ",u,G.vexs[k],D[k]);
		printf("\npath：（if P[v][w]=TRUE,then w is a point from v0 to v）\n");
		printf("  ");
		for( int i=0;i<G.vexnum;i++)
			printf("%c ",G.vexs[i]);
		printf("\n");
		for(int m=0; m<G.vexnum;m++){
			printf("%c ",G.vexs[m]);
			for(int n=0; n<G.vexnum;n++)
				printf("%d ",P[m][n]);
			printf("\n");
		}

		printf("FLOYD\n");
		PathMatrix PF[MAX_VERTEX_NUM];
		DistancMatrix DF;
		ShortestPath_FLOYD( G,  PF, DF);
		printf("outcome\nshortest distance：\n  ");

		for(int t=0;t<G.vexnum;t++)
			printf("%c ",G.vexs[t]);
		printf("\n");
		for(int v=0;v<G.vexnum;v++){
			printf("%c ",G.vexs[v]);
			for(int w=0;w<G.vexnum;w++){
				if(DF[v][w] == INFINITY) 
					printf("^ ");//输出^表示无路可达
				else printf("%d ",DF[v][w]);
			}
		printf("\n");
		}

		printf("shortest path\n ");
		for(int x=0;x<G.vexnum;x++){
			printf("%cthe shortest path\n  ",G.vexs[x]);
			for( int i=0;i<G.vexnum;i++)
				printf("%c ",G.vexs[i]);
			printf("\n");
			for(int y=0;y<G.vexnum;y++ ){
				printf("%c ",G.vexs[y]);
				for(int z=0;z<G.vexnum;z++)
					printf("%d ",PF[x][y][z]);//从x到y的最短路径	
				printf("\n");
			}
		}
	}
	system("pause");
	return 0;
}