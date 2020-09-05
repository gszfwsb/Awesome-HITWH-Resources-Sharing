#include <stdio.h>
#include <stdlib.h>
#include <string.h>
int countspcae(char *str)
{   int count=0;
    int i;
    for (i=0;i<strlen(str);i++)
    {
        if (str[i]==' ')
            count++;
    }
    return count;
}

void normalize(char *str,int *startChar,int *endChar,int numColume)
{  char straline[200]="";
   int i,j;
   while ( (str[*endChar]!=' ')&&(str[*endChar]!='.')&& (str[*endChar]!=',') && (str[*endChar]!='!'))
    (*endChar)--;
     for (i=*startChar,j=0;i<*endChar;i++,j++)  straline[j]=str[i];
     if (str[*endChar]!=' ') straline[++j]=str[*endChar];
     *startChar=*endChar+1;
     *endChar=*startChar+numColume;
     standardize(straline,numColume);
}

void standardize(char *straline,int numColume)
{
    int spacenum=countspcae(straline);//原来空格数
    int span=numColume-strlen(straline); //插入总量
    int insertnum=span/spacenum; //平均每个空格后面插入数量
    int restnum=span%spacenum;//多余空格
    int i,count=0;
    char output[200]="";
    if (spacenum>0)
    {

    for (i=0;i<numColume;i++) output[i]=' '; //初始化每一位都是空格再填数
    for (i=0;i<strlen(straline);i++)
    {
        if (straline[i]==' ')
        {
            count+=insertnum;
            if (restnum>0)
            {
                count++;
                restnum--;
            }
        }
        output[count]=straline[i];
        count++;
    }
    puts(output);
    }
   else puts(straline);

}

int main()
{ char strText[]={"We have an old musical instrument.It is called a clavichord."
    "It was made in Germany in 1681.Our clavichord is kept in the living-room."
    "It has belonged to our family for a long time.The instrument was bought by my grandfather many years ago."
    "Recently it was damaged by a visitor.She tried to play jazz on it!"
    "She struck the keys too hard and two of the strings were broken."
    "My father was shocked.Now we are not allowed to touch it.It is being repaired by a friend of my father's."};
  int numColume,startChar,endChar;
  int textLen=strlen(strText),i;
   printf("Input numColumn:(20<=n<120)\n");
  scanf("%d",&numColume);
  if (numColume>=textLen)
    {
      puts(strText);
      return 0;
    }
    startChar=0; endChar=numColume-1;
      while (endChar<textLen)
          {
            normalize(strText,&startChar,&endChar,numColume);
          }
        for (i=startChar;i<textLen;i++) printf("%c",strText[i]);  //打印剩余字符
  return 0;

}
