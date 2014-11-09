package puzzle;

import java.util.Random;

import cell.Cell;


public class Puzzle {
	public Cell[][] cellArray = new Cell[9][9];
	public Random rand = new Random();
	
	
	//Generates a Random-Ass puzzle
	public Puzzle(int xoffset, int cellsize, int yoffset)
	{
		for(int j = 0; j<9; j++)
			for(int i = 0; i<9; i++)
			{
				boolean l;
				if((rand.nextInt(3)) == 0)
					l = true;
				else
					l = false;
				
				cellArray[j][i] = new Cell(rand.nextInt(9),l, xoffset + (cellsize * i), yoffset + (cellsize * j), cellsize);		
			}
		
			
	}
	
	
	public Cell[][] getArray()
	{		
		return cellArray;
	}
	
}
