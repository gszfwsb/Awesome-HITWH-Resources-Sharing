#include <iostream>
#include <cstring>
#define ERROR -1
using namespace std;
typedef char sub_str[255]; //定义字符串类型sub_str
int Index(sub_str S, sub_str T, int pos = 0) //默认从第一位开始匹配，所以pos设默认值=0
{
  int i = pos;
  int j = 0; //下标从0开始
  while (i < strlen(S) && j < strlen(T))
  { //当指针没有匹配越界持续进行
    if (S[i] == T[j])
    {
      ++i;
      ++j;
    } // 如果匹配上就继续比较后继字符
    else
    {
      i = i - j + 1;
      j = 0;
    } // 指针回溯重新开始匹配
  }
  if (j == strlen(T)) //如果字串到达尾部，也就是全部匹配上
    return i - strlen(T);
  else
    return ERROR;
} // Index
int main()
{
  sub_str S, T;
  int pos;
  cout << "input mainstring  S:";
  cin >> S;
  cout << "input substring T:";
  cin >> T;
  cout << "input begin postion of match:";
  cin >> pos;
  int result = Index(S, T, pos);
  if (result != -1)
    cout << "the match pos =" << result << endl;
  else
    cout << "mismatch!" << endl;
  return 0;
}
