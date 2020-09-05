#include <bits/stdc++.h>
using namespace std;

struct student
{
    int xuehao;
    char ia[7];
    int chengji;
} stu[100001];

bool cmp1(student a, student b)
{
    if (a.xuehao != b.xuehao)
        return a.xuehao < b.xuehao;
}

bool cmp2(student a, student b)
{
    if (strcmp(a.ia, b.ia) != 0)
        return strcmp(a.ia, b.ia) < 0;
    return a.xuehao < b.xuehao;
}

bool cmp3(student a, student b)
{
    if (a.chengji != b.chengji)
        return a.chengji < b.chengji;
    return a.xuehao < b.xuehao;
}

int Partition(student a[], int low, int high, bool (*compare)(student a, student b))
{
    student x = a[high];             //将输入数组的最后一个数作为主元，用它来对数组进行划分
    int i = low - 1;                 //i是最后一个小于主元的数的下标
    for (int j = low; j < high; j++) //遍历下标由low到high-1的数
    {
        if (compare(a[j], x)) //如果数小于主元的话就将i向前挪动一个位置，并且交换j和i所分别指向的数
        {
            student temp;
            i++;
            temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }
    //经历上面的循环之后下标为从low到i（包括i）的数就均为小于x的数了，现在将主元和i+1位置上面的数进行交换
    a[high] = a[i + 1];
    a[i + 1] = x;
    return i + 1;
}
void QuickSort(student a[], int low, int high, bool (*compare)(student a, student b))
{
    if (low < high)
    {
        int q = Partition(a, low, high, compare);
        QuickSort(a, low, q - 1, compare);
        QuickSort(a, q + 1, high, compare);
    }
}

int main()
{

    int i, n, c;
    scanf("%d %d", &n, &c);
    for (i = 0; i < n; i++)
    {
        scanf("%d", &stu[i].xuehao);
        scanf("%s", &stu[i].ia);
        scanf("%d", &stu[i].chengji);
    }
    if (c == 1)
    {
        QuickSort(stu, 0, n-1, cmp1);
    }
    if (c == 2)
    {
        QuickSort(stu, 0, n-1, cmp2);
    }
    if (c == 3)
    {
        QuickSort(stu, 0, n-1, cmp3);
    }
    for (i = 0; i < n; i++)
    {
        if (i != n - 1)
            printf("%06d %s %d\n", stu[i].xuehao, stu[i].ia, stu[i].chengji);
        else
            printf("%06d %s %d", stu[i].xuehao, stu[i].ia, stu[i].chengji);
    }
    return 0;
}
