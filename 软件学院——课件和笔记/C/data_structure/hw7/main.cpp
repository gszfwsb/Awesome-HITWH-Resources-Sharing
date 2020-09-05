#include <stdio.h>
#include <stdlib.h>
#include "OLGraph.h"
int main(){
	OLGraph G;
	CreateDG( G );
	Output(G);
	system("pause");
	return 0;
}