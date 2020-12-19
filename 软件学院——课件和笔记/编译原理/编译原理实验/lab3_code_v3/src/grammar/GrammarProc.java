package grammar;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

public class GrammarProc 
{
	public static String emp = "ε";  // 空串
	public static String end = "#";  // 结束符
	public static TreeSet<String> VN = new TreeSet<String>();  // 非终结符集
	public static TreeSet<String> VT = new TreeSet<String>();  // 终结符集
	public static ArrayList<Production> F = new ArrayList<Production>();  // 产生式集
	//  每个符号的first集
	public static HashMap<String,TreeSet<String> > firstMap = new HashMap<String,TreeSet<String>>();
	
	public static HashMap<String,TreeSet<String> > followMap = new HashMap<String,TreeSet<String> >();  // follow
	//public static TreeSet<String> keywords = new TreeSet<String>();  // 关键字集
	static
	{
		// 从文件中读取文法，添加相应的产生式
		try 
		{
			read("grammar.txt");
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}

		// 添加非终结符   18 个
		VN.add("P'");  VN.add("P");   VN.add("D");   VN.add("S");   VN.add("T");  
		VN.add("X");   VN.add("C");   VN.add("E");   VN.add("E1");  VN.add("E2");  
		VN.add("S1");  VN.add("S2");  VN.add("L");   VN.add("B");   VN.add("B1");  
		VN.add("B2");  VN.add("R");   VN.add("EL");  VN.add("S3");  VN.add("S0");
		VN.add("M0");   VN.add("M");  VN.add("N");   VN.add("N1");  VN.add("N2");
		
		// 添加终结符  35 个
		VT.add("proc");  VT.add("id");    VT.add(";");     VT.add("record"); VT.add("integer");
		VT.add("real");  VT.add("[");     VT.add("]");     VT.add("num");    VT.add("=");  
		VT.add("+");     VT.add("*");     VT.add("-");     VT.add("(");      VT.add(")");  
		VT.add("if");    VT.add("then");  VT.add("else");  VT.add("while");  VT.add("do");    
		VT.add("or");     VT.add("and");  VT.add("not");   VT.add("true");   VT.add("false"); 
		VT.add("<");      VT.add("<=");   VT.add("==");    VT.add("!=");     VT.add(">");     
		VT.add(">=");     VT.add("call"); VT.add("begin");     VT.add("end");    VT.add(",");
		//VT.add(emp);  
		
		addFirst();
		//addFollow();
	}
	
	public static void main(String[] args)
	{
		//addFirst();
		//Iterator<String> iterVT = VN.iterator();
		for(int i=0; i<F.size(); i++)
		{
			//String vt = iterVT.next();
			System.out.println(i+":  "+F.get(i).toString());			
		}
	}
	
	/**
	 * 从文件中读取文法并且存储到CFG类的静态容器中，编号就是容器的index
	 * @param filename
	 * @throws FileNotFoundException
	 */
	private static void read(String filename) throws FileNotFoundException
	{
		File file = new File(filename);
		Scanner scanner = new Scanner(file);
		while(scanner.hasNext())
		{
			String line = scanner.nextLine().trim();
			if(!line.equals(""))
			{
				String[] div = line.split("->");
				String[] right = div[1].split("\\|");//将合并书写的多个表达式解析成多个
				for(String r:right)
				{
					Production derivation = new Production(div[0].trim()+"->"+r.trim());
					F.add(derivation);//存储到静态的容器中
				}
			}			
		}
		scanner.close();
	}
	
	/**
	 * 计算所有符号的first集合
	 * 中间需要若干步推导的使用一个递归方法解决问题
	 */
	private static void addFirst()
	{
		//将所有的终结符的first都设为本身
		Iterator<String> iterVT = VT.iterator();
		while(iterVT.hasNext())
		{
			String vt = iterVT.next();
			firstMap.put(vt,new TreeSet<String>());
			firstMap.get(vt).add(vt);
		}
		//计算所有非终结符的first集合
		Iterator<String> iterVN = VN.iterator();
		while(iterVN.hasNext())
		{
			String vn = iterVN.next();
			firstMap.put(vn, new TreeSet<String>());//因为后续操作没有交叉涉及firstMap，所以不必分成两个while循环，合成一趟即可
			firstMap.get(vn).addAll(findFirst(vn));
			/*int dSize = F.size();
			for(int i = 0;i < dSize;i++)
			{
				Production d = F.get(i);
				if(d.left.equals(vn))
				{//其实可以到后面抽象成一个方法获取，这里懒得改了
					if(VT.contains(d.list.get(0)) || d.list.get(0).equals(emp))
					{//如果是产生式右端第一个文法符号是一个终结符或空符号，则直接添加
						firstMap.get(vn).add(d.list.get(0));
					} 
					else 
					{//如果产生式右端第一个文法符号是个非终结符，则需要进行递归查找
						firstMap.get(vn).addAll(findFirst(d.list.get(0)));
					}
				}
			}*/
		}
	}
	
	/**
	 * 一个用于查找first的递归函数
	 * @param vn
	 * @return 返回first集
	 */
	private static TreeSet<String> findFirst(String vn)
	{
		TreeSet<String> set = new TreeSet<String>();
		int size1 = 0;
		int size2 = 0;
		while(true)
		{
			size1 = set.size(); 
			for(Production d:F)
			{
				if(d.left.equals(vn))
				{
					if(VT.contains(d.list.get(0)))  // 终结符，直接加入
					{
						set.add(d.list.get(0));
					} 
					else if(d.list.get(0).equals(emp))  // 空符号，直接加入
					{
						set.add(emp);
					}
					else if(VN.contains(d.list.get(0)))  // 非终结符，递归
					{
						if(!vn.equals(d.list.get(0)))  // 去除类似于E->E*E这样的左递归
						{
							TreeSet<String> set2 = findFirst(d.list.get(0));
							set.addAll(set2);  // 再次递归
							if(set2.contains(emp))
							{
								for(int j=1; j<d.list.size(); j++)
								{
									TreeSet<String> set3 = findFirst(d.list.get(j));									
									set.addAll(set3);  // 再次递归
									if(!set3.contains(emp))
									{
										break;
									}
								}
							}							
						}
					}
				}
			}
			size2 = set.size(); 
			if(size1 == size2)
				break;
		}
		
		return set;
	}
	
	
	/**
	 * 用于计算非终结符的follow
	 */
	private static void addFollow()
	{
		Iterator<String> iterVN = VN.iterator();
		HashMap<String,ArrayList<String> > hashmap = new HashMap<String,ArrayList<String> >();
		while(iterVN.hasNext())
		{
			String vn = iterVN.next();
			followMap.put(vn, new TreeSet<String>());
			hashmap.put(vn, new ArrayList<String>());
			for(Production d:F)
			{
				if(d.list.contains(vn))
				{//这里用一个循环是因为考虑了产生式右端存在多个相同元素
					ArrayList<Integer> index = new ArrayList<Integer>();
					for(int i = 0;i < d.list.size();i++)
					{
						if(d.list.get(i).equals(vn))
						{
							index.add(i);
						}
					}
					for(int i:index)
					{
						if(i == (d.list.size()-1))
						{//如果在一个产生式右端的末尾，则需要加入$
							followMap.get(vn).add(end);
							hashmap.get(vn).add(d.left);
						} 
						else 
						{//如果不在末尾，则直接加上后一个元素的first
							TreeSet<String> add = new TreeSet<String>();
							Iterator<String> iter = firstMap.get(d.list.get(i+1)).iterator();
							while(iter.hasNext())
							{
								String value = iter.next();
								if(!value.equals(emp))
								{//滤掉空串
									add.add(value);
								}
							}
							followMap.get(vn).addAll(add);
						}
					}
				}
			}
			Iterator<String> iter = hashmap.keySet().iterator();
			while(iter.hasNext())
			{
				String key = iter.next();
				ArrayList<String> value = hashmap.get(key);
				if(value.size() != 0)
				{
					for(String v:value)
					{
						followMap.get(key).addAll(followMap.get(v));
						followMap.get(v).addAll(followMap.get(key));
					}
				}
			}
		}
	}

}
