#include <stdio.h>

#define MaxQSize 1000
#define ERROR -1
typedef struct
{
    int Customer[MaxQSize];
    int rear;
    int front;
} Queue;

void InitialQ(Queue *PtrQ)
{
    PtrQ->rear = PtrQ->front = 0;
}

int IsEmptyQ(Queue *PtrQ)
{
    return PtrQ->front == PtrQ->rear;
}

void AddQ(Queue *PtrQ, int Item) /* 将元素Item插入队列PtrQ中 */
{
    if ((PtrQ->rear + 1) % MaxQSize == PtrQ->front)
        return;
    PtrQ->Customer[PtrQ->rear] = Item;
    PtrQ->rear = (PtrQ->rear + 1) % MaxQSize;
}

int DeleteQ(Queue *PtrQ) /* 从队列中删除队头并返回  */
{
    if (IsEmptyQ(PtrQ))
        return ERROR;
    int Item = PtrQ->Customer[PtrQ->front];
    PtrQ->front = (PtrQ->front + 1) % MaxQSize;
    return Item;
}

int main()
{
    int N, i, cur, flag;
    Queue A, B;

    /* 初始化两个队列 */
    InitialQ(&A);
    InitialQ(&B);

    scanf("%d", &N);
    for (i = 0; i < N; i++)
    { /* 根据整数的奇偶性，将每个整数插入相应队列中 */
        scanf("%d", &cur);
        if (cur % 2)
            AddQ(&A, cur);
        else
            AddQ(&B, cur);
    }
    flag = 0; /* 标记第1个顾客尚未输出 */
    while (!IsEmptyQ(&A) && !IsEmptyQ(&B))
    { /*A和B两个队列都不空 */
        if (!flag)
        { /* 第1个顾客输出后无空格 */
            printf("%d", DeleteQ(&A));
            flag = 1;
        }
        else
            printf(" %d", DeleteQ(&A));
        if (!IsEmptyQ(&A))
            printf(" %d", DeleteQ(&A));
        printf(" %d", DeleteQ(&B));
    }
    while (!IsEmptyQ(&A)) /*A队列不空,B空 */
        if (!flag)
        { /* 第1个顾客输出后无空格 */
            printf("%d", DeleteQ(&A));
            flag = 1;
        }
        else
            printf(" %d", DeleteQ(&A));
    while (!IsEmptyQ(&B)) /*B队列不空,A空 */
        if (!flag)
        { /* 第1个顾客输出后无空格 */
            printf("%d", DeleteQ(&B));
            flag = 1;
        }
        else
            printf(" %d", DeleteQ(&B));
    printf("\n");
    return 0;
}
