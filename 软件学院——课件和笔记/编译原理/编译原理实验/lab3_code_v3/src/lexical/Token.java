package lexical;

public class Token 
{
	public int line;
	public String value;
	public int code;
	
	public Token(int line, String value, int code)
	{
		this.line = line;
		this.value = value;
		this.code = code;		
	}
	public String toString()
	{
		return this.line + ":< " + this.value + " ," + this.code + " >";
	}
}
