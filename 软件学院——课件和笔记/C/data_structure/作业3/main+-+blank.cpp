#include <iostream>
#include   <stdlib.h>
#include   <time.h>
using namespace std;
#define  MAXSIZE  12500
#define OK 1
typedef int Status;
typedef int ElemType;
typedef struct
{
	int  i, j;       //�÷���Ԫ�����±�����±�
	ElemType  e;    // �÷���Ԫ��ֵ
} Triple;            // ��Ԫ������
typedef struct
{
	Triple  data[MAXSIZE + 1];    //data[0]δ��
	int     mu, nu, tu; //����������������
} TSMatrix;               // ϡ���������

/*****************************************************************
CreatMatrix(TSMatrix &M)�������һ��ϡ������Ϊfact��row*col�ľ���
fact��[0,0.05]
row��[1,100]
col��[1,100]
*******************************************************************/
Status CreatMatrix(TSMatrix &M)
{
	int row, col; //����������
	float fact;//ϡ������
	srand(time(NULL));
	row = rand() % 100 + 1; //��������
	col = rand() % 100 + 1; //��������
	fact = rand() * 0.05 / RAND_MAX; //�����������
	M.mu = row;
	M.nu = col;
	M.tu = row * col * fact;
	for(int k = 1; k <= M.tu; k++)
	{
		int q = 1;
		do
		{
			M.data[k].i = rand() % row + 1; //����һ��Ԫ�ص��к�
			M.data[k].j = rand() % col + 1; //����һ��Ԫ�ص��к�
			//�������������п����ظ���Ӧ��ȥ��
			if(M.data[k].i == M.data[q].i && M.data[k].j == M.data[q].j)//���ظ�
			{
				q = 1;
				continue;//ת����ʼ��������,���±ȶ�
			}
			q++;
		}
		while(q < k);
		M.data[k].e = rand(); //���ɸ�Ԫ��ֵ
	}
	return OK;
}
/**
ת��
**/
Status TransposeSMatrix(TSMatrix M, TSMatrix &T)
{
	int p,q,col;
	T.mu = M.nu;
	T.nu = M.mu;
	T.tu = M.tu;
	if (T.tu){
		q=1;
		for (col = 1;col<=M.nu;col++){
			for (p=1;p<=M.tu;p++){
				if(M.data[p].j==col){
					T.data[q].i = M.data[p].j;
					T.data[q].j = M.data[p].i;
					T.data[q].e = M.data[p].e;
					q++;
				}
			}
		}

	}
	return OK;
}
/**
���
**/
void outputMatrix(TSMatrix M)
{
	cout << "--------------------------------------------" << endl;
	cout << M.mu << "*" << M.nu << "  total = " << M.tu << endl;
	cout << "--------------------------------------------" << endl;
	for(int i = 1; i <= M.tu; i++)
	{
		cout << "(" << M.data[i].i << "," << M.data[i].j << "," << M.data[i].e << ")" << endl;
	}
}

int main()
{
	TSMatrix M, T;
	CreatMatrix(M);
	outputMatrix(M);
	TransposeSMatrix( M, T);
	outputMatrix(T);
	return 0;
}
