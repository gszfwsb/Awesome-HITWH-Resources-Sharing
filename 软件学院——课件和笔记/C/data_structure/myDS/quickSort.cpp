#include <iostream>
using namespace std;

int partition(int a[], int low, int high)
{
    int temp = a[low];
    int pivotkey = a[low];
    while (low < high)
    {
        while (low < high && a[high] >= pivotkey)
        {
            high--;
        }
        a[low] = a[high];
        while (low < high && a[low] <= pivotkey)
        {
            low++;
        }
        a[high] = a[low];
    }
    a[low] = temp;
    return low;
}

void quickSort(int a[], int low, int high)
{
    if (low < high)
    {
        int pivotloc = partition(a, low, high);
        quickSort(a, low, pivotloc - 1);
        quickSort(a, pivotloc + 1, high);
    }
}


int main()
{
    int a[5] = {6, 453, 1, -56, 12};
    quickSort(a, 0, 4);
    for (int i = 0; i < 5; i++)
    {
        cout << a[i] << ' ';
    }
}