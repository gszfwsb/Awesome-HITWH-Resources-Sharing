#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <malloc.h>
#define ERROR -1
int *next; //声明一个next数组指针，来为后续动态开辟内存
void getNext(char *p, int *next)
{
    next[0] = -1;      //为了方便，next[0]我们设为-1
    int i = 0, j = -1; //初值
    int len = (int)strlen(p);
    while (i < len) //当i==len的时候退出循环
    {
        if (j == -1 || p[i] == p[j]) //如果j=-1或者自己和自己匹配成功（前后缀串）
        {
            next[++i] = ++j; //动态规划：等于上一位next值+1
        }
        else
            j = next[j]; //匹配失败就回溯到next值
    }
}
int KMP(char *t, char *p, int pos)
{
    int i = pos; //匹配串指针
    int j = 0;   //子串指针
    int lent = (int)strlen(t);
    int lenp = (int)strlen(p);
    while (i < lent && j < lenp) //当指针越界时退出循环
    {
        if (j == -1 || t[i] == p[j]) //如果j=-1或者匹配成功
        //同时平移一位继续比较
        {
            i++;
            j++;
        }
        //匹配失败，回溯到next值
        else
            j = next[j];
    }
    //完全匹配成功，返回下标位置
    if (j == lenp)
        return i - j;
    //返回失败
    else
        return ERROR;
}
int main()
{
    //给一个实例
    char *s1 = "abeacdabcdabc";
    char *s2 = "abcdabc";
    next = (int *)malloc(sizeof(int) * strlen(s2));
    getNext(s2, next);
    printf("the match position is No.%d\n", KMP(s1, s2, 1) + 1);
    free(next);
    return 0;
}