/*
**Programmer <James McConkey and Ethan Smith>
*/

package cell;

import java.awt.Color;

public class Cell {
	private int value;
	private int x;
	private int y; 
	private int cellsize;
	private Color cellColor;
	boolean locked;
	
	//Constructor
	public Cell(int n, boolean l, int ix, int iy, int icellsize) {
		value = n;	
		locked = l;
		x = ix;
		y = iy;
		
		cellColor = Color.white;
		
		cellsize = icellsize;
		
		if(locked==false) {
			value = 0;
		}	
	}
	
	//Methods
	public boolean getLocked() {	
		return locked;
	}
	
	public int getValue() {
		return value;		
	}
	
	public boolean checkClick(int mx, int my) {
		boolean r = false;
		if((((mx >= x) && (mx <= (x + cellsize-1))) && ((my >= y) && (my <= (y + cellsize-1)))) && (locked == false)) {
			r = true;
		}
		return r;
	}
	
	public void setValue(int v) {
		value = v;		
	}
	
	public void overwriteCell(int v,boolean l) {
		value = v;
		locked = l;	
	}
	
	public String getString() {
		if(value == 0) {
			return " ";
		}
		else {
			return Integer.toString(value);
		}
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setColor(Color c) {
		cellColor = c;
	}
	public Color getColor() {
		return cellColor;
	}
}