package semantic;

public class Symbol
{
	private String name;  // 符号名
	private String type;  // 符号类型
	private int offset;  // 偏移量
	
	/**
	 * 符号表中每一个符号的构造函数
	 * @param name 符号名
	 * @param type 符号类型
	 * @param offset 偏移量
	 */
	public Symbol(String name, String type, int offset)
	{
		this.name = name;
		this.type = type;
		this.offset = offset;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public String getType() 
	{
		return type;
	}
	
	public void setType(String type) 
	{
		this.type = type;
	}
	
	public int getOffset() 
	{
		return offset;
	}
	
	public void setOffset(int offset) 
	{
		this.offset = offset;
	}
	
	public String toString()
	{
		String result = "(" + name + ",\t" + type + ",\t" + offset + ")";
		return result;
	}
	

}
