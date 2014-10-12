package cell;



public class Cell {
	
	private int number;
	private int x;
	private int y; 
	private int cellsize;
	
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
	
	public int getNumber()
	{
		return number;		
	}
	
	public boolean checkClick(int mx, int my)
	{
		boolean r = false;
		if((((mx >= x) && (mx <= (x + cellsize))) && ((my >= y) && (my <= (y + cellsize)))) && (locked == false))
		{
			System.out.println("Cell " + x + ":" + y + " has been clicked. Value: " + number);
			number = number +1;
			r = true;
		}	
		
		return r;
		
	}
	
	public String getString()
	{
		if(number == 0)
			return " ";		
		else
			return Integer.toString(number);
	}
	
}