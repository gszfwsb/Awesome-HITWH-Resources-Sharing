#include <stdio.h>
#include <stdlib.h>
void swap(int *a,int *b)
{
    int t;
    t=*a;
    *a=*b;
    *b=t;
}

void quicksort(int *a,int left,int right)
{  if (left>=right) return;
   int mid=partition(a,left,right);
   quicksort(a,left,mid-1);
   quicksort(a,mid+1,right);

}

int partition(int *a,int left,int right)
{
    int l=left;
    int sub=l-1;
    int key=a[right];
    for (;l<right;l++)
    {
        if (a[l]<=key)
        {
            swap(&a[l],&a[++sub]);
        }
    }
    swap(&a[right],&a[++sub]);//寻找插入位置（中间）)！
    return sub;
}


int main()
{  int i,a[]={10,6,5,2,3,8,7,4,9,1};
   int n=sizeof(a)/sizeof(int);
   quicksort(a,0,n-1);
   printf("排序后的数组\n");
   for (i=0;i<n;i++)
    printf("%-4d",a[i]);
    return 0;
}
