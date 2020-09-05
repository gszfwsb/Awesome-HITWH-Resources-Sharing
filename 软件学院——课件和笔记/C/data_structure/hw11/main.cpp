#include "ALGraph.h"
#include <iostream>
using namespace std;
int main(){
	ALGraph G;
	ALGraph InverseG;
	CreateGraph(G);//邻接表方式建图或网的
	if(G.kind==DG||G.kind==DN)//建立G的逆邻接表InverseG
		CreateInverseGraph(InverseG,G);
	cout<<"adjacency list is G\n";
	Output(G);
	if(G.kind==DG||G.kind==DN){//输出逆邻接表,拓扑排序
		cout<<"reverse-adjacency list isInverseG\n";
		Output(InverseG);

		printf("\nThe result of topological sort is\n");
		if(TopologicalSort(G)==0){
			printf("\nTopological sort is for directed acyclic graph or net\n");		
		}
		if(G.kind==DN){
			printf("\nThe critical path of directed network is\n");
			CriticalPath(G);
		}
	}
	system("pause");
	return 0;
}