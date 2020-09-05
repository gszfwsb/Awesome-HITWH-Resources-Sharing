#ifndef _BITREE_H
#define _BITREE_H
#include <iostream>
#include "stack_op.h"
#include "stack.h"
#define OK 1
#define ERROR 0
#define TRUE 1
#define FALSE 0
typedef int Status;
void CrtExptree(BiTree &T, char exp[] );
void PreOrder (BiTree T);
void InOrder (BiTree T);
void PostOrder (BiTree T);
void CrtNode(BiTree& T,char ch);
void CrtSubtree (BiTree& T, char c);
Status IN(char ch,char OP[]);
Status precede(char c,char ch);
#endif