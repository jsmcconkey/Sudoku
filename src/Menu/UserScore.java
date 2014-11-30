package Menu;



public class UserScore
{
	private int value;
	private String name;
	
	public UserScore(String n, int v)
	{
		value = v;
		name = n;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getValue()
	{
		return value;
	}
	
	public void addPoints(int p)
	{
		value = value + p;
	}
}
