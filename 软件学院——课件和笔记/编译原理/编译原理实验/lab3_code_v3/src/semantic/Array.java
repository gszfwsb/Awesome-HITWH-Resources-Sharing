package semantic;

public class Array
{
	// 以"array(2,array(2,integer))"为例
	private int length;  // 长度：2
	private Array type;  // 数组类型：array(2,integer)
	private String baseType;  // 基本类型：integer

	
	public int getLength()
	{
		return length;
	}
	
	public Array getType()
	{
		return type;
	}
	
	public String getBaseType()
	{
		return baseType;
	}
	
	public void setLength(int length)
	{
		this.length=length;
	}
	
	public void setType(Array type)
	{
		this.type=type;
	}
	
	public void setBaseType(String baseType)
	{
		this.baseType=baseType;
	}

}
