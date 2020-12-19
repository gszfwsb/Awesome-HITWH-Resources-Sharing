package com.analyze;

import com.analyze.Production;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class GrammerAnalyze {
	//产生式集,终结符集,非终结符集,FIRST集,FOLLOW集
	private ArrayList<Production> productions;
	private ArrayList<String> terminals;
	private ArrayList<String> nonterminals;
	private HashMap<String, ArrayList<String>> firsts;
	private HashMap<String, ArrayList<String>> follows;
	//构造方法
	public GrammerAnalyze() {
			productions = new ArrayList<Production>();
			terminals = new ArrayList<String>();
			nonterminals = new ArrayList<String>();
			firsts = new HashMap<String, ArrayList<String>>();
			follows = new HashMap<String, ArrayList<String>>();	
			//从文件中读取产生式
			readProductions();
			setNonTerminals();
			setTerminals();
			//outputProductions();
			//outputNonterminals();
			//outputTerminals();
			getFirst();
			getFollow();
			getSelect();
			//outputFirst();
			//outputFollow();
			//outputSelect();
			createPredict();
	}
	
	
	//遍历产生式Select
	public void outputSelect() {
		for(int i = 0; i < productions.size(); i++) {
			System.out.println(productions.get(i).select);
		}
	}
	
	//遍历firsts集
	public void outputFollow() {
		for(Entry<String, ArrayList<String>> entry : follows.entrySet()) {
	         entry.getKey();
	         entry.getValue();
	         System.out.print(entry.getKey());
	         System.out.print(" -> ");
	         System.out.print(entry.getValue());
	         System.out.println();
		}
	}
	
	//遍历firsts集
	public void outputFirst() {
		for(Entry<String, ArrayList<String>> entry : firsts.entrySet()) {
             entry.getKey();
             entry.getValue();
             System.out.print(entry.getKey());
             System.out.print(" -> ");
             System.out.print(entry.getValue());
             System.out.println();
		}
	}
	
	//遍历产生式Production
	public void outputProductions() {
		for(int i = 0; i < productions.size(); i++) {
			System.out.printf("%s -> ", productions.get(i).returnLeft());
			for(int j = 0; j < productions.get(i).returnRights().length; j++) {
				System.out.printf("%s ", productions.get(i).returnRights()[j]);
			}
			System.out.println();
		}
	}
	
	//遍历非终结符集
	public void outputNonterminals() {
		for(int i = 0; i < nonterminals.size(); i++) {
			System.out.println(nonterminals.get(i));
		}
	}
	
	//遍历非终结符集
	public void outputTerminals() {
		for(int i = 0; i < terminals.size(); i++) {
			System.out.println(terminals.get(i));
		}
	}
	
	//从文件中读取产生式
	public void readProductions() {
        try {
		    File file = new File("C:\\Users\\57581\\Downloads\\HIT_Compiler_Experiment-master\\HIT_Compiler_Experiment-master\\Compiler2\\src\\grammar.txt");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			String left;
			String right;
			Production production;
			while ((line=reader.readLine())!=null) {
				left = line.split("->")[0].trim();
				right = line.split("->")[1].trim();
				production = new Production(left, right.split(" "));
				productions.add(production);
			}
			reader.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 		
	}
	
	//获得非终结符集
	public void setNonTerminals() {
		try {
			File file = new File("C:\\Users\\57581\\Downloads\\HIT_Compiler_Experiment-master\\HIT_Compiler_Experiment-master\\Compiler2\\src\\grammar.txt");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			String left;
			while ((line=reader.readLine())!=null) {
				left = line.split("->")[0].trim();
				if(nonterminals.contains(left)) 
				  continue;
				else 
				  nonterminals.add(left);
						
				}
				reader.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
	}
	
	//获得终结符集,依赖于获得产生式函数
	public void setTerminals() {
		//遍历所有的产生式
		String[] rights;
		for (int i = 0; i < productions.size(); i++) {
			rights = productions.get(i).returnRights();
			//从右侧寻找终结符
			for (int j = 0; j < rights.length; j++) {
				if(nonterminals.contains(rights[j])||rights[j].equals("$"))
					continue;
				else
				    terminals.add(rights[j]);
			}
		}
	}
	
	//使用已经消除左递归的文法，指导书上的文法实在不好弄。
	//获取First集
	public void getFirst(){
		//终结符全部求出first集
		ArrayList<String> first;
		//求出所有终结符的First集
		for (int i = 0; i < terminals.size(); i++) {
			first = new ArrayList<String>();
			first.add(terminals.get(i));
			firsts.put(terminals.get(i), first);
		}
		
		//给所有非终结符注册一下
		for (int i = 0; i < nonterminals.size(); i++) {
			first = new ArrayList<String>();
			firsts.put(nonterminals.get(i), first);
		}
			
		boolean flag;
		while (true) {
			flag = true;
			String left;
			String right;
			String[] rights;
			for (int i = 0; i < productions.size(); i++) {
				//遍历每一个产生式
				left = productions.get(i).returnLeft();
				rights = productions.get(i).returnRights();
				for (int j = 0; j < rights.length; j++) {
					//遍历每个产生式右部的每个元素
					right = rights[j];
					//right是否存在，遇到空怎么办
					//如果right不为空
					if(!right.equals("$")) {
						for (int l = 0; l < firsts.get(right).size(); l++) {
							if(firsts.get(left).contains(firsts.get(right).get(l))){
								continue;
							}
							else {
								firsts.get(left).add(firsts.get(right).get(l));
								flag=false;
							}
						}
					}
					//如果右部可以为空
					if (isCanBeNull(right)) {
							continue;
					}
					else {
						break;
					}
				}
			}
			if (flag == true) {
				break;
			}				
		}
	//非终结符的first集
	}
	
	//判断是否产生$
	public boolean isCanBeNull(String symbol) {
		String[] rights;
		for (int i = 0; i < productions.size(); i++) {
			//找到产生式
			if (productions.get(i).returnLeft().equals(symbol)) {
				rights = productions.get(i).returnRights();
				if (rights[0].equals("$")) {
					return true;
				}
			}
		}
		return false;
	}
	
	//获得Follow集
	public void getFollow() {
		//所有非终结符的follow集初始化一下
		ArrayList<String> follow;
		for (int i = 0; i < nonterminals.size(); i++) {
			follow = new ArrayList<String>();
			follows.put(nonterminals.get(i), follow);
		}
		//将#加入到follow(S)中
		follows.get("S").add("#");
		
		boolean flag;
		boolean fab;
		while (true) {
			flag = true;
			//循环，遍历所有产生式
			for (int i = 0; i < productions.size(); i++) {
				String left;
				String right;
				String[] rights;
				rights = productions.get(i).returnRights();
				//遍历产生式右部
				for (int j = 0; j < rights.length; j++) {
					right = rights[j];
						
					//非终结符
					if (nonterminals.contains(right)) {
						fab = true;
						for(int k = j+1; k < rights.length; k++) {
						    //查找first集
							for(int v = 0; v < firsts.get(rights[k]).size(); v++) {
									//将后一个元素的first集加入到前一个元素的follow集中
									if(follows.get(right).contains(firsts.get(rights[k]).get(v))) {
										continue;
									}
									else {
										follows.get(right).add(firsts.get(rights[k]).get(v));
										flag=false;
									}
								}
								if (isCanBeNull(rights[k])) {
									continue;
								}
								else {
									fab = false;
									break;
								}
							}
						    //如果存在一个产生式A→αB，或存在产生式A→αBβ且FIRST(β) 包含ε，
						    //那么 FOLLOW(A)中的所有符号都在FOLLOW(B)中
							if(fab) {
								left = productions.get(i).returnLeft();
								for (int p = 0; p < follows.get(left).size(); p++) {
									if (follows.get(right).contains(follows.get(left).get(p))) {
										continue;
									}
									else {
										follows.get(right).add(follows.get(left).get(p));
										flag = false;
									}
								}
							}
						}				
					}
				}
			    //全部处理后跳出循环
				if(flag==true){
					break;
				}	
			}			
		    //清除follow集中的#
		    String left;
		    for (int j = 0; j < nonterminals.size(); j++) {
			    left = nonterminals.get(j);
			    for (int v=0; v<follows.get(left).size(); v++) {
					if(follows.get(left).get(v).equals("#"))
						follows.get(left).remove(v);
			}
		}
	}
	
	//获取Select集 
	public void getSelect() {
		String left;
		String right;
		String[] rights;
		ArrayList<String> follow = new ArrayList<String>();
		ArrayList<String> first = new ArrayList<String>();
			
		for (int i = 0; i < productions.size(); i++) {
			left = productions.get(i).returnLeft();
			rights = productions.get(i).returnRights();
			if(rights[0].equals("$")) {
				// select(i) = follow(A)
				follow = follows.get(left);
				for (int j = 0; j < follow.size(); j++) {
					if(productions.get(i).select.contains(follow.get(j))){
						continue;
					}
					else {
						productions.get(i).select.add(follow.get(j));
					}
				}		
			}
			//如果文法G的第i个产生式为A→aβ，则定义
			//SELECT(i)={a}
			else {
				boolean flag = true;
				for (int j = 0; j < rights.length; j++) {
					right = rights[j];
					first = firsts.get(right);
					for (int v = 0; v < first.size(); v++) {
						if (productions.get(i).select.contains(first.get(v))) {
							continue;
						}
						else {
							productions.get(i).select.add(first.get(v));
						}
					}
					if(isCanBeNull(right)) {
						continue;
					}
					else {
						flag = false;
						break;
					}
				}
				//First集中有空
				if (flag) {
					follow = follows.get(left);
					for (int j = 0; j < follow.size(); j++) {
						if (productions.get(i).select.contains(follow.get(j))) {
							continue;
						}
						else {
							productions.get(i).select.add(follow.get(j));
						}
					}
				}
			}
		}		
	}
	
	//生成产生式
	public void createPredict() {
		Production production;
		String line;
		String[] rights;
		try {
			File file = new File("C:\\Users\\57581\\Downloads\\HIT_Compiler_Experiment-master\\HIT_Compiler_Experiment-master\\Compiler2\\src\\AnalysisTable.txt");
			//if file doesn't exists, then create it
		    if(!file.exists()) {
		        file.createNewFile();
		     }
		    
		    FileWriter fw = new FileWriter(file.getAbsoluteFile());
		    BufferedWriter bw = new BufferedWriter(fw);
		    
			for (int i = 0; i < productions.size(); i++) {
			    production = productions.get(i);
			    for (int j = 0; j < production.select.size(); j++) {
					line = production.returnLeft()+"#"+production.select.get(j)+" ->";
					rights = production.returnRights();
					for (int v = 0; v < rights.length; v++) {
						line = line+" "+rights[v];
					}
					line = line+"\n";
					//写入文件
					bw.write(line);
				}
			}
			bw.close();				
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}			
	}
}
