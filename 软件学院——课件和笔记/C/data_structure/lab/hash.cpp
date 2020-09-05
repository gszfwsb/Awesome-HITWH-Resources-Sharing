#include <cstdio>
#include <cstring>
#include <cstdlib>
#define D 1000173169
#define M 262143
using namespace std;
struct HashNode
{
	char str[20];
	bool belong[110];
	HashNode *next;
} * HashMap[270000];

int n;
long long pow[20];
int countGraph[110][110];
int numWord[110];

void countPow()
{
	pow[0] = 1;
	for (int i = 1; i < 10; i++)
		pow[i] = ( pow[i - 1] <<5) % D;
}
int countHash(char str[110])
{
	long long res = 0;
	for (int i = 0; str[i]; i++)
		res = (res + (str[i] - 'a' + 1) * pow[i]) % D;
	return res;
}
void writeData(HashNode *p, char str[110], int FileNum)
{
	numWord[FileNum]++;
	strcpy(p->str, str);
	memset(p->belong, false, sizeof(p->belong));
	p->belong[FileNum] = true;
	p->next = NULL;
}
void insert(int HashNum, char str[110], int FileNum)
{
	int u = HashNum & M;
	HashNode *p, *node, *pr;
	if (HashMap[u] == NULL)
	{
		HashMap[u] = (HashNode *)malloc(sizeof(HashNode));
		writeData(HashMap[u], str, FileNum);
		return;
	}
	for (p = HashMap[u]; p; p = p->next)
	{
		pr = p;
		if (strcmp(p->str, str) == 0)
		{
			if (p->belong[FileNum])
				return;
			numWord[FileNum]++;
			for (int i = 1; i <= n; i++)
			{
				if (p->belong[i])
				{
					countGraph[i][FileNum]++;
					countGraph[FileNum][i]++;
				}
			}
			p->belong[FileNum] = true;
			return;
		}
	}
	node = (HashNode *)malloc(sizeof(HashNode));
	writeData(node, str, FileNum);
	pr->next = node;
}

void readData(int FileNum)
{
	char str[110], c = getchar();
	int pos = 0, HashNum;
	while (c != '#')
	{
		if (c >= 'a' && c <= 'z')
			str[pos++] = c;
		else if (c >= 'A' && c <= 'Z')
			str[pos++] = c - 'A' + 'a';
		else
		{
			if (pos >= 3)
			{
				str[pos] = str[10] = 0;
				HashNum = countHash(str);
				insert(HashNum, str, FileNum);
			}
			pos = 0;
		}
		c = getchar();
	}
}
int main()
{
	int m, u, v;
	scanf("%d", &n);
	memset(HashMap,sizeof(HashMap),NULL);
	countPow();
	for (int i = 1; i <= n; i++)
		readData(i);
	scanf("%d", &m);
	while (m--)
	{
		scanf("%d%d", &u, &v);
		printf("%.1lf%\n", 1.0 * countGraph[u][v] / (numWord[u] + numWord[v] - countGraph[u][v]) * 100);
	}
	return 0;
}
