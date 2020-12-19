package grammar;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import lexical.Lexical;
import lexical.Token;


public class SyntaxParser 
{
	
	private Lexical lex;  // 词法分析器
	private ArrayList<Token> tokenList = new ArrayList<Token>();  // 从词法分析器获得的所有token
	private int length;  // tokenlist的长度
	private int index;  // 语法分析进行到的位置
	
	private AnalyzeTable table;  //构造的语法分析表
	private Stack<Integer> stateStack;  //用于存储相应的DFA状态号
	private static StringBuffer result = new StringBuffer();  // 保存规约结果
	//private static StringBuffer error = new StringBuffer();  // 保存错误报告结果
	
	public static ArrayList<Tree> tree = new ArrayList<Tree>();  // 语法树
	private Stack<TreeNode> treeNodeID = new Stack<TreeNode>();;  // 用于存储相应的树节点
	private int count;  // 记录节点号
	//private Error error = null;
	
	/*public static void main(String[] args)
	{
		SyntaxParser parser = new SyntaxParser("test1.txt", tree);
		parser.analyze();
		printTree();
		writefile(result);
	}*/
	
	
	/**
	 * 读入测试文件，返回字符串
	 * @param filename 文件名
	 * @return 文件内容
	 */
	public static String readfile(String filename)
	{
		StringBuffer result = new StringBuffer();
		File file = new File(filename);
		try
		{			
			InputStream in = new FileInputStream(file);
			int tempbyte;
			while ((tempbyte=in.read()) != -1) 
			{
				result.append(""+(char)tempbyte);
			}
			in.close();
		}
		catch(Exception event)
		{
			event.printStackTrace();
		}
		return result.toString();
	}
	
	
	public SyntaxParser(String filename, ArrayList<Tree> tree)
	{
		this.lex = new lexical.Lexical(readfile(filename), this.tokenList);
		this.lex.analyze();  // 词法分析
		int last = tokenList.get(tokenList.size()-1).line + 1;
		this.tokenList.add(new Token(last,"#",-1));		
		this.length = this.tokenList.size();
		
		this.index = 0;
		this.table = new AnalyzeTable();  // 生成分析表
		this.stateStack = new Stack<Integer>();  // 状态栈
		this.stateStack.push(0);  // 初始为0状态
		
		this.table.dfa.writefile();  // 写入文件"DFA_state_set.txt"		
		this.table.print();  // 写入文件"LR_analysis_table.txt"
		
		this.tree = tree;
		//this.result = result;
		for(int i = 0;i < tokenList.size();i++)
		{
			//System.out.println(tokenList.get(i).toString());
			result.append(tokenList.get(i).toString() + "\n");
		}
		
		analyze();
		printTree();
		writefile(result);
	}
	
	public Token readToken()
	{
		if(index < length)
		{
			return tokenList.get(index++);
		} 
		else 
		{
			return null;
		}
	}
	
	/**
	 * 返回种别码对应的文法单词
	 * @param valueType
	 * @return
	 */
	private String getValue(Token valueType)
	{
		try
		{
			int code = valueType.code;
			if(code == 1)
				return "id";
			else if(code == 2)
				return "num";
			else if(code < 400 && code >=101)
				return valueType.value;
			else if(valueType.value.equals("#"))
				return "#";
			else
				return " ";
		}
		catch(Exception NullPointerException)
		{
			return "";	
		}
	}
	
	/**
	 * 主体部分 语法分析
	 */
	public void analyze()
	{
		int m = 0;
		int last=1;
		while(true)
		{			
			result.append("当前待输入: ");
			//System.out.print("当前待输入: ");
			printInput();
			result.append("\n\n");
			//System.out.println();
			//System.out.println();
			
			Token token = readToken();
			String value = getValue(token);
			
			if(value.equals(""))
			{
				error();
				m++;
				if(m>50)
				{
					System.out.println("不符合文法");
					break;
				}
				continue;
			}
			else if(value.equals(" "))
			{
				continue;
			}
	
			int state = stateStack.lastElement();
			String action = table.ACTION(state, value);		
			//System.out.println(action);
			if(action.startsWith("s"))
			{
				int newState = Integer.parseInt(action.substring(1));
				stateStack.push(newState);
				//System.out.print("移入"+"\t");
				result.append("移入"+"\t");
				//System.out.print("状态表:"+stateStack.toString()+"\t");
				
				treeNodeID.push(new TreeNode(count++,value,token.value,last));  
			} 
			else if(action.startsWith("r"))
			{
				Production derivation = GrammarProc.F.get(Integer.parseInt(action.substring(1)));
				//System.out.println("dsdsds");
				System.out.println(derivation);
				result.append(derivation + "\n");
				int r = derivation.list.size();
				index--;
				
				LinkedList<TreeNode> son = new LinkedList<TreeNode>(); 
				if(!derivation.list.get(0).equals("ε"))
				{
					for(int i = 0;i < r;i++)
					{				
						stateStack.pop();
						
						son.addFirst(treeNodeID.pop()); 
					}
				}

				int s = table.GOTO(stateStack.lastElement(), derivation.left);
				//System.out.print(s);
				stateStack.push(s);
				//System.out.print("规约"+"\t");
				result.append("规约"+"\t");
				//System.out.print("状态表:"+stateStack.toString()+"\t");
				
				treeNodeID.push(new TreeNode(count++,derivation.left,"--",last)); 
				ArrayList<TreeNode> sonList = new ArrayList<TreeNode>(son);
				tree.add(new Tree(treeNodeID.peek(),sonList));              
			} 
			else if(action.equals(AnalyzeTable.acc))
			{
				System.out.print("语法分析完成"+"\t"+"\n");
				result.append("语法分析完成"+"\t");
				//System.out.print("状态表:"+stateStack.toString()+"\t");
				return;
			} 
			else 
			{
				//m = 0;
				error();
				m++;
				if(m>50)
				{
					System.out.println("不符合文法");
					break;
				}
				while(action.startsWith("r"))
				{
					index = index - 1;
					Token token1 = readToken();
					tokenList.remove(token1);
					
					if(tokenList.size()==0)
						break;
					index = index - 1;
					
					String value1 = getValue(token1);
					stateStack.pop();

					if(value.equals(""))
					{
						error();
						m++;
						if(m>50)
						{
							System.out.println("不符合文法");
							break;
						}
						continue;
					}
					if(value.equals(" "))
						continue;
					
					int state1 = stateStack.lastElement();
					action = table.ACTION(state1, value1);
					//System.out.println(action);					
				}
			}
			if(m>50)
			{
				System.out.println("不符合文法");
				break;
			}
			last = token.line;
		}
	}
	
	

	/**
	 * 出错
	 */
	public void error()
	{
		result.append("Error at Line[" + tokenList.get(index-1).line + "]:  \""+
				tokenList.get(index-1).value + "\" 单词处发现了错误。");
		System.out.println("Error at Line[" + tokenList.get(index-1).line + "]:  \""+
				tokenList.get(index-1).value + "\" 单词处发现了错误。");
	}
	
	/**
	 * 输出结果
	 */
	private static void writefile(StringBuffer str)
	{
        String path = "LR_Analysis_Result.txt";
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
	
	
	/**
	 * 打印即将输入的程序
	 */
	private void printInput()
	{
		String output = "";
		for(int i = index;i < tokenList.size();i++)
		{
			output += tokenList.get(i).value;
			output += " ";
		}
		//System.out.print(output);
		result.append(output);
	}
	
	
	/**
	 * 打印语法树
	 */
	private static void printTree()
	{
		String output = "\n";
		for(int i=0; i<tree.size(); i++)
		{
			output += tree.get(i).toString();
			output += "\n";
		}
		//System.out.print(output);
		result.append(output);
	}
}
