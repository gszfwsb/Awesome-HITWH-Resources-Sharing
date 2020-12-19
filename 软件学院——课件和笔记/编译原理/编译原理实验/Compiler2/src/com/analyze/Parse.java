package com.analyze;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Parse {
	private HashMap<String, String> predictMap; 
	private ArrayList<String> inputCache;
	private ArrayList<String> stack;
	private JTable jtable2;
	private JTable jtable4;
	
	public Parse(ArrayList<String> input_cache, JTable jtable2, JTable jtable4){
		predictMap = new HashMap<String, String>();
		this.inputCache = input_cache;
		stack = new ArrayList<String>();
		this.jtable2 = jtable2;
		this.jtable4 = jtable4;
		getPredictMap();
	}
	
	//句法分析
	public void Parsing() {
		//遍历输入缓冲区
		/*for(int i = 0; i < inputCache.size(); i++) {
			System.out.println(inputCache.get(i));
		}*/
		//初始符号压入栈
		stack.add("S");
	    String right;
		String leftandinput;
		String process="";
		//当栈非空，输入缓冲区存在
        while (stack.size()>0 && inputCache.size()>0 ) {
           //输入缓冲区与推导符号串第一个字符相等的话，删掉
			try {
				if(inputCache.get(0).equals(stack.get(stack.size()-1))) {
					inputCache.remove(0);
					stack.remove(stack.size()-1);
					continue;
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			//匹配字符
			leftandinput = stack.get(stack.size()-1)+"-"+inputCache.get(0);
			//能够找到匹配的
			if((right=predictMap.get(leftandinput))!=null) {
                //输出产生式和推导过程
				process = "";
				for (int i = stack.size()-1; i>-1; i--) {
					process = process + stack.get(i)+" ";
				}
                //输出
				DefaultTableModel tableModel = (DefaultTableModel) jtable4.getModel();
                tableModel.addRow(new Object[] {stack.get(stack.size()-1)+" -> "+right, process});
                jtable4.invalidate();
				//删掉产生的字符，压入堆栈
				stack.remove(stack.size()-1);
				if(right.equals("$")) {
					//只弹不压
				}
				//压入后序字符
				else {
					String[] arg = right.split(" ");
					for(int i = arg.length-1; i>-1; i--) {
						//反向压入堆栈
						stack.add(arg[i]);
					}
				}				
			}
			//否则的话报错
			else {
				//重新书写process
				process="";
				for (int i = stack.size()-1; i>-1; i--) {
					process = process + stack.get(i)+ " ";
				}
				//tbmodel_lex_result.addRow(new String[]{process, "ERROR!  无法识别的字符"+input_cache.get(0)+"产生式"+leftandinput});
				DefaultTableModel tableModel = (DefaultTableModel) jtable2.getModel();
                tableModel.addRow(new Object[] { "无法识别的字符:"+ inputCache.get(0),"产生式:"+leftandinput});
                jtable4.invalidate();
				inputCache.remove(0);
			}
		}
	}
	
	//获得预测分析表中的产生式以及对应的select集
	//存储方式为键值对的形式
	public void getPredictMap(){
		String text_line;
		String left;
		String symbol;
		String right;
		try {
			// 初始化
			predictMap = new HashMap<String, String>();
			File file = new File("C:\\Users\\57581\\Downloads\\HIT_Compiler_Experiment-master\\HIT_Compiler_Experiment-master\\Compiler2\\src\\AnalysisTable.txt");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			while ((text_line = reader.readLine())!=null){
				left = text_line.split("#")[0];
				symbol = (text_line.split("#")[1]).split("->")[0].trim();
				right = (text_line.split("#")[1]).split("->")[1].trim();
				predictMap.put(left+"-"+symbol, right);
            }
			reader.close();			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
