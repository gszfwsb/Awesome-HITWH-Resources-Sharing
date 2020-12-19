package semantic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import grammar.SyntaxParser;
import grammar.Tree;
import grammar.TreeNode;

public class Smantic
{
	private static ArrayList<Tree> tree =  new ArrayList<Tree>();  // 语法树
	static List<Properties> tree_pro;  // 语法树节点属性
	
	static List<Stack<Symbol>> table = new ArrayList<Stack<Symbol>>();  // 符号表  
	static List<Integer> tablesize = new ArrayList<Integer>();  // 记录各个符号表大小
	
	static List<String> three_addr = new ArrayList<String>();  // 三地址指令序列
	static List<FourAddr> four_addr = new ArrayList<FourAddr>();  // 四元式指令序列
	static List<String> errors = new ArrayList<String>();  // 错误报告序列
	
    static String t;  // 类型
    static int w;  // 大小
    static int offset;  // 偏移量
	static int temp_cnt = 0;  // 新建变量计数  
    static int nextquad = 1;  // 指令位置
    
	static List<String> queue = new ArrayList<String>();  // 过程调用参数队列
    static Stack<Integer> tblptr = new Stack<Integer>();  // 符号表指针栈
    static Stack<Integer> off = new Stack<Integer>();  //  符号表偏移大小栈
    
	static int nodeSize;  // 语法树上的节点数
	static int treeSize;  // 语法树大小
	static int initial = nextquad;  // 记录第一条指令的位置
    public Smantic(String filename, List<Stack<Symbol>> table, 
    	List<String> three_addr, List<FourAddr> four_addr, List<String> errors)
    {
		SyntaxParser parser = new SyntaxParser(filename, tree);
		treeSize = tree.size();
		nodeSize = tree.get(treeSize-1).getFather().getId() + 1;
		tree_pro = Arrays.asList(new Properties[nodeSize]);  // 符号属性

		Smantic.three_addr = three_addr;
		Smantic.four_addr = four_addr;
		Smantic.table = table;
		Smantic.errors = errors;
		
		if (treeSize==0)
			return;
		dfs(tree.get(treeSize-1));

		util.print_ins(three_addr, four_addr);
		util.print_table(table);
		util.print_errors(errors);
    }
    
    
	public static void main(String[] args)
	{
//		SyntaxParser parser = new SyntaxParser("D:\\test1.txt",tree);
//		parser.analyze();
//		System.out.println(tree_pro.size());

		Smantic se = new Smantic("D:\\test1.txt",table,three_addr,four_addr,errors);
//		if (treeSize==0)
//			return;
//		dfs(tree.get(treeSize-1));
//
//		util.print_ins(three_addr, four_addr);
//		util.print_table(table);
//		util.print_errors(errors);
	}
    
	/**
	 * 深搜遍历语法树
	 * @param tree 语法树根节点
	 */
	public static void dfs(Tree tree)
	{				
		int flag = 0;
		for(int i=0; i<tree.getChildren().size(); i++)
		{
			TreeNode tn = tree.getChildren().get(i);
			if (!util.endPoint(tn))  // 非终结符
			{
				flag = 1;
				// 找到邻接表的下一节点
				Tree f = findTreeNode(tn.getId());  
				dfs(f);  // 递归遍历孩子节点
				findSemantic(f);  // 查找相应的语义动作函数
			}			
		}
		if (flag == 0)
		{
			return;
		}
	}
	
	/**
	 * 向符号表中增加元素
	 * @param i 第i个符号表
	 * @param name 元素名字
	 * @param type 元素类型
	 * @param offset 偏移量
	 */
    private static void enter(int i, String name, String type, int offset)
    {        
    	if(table.size()==0)
    	{
    		table.add(new Stack<Symbol>());
    	}
    	Symbol s = new Symbol(name,type,offset);
        table.get(i).push(s);  
    }  
    
    /**
     * 查找符号表，查看变量是否存在  
     * @param s 名字
     * @return 该名字在符号表中的位置
     */
    private static int[] lookup(String s) 
    {     
    	int[] a = new int[2]; 
    	for (int i=0; i<table.size(); i++)
    	{
        	for (int j=0; j<table.get(i).size(); j++)
        	{
	    		if(table.get(i).get(j).getName().equals(s))
	    		{
	    			a[0] = i;
	    			a[1] = j;
	    			return a;
	    		}
        	}
    	}
		a[0] = -1;
		a[1] = -1;
		return a;
    }  
  
    /**
     * 新建一个变量  
     * @return 新建变量名
     */
    private static String newtemp()
    {                
        return "t" + (++temp_cnt);  
    }  
    
    /**
     * 回填地址
     * @param list 需要回填的指令序列
     * @param quad 回填的地址
     */
    private static void backpatch(List<Integer> list, int quad)
    {
    	for(int i=0; i<list.size(); i++)
    	{
    		int x = list.get(i) - initial;
    		three_addr.set(x, three_addr.get(x)+quad);
    		four_addr.get(x).setToaddr(String.valueOf(quad));
    	}
    }

    /**
     * 合并列表
     * @param a 列表
     * @param b 列表
     * @return a与b合并后的列表
     */
    private static List<Integer> merge(List<Integer> a,List<Integer> b)
    {
    	List<Integer> a1 = a;
    	a1.addAll(b);
    	return a1;
    }
    
    /**
     * 返回下一条指令地址
     * @return 下一条指令地址
     */
    private static int nextquad() 
    {          
    	 return three_addr.size() + nextquad;  
    }  
	
    /**
     * 新建包含i的列表并返回
     * @param i 
     * @return 列表
     */
    private static List<Integer> makelist(int i) 
    {          
    	 List<Integer> a1 = new ArrayList<Integer>();
    	 a1.add(i);
    	 return a1;  
    }  
    
    /**
     * 新增一个符号表
     */
    public static void mktable()
    {
    	table.add(new Stack<Symbol>());
    }
    
    public static void addwidth(List<Stack<Symbol>> table, int width)
    {
    	return;
    }
    

	public static Tree findTreeNode(int id)
	{
		for(Tree t:tree)
		{
			if (t.getFather().getId() == id)
			{
				return t;
			}
		}
		return null;
	}
	
	
    // P -> proc id ; M0 begin D S end  {addwidth(top(tblptr),top(offset));pop(tblptr);pop(offset)}
    public static void semantic_1(Tree tree)
    {
    	tblptr.pop();
    	off.pop();    	
    }
	
    // S -> S1 M S2  {backpatch(S1.nextlist,M.quad); S.nextlist=S2.nextlist;}
    public static void semantic_3(Tree tree)
    {
    	int S = tree.getFather().getId();  // S
    	int S1 = tree.getChildren().get(0).getId();  // S1
    	int M = tree.getChildren().get(1).getId();  // M
    	int S2 = tree.getChildren().get(2).getId();  // S2

    	backpatch(tree_pro.get(S1).getNext(), tree_pro.get(M).getQuad());
    	
    	Properties a1 = new Properties();
    	a1.setNext(tree_pro.get(S2).getNext());
    	tree_pro.set(S,a1);
    }
	
    // D -> T id ; {enter(top(tblptr),id.name,T.type,top(offset)); 
    //              top(offset) = top(offset)+T.width}
    public static void semantic_5(Tree tree)
    {
    	int T = tree.getChildren().get(0).getId();  // T
    	String id = tree.getChildren().get(1).getValue();  // id
    	
    	int[] i = lookup(id);
    	if (i[0] == -1)
    	{        	
        	enter(tblptr.peek(), id, tree_pro.get(T).getType(), off.peek());
        	int s = off.pop();
        	off.push(s + tree_pro.get(T).getWidth());
        	offset = offset + tree_pro.get(T).getWidth();
    	}
    	else
    	{
    		String s = "Error at Line [" +  tree.getChildren().get(1).getLine() +
    				"]:\t[" + "变量" + id + "重复声明]" ;   		
    		errors.add(s);   		
    	}
    }

    // T -> X C {T.type=C.type; T.width=C.width;}
    public static void semantic_6(Tree tree)
    {
    	int T = tree.getFather().getId();  // T
    	ArrayList<TreeNode> c = tree.getChildren();
    	int X = c.get(0).getId();  // X
    	int C = c.get(1).getId();  // C
    	//t = tree_pro.get(X).getType();
    	//w = tree_pro.get(X).getWidth();
    	
    	Properties a1 = new Properties();
    	a1.setType(tree_pro.get(C).getType());
    	a1.setWidth(tree_pro.get(C).getWidth());
    	tree_pro.set(T,a1);
    }
    
    
    // T -> record N2 D end   {T.type=record(top(tblptr)); 
    //                         T.width=top(offset); pop(tblptr); pop(offset)}
    public static void semantic_7(Tree tree)
    {
    	int T = tree.getFather().getId();  // T
    	
    	Properties a1 = new Properties();
    	a1.setType("record");
    	a1.setWidth(off.pop());
    	tblptr.pop();
    	
    	tree_pro.set(T,a1);
    }
    
    
    // X -> integer  {X.type=integer; X.width=4;}{t=X.type; w=X.width;}
    public static void semantic_8(Tree tree)
    {
    	int X = tree.getFather().getId();  // X
    	t = "integer";
    	w = 4;
    	
    	Properties a1 = new Properties();
    	a1.setType("integer");
    	a1.setWidth(4);
    	tree_pro.set(X,a1);
    }
    
    // X -> real  {X.type=real; X.width=8;}{t=X.type; w=X.width;}
    public static void semantic_9(Tree tree)
    {
    	int X = tree.getFather().getId();  // X
    	t = "real";
    	w = 8;
    	   	
    	Properties a1 = new Properties();
    	a1.setType("real");
    	a1.setWidth(8);
    	tree_pro.set(X,a1);
    }
    
    // C -> [ num ] C1  {C.type=array(num.val,C1.type); C.width=num.val*C1.width;}
    public static void semantic_10(Tree tree)
    {
    	int C = tree.getFather().getId();  // C
    	int num = Integer.parseInt(tree.getChildren().get(1).getValue());  // num
    	int C1 = tree.getChildren().get(3).getId();  // C1
    	Properties a1 = new Properties();
    	Array a2 = new Array();
    	
    	a2.setLength(num);
    	String type = tree_pro.get(C1).getType();
    	if (type.startsWith("array"))
    	{
    		a2.setType(tree_pro.get(C1).getArray());
    		a2.setBaseType("array");
    	}
    	else
    	{	
    		a2.setBaseType(type);
    	}	
		a1.setArray(a2);
    	a1.setType(util.arrayString(a2));
    	a1.setWidth(num*tree_pro.get(C1).getWidth());
    	tree_pro.set(C,a1);
    }
    
    // C -> ε  {C.type=t; C.width=w;}
    public static void semantic_11(Tree tree)
    {
    	//System.out.println("dssd");
    	//System.out.println(t+w);
    	int C = tree.getFather().getId();  // C
    	Properties a1 = new Properties();    	
    	a1.setType(t);
    	a1.setWidth(w);
    	tree_pro.set(C,a1);
    }
    
    //  S -> id = E ;  {p=lookup(id.lexeme); if p==nil then error; 
    //                  gencode(p'='E.addr); S.nextlist=null} 
    public static void semantic_12(Tree tree)
    {
    	int S = tree.getFather().getId();  // S
    	String id = tree.getChildren().get(0).getValue();  // id
    	int E = tree.getChildren().get(2).getId();  // E
    	
    	int[] i = lookup(id);
    	if (i[0]==-1)
    	{
    		String s = "Error at Line [" +  tree.getChildren().get(0).getLine() + "]:\t[" +
    				"变量" + id + "引用前未声明]" ;   		
    		errors.add(s);   
        	enter(tblptr.peek(), id, "integer", offset);
        	offset = offset + 4;
    	}
    	
    	String code = id + " = " + tree_pro.get(E).getAddr();
    	three_addr.add(code);
    	four_addr.add(new FourAddr("=",tree_pro.get(E).getAddr(),"-",id));
    	//System.out.println(code); 		 
    	
    	Properties a1 = new Properties();    	
    	a1.setNext(new ArrayList<Integer>());
    	tree_pro.set(S,a1);
    }
    
    //  S -> L = E ;  {gencode(L.array'['L.offset']''='E.addr); S.nextlist=null} 
    public static void semantic_13(Tree tree)
    {
    	int S = tree.getFather().getId();  // S
    	int L = tree.getChildren().get(0).getId();  // L
    	int E = tree.getChildren().get(2).getId();  // E
    	
    	String code = tree_pro.get(L).getName() + "[" +tree_pro.get(L).getOffset() 
    			+ "] = " + tree_pro.get(E).getAddr();
    	three_addr.add(code);
    	four_addr.add(new FourAddr("=",tree_pro.get(E).getAddr(),"-",
    			tree_pro.get(L).getName() + "[" +tree_pro.get(L).getOffset() + "]"));
    	//System.out.println(code); 		
    	
    	Properties a1 = new Properties();    	
    	a1.setNext(new ArrayList<Integer>());
    	tree_pro.set(S,a1);
    }
      
    
    //  E -> E1 + E2  {E.addr=newtemp(); gencode(E.addr'='E1.addr'+'E2.addr);}
    public static void semantic_14(Tree tree)
    {
    	int E = tree.getFather().getId();  // E 	
    	int E1 = tree.getChildren().get(0).getId();  // E1 	
    	int E2 = tree.getChildren().get(2).getId();  // E2 	
    	String newtemp1 = newtemp();
    	
    	
    	if ((tree_pro.get(E1).getType().equals("integer") && tree_pro.get(E2).getType().equals("integer")) ||
    		(tree_pro.get(E1).getType().equals("real") && tree_pro.get(E2).getType().equals("real")))
    	{
    		Properties a1 = new Properties();
	    	a1.setAddr(newtemp1);
	    	a1.setType(tree_pro.get(E1).getType());
	    	tree_pro.set(E,a1);
	    	
	    	String code = newtemp1 + " = " + tree_pro.get(E1).getAddr() + 
	    			"+" + tree_pro.get(E2).getAddr();
	    	three_addr.add(code);
	    	four_addr.add(new FourAddr("+",tree_pro.get(E1).getAddr(),
	    			tree_pro.get(E2).getAddr(),newtemp1));
	    	//System.out.println(code); 
	    	//System.out.println("ccc"); 
	    	//System.out.println(tree_pro.get(E).getAddr()); 	
    	}
    	if ((tree_pro.get(E1).getType().equals("real") && tree_pro.get(E2).getType().equals("integer")))
        {
    		String newtemp2 = newtemp();
    		Properties a1 = new Properties();
	    	a1.setAddr(newtemp2);
	    	a1.setType("real");
	    	tree_pro.set(E,a1);
	    	
	    	String code1 = newtemp1 + " = intTOreal " + tree_pro.get(E2).getAddr();
	    	String code2 = newtemp2 + " = " + tree_pro.get(E1).getAddr() + "+" + newtemp1;
	    	three_addr.add(code1);
	    	three_addr.add(code2);
	    	four_addr.add(new FourAddr("=","intTOreal" + tree_pro.get(E2).getAddr(),"-",newtemp1));
	    	four_addr.add(new FourAddr("+",tree_pro.get(E1).getAddr(),newtemp1,newtemp2));
	    	//System.out.println(code); 	
        }
    	if ((tree_pro.get(E1).getType().equals("integer") && tree_pro.get(E2).getType().equals("real")))
    	{
    		String newtemp2 = newtemp();
    		Properties a1 = new Properties();
	    	a1.setAddr(newtemp2);
	    	a1.setType("real");
	    	tree_pro.set(E,a1);
	    	
	    	String code1 = newtemp1 + " = intTOreal " + tree_pro.get(E1).getAddr();
	    	String code2 = newtemp2 + " = " + newtemp1 + "+" + tree_pro.get(E2).getAddr();
	    	three_addr.add(code1);
	    	three_addr.add(code2);
	    	four_addr.add(new FourAddr("=","intTOreal" + tree_pro.get(E1).getAddr(),"-",newtemp1));
	    	four_addr.add(new FourAddr("+",newtemp1,tree_pro.get(E2).getAddr(),newtemp2));
    	}
    	if (tree_pro.get(E1).getType().contains("array"))
    	{
    		String newtemp2 = newtemp();
    		Properties a1 = new Properties();
	    	a1.setAddr(newtemp2);
	    	a1.setType("integer");
	    	tree_pro.set(E,a1);
	    	
	    	int x = util.typeWidth(tree_pro.get(E1).getType());
	    	String code1 = newtemp1 + " = " + x;
	    	String code2 = newtemp2 + " = " + newtemp1 + "+" + tree_pro.get(E2).getAddr();
	    	three_addr.add(code1);
	    	three_addr.add(code2);
	    	four_addr.add(new FourAddr("=",String.valueOf(x),"-",newtemp1));
	    	four_addr.add(new FourAddr("+",newtemp1,tree_pro.get(E2).getAddr(),newtemp2));
	    	
    		String s = "Error at Line [" +  tree.getChildren().get(0).getLine() + "]:\t[" +
    				"整型变量与数组变量相加减]" ;   		
    		errors.add(s);   	
    	}
    	if (tree_pro.get(E2).getType().contains("array"))
    	{
    		String newtemp2 = newtemp();
    		Properties a1 = new Properties();
    		//System.out.println(newtemp2);
	    	a1.setAddr(newtemp2);
	    	a1.setType("integer");
	    	tree_pro.set(E,a1);
	    	
	    	int x = util.typeWidth(tree_pro.get(E2).getType());
	    	String code1 = newtemp1 + " = " + x;
	    	String code2 = newtemp2 + " = " + tree_pro.get(E1).getAddr() + "+" + newtemp1;
	    	three_addr.add(code1);
	    	three_addr.add(code2);
	    	four_addr.add(new FourAddr("=",String.valueOf(x),"-",newtemp1));
	    	four_addr.add(new FourAddr("+",tree_pro.get(E1).getAddr(),newtemp1,newtemp2));
	    	
    		String s = "Error at Line [" +  tree.getChildren().get(1).getLine() + "]:\t[" +
    				"整型变量与数组变量相加减]" ;   		
    		errors.add(s);  
    	}
    }


    //  E -> E1  {E.addr=E1.addr} 
    public static void semantic_15_17(Tree tree)
    {
    	int E = tree.getFather().getId();  // E 	
    	int E1 = tree.getChildren().get(0).getId();  // E1 	
    	
    	Properties a1 = new Properties();
    	a1.setAddr(tree_pro.get(E1).getAddr());
    	a1.setType(tree_pro.get(E1).getType());
    	tree_pro.set(E,a1);	
    }
    
    
    //  E -> E1 * E2  {E.addr=newtemp(); gencode(E.addr'='E1.addr'*'E2.addr);}
    public static void semantic_16(Tree tree)
    {
    	int E = tree.getFather().getId();  // E 	
    	int E1 = tree.getChildren().get(0).getId();  // E1 	
    	int E2 = tree.getChildren().get(2).getId();  // E2 	
    	String newtemp = newtemp();
    	
    	Properties a1 = new Properties();
    	a1.setAddr(newtemp);
    	tree_pro.set(E,a1);
    	
    	String code = newtemp + " = " + tree_pro.get(E1).getAddr() + 
    			"*" + tree_pro.get(E2).getAddr();
    	three_addr.add(code);
    	four_addr.add(new FourAddr("*",tree_pro.get(E1).getAddr(),
    			tree_pro.get(E2).getAddr(),newtemp));		
    }
    
    
    //  E -> ( E1 )  {E.addr=E1.addr}
    public static void semantic_18(Tree tree)
    {
    	int E = tree.getFather().getId();  // E 	
    	int E1 = tree.getChildren().get(1).getId();  // E1 	
    	
    	Properties a1 = new Properties();
    	a1.setAddr(tree_pro.get(E1).getAddr());
    	a1.setType(tree_pro.get(E1).getType());
    	tree_pro.set(E,a1);	
    }
    
    
    //  E -> - E1  {E.addr=newtemp(); gencode(E.addr'=''uminus'E1.addr);}
    public static void semantic_19(Tree tree)
    {
    	int E = tree.getFather().getId();  // E 	
    	int E1 = tree.getChildren().get(1).getId();  // E1 	
    	String newtemp = newtemp();
    	
    	Properties a1 = new Properties();
    	a1.setAddr(newtemp);
    	a1.setType(tree_pro.get(E1).getType());
    	tree_pro.set(E,a1);
    	
    	String code = newtemp + " = -" + tree_pro.get(E1).getAddr();
    	three_addr.add(code);
    	four_addr.add(new FourAddr("=","-" + tree_pro.get(E1).getAddr(),
    			"-",newtemp));
    	//System.out.println(code); 	
    }
    
    
    //  E -> id  {E.addr=lookup(id.lexeme); if E.addr==null then error;}
    public static void semantic_20(Tree tree)
    {
       	int E = tree.getFather().getId();  // E 	
    	String id = tree.getChildren().get(0).getValue();  // id
    	
    	int[] i = lookup(id);
    	if (i[0]==-1)
    	{
    		String s = "Error at Line [" +  tree.getChildren().get(0).getLine() + "]:\t[" +
    				"变量" + id + "引用前未声明]" ;   		
    		errors.add(s);   
        	enter(tblptr.peek(), id, "integer", offset);
        	offset = offset + 4;
        	//System.out.println("ddd");
        	
        	Properties a1 = new Properties();
        	a1.setAddr(id);
        	a1.setType("integer");
        	tree_pro.set(E,a1);
        	return;
    	}	
    	
    	Properties a1 = new Properties();
    	a1.setAddr(id);
    	a1.setType(table.get(i[0]).get(i[1]).getType());
    	tree_pro.set(E,a1);
    	//System.out.println(tree_pro.get(E).getAddr());
    	//System.out.println(table.get(i[0]).get(i[1]).getType());
    }
    
    
    //  E -> num  {E.addr=lookup(num.lexeme); if E.addr==null then error}
    public static void semantic_21(Tree tree)
    {
       	int E = tree.getFather().getId();  // E 	
    	String num = tree.getChildren().get(0).getValue();  // num
    	/*
    	int i = lookup(num);
    	if (i==-1)
    	{
    		String error = num + "符号未定义";
    		System.out.println(error); 		   		
    	}		
    	*/
    	Properties a1 = new Properties();
    	a1.setAddr(num);
    	a1.setType("integer");
    	tree_pro.set(E,a1);
    }
    
    //  E -> L {E.addr=newtemp(); gencode(E.addr'='L.array'['L.offset']');}
    public static void semantic_22(Tree tree)
    {
       	int E = tree.getFather().getId();  // E 	
       	int L = tree.getChildren().get(0).getId();  // L 
    	String newtemp = newtemp();
    	
    	Properties a1 = new Properties();
    	a1.setAddr(newtemp);
    	a1.setType("integer");
    	tree_pro.set(E,a1);

    	String code = newtemp + " = " + tree_pro.get(L).getName() +
    			"[" +tree_pro.get(L).getOffset() + "] ";
    	three_addr.add(code);
    	four_addr.add(new FourAddr("=",
    			tree_pro.get(L).getName() +"[" +tree_pro.get(L).getOffset() + "]","-",newtemp));
    	//System.out.println(code); 		
    }
    
    //  L -> id [ E ] {L.array=lookup(id.lexeme); if L.array==nil then error; 
    //  L.type=L.array.type.elem; L.offset=newtemp(); gencode(L.offset'='E.addr'*'L.type.width);}
    public static void semantic_23(Tree tree)
    {
       	int L = tree.getFather().getId();  // L 	
       	String id = tree.getChildren().get(0).getValue();  // id
       	int E = tree.getChildren().get(2).getId();  // E
    	String newtemp = newtemp();
       	
    	int[] i = lookup(id);
    	if (i[0]==-1)
    	{
    		String error = id + "符号未定义";
    		//System.out.println(error); 		
    		
    		String s = "Error at Line [" +  tree.getChildren().get(0).getLine() + "]:\t[" +
    				"数组变量" + id + "引用前未声明]" ;   		
    		errors.add(s);  
    		//return;
    		
    		Properties a1 = new Properties();
        	a1.setName(id);
        	a1.setType("array(1,integer)");
        	a1.setOffset(newtemp);
        	tree_pro.set(L,a1);
        	
        	String code = newtemp + " = " + 4;
            four_addr.add(new FourAddr("=",String.valueOf(4),"-",newtemp));
        	
        	three_addr.add(code);
        	//System.out.println(code); 
        	return;
    	}	

    	if(!table.get(i[0]).get(i[1]).getType().contains("array"))
    	{   		
    		String s = "Error at Line [" +  tree.getChildren().get(0).getLine() + "]:\t[" +
    				"非数组变量" + id + "访问数组]" ;   		
    		errors.add(s);  
    		//return;
    	}
    	
    	Properties a1 = new Properties();
    	a1.setName(id);
    	a1.setType(util.elemType(table.get(i[0]).get(i[1]).getType()));
    	a1.setOffset(newtemp);
    	tree_pro.set(L,a1);
    	
    	String code = "";   	
    	String s = util.elemType(table.get(i[0]).get(i[1]).getType());
    	//System.out.println(s);
    	if (s.contains("array"))
    	{
    		code = newtemp + " = " + tree_pro.get(E).getAddr() + "*" + util.typeWidth(s);
        	four_addr.add(new FourAddr("*",tree_pro.get(E).getAddr(),
        			String.valueOf(util.typeWidth(s)),newtemp));
    	}
    	else
    	{
    		code = newtemp + " = " + tree_pro.get(E).getAddr();
        	four_addr.add(new FourAddr("=",tree_pro.get(E).getAddr(),"-",newtemp));
    	}
    	three_addr.add(code);
    	//System.out.println(code); 
    	
    }
    
    
    //  L -> L1 [ E ]  {L.array=L1.array; L.type=L1.type.elem; t=newtemp(); 
    //  gencode(t'='E.addr'*'L.type.width); L.offset=newtemp(); gencode(L.offset'='L1.offset'+'t);}
    public static void semantic_24(Tree tree)
    {
       	int L = tree.getFather().getId();  // L 	
       	int L1 = tree.getChildren().get(0).getId();  // L1
       	int E = tree.getChildren().get(2).getId();  // E
    	String newtemp1 = newtemp();
    	String newtemp2 = newtemp();
       	
    	Properties a1 = new Properties();
    	a1.setName(tree_pro.get(L1).getName());
    	a1.setType(util.elemType(tree_pro.get(L1).getType()));
    	a1.setOffset(newtemp2);
    	tree_pro.set(L,a1);
    	
    	String code1 = "";   
    	String s = util.elemType(tree_pro.get(L1).getType());
    	//System.out.println(s);
    	if (s.contains("array"))
    	{
        	code1 = newtemp1 + " = " + tree_pro.get(E).getAddr() + "*" + util.typeWidth(s);
        	four_addr.add(new FourAddr("*",tree_pro.get(E).getAddr(),
        			String.valueOf(util.typeWidth(s)),newtemp1));
    	}
    	else
    	{
    		code1 = newtemp1 + " = " + tree_pro.get(E).getAddr() + "*" + w;
        	four_addr.add(new FourAddr("*",tree_pro.get(E).getAddr(),
        			String.valueOf(w),newtemp1));
    	}
    	three_addr.add(code1);
    	//System.out.println(code1); 	
    	
    	String code2 = newtemp2 + " = " + tree_pro.get(L1).getOffset() +
    			"+" + newtemp1;
    	three_addr.add(code2);
    	four_addr.add(new FourAddr("+",tree_pro.get(L1).getOffset(),newtemp1,newtemp2));
    	//System.out.println(code2); 	
    }
    
    
    //  B -> B1 or M B2  {backpatch(B1.falselist,M.quad); 
    //                    B.truelist=merge(B1.truelist,B2.truelist); 
    //                    B.falselist=B2.falselist}
    public static void semantic_25(Tree tree)
    {
       	int B = tree.getFather().getId();  // B 	
       	int B1 = tree.getChildren().get(0).getId();  // B1
       	int M = tree.getChildren().get(2).getId();  // M
       	int B2 = tree.getChildren().get(3).getId();  // B2
       	
       	backpatch(tree_pro.get(B1).getFalse(),tree_pro.get(M).getQuad());
       	
    	Properties a1 = new Properties();
    	a1.setTrue(merge(tree_pro.get(B1).getTrue(),tree_pro.get(B2).getTrue()));
    	a1.setFalse(tree_pro.get(B2).getFalse());
    	tree_pro.set(B,a1);
    }
    
    
    //  B -> B1  {B.truelist=B1.truelist; B.falselist=B1.falselist}
    public static void semantic_26_28(Tree tree)
    {	
       	int B = tree.getFather().getId();  // B 	
       	int B1 = tree.getChildren().get(0).getId();  // B1
       	
    	Properties a1 = new Properties();
    	a1.setTrue(tree_pro.get(B1).getTrue());
    	a1.setFalse(tree_pro.get(B1).getFalse());
    	tree_pro.set(B,a1);
    }
    
    
    //  B -> B1 and M B2  {backpatch(B1.truelist M.quad); B.truelist=B2.truelist; 
    //                     B.falselist=merge(B1.falselist, B2.falselist)}
    public static void semantic_27(Tree tree)
    {
       	int B = tree.getFather().getId();  // B 	
       	int B1 = tree.getChildren().get(0).getId();  // B1
       	int M = tree.getChildren().get(2).getId();  // M
       	int B2 = tree.getChildren().get(3).getId();  // B2
       	
       	backpatch(tree_pro.get(B1).getTrue(),tree_pro.get(M).getQuad());
       	
    	Properties a1 = new Properties();
    	a1.setTrue(tree_pro.get(B2).getTrue());
    	a1.setFalse(merge(tree_pro.get(B1).getFalse(),tree_pro.get(B2).getFalse()));
    	tree_pro.set(B,a1);
    }
    
    //  B -> not B1  {B.truelist=B1.falselist; B.falselist=B1.truelist}
    public static void semantic_29(Tree tree)
    {
       	int B = tree.getFather().getId();  // B 	
       	int B1 = tree.getChildren().get(1).getId();  // B1
       	
    	Properties a1 = new Properties();
    	a1.setTrue(tree_pro.get(B1).getFalse());
    	a1.setFalse(tree_pro.get(B1).getTrue());
    	tree_pro.set(B,a1);
    }
    
    
    //  B -> ( B1 )  {B.truelist := B1.truelist; B.falselist := B1.falselist}
    public static void semantic_30(Tree tree)
    {
       	int B = tree.getFather().getId();  // B 	
       	int B1 = tree.getChildren().get(1).getId();  // B1
       	
    	Properties a1 = new Properties();
    	a1.setTrue(tree_pro.get(B1).getTrue());
    	a1.setFalse(tree_pro.get(B1).getFalse());
    	tree_pro.set(B,a1);
    }
    
    
    //  B -> E1 R E2  {B.truelist=makelist(nextquad); B.falselist= makelist(nextquad+1); 
    //                gencode('if' E1.addr relop.op E2.addr 'goto –'); gencode('goto –')}
    public static void semantic_31(Tree tree)
    {
       	int B = tree.getFather().getId();  // B	
       	int E1 = tree.getChildren().get(0).getId();  // E1	
       	int R = tree.getChildren().get(1).getId();  // R	
       	int E2 = tree.getChildren().get(2).getId();  // E2	
    	
    	Properties a1 = new Properties();
    	a1.setTrue(makelist(nextquad()));
    	a1.setFalse(makelist(nextquad()+1));
    	tree_pro.set(B,a1);

    	String code1 = "if " + tree_pro.get(E1).getAddr() + tree_pro.get(R).getName()
    			+ tree_pro.get(E2).getAddr() + " goto ";
    	three_addr.add(code1);
    	four_addr.add(new FourAddr("j" + tree_pro.get(R).getName(),
    			tree_pro.get(E1).getAddr(),tree_pro.get(E2).getAddr(),"-"));
    	
    	String code2 = "goto ";
    	three_addr.add(code2);
    	four_addr.add(new FourAddr("j","-","-","-"));
    	//System.out.println(code1); 
    	//System.out.println(three_addr.get(three_addr.size()-2)); 
    }
    
    //  B -> true  {B.truelist=makelist(nextquad); gencode('goto –')}
    public static void semantic_32(Tree tree)
    {
       	int B = tree.getFather().getId();  // B	
    	
    	Properties a1 = new Properties();
    	a1.setTrue(makelist(nextquad()));
    	tree_pro.set(B,a1);

    	String code = "goto ";
    	three_addr.add(code);
    	four_addr.add(new FourAddr("j","-","-","-"));
    	//System.out.println(code); 
    }
    
    //  B -> false  {B.falselist=makelist(nextquad); gencode('goto –')}
    public static void semantic_33(Tree tree)
    {
       	int B = tree.getFather().getId();  // B	
    	
    	Properties a1 = new Properties();
    	a1.setFalse(makelist(nextquad()));
    	tree_pro.set(B,a1);

    	String code = "goto ";
    	three_addr.add(code);
    	four_addr.add(new FourAddr("j","-","-","-"));
    	//System.out.println(code); 
    }
    
    //  R -> < | <= | == | != | > | >=  {R.name=op}
    public static void semantic_34to39(Tree tree)
    {
       	int R = tree.getFather().getId();  // R	
       	String op = tree.getChildren().get(0).getValue();  // op	
       	
    	Properties a1 = new Properties();
    	a1.setName(op);
    	tree_pro.set(R,a1);
    }
    
    //  S -> S1  {S.nextlist=S1.nextlist}
    public static void semantic_40_41_50(Tree tree)
    {
       	int S = tree.getFather().getId();  // S	
       	int S1 = tree.getChildren().get(0).getId();  // S1	
       	
    	Properties a1 = new Properties();
    	List<Integer> li = tree_pro.get(S1).getNext();
    	//if (li!=null)
    	a1.setNext(tree_pro.get(S1).getNext());
    	tree_pro.set(S,a1);
    }
    
    //  S -> if B then M1 S1 N else M2 S2
    //  {backpatch(B.truelist, M1.quad); backpatch(B.falselist,M2.quad); 
    //  S.nextlist=merge(S1.nextlist,merge(N.nextlist, S2.nextlist))}
    public static void semantic_42_44(Tree tree)
    {
       	int S = tree.getFather().getId();  // S	
       	int B = tree.getChildren().get(1).getId();  // B	
       	int M1 = tree.getChildren().get(3).getId();  // M1
       	int S1 = tree.getChildren().get(4).getId();  // S1
       	int N = tree.getChildren().get(5).getId();  // N
       	int M2 = tree.getChildren().get(7).getId();  // M2
       	int S2 = tree.getChildren().get(8).getId();  // S2
       	
       	backpatch(tree_pro.get(B).getTrue(), tree_pro.get(M1).getQuad());
       	backpatch(tree_pro.get(B).getFalse(), tree_pro.get(M2).getQuad());
    	Properties a1 = new Properties();
    	a1.setNext(merge(tree_pro.get(S1).getNext(),
    			merge(tree_pro.get(N).getNext(), tree_pro.get(S2).getNext())));
    	tree_pro.set(S,a1);
    }
    
    
    //  S -> while M1 B do M2 S1  {backpatch(S1.nextlist, M1.quad); 
    //       backpatch(B.truelist,M2.quad); S.nextlist=B.falselist; gencode('goto'M1.quad)}
    public static void semantic_43(Tree tree)
    {
       	int S = tree.getFather().getId();  // S	
       	int M1 = tree.getChildren().get(1).getId();  // M1
       	int B = tree.getChildren().get(2).getId();  // B	     	
       	int M2 = tree.getChildren().get(4).getId();  // M2
       	int S1 = tree.getChildren().get(5).getId();  // S1

       	backpatch(tree_pro.get(S1).getNext(), tree_pro.get(M1).getQuad());
       	backpatch(tree_pro.get(B).getTrue(), tree_pro.get(M2).getQuad());
    	Properties a1 = new Properties();
    	a1.setNext(tree_pro.get(B).getFalse());
    	tree_pro.set(S,a1);
    	
    	String code = "goto " + tree_pro.get(M1).getQuad();
    	three_addr.add(code);
    	four_addr.add(new FourAddr("j","-","-",String.valueOf(tree_pro.get(M1).getQuad())));
    	//System.out.println(code); 
    }
    
    //  S -> if B then M S1  {backpatch(B.truelist,M.quad); 
    //                        S.nextlist=merge(B.falselist,S1.nextlist)}
    public static void semantic_45(Tree tree)
    {
       	int S = tree.getFather().getId();  // S	
       	int B = tree.getChildren().get(1).getId();  // B	
       	int M = tree.getChildren().get(3).getId();  // M
       	int S1 = tree.getChildren().get(4).getId();  // S1

       	backpatch(tree_pro.get(B).getTrue(), tree_pro.get(M).getQuad());

    	Properties a1 = new Properties();
    	a1.setNext(merge(tree_pro.get(B).getFalse(), tree_pro.get(S1).getNext()));
    	tree_pro.set(S,a1);
    }
    
    //  S -> begin S1 end  {S.nextlist=S1.nextlist}
    public static void semantic_46_47_48(Tree tree)
    {
       	int S = tree.getFather().getId();  // S	
       	int S1 = tree.getChildren().get(1).getId();  // S1	
       	
    	Properties a1 = new Properties();
    	a1.setNext(tree_pro.get(S1).getNext());
    	tree_pro.set(S,a1);
    }
    
    
    //  S -> S1 ; M S2  {backpatch(S1.nextlist, M.quad); S.nextlist=S2.nextlist}
    public static void semantic_49(Tree tree)
    {
       	int S = tree.getFather().getId();  // S	
       	int S1 = tree.getChildren().get(0).getId();  // S1	
       	int M = tree.getChildren().get(2).getId();  // M
       	int S2 = tree.getChildren().get(3).getId();  // S2

       	backpatch(tree_pro.get(S1).getNext(), tree_pro.get(M).getQuad());

    	Properties a1 = new Properties();
    	a1.setNext(tree_pro.get(S2).getNext());
    	tree_pro.set(S,a1);
    }
        
    //  {t := mktable(nil); push(t, tblptr); push(0, offset)}
    //  M0 -> ε {offset=0;}
    public static void semantic_51()
    {
    	mktable();
    	int size = table.size()-1;
    	tblptr.push(size);
    	off.push(0);
    	offset = 0;
    }
    
    //  M -> ε  {M.quad=nextquad}
    public static void semantic_52(Tree tree)
    {
       	int M = tree.getFather().getId();  // M	
       	
    	Properties a1 = new Properties();
    	a1.setQuad(nextquad());
    	tree_pro.set(M,a1);
    }
    
    //  N -> ε  {N.nextlist=makelist(nextquad); gencode('goto –')}
    public static void semantic_53(Tree tree)
    {
       	int N = tree.getFather().getId();  // N	
       	
    	Properties a1 = new Properties();
    	a1.setNext(makelist(nextquad()));
    	tree_pro.set(N,a1);
    	
    	String code = "goto ";
    	three_addr.add(code);
    	four_addr.add(new FourAddr("j","-","-","-"));
    	//System.out.println(code); 
    }
    

    
    //  S -> call id ( EL )  
    //  {n=0; for queue中的每个t do {gencode('param't); n=n+1} 
    //   gencode('call'id.addr','n);}
    public static void semantic_54(Tree tree)
    {
    	int S = tree.getFather().getId();  // S
    	String id = tree.getChildren().get(1).getValue();  // id
    	int[] index = lookup(id);
    	
    	if (!table.get(index[0]).get(index[1]).getType().equals("函数"))
    	{
    		String s = "Error at Line [" +  tree.getChildren().get(0).getLine() 
    				+ "]:\t[" + id + "不是函数,不能用于call语句]" ;   		
    		errors.add(s);   
        	Properties a1 = new Properties();    	
        	a1.setNext(new ArrayList<Integer>());
        	tree_pro.set(S,a1);
        	return;
    	}
    	
    	int size = queue.size();
    	for (int i=0; i<size; i++)
    	{
        	String code = "param " + queue.get(i);
        	three_addr.add(code);
        	four_addr.add(new FourAddr("param","-","-",queue.get(i)));
    	}
    	String code = "call " + id + " " + size;
    	three_addr.add(code);
    	four_addr.add(new FourAddr("call",String.valueOf(size),"-",id));
    	
    	Properties a1 = new Properties();    	
    	a1.setNext(new ArrayList<Integer>());
    	tree_pro.set(S,a1);
    }
    
    
    //  EL -> EL , E  {将E.addr添加到queue的队尾}
    public static void semantic_55(Tree tree)
    {
    	int E = tree.getChildren().get(2).getId();  // E
    	queue.add(tree_pro.get(E).getAddr());
    }
    
    
    //  EL -> E  {初始化queue,然后将E.addr加入到queue的队尾}
    public static void semantic_56(Tree tree)
    {
    	int E = tree.getChildren().get(0).getId();  // E
    	queue.clear();
    	queue.add(tree_pro.get(E).getAddr());
    }
    
    
    //  D -> proc id; N1 D S 
    // {t=top(tblptr); addwidth(t, top(offset));
    //  pop(tblptr); pop(offset); enterproc(top(tblptr), id.name,t)}
    public static void semantic_57(Tree tree)
    {
    	String id = tree.getChildren().get(1).getValue();
    	int t = tblptr.peek();
    	//tablesize.add(off.peek());
    	tblptr.pop();
    	off.pop();
    	
    	enter(tblptr.peek(), id, "函数", t);
    }
    
    //  N1 -> ε {t:= mktable(top(tblptr)); push(t, tblptr); push(0, offset)}
    public static void semantic_58(Tree tree)
    {    	
    	mktable();
    	int size = table.size()-1;
    	tblptr.push(size);
    	off.push(0);
    }
    
    //  N2 -> ε {t:= mktable(nil); push(t, tblptr); push(0, offset)}
    public static void semantic_59(Tree tree)
    {    	
    	mktable();
    	int size = table.size()-1;
    	tblptr.push(size);
    	off.push(0);
    }
    
    public static void findSemantic(Tree tree)
    {
    	String s = util.treeToPro(tree);
    	//System.out.println(s);

    	
    	if (s.equals("P -> proc id ; M0 begin D S end"))
    	{
    		semantic_1(tree);
    	}
    	else if (s.equals("S -> S M S"))
    	{
    		semantic_3(tree);
    	}
    	else if (s.equals("D -> T id ;"))
    	{
    		semantic_5(tree);
    	}
    	else if (s.equals("T -> X C"))
    	{
    		semantic_6(tree);
    	}
    	else if (s.equals("T -> record N2 D end"))
    	{
    		semantic_7(tree);
    	}
    	else if (s.equals("X -> integer"))
    	{
    		semantic_8(tree);
    	}
    	else if (s.equals("X -> real"))
    	{
    		semantic_9(tree);
    	}
    	else if (s.equals("C -> [ num ] C"))
    	{
    		semantic_10(tree);
    	}
    	else if (s.equals("C ->"))
    	{
    		semantic_11(tree);
    	}
    	else if (s.equals("S -> id = E ;"))
    	{
    		semantic_12(tree);
    	}
    	else if (s.equals("S -> L = E ;"))
    	{
    		semantic_13(tree);
    	}
    	else if (s.equals("E -> E + E1"))
    	{
    		semantic_14(tree);
    	}
    	else if (s.equals("E -> E1") || s.equals("E1 -> E2"))
    	{
    		semantic_15_17(tree);
    	}
    	else if (s.equals("E1 -> E1 * E2"))
    	{
    		semantic_16(tree);
    	}
    	else if (s.equals("E2 -> ( E )"))
    	{
    		semantic_18(tree);
    	}
    	else if (s.equals("E2 -> - E"))
    	{
    		semantic_19(tree);
    	}
    	else if (s.equals("E2 -> id"))
    	{
    		semantic_20(tree);
    	}
    	else if (s.equals("E2 -> num"))
    	{
    		semantic_21(tree);
    	}
    	else if (s.equals("E2 -> L"))
    	{
    		semantic_22(tree);
    	}
    	else if (s.equals("L -> id [ E ]"))
    	{
    		semantic_23(tree);
    	}
    	else if (s.equals("L -> L [ E ]"))
    	{
    		semantic_24(tree);
    	}
    	else if (s.equals("B -> B or M B1"))
    	{
    		semantic_25(tree);
    	}
    	else if (s.equals("B -> B1") || s.equals("B1 -> B2"))
    	{
    		semantic_26_28(tree);
    	}
    	else if (s.equals("B1 -> B1 and M B2"))
    	{
    		semantic_27(tree);
    	}
    	else if (s.equals("B2 -> not B"))
    	{
    		semantic_29(tree);
    	}
    	else if (s.equals("B2 -> ( B )"))
    	{
    		semantic_30(tree);
    	}
    	else if (s.equals("B2 -> E R E"))
    	{
    		//System.out.println(s);
    		semantic_31(tree);
    	}
    	else if (s.equals("B2 -> true"))
    	{
    		semantic_32(tree);
    	}
    	else if (s.equals("B2 -> false"))
    	{
    		semantic_33(tree);
    	}
    	else if (s.equals("R -> <") || s.equals("R -> <=") || s.equals("R -> ==") 
    			|| s.equals("R -> !=") || s.equals("R -> >") || s.equals("R -> >="))
    	{
    		semantic_34to39(tree);
    	}
    	else if (s.equals("S -> S1") || s.equals("S -> S2") || s.equals("S3 -> S"))
    	{
    		semantic_40_41_50(tree);
    	}
    	else if (s.equals("S1 -> if B then M S1 N else M S1") || 
    			s.equals("S2 -> if B then M S1 N else M S2"))
    	{
    		semantic_42_44(tree);
    	}
    	else if (s.equals("S1 -> while M B do M S0"))
    	{
    		semantic_43(tree);
    	}
    	else if (s.equals("S2 -> if B then M S0"))
    	{
    		semantic_45(tree);
    	}
    	else if (s.equals("S0 -> begin S3 end") || s.equals("S1 -> begin S3 end") ||
    			s.equals("S2 -> begin S3 end"))
    	{
    		semantic_46_47_48(tree);
    	}
    	else if (s.equals("S3 -> S3 ; M S"))
    	{
    		semantic_49(tree);
    	}
    	else if (s.equals("M0 ->"))
    	{
    		semantic_51();
    	}
    	else if (s.equals("M ->"))
    	{
    		semantic_52(tree);
    	}
    	else if (s.equals("N ->"))
    	{
    		semantic_53(tree);
    	}
    	else if (s.equals("S -> call id ( EL ) ;"))
    	{
    		semantic_54(tree);
    	}
    	else if (s.equals("EL -> EL , E"))
    	{
    		semantic_55(tree);
    	}
    	else if (s.equals("EL -> E"))
    	{
    		semantic_56(tree);
    	}
    	else if (s.equals("D -> proc id ; N1 begin D S end"))
    	{
    		semantic_57(tree);
    	}
    	else if (s.equals("N1 ->"))
    	{
    		semantic_58(tree);
    	}
    	else if (s.equals("N2 ->"))
    	{
    		semantic_59(tree);
    	}
    }
}
