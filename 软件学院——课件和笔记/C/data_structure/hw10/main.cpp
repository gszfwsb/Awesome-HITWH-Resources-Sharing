#include "stdio.h"
#include "AMLGraph.h"
#include "stdlib.h"
int main(){
	AMLGraph G;
	CreateGraph( G );
	Output(G);
	system("pause");
	return 0;
}