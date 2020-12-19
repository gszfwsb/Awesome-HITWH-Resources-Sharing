package semantic;


public class FourAddr 
{
	private String op;  // 操作符
	private String param1;  // 参数一
	private String param2;  // 参数二
	private String toaddr;  // 地址
	
	/**
	 * 四元式构造函数
	 * @param op 操作符
	 * @param param1 参数一
	 * @param param2 参数二
	 * @param toaddr 地址
	 */
	public FourAddr(String op, String param1, String param2, String toaddr)
	{
		this.op = op;
		this.param1 = param1;
		this.param2 = param2;
		this.toaddr = toaddr;
	}
	
	public String getOp() 
	{
		return op;
	}
	
	public void setOp(String op) 
	{
		this.op = op;
	}
	public String getParam1() 
	{
		return param1;
	}
	
	public void setParam1(String param1) 
	{
		this.param1 = param1;
	}
	
	public String getParam2() 
	{
		return param2;
	}
	public void setParam2(String param2) 
	{
		this.param2 = param2;
	}
	
	public String getToaddr() {
		return toaddr;
	}
	
	public void setToaddr(String toaddr) 
	{
		this.toaddr = toaddr;
	}

	public String toString()
	{
		String result = "(" + op + ",\t" + param1 + ",\t" + param2 + ",\t" + toaddr + ")\t";
		return result;
	}
	
}
