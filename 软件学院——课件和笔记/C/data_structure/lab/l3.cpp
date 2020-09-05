#include <bits/stdc++.h>
using namespace std;
#define MAXN 105
bool connect[MAXN][MAXN];
bool visit[MAXN];

void initvisit()
{
    memset(visit, 0, MAXN);
}
void initvertex(int vertex, int edge)
{
    int v1, v2;
    for (int i = 0; i < edge; i++)
    {
        cin >> v1 >> v2;
        connect[v2 - 1][v1 - 1] = connect[v1 - 1][v2 - 1] = 1;
    }
}
int BFS(int v, int vertex)
{
    const int MAXSIZE = 10002;
    int queue[MAXSIZE];
    int head = -1, rear = -1;
    int count = 1, curlevel = 0, curlast = v, tail;
    visit[v] = 1;
    queue[++rear] = v; //enqueue
    while (head < rear)
    {
        int queuehead = queue[++head]; //dequeue
        for (int i = 0; i < vertex; i++)
        {
            if (connect[queuehead][i] && !visit[i])
            {
                visit[i] = 1;
                queue[++rear] = i; //enqueue
                count++;
                tail = i;
            }
        }
        if (queuehead == curlast)
        {
            curlevel++;
            curlast = tail;
        }
        if (curlevel == 6)
            break;
    }
    return count;
}

int main()
{
    int vertex, edge;
    cin >> vertex >> edge;
    initvertex(vertex, edge);
    double ratio;
    for (int v = 0; v < vertex; v++)
    {
        initvisit();
        cout << 123 << endl;
        int count = BFS(v, vertex);
        ratio = (double)count / vertex * 100;
        printf("%d: %.2lf%%\n", v + 1, ratio);
    }
    return 0;
}