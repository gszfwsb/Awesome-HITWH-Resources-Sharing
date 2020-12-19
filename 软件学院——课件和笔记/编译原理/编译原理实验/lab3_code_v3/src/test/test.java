package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import semantic.Properties;

public class test
{

	public static String left;
	public static ArrayList<String> list = new ArrayList<String>();
	
	public static String elemType(String s)
	{
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
	
	public static int typeWidth(String s)
	{
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
	
	public static void main(String[] args)
	{
		
		String s = "array(3,array(5,array(8,int)))";
		System.out.println(elemType(s));
		System.out.println(typeWidth(s));
		/*List<Properties> tree_pro = Arrays.asList(new Properties[10]);  // ·ûºÅ±í  
		System.out.println(tree_pro.size());
		System.out.println(tree_pro);
		
		Properties a = new Properties();
		a.setBegin(1);
		tree_pro.set(2,a);
		tree_pro.get(2).setBegin(2);;
		
		System.out.println(tree_pro.get(2).getBegin());
		
		String s = tree_pro.get(1).getAddr();
		if(s==null)
			System.out.println("ff");*/
		//System.out.println(tree_pro.get(1).getBegin());
		
	
		//String[] s={"integer","fd"};
		//s[1] = "ff";
		//System.out.println(s);
		/*System.out.println(",".length());
		System.out.println(String.valueOf(122).length());
		System.out.println("".length());*/
		/*System.out.println("P'");
		String s=" D -> D D  ";
		String[] div = s.split("->");
		left = div[0].trim();
		String[] v = div[1].trim().split(" ");
		System.out.println(left);
		System.out.println(div[1].trim());
		
		for(int i = 0;i < v.length;i++)
		{		
			if(!v[i].trim().equals(""))	
			{
				list.add(v[i].trim());
				System.out.println(v[i].trim());
			}		
		}
		System.out.println(list);
				String result = left+"->";
		for(String r:list)
		{
			result += r;
			result += " ";
		}
		result.trim();
		// TODO Auto-generated method stub
*/
	}

}
