#include <bits/stdc++.h>
using namespace std;
int depth(char *pre,char *in,int num){
    if (!num) return 0;
    int i=0;
    for (i=0;i<num;i++){
        if (pre[0]==in[i]) break;
    }
    int left = depth(pre+1,in,i);
    int right = depth(pre+i+1,in+i+1,num-i-1);
    return max(left,right)+1;
}
int main(){
   int n;
   cin >> n;
   char pre[n],in[n];
   cin >> pre >> in;
   cout << depth(pre,in,n) <<endl;
   return 0;
}