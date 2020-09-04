#include<iostream>
#include<cstdlib>
#include<cstdio>
#include<cstring>
using namespace std;
const int maxN=128;
int g[maxN][maxN];
int d[maxN],ok[maxN],vis[maxN];
int n;
 
void dfs(int u)
{
	int v,cnt=0;
	ok[u]=1;
	vis[u]=1;
	for(v=1;v<=n;++v)
		if(g[u][v]==1&&d[v]>1){
			++cnt;
			if(cnt==3)
				return;
		}
	for(v=1;v<=n;++v)
		if(g[u][v]==1&&vis[v]==0){
			if(d[v]==1){
				ok[v]=1;
				continue;
			}
			else
				dfs(v);
		}		
}
int main()
{
	int cases=0;
	while(scanf("%d",&n)==1&&n){
		int m;
		memset(g,0,sizeof(g));memset(d,0,sizeof(d));memset(ok,0,sizeof(ok));memset(vis,0,sizeof(vis));
		scanf("%d",&m);
		for(int i=0;i<m;++i){
			int a,b;
			scanf("%d%d",&a,&b);
			g[a][b]=g[b][a]=1;
			++d[a];++d[b];
		}
        if(m!=n-1){
			printf("Graph %d is not a caterpillar.\n",++cases);
			continue;
        }
		int i,flag=0;
		for(i=1;i<=n;++i)
			if(d[i]>1)
				break;
		dfs(i);	
		for(i=1;i<=n;++i)
			if(ok[i]!=1)
				flag=1;
		if(flag==1)
			printf("Graph %d is not a caterpillar.\n",++cases);	
		else
			printf("Graph %d is a caterpillar.\n",++cases);
	}
} 