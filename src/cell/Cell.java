package cell;



public class Cell {
	
	int number;
	boolean locked;
	
	
	
	public Cell(int n, boolean l){
		number = n;	
		locked = l;
	}
	
	public boolean getLocked()
	{	
		return locked;
	}

	public void Click()
	{
		number = number + 1;
	}
	
	public String getString()
	{
		if(number == 0)
			return " ";		
		else
			return Integer.toString(number);
	}
	
}