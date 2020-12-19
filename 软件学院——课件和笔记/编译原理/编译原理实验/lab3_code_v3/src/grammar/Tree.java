package grammar;

import java.util.ArrayList;

public class Tree
{
	private TreeNode father;  // 父节点
	private ArrayList<TreeNode> children;  // 孩子列表
	
	/**
	 * 语法树的构造函数
	 * @param father 父节点
	 * @param children 孩子列表
	 */
	public Tree(TreeNode father, ArrayList<TreeNode> children)
	{
		this.father = father;
		this.children = children;
	}
	
	public TreeNode getFather() 
	{
		return father;
	}
	
	public ArrayList<TreeNode> getChildren() 
	{
		return children;
	}
	
	
	public void addChild(TreeNode child) 
	{
		children.add(child);
	}
	
	public String toString()
	{
		String result = father.toString() + " -> ";
		for (int i=0; i<children.size(); i++)
		{
			result += children.get(i).toString();
		}
		return result;
	}
	
	
	public void print()
	{
		String result = father.toString() + " -> ";
		for (int i=0; i<children.size(); i++)
		{
			result += children.get(i).toString();
		}
		System.out.println(result);
	}


}
