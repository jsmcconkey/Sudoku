package puzzle;

import java.util.Random;

import cell.Cell;


public class Puzzle {
	public Cell[][] cellArray = new Cell[9][9];
	public Cell[][] answerArray = new Cell[9][9];	
	private String difficulty;
	
	//Generates a Blank Puzzle
	public Puzzle(int xoffset, int cellsize, int yoffset)
	{
		for(int j = 0; j<9; j++)
			for(int i = 0; i<9; i++)
			{
				answerArray[j][i] = new Cell(0,false, xoffset + (cellsize * i), yoffset + (cellsize * j), cellsize);	
				cellArray[j][i] = new Cell(0,false, xoffset + (cellsize * i), yoffset + (cellsize * j), cellsize);		
			}
		
			
	}
	
	public void setCell(int row, int column, int value, boolean finished)
	{
		if(finished == false)
			cellArray[row][column].overwriteCell(value);	
		else
			answerArray[row][column].overwriteCell(value);
	}
	
	public boolean checkReal()
	{
		//Checks the legitimacy of a puzzle after loading it
		boolean r = true;
		if(difficulty.equals(null))
		{
			r = false;
		}
		
		int a = 0;
		
		for(int j = 0; j<9; j++)
			for(int i = 0; i<9; i++)
			{
				//Counts the amount of locked cells
				if(cellArray[j][i].getLocked() == true)	
					a++;
			}	
		
		
		
		if(a<5)
		{
			r = false;			
		}
		
		return r;
		
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
