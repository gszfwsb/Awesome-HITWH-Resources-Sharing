package semantic;

import java.util.List;
import java.util.Stack;

import grammar.Tree;
import grammar.TreeNode;

public class util
{
	public static String arrayString(Array a)
	{
		String b = a.getBaseType();
		if(!b.equals("array"))
		{
			return "array" + "(" + a.getLength() + "," + b + ")";
		}
		else
		{
			return "array" + "(" + a.getLength() + "," + arrayString(a.getType()) + ")";
		}
	}
	
	// "array(3,array(5,array(8,int)))" ---> "array(5,array(8,int))"
	public static String elemType(String s)
	{
		if (!s.contains("array"))
			return "integer";
		
		int i;
		int len = s.length();
		for (i=0; i<len; i++) 
		{
            if (s.charAt(i) == ',') 
            {
            	break;
            }
        }		
		return s.substring(i+1, len-1);
	}
    
	//  "array(3,array(5,array(8,int)))" ---> 3
	public static int typeWidth(String s)
	{
		if (!s.contains("array"))
			return 4;
		
		int i,j=0;
		int len = s.length();
		for (i=0; i<len; i++) 
		{
            if (s.charAt(i) == '(') 
            {
            	j = i;
            }
            if (s.charAt(i) == ',') 
            {
            	break;
            }
        }		
		return Integer.valueOf(s.substring(j+1,i));
	}
	
	public static String treeToPro(Tree tree)
	{
		String result = tree.getFather().getSymbol()+" -> ";
		for(TreeNode c:tree.getChildren())
		{
			result += c.getSymbol();
			result += " ";
		}
		return result.trim();
	}
		
	public static Boolean endPoint(TreeNode t)
	{
		if (t.getValue().equals("--"))
		{
			return false;
		}
		return true;
	}
	
	public static String print_ins(List<String> three_addr, List<FourAddr> four_addr)
	{
		StringBuffer s = new StringBuffer();
		for (int i=0; i<three_addr.size(); i++)
		{
			s.append((Smantic.initial + i) + ":  \t");
			s.append(four_addr.get(i).toString());	
			s.append("  \t");	
			s.append(three_addr.get(i));	
			//System.out.println(three_addr.get(i));
			s.append("\n");
		}
		System.out.println(s);
		return s.toString();			
	}
	public static String print_table(List<Stack<Symbol>> table)
	{
		StringBuffer s = new StringBuffer();
		s.append("name," + "\t" + "type," + "\t" + "offset");	
		s.append("\n");
		for (int i=0; i<table.size(); i++)
		{
			s.append((i)+" \t");	
			s.append(table.get(i).toString());	
			s.append("\n");
		}
		System.out.println(s);
		return s.toString();			
	}
	
	public static String print_errors(List<String> e)
	{
		StringBuffer s = new StringBuffer();
		for (int i=0; i<e.size(); i++)
		{
			s.append(e.get(i));	
			//System.out.println(three_addr.get(i));
			s.append("\n");
		}
		System.out.println(s);
		return s.toString();			
	}
	
	
	public static Object[][] gui_ins(List<String> three_addr, List<FourAddr> four_addr)
	{
		int le = three_addr.size();
		Object[][] ins = new Object[le][3];
		for (int i=0; i<le; i++)
		{
			ins[i][0] = i+1;
			ins[i][1] = four_addr.get(i).toString();
			ins[i][2] = three_addr.get(i);
		}	
		return ins;			
	}
	public static Object[][] gui_table(List<Stack<Symbol>> table)
	{
		int num = 0;
		int le = table.size();
		for (int i=0; i<le; i++)
		{
			for (int j=0; j<table.get(i).size(); j++)
			{
				num++;
			}
		}	
		
		
		Object[][] t = new Object[num+1][4];
		int n = 0;
		for (int i=0; i<le; i++)
		{
			for (int j=0; j<table.get(i).size(); j++)
			{
				t[n][0] = i;
				t[n][1] = table.get(i).get(j).getName();
				t[n][2] = table.get(i).get(j).getType();
				t[n][3] = table.get(i).get(j).getOffset();
				n++;
			}
		}	
		return t;		
	}
	
	public static Object[][] gui_errors(List<String> e)
	{
		int le = e.size();
		Object[][] t = new Object[le][1];
		for (int i=0; i<le; i++)
		{
			t[i][0] = e.get(i);
		}	
		return t;		
	}
}
