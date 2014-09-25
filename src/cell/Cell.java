package cell;



public class Cell {
	
	int number;
	int x;
	int y; 
	int cellsize;
	
	boolean locked;
	
	
	
	public Cell(int n, boolean l, int ix, int iy, int icellsize){
		number = n;	
		locked = l;
		x = ix;
		y = iy;
		
		cellsize = icellsize;
		
	}
	
	public boolean getLocked()
	{	
		return locked;
	}

	public void Click()
	{
		System.out.println("Cell " + x + ":" + y + " has been clicked. Value: " + number);
	}
	
	public void checkClick(int mx, int my)
	{
		if(((mx >= x) && (mx <= (x + cellsize))) && ((my >= y) && (my <= (y + cellsize))))
		{
			Click();	
		}	
		
	}
	
	public String getString()
	{
		if(number == 0)
			return " ";		
		else
			return Integer.toString(number);
	}
	
}