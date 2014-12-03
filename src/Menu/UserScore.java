/*
**Programmer <James McConkey and Ethan Smith>
*/

package Menu;

public class UserScore {
	private int value;
	private String name;
	
	//Constructor
	public UserScore(String n, int v) {
		value = v;
		name = n;
	}
	
	//Methods
	public String getName() {
		return name;
	}
	
	public int getValue() {
		return value;
	}
	
	public void addPoints(int p) {
		value = value + p;
	}
}
