package loginscreen;

public class User {
	
	//Data structure to score all of the users, array list of users
	protected String username;
	protected String password;
	public int score;
	
	
	//Constructors
	public User()
	{
		this.username = "unassigned";
		this.password = "unassigned";
		this.score = 0;
	}
	
	public User(String username, String password, int score)
	{
		this.username = username;
		this.password = password;
		this.score = score;
	}
	
	//Methods
	public String getUsername()
	{
		return this.username;
	}
	
	public String getPassword()
	{
		return this.password;
	}
	
	public int getScore()
	{
		return this.score;
	}

}
