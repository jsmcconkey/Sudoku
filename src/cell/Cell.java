package cell;



public class Cell {
	
	private int value;
	private int x;
	private int y; 
	private int cellsize;
	
	boolean locked;
	
	
	
	public Cell(int n, boolean l, int ix, int iy, int icellsize){
		value = n;	
		locked = l;
		x = ix;
		y = iy;
		
		cellsize = icellsize;
		
		if(locked==false)
		{
			value = 0;
		}
		
	}
	
	public boolean getLocked()
	{	
		return locked;
	}
	
	public int getValue()
	{
		return value;		
	}
	
	public boolean checkClick(int mx, int my)
	{
		boolean r = false;
		if((((mx >= x) && (mx <= (x + cellsize-1))) && ((my >= y) && (my <= (y + cellsize-1)))) && (locked == false))//If the bounds are correct
			r = true;

		
		return r;
		
	}
	
	public void setValue(int v)
	{
		value = v;		
	}
	
	public String getString()
	{
		if(value == 0)
			return " ";		
		else
			return Integer.toString(value);
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
}