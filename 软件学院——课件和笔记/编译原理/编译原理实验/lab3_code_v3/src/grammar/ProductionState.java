package grammar;

public class ProductionState implements java.lang.Cloneable
{
	public Production d;  // 产生式
	public String lr;  // 后继符
	public int index;  // 后继符位置
	
	/**
	 * DFA状态集中每一个状态
	 * 此时表示类似于“A->BC.D, a”的形式
	 * @param d  产生式
	 * @param lr 后继符
	 * @param index 后继符位置
	 */
	public ProductionState(Production d,String lr,int index)
	{
		this.d = d;
		this.lr = lr;
		this.index = index;
	}
	
	public String toString()
	{
		String result = d.left+"->";
		int length = d.list.size();
		for(int i = 0;i < length;i++)
		{
			if(length == 1 && d.list.get(0).equals("ε"))
			{
				result += " .";
				break;
			}
			else
			{
				result += " ";
				if(i == index)
				{
					result += ".";
				}
				result += d.list.get(i);
			}
		}
		if(index == length && !d.list.get(0).equals("ε"))
		{
			result += ".";
		}
		result += " ,";
		result += lr;
		return result;
	}
	
	public boolean equalTo(ProductionState lrd)
	{
		if(d.equalTo(lrd.d)&&lr.hashCode()==lrd.lr.hashCode()&&index==lrd.index)
		{
			return true;
		} 
		else 
		{
			return false;
		}
	}
	
	public void print()
	{
		System.out.println(this.toString());
	}
	
	public Object clone()
	{
		return new ProductionState(d,lr,index);
	}

}
