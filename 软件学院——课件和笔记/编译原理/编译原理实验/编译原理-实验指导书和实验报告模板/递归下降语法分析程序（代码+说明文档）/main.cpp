#include <iostream>
#include <cstdio>
#include <cstdlib>
#include <cstring>
#define _KEY_WORDEND "waiting for your expanding"

using namespace std;

typedef struct //词的结构，二元组形式（单词种别，单词自身的值）
{
	int typenum; //单词种别
	char * word;
}WORD;

WORD* oneword = new WORD;
char input[255];
char token[255] = "";
int p_input; //指针
int p_token;
int kk;//标记是否已经出错，如果整个程序判断到最后没有出错，输出Success
int flag=0;//标记括号数目，falg=0左右括号匹配，flag>0左括号多了，flag<0右括号多了
bool print_flag=true;//标志是否已经检测出缺少右括号的错误
int row=1;//定义行数，每次读取一个回车则为换行
int error_row = 0;//标志总共几个错误
char ch;
char * rwtab[] = { "begin","if","then","while","do","end","int","main","else","float","double","return","cout",_KEY_WORDEND };


WORD * scanner();//扫描,获取到每个单词的种别码
int Irparser();//程序的判断
int yucu();//语句串的分析
int statement();//语句的分析
int expression();//表达式的判断
int term();//项的分析
int factor();//因子的分析
char m_getch();

int main()
{
    //以“#”结束
    p_input=0;
    printf("---语法分析程序开始---\n");
    printf("读取到的源程序如下：\n");
    /*do
    {
        scanf("%c",&ch);
        input[p_input++]=ch;
    }while(ch!='#');*/

    FILE *fp=NULL;
    fp = fopen("error_data.txt","r");
    if(fp == NULL)
    {
        printf("Not found file!");
        return 0;
    }
    while((input[p_input]=getc(fp))!=EOF)
    {
        putchar(input[p_input]);
        p_input++;
    }

    printf("\n");

    p_input=0;
    kk=0;
    oneword = scanner();
    Irparser();
    printf("\n");
    printf("===以上是程序分析结果===\n");
    printf("总共%d行，其中有%d个错误\n",row,error_row);//输出行数以及错误个数
    printf("========================\n");
    printf("---语法分析程序结束---\n");
    return 0;
}

//从输入缓冲区读取一个字符到ch中
char m_getch()
{
	ch = input[p_input];
	p_input++;
	return ch;
}

//去掉空白符号，不包括回车
void getbc()
{
	while (ch == ' ')
	{
		ch = input[p_input];
		p_input++;
	}
}

//拼接单词
void concat()
{
	token[p_token] = ch;
	p_token++;
	token[p_token] = '\0';
}

//判断是否字母
int letter()
{
	if (ch >= 'a'&&ch <= 'z' || ch >= 'A'&&ch <= 'Z')
		return 1;
	else
		return 0;
}

//判断是否数字
int digit()
{
	if (ch >= '0'&&ch <= '9')
		return 1;
	else
		return 0;
}

//检索关键字表格
int reserve()
{
	int i = 0;
	while(strcmp(rwtab[i], _KEY_WORDEND))
	{
		if (!strcmp(rwtab[i], token))
			return i + 1;
		i++;
	}
	return 10;//如果不是关键字，则返回种别码10
}

//回退一个字符
void retract()
{
	p_input--;
}

//词法扫描程序
WORD * scanner()
{
	WORD * myword = new WORD;
	myword->typenum = 10;  //初始值
	myword->word = "";
	p_token = 0;   //单词缓冲区指针
	m_getch();
	getbc();//去掉空白

	if (letter())//判断读取到的首字母是字母
	{
	        //如int
		while (letter() || digit())
		{
			concat(); //连接
			m_getch();
		}
		retract(); //回退一个字符
		myword->typenum = reserve();//判断是否为关键字，返回种别码
		myword->word = token;
		return myword;
	}
	else if (digit())  //判断读取到的单词首字符是数字
	{
		while (digit()) //所有数字连接起来
		{
			concat();
			m_getch();
		}
		retract();
		//数字单词种别码统一为20，单词自身的值为数字本身
		myword->typenum = 20;
		myword->word = token;
		return(myword);
	}
	else switch (ch)
	{
	case '=':
		m_getch();//首字符为=,再读取下一个字符判断
		if (ch == '=')
		{
			myword->typenum = 39;
			myword->word = "==";
			return(myword);
		}
		retract();//读取到的下个字符不是=，则要回退，直接输出=
		myword->typenum = 21;
		myword->word = "=";
		return(myword);
		break;
	case '+':
		myword->typenum = 22;
		myword->word = "+";
		return(myword);
		break;
	case '-':
		myword->typenum = 23;
		myword->word = "-";
		return(myword);
		break;
        case '/'://读取到该符号之后，要判断下一个字符是什么符号，判断是否为注释
                m_getch();//首字符为/,再读取下一个字符判断
		if (ch == '*') // 说明读取到的是注释
		{
		        m_getch();

			while(ch != '*')
                        {
                                m_getch();//注释没结束之前一直读取注释，但不输出
                                if(ch == '*')
                                {
                                        m_getch();
                                        if(ch == '/')//注释结束
                                        {
                                                myword->typenum = 999;
                                                myword->word = "注释";
                                                return (myword);
                                                break;
                                        }
                                }

                        }

		}
                else
                {
                        retract();//读取到的下个字符不是*，即不是注释，则要回退，直接输出/

                        myword->typenum = 25;
                        myword->word = "/";
                        return (myword);
                        break;
                }
        case '*':
		myword->typenum = 24;
		myword->word = "*";
		return(myword);
		break;
	case '(':
		myword->typenum = 26;
		myword->word = "(";
		return(myword);
		break;
	case ')':
		myword->typenum = 27;
		myword->word = ")";
		return(myword);
		break;
	case '[':
		myword->typenum = 28;
		myword->word = "[";
		return(myword);
		break;
	case ']':
		myword->typenum = 29;
		myword->word = "]";
		return(myword);
		break;
	case '{':
		myword->typenum = 30;
		myword->word = "{";
		return(myword);
		break;
	case '}':
		myword->typenum = 31;
		myword->word = "}";
		return(myword);
		break;
	case ',':
		myword->typenum = 32;
		myword->word = ",";
		return(myword);
		break;
	case ':':
	        m_getch();
		if (ch == '=')
		{
			myword->typenum = 18;
			myword->word = ":=";
			return(myword);
			break;
		}
		else
                {
                        retract();
                        myword->typenum = 33;
                        myword->word = ":";
                        return(myword);
                        break;
                }
        case ';':
                myword->typenum = 34;
                myword->word = ";";
                return(myword);
                break;
	case '>':
		m_getch();
		if (ch == '=')
		{
			myword->typenum = 37;
			myword->word = ">=";
			return(myword);
			break;
		}
		retract();
		myword->typenum = 35;
		myword->word = ">";
		return(myword);
		break;
	case '<':
		m_getch();
		if (ch == '=')
		{
			myword->typenum = 38;
			myword->word = "<=";
			return(myword);
			break;
		}
		else if(ch == '<')
                {
                        myword->typenum = 42;
			myword->word = "<<";
			return(myword);
			break;
                }
                else
                {
                        retract();
                        myword->typenum = 36;
                        myword->word = "<";
                        return (myword);
                }
	case '!':
		m_getch();
		if (ch == '=')
		{
			myword->typenum = 40;
			myword->word = "!=";
			return(myword);
			break;
		}
		retract();
		myword->typenum = -1;
		myword->word = "ERROR";
		return(myword);
		break;
        case ' " ':
                myword->typenum = 41;
		myword->word = " \" ";
		return(myword);
		break;
        case '\n'://回车要作为单词返回，方便后面语法分析中一行一行判断
                myword->typenum = 50;
		myword->word = "回车";
		return(myword);
		break;
	case '\0':
		myword->typenum = 1000;
		myword->word = "OVER";
		return(myword);
		break;
        case '#':
                myword->typenum = 0;
                myword->word = "#";
                return (myword);
                break;
	default:
		myword->typenum = -1;
		myword->word = "ERROR";
		return(myword);
		break;
	}
}


void check_enter()//检查到是回车，则将row++，取下一个字符
{
   if(oneword->typenum == 50)
   {
       row++;
       oneword = scanner();
   }
   return;
}

void error_enter()//出错之后检查回车直接跳到下一行
{
    while(oneword->typenum != 50)
    {
        oneword = scanner();
    }
    row++;//注意该函数执行完之后，行数对应加1，但是此时oneword存储的是“回车”单词的信息
}


int Irparser()
{
    printf("========================\n");
    printf("===以下是程序分析结果===\n");
    printf("\n");
    if(oneword->typenum == 1)//begin单词的种别码就是1，该程序中是从begin开始的
    {
        oneword = scanner();
        check_enter();//检查下是否读取到回车了
        yucu();//开始执行语句串的判断
        if(oneword->typenum == 6)//检查到最后的end
        {
            oneword = scanner();
            //cout << oneword->typenum <<endl;
            if(oneword->typenum == 0 && (kk == 0))//取到了结束符,kk=0说明整个程序都是没有错误的
            {
                printf("Success\n");
            }
        }
        else//程序最后没有取到end，报错
        {
                printf("第%d行：缺少‘end’\n",row);
                error_row++;
                kk=1;
        }
    }
    else//处理缺少begin的情况
    {
        printf("开始处：缺少‘begin’\n");
        error_row++;//即使第一行开始的不是begin，此时缺少begin也算是一个错误
        kk=1;//说明已经出错，即使后面没有报错，最后也不能报Success
        yucu();//跳到下一行同样执行语句串的判断
        if(oneword->typenum == 6)
        {
                oneword = scanner();
                if(oneword->typenum == 0)//这个是在begin缺少的情况下判断end，
                        return 0;       //已经出错了，不可能输出Success，所以不需要判断kk

        }
        else
        {
                printf("第%d行：缺少‘end’\n",row);
                kk=1;
                error_row++;//错的个数增加1
        }
    }
    return 0;
}

int yucu()//语句串分析<语句串>::=<语句>{;<语句>}
{
    statement();//对语句串的判断转换成对语句的判断
    while(oneword->typenum == 34)//34是分隔符；的种别码
    {
        oneword = scanner();
        check_enter();//判断是否读取到回车
        statement();
    }
    if(oneword->typenum == 50)//50代表的是回车
    {
        row++;
        oneword = scanner();
        if(oneword->typenum != 6 && oneword->typenum != 0)//读取到的是其他单词，说明缺少了分隔符
        {
               printf("第%d行：缺少分隔符\n",row-1);
               error_row++;//错的个数增加1
               kk=1;//标志是否已经出错了
               yucu();//同样进行语句串的判断
        }
        else return 0;
    }
    if(oneword->typenum != 6 && oneword->typenum != 0)//不同语句可以放在同一行的，此时读取到的不是回车
    {                                                 //而是其他变量，报错，缺少分隔符
        printf("第%d行：缺少分隔符\n",row);
        error_row++;//错的个数增加1
        kk=1;//标志是否已经出错了
        error_enter();//出错之后检查下回车，继续下一行的判断
        oneword = scanner();//读取下一行的首个字符
        yucu();//同样进行语句串的判断
    }
    return 0;
}

int statement()//语句分析<语句>::=<赋值语句>    <赋值语句>::=ID:=<表达式>
{
    if(oneword->typenum == 10)
    {
        oneword = scanner();
        if(oneword->typenum == 18)  //“:=”单词种别码是18
        {
            oneword = scanner();
            expression();//读取到赋值号之后进行表达式的判断
        }
        else//读取到的赋值号不是:=，报错
        {
            printf("第%d行：赋值号错误\n",row);
            error_row++;//出错的个数加1
            kk=1;//标志已经出错
            error_enter();//跳到下一行再次处理
            oneword = scanner();
            yucu();
        }
    }
    else if(oneword->typenum == 6 || oneword->typenum == 0)
    {
        return 0;
    }
    else
    {
        printf("第%d行：变量名错误\n",row);
        error_row++;//出错个数加1
        kk=1;
        error_enter();
        oneword = scanner();
        yucu();
    }
    return 0;
}

int expression()//表达式的分析
{
    term();
    while(oneword->typenum == 22 || oneword->typenum == 23)
    {
        oneword = scanner();
        term();
    }
    return 0;
}

int term()//项的判断
{
    factor();//转入到因子的判断中
    while(oneword->typenum == 24 || oneword->typenum == 25)
    {
        oneword = scanner();
        factor();//出现乘除之后继续进行因子的判断
    }
    return 0;
}

int factor()//因子的判断
{
    if(oneword->typenum == 10 || oneword->typenum == 20)
    {
        oneword = scanner();
        if(oneword->typenum == 27)
        {
                flag--;
                if(flag<0)
                {
                        printf("第%d行：缺少左括号\n",row);
                        kk=1;
                        error_row++;//出错的行增加1
                        error_enter();//跳到下一行进行新的处理
                        oneword = scanner();
                        yucu();
                } //由于在这里判断完之后有一种情况是要返回到factor函数的else if中的expression中的
                  //如果同时出现两个右括号，d:=(a+((a*(b+c))，则会出现flag多减了一次，所以要将flag+1
                flag++;
        }
    }

    else if(oneword->typenum == 26)
    {
        flag++;
        oneword = scanner();
        expression();
        while(oneword->typenum == 27)
        {
                flag--;//对flag的处理设置要正确
                       //由于读取到了括号了，先处理flag再进行相关判断
                oneword = scanner();
                if(flag<0)
                {
                        printf("第%d行：缺少左括号\n",row);
                        kk=1;
                        error_row++;//出错的行增加1
                        error_enter();//执行回车跳到下一行
                        oneword = scanner();
                        yucu();
                }
        }
        if(flag>0 && print_flag==true)
        {
            printf("第%d行：缺少右括号\n",row);
            print_flag=false;
            error_row++;//出错个数加1
            kk=1;//标志出错
            error_enter();//回车处理，跳转到下一行
            oneword = scanner();//读取单词
            yucu();//再次进行相关语句串判断
        }
    }
    else
    {
        printf("第%d行：表达式错误\n",row);
        error_row++;//出错格式加1
        kk=1;//标志出错
        error_enter();//回车处理，跳转下一行
        oneword = scanner();//读取单词
        yucu();//语句串判断
    }
    return 0;
}
