/*
 * Programmers <James McConkey and Ethan Smith>
 */

package puzzle;

import cell.Cell;

public class Puzzle {
	public Cell[][] cellArray = new Cell[9][9];
	public Cell[][] answerArray = new Cell[9][9];	
	private String difficulty;
	private boolean hasAnswer = false;
	
	//Generates a Blank Puzzle
	public Puzzle(int xoffset, int cellsize, int yoffset) {
		for(int j = 0; j<9; j++) {
			for(int i = 0; i<9; i++) {
				answerArray[j][i] = new Cell(0,false, xoffset + (cellsize * i), yoffset + (cellsize * j), cellsize);	
				cellArray[j][i] = new Cell(0,false, xoffset + (cellsize * i), yoffset + (cellsize * j), cellsize);		
			}
		}	
	}
	
	public void setCell(int row, int column, int value, boolean finished, boolean locked) {
		if(finished == false) {
			cellArray[row][column].overwriteCell(value,locked);	
		}
		else {
			answerArray[row][column].overwriteCell(value,locked);
			hasAnswer = true;
		}
	}
	
	public boolean checkReal() {
		//Checks the legitimacy of a puzzle after loading it
		boolean r = true;
		if(difficulty == null) {
			r = false;
		}
		
		if(hasAnswer == false) {
			r = false;
		}
		
		int a = 0;
		
		for(int j = 0; j<9; j++) {
			for(int i = 0; i<9; i++) {
				//Counts the amount of locked cells
				if(cellArray[j][i].getLocked() == true)	{
					a++;
				}
			}	
		}

		if(a<5) {
			r = false;			
		}
		
		return r;
	}
	
	public String getDifficulty() {
		return difficulty;
	}
	
	public boolean hasAnswer() {
		return hasAnswer;		
	}
	
	public Cell[][] getAnswerArray() {
		return answerArray;
	}
	
	
	public Cell[][] getArray() {		
		return cellArray;
	}
	
	public void setDifficulty(String d) {
		difficulty = d;
	}
}