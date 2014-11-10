package puzzle;

import java.util.Random;

import cell.Cell;


public class Puzzle {
	public Cell[][] cellArray = new Cell[9][9];
	public Random rand = new Random();
	private String difficulty;
	
	//Generates a Blank Puzzle
	public Puzzle(int xoffset, int cellsize, int yoffset)
	{
		for(int j = 0; j<9; j++)
			for(int i = 0; i<9; i++)
			{
				
				cellArray[j][i] = new Cell(0,false, xoffset + (cellsize * i), yoffset + (cellsize * j), cellsize);		
			}
		
			
	}
	
	public void setCell(int row, int column, int value)
	{
		cellArray[row][column].overwriteCell(value);	
	}
	
	
	public Cell[][] getArray()
	{		
		return cellArray;
	}
	
	public void setDifficulty(String d)
	{
		difficulty = d;
	}
	
}
