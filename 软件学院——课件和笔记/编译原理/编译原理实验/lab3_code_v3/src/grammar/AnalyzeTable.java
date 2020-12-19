package grammar;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class AnalyzeTable 
{
	
	public static String error = "--";  // 错误符号
	public static String acc = "acc";  // ACC，接收成功符号
	public DFAStateSet dfa;  // 所有DFA状态
	public int stateNum;  // DFA状态数
	
	public int actionLength;  // Action表列数
	public int gotoLength;  // GoTo表列数
	private String[] actionCol;  // Action表列名数组
	private String[] gotoCol;  // GoTo表列名数组
	private String[][] actionTable;  // Action表，二维数组
	private int[][] gotoTable;  // GoTo表，二维数组
	
	//  当第x号DFA状态,输入S符号时,转移到第y号DFA状态,则:
	private ArrayList<Integer> gotoStart = new ArrayList<Integer>();  // 存储第x号DFA状态
	private ArrayList<Integer> gotoEnd = new ArrayList<Integer>();  // 存储第y号DFA状态
	private ArrayList<String> gotoPath = new ArrayList<String>();  // 存储S符号
	
	/**
	 * 构造分析表
	 */
	public AnalyzeTable()
	{
		createTableHeader();//建表
		this.actionLength = actionCol.length;
		this.gotoLength = gotoCol.length;
		createDFA();//创建DFA
		this.stateNum = dfa.size();
		this.gotoTable = new int[stateNum][gotoLength+actionLength-1];
		this.actionTable = new String[stateNum][actionLength];
		createAnalyzeTable();//填充语法分析表的相关内容
	}
	
	/**
	 * 利用这个方法建立一个LR(1)语法分析表的表头
	 */
	private void createTableHeader()
	{
		//以下是建立一个表的列
		this.actionCol = new String[GrammarProc.VT.size()+1];
		this.gotoCol = new String[GrammarProc.VN.size()+GrammarProc.VT.size()];
		Iterator<String> iter1 = GrammarProc.VT.iterator();
		Iterator<String> iter2 = GrammarProc.VN.iterator();
		int i = 0;
		int j = 0;
		while(iter1.hasNext())  // 终结符集合
		{
			String vt = iter1.next();
			if(!vt.equals(GrammarProc.emp))
			{
				actionCol[i] = vt;
				gotoCol[i] = vt;
				i++;
			}
		}
		actionCol[i] = "#";
		while(iter2.hasNext())
		{
			String vn = iter2.next();
			gotoCol[i] = vn;
			i++;
		}
	}
	
	//private ArrayList<DFAState> stateList = new ArrayList<DFAState>();//用于下列递归方法的一个公共的容器

	/**
	 * 利用这个递归方法建立一个用于语法分析的DFA
	 * 不再有项集可插入的判定标准为:
	 */
	private void createDFA()
	{
		this.dfa = new DFAStateSet();
		DFAState state0 = new DFAState(0);
		state0.addNewDerivation(new ProductionState(getDerivation("P'").get(0),"#",0));  // 首先加入S'->·S,#
		for(int i = 0;i < state0.set.size();i++)
		{
			ProductionState lrd = state0.set.get(i);
			if(lrd.index < lrd.d.list.size())  // 非规约状态
			{
				String A = lrd.d.list.get(lrd.index);  // 获取"."后面的符号
				//String b = null;  // 紧跟A的符号，即"."后面的后面的的符号
				Set<String> firstB = new HashSet<String>();
				if(lrd.index==lrd.d.list.size()-1)  // 类似于“A->BBB.C, #”的状态
				{
					//b = lrd.lr;
					firstB.add(lrd.lr);
				} 
				else 
				{
					//b = lrd.d.list.get(lrd.index+1);
					//firstB = first(lrd.d.list.get(lrd.index+1));
					Boolean flag = true;
					for(int m = lrd.index+1; m < lrd.d.list.size(); m++)
					{
						Set<String> list1 = first(lrd.d.list.get(m));	
						firstB.addAll(list1);
						if(!list1.contains("ε"))
						{
							flag = false;
							break;
						}						
					}
					if(flag)
					{
						firstB.add(lrd.lr);
					}
				}
				if(GrammarProc.VN.contains(A))
				{
					ArrayList<Production> dA = getDerivation(A);
					for(int j=0,length1=dA.size();j<length1;j++)
					{
						for(String f:firstB)
						{
							if(!f.equals("ε"))
							{
								ProductionState lrd1;
								if(dA.get(j).list.get(0).equals("ε"))
								{
									lrd1 = new ProductionState(dA.get(j), f, 1);		
								}
								else
								{
									lrd1 = new ProductionState(dA.get(j), f, 0);		
								}					
								if(!state0.contains(lrd1))
								{
									state0.addNewDerivation(lrd1);
								}
							}
						}
					}
				}
			}
		}
		dfa.states.add(state0);
		//state0建立成功后开始递归建立其他的状态
		ArrayList<String> gotoPath = state0.getGotoPath();
		for(String path:gotoPath)
		{
			ArrayList<ProductionState> list = state0.getLRDs(path);//直接通过路径传到下一个状态的情况
			addState(0,path,list);//开始进行递归，建立用于分析的DFA
		}
	}
	
	/**
	 * 通过输入一个从上一个状态传下来的LR产生式的list获取下一个状态，
	 * 如果该状态已经存在，则不作任何操作，跳出递归，如果该状态不存在，则加入该状态，继续进行递归
	 * @param list
	 * @param lastState 上一个状态的编号
	 */
	private void addState(int lastState,String path,ArrayList<ProductionState> list)
	{
		DFAState temp = new DFAState(0);
		for(int i = 0;i < list.size();i++)
		{
			list.get(i).index++;
			temp.addNewDerivation(list.get(i));
		}
		for(int i = 0;i < temp.set.size();i++)
		{
			if(temp.set.get(i).d.list.size() != temp.set.get(i).index)  // 非规约
			{
				String A = temp.set.get(i).d.list.get(temp.set.get(i).index);
						
				Set<String> firstB = new HashSet<String>();
				if(temp.set.get(i).index+1 == temp.set.get(i).d.list.size())  // 类似于“A->BBB.C, #”的状态
				{
					firstB.add(temp.set.get(i).lr);
				} 
				else 
				{
					Boolean flag = true;
					for(int m = temp.set.get(i).index+1; m < temp.set.get(i).d.list.size(); m++)
					{
						Set<String> list1 = first(temp.set.get(i).d.list.get(m));	
						firstB.addAll(list1);
						if(!list1.contains("ε"))
						{
							flag = false;
							break;
						}						
					}
					if(flag)
					{
						firstB.add(temp.set.get(i).lr);
					}
				}
							
				ArrayList<Production> dA = getDerivation(A);

				//ArrayList<String> firstB = first(B);
				for(int j = 0;j < dA.size();j++)
				{
					for(String f:firstB)
					{
						if(!f.equals("ε"))
						{
							ProductionState lrd;
							if(dA.get(j).list.get(0).equals("ε"))
							{
								lrd = new ProductionState(dA.get(j), f, 1);		
							}
							else
							{
								lrd = new ProductionState(dA.get(j), f, 0);	
							}	
							if(!temp.contains(lrd))
							{
								temp.addNewDerivation(lrd);
							}
						}
					}
				}
			}
		}
		for(int i = 0;i < dfa.states.size();i++)
		{
			if(dfa.states.get(i).equalTo(temp))
			{
				gotoStart.add(lastState);
				gotoEnd.add(i);
				gotoPath.add(path);
				return;
			}
		}
		temp.id = dfa.states.size();
		dfa.states.add(temp);
		gotoStart.add(lastState);
		gotoEnd.add(temp.id);
		gotoPath.add(path);
		ArrayList<String> gotoPath = temp.getGotoPath();
		for(String p:gotoPath)
		{
			ArrayList<ProductionState> l = temp.getLRDs(p);//直接通过路径传到下一个状态的情况
			addState(temp.id,p,l);
		}
	}
	
	/**
	 * 获取产生式左部为v的产生式列表
	 * @param v
	 * @return
	 */
	public ArrayList<Production> getDerivation(String v)
	{
		ArrayList<Production> result = new ArrayList<Production>();
		Iterator<Production> iter = GrammarProc.F.iterator();
		while(iter.hasNext())
		{
			Production d = iter.next();
			if(d.left.equals(v))
			{
				result.add(d);
			}
		}
		return result;
	}
	
	/**
	 * 用于获取文法符号v的first集列表
	 * @param v
	 * @return
	 */
	private Set<String> first(String v)
	{
		Set<String> result = new HashSet<String>();
		if(v.equals("#"))
		{
			result.add("#");
		} 
		else 
		{
			//System.out.println(v);
			Iterator<String> iter = GrammarProc.firstMap.get(v).iterator();
			while(iter.hasNext())
			{
				result.add(iter.next());
			}
		}
		return result;
	}
	
	/**
	 * 利用这个方法填充语法分析表的相关内容
	 */
	private void createAnalyzeTable()
	{
		for(int i = 0;i < gotoTable.length; i++)
		{
			for(int j = 0;j < gotoTable[0].length;j++)
			{
				gotoTable[i][j] = -1;
			}
		}
		for(int i = 0;i < actionTable.length;i++)
		{
			for(int j = 0;j < actionTable[0].length;j++)
			{
				actionTable[i][j] = AnalyzeTable.error;
			}
		}
		//完善语法分析表的goto部分
		int gotoCount = this.gotoStart.size();
		for(int i = 0;i < gotoCount;i++)
		{
			int start = gotoStart.get(i);
			int end = gotoEnd.get(i);
			String path = gotoPath.get(i);
			int pathIndex = gotoIndex(path);
			//if(this.gotoTable[start][pathIndex]!=-1)
			//	System.out.println("vvvvvvvvvvvvvvvvvvvvvvvvvvv");
			this.gotoTable[start][pathIndex] = end;
		}
		//完善语法分析表的action部分
		int stateCount = dfa.states.size();
		for(int i = 0;i < stateCount;i++)
		{
			DFAState state = dfa.get(i);//获取dfa的单个状态
			for(ProductionState lrd:state.set)
			{//对每一个进行分析
				if(lrd.index == lrd.d.list.size())
				{
					if(!lrd.d.left.equals("P'"))
					{
						int derivationIndex = derivationIndex(lrd.d);
						String value = "r"+derivationIndex;
						//if(!actionTable[i][actionIndex(lrd.lr)].equals("--"))
							//System.out.println("vvvvvvvvvvvvvvvvvvvvvvvvvvv");
						actionTable[i][actionIndex(lrd.lr)] = value;//设为规约
					} 
					else 
					{
						actionTable[i][actionIndex("#")] = AnalyzeTable.acc;//设为接受
					}
				} 
				else 
				{
					String next = lrd.d.list.get(lrd.index);//获取·后面的文法符号
					if(GrammarProc.VT.contains(next))
					{//必须是一个终结符号
						//if(!actionTable[i][actionIndex(next)].equals("--"))
							//System.out.println("vvvvvvvvvvvvvvvvvvvvvvvvvvv");
						if(gotoTable[i][gotoIndex(next)] != -1)
						{
							actionTable[i][actionIndex(next)] = "s"+gotoTable[i][gotoIndex(next)];
						}
					}
				}
			}
		}
	}
	
	private int gotoIndex(String s)
	{//返回goto中的列数
		for(int i = 0;i < gotoLength;i++)
		{
			if(gotoCol[i].equals(s))
			{
				return i;
			}
		}
		return -1;
	}
	
	private int actionIndex(String s)
	{//返回action中的列数
		for(int i = 0;i < actionLength;i++)
		{
			if(actionCol[i].equals(s))
			{
				return i;
			}
		}
		return -1;
	}
	
	private int derivationIndex(Production d)
	{//返回是第几个表达式
		int size = GrammarProc.F.size();
		for(int i = 0;i < size;i++)
		{
			if(GrammarProc.F.get(i).equals(d))
			{
				return i;
			}
		}
		return -1;
	}
	
	public String ACTION(int stateIndex,String vt)
	{
		int index = actionIndex(vt);
		return actionTable[stateIndex][index];
	}
	
	public int GOTO(int stateIndex,String vn)
	{
		int index = gotoIndex(vn);
		return gotoTable[stateIndex][index];
	}
	
	/**
	 * 打印语法分析表
	 */
	public void print()
	{
		StringBuffer result = new StringBuffer();
		String colLine = form("");
		for(int i = 0;i < actionCol.length;i++)
		{
			if(!actionCol[i].equals("integer")&&!actionCol[i].equals("record"))
				colLine += "\t";
			colLine += form(actionCol[i]);
		}
		for(int j = actionCol.length-1;j < gotoCol.length;j++)
		{
			colLine += "\t";
			colLine += form(gotoCol[j]);
		}
		result.append(colLine + "\n");
		//System.out.println(colLine);
		int index = 0;
		for(int i = 0;i < dfa.states.size();i++)
		{
			String line = form(String.valueOf(i));
			while(index < actionCol.length)
			{
				line += "\t";
				line += form(actionTable[i][index]);
				index++;
			}
			index = actionCol.length-1;
			while(index < gotoCol.length)
			{
				line += "\t";
				if(gotoTable[i][index] == -1)
				{
					line += form("--");
				} 
				else 
				{
					line += form(String.valueOf(gotoTable[i][index]));
				}
				index++;
			}
			index = 0;
			line += "\t";
			result.append(line + "\n");
			writefile(result);
			//System.out.println(line);
		}
	}
	
	public String form(String str)
	{
		for(int i = 0; i < 9-str.length(); i++)
		{
			str += " ";
		}
		return str;
	}
	
	
	public void writefile(StringBuffer str)
	{		
        String path = "LR_Analysis_Table.txt";
        try 
        {
            File file = new File(path);
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(str.toString()); 
            bw.close(); 
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
	}
	
	public int getStateNum()
	{
		return dfa.states.size();
	}

}
