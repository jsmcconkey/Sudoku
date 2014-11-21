package playingfield;

import javax.swing.*;

import java.awt.Font;

import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import puzzle.Puzzle;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

import cell.Cell;
import gridselector.GridSelector;

import java.awt.BorderLayout; 
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;


public class PlayingField extends JPanel implements Serializable
{
	private static final long serialVersionUID = -6651451576028481191L;
	//Main Variables
	public Cell[][] cellArray;
	public Cell[][] answerArray;
	public String cellAt;
	public Font bigFont = new Font("Serif", Font.BOLD, 16);
	public GridSelector buttongrid = new GridSelector();
	public boolean flag = false;
	public boolean hasAnswer = false;
	
	//Buttons
	public JButton giveUp;
	public JButton savePuzzle;
	public JButton checkPuzzle;
	
	//Offsets
	public int xoffset;
	public int yoffset;
	public int cellsize;
	
	//Timer
	
	//Color Note Taking System
	public JButton blueButton;
	public JButton greenButton;
	public JButton redButton;
	public JButton yellowButton;
	public JButton clearColor;
	public JButton backToGridSelector;
	public boolean colorIsClicked;
	public Color colorClicked;
	

	
	public JButton finishGame = new JButton("Finish Game");
	
	public PlayingField(int x, int c, int y){
		    super();		
		    
		    
		    setBackground(Color.WHITE);	
		    this.setLayout(null); //Allows us to put the button added where we want to...does not start it in a default spot
                    
            xoffset = x;
            yoffset = y;
            cellsize = c;
		    
		    //Buttons
		    add(buttongrid);
		    buttongrid.setVisible(false);
            buttongrid.setBounds(0, 0, 54, 54); //Makes it so that the buttongrid does not show up until first click...
            
            giveUp = new JButton("Give Up");
            giveUp.setBounds(510, 0, 120, 50);
            giveUp.setBackground(new Color(102,255,255));
            add(giveUp);
            
            savePuzzle = new JButton("Save Puzzle");
            savePuzzle.setBounds(510, 310, 120, 50);
            savePuzzle.setBackground(new Color(102,255,255));
            add(savePuzzle);
            
            checkPuzzle = new JButton("Check");
            checkPuzzle.setBounds(510, 370, 120, 100);
            checkPuzzle.setBackground(new Color(102,255,255));
            add(checkPuzzle); 
                        
            blueButton = new JButton("");
            blueButton.setBounds(510, 165, 55, 40);
            blueButton.setBackground(Color.blue);
            add(blueButton);
            
            greenButton = new JButton("");
            greenButton.setBounds(575, 165, 55, 40);
            greenButton.setBackground(Color.green);
            add(greenButton);
            
            redButton = new JButton("");
            redButton.setBounds(510, 210, 55, 40);
            redButton.setBackground(Color.red);
            add(redButton);
            
            yellowButton = new JButton("");
            yellowButton.setBounds(575, 210, 55, 40);
            yellowButton.setBackground(Color.yellow);
            add(yellowButton);
            
            clearColor = new JButton("ERASE");
            clearColor.setBounds(510, 120, 120, 40);
            clearColor.setBackground(Color.white);
            add(clearColor);
            
            backToGridSelector = new JButton("Selector");
            backToGridSelector.setBounds(510, 75, 120, 40);
            backToGridSelector.setBackground(Color.LIGHT_GRAY);
            add(backToGridSelector);
            
    		    
		    addMouseListener(new MouseAdapter() {
		        public void mousePressed(MouseEvent e) 
		        {
		            //System.out.println("Mouse Pressed - X: " + e.getX() + " Y: " + e.getY());
		            checkCells(e.getX(),e.getY());
		        }
		    });
		    
			backToGridSelector.addMouseListener(new MouseAdapter(){
				public void mousePressed(MouseEvent e){
					colorIsClicked = false;
					buttongrid.setVisible(true);
				}
			});
			
			clearColor.addMouseListener(new MouseAdapter(){
				public void mousePressed(MouseEvent e){
					colorIsClicked = true;
					colorClicked = Color.white;
					buttongrid.setVisible(false);
				}
			});
			
			blueButton.addMouseListener(new MouseAdapter(){
				public void mousePressed(MouseEvent e){
					colorIsClicked = true;
					colorClicked = Color.blue;
					buttongrid.setVisible(false);
				}
			});
			
			greenButton.addMouseListener(new MouseAdapter(){
				public void mousePressed(MouseEvent e){
					colorIsClicked = true;
					colorClicked = Color.green;
					buttongrid.setVisible(false);
				}
			});
			
			redButton.addMouseListener(new MouseAdapter(){
				public void mousePressed(MouseEvent e){
					colorIsClicked = true;
					colorClicked = Color.red;
					buttongrid.setVisible(false);
				}
			});
			
			yellowButton.addMouseListener(new MouseAdapter(){
				public void mousePressed(MouseEvent e){
					colorIsClicked = true;
					colorClicked = Color.yellow;
					buttongrid.setVisible(false);
				}
			});


	}
	
	public void setGrid(Puzzle grid)
	{
	    //Main Setup 
		cellArray = grid.getArray();
		
		if(grid.hasAnswer() == true)
		{
			answerArray = grid.getAnswerArray();
			hasAnswer = true;
		}		
	}
	
	public Cell[][] getArray()
	{
		return cellArray;
	}
	
	public void checkCells(int x, int y)
	{
		//This function runs through every cell every time the user clicks, in order to tell when one has been clicked on
		for(int j = 0; j<9; j++)
			for(int i = 0; i<9; i++)
			{
				if(cellArray[j][i].checkClick(x, y) == true && colorIsClicked == false){
					buttongrid.resetLoc(x, y, cellArray[j][i]);
					buttongrid.setVisible(true);
				}
//				else if(cellArray[j][i].checkClick(x, y) == true && colorIsClicked == true){
//					buttongrid.setVisible(false);
//					cellArray[j][i].setColor(colorClicked);
//				}
			}
				
	}
	
	
	public boolean checkVictory()
	{
		//This function checks for the victory condition when the user presses the finish button
		boolean r = true;
		
		
		//If the board already has a stored answer
		if(hasAnswer==true)
		{
			boolean match = true;
			
			for(int j = 0; j<9; j++)
				for(int i = 0; i<9; i++)
				{
					int v1 = answerArray[j][i].getValue();
					int v2 = cellArray[j][i].getValue();
					
					if(v1 != v2)
						match = false;
						
				}		
			r = match;
		}
		
		
		
		//If the Puzzle does NOT have a stored answer
		else if(hasAnswer==false)
		{
			//This loop checks every row for 1-9
			for(int j = 0; j<9; j++)//Rows
			{	
				ArrayList<Integer> numbers = new ArrayList<Integer>();
				
				for(int i = 0; i<9; i++)//Columns
				{
					numbers.add(cellArray[j][i].getValue());								
				}
				
				if(!(numbers.contains(1) && numbers.contains(2) && numbers.contains(3) && numbers.contains(4) && numbers.contains(5) && numbers.contains(6) && numbers.contains(7) && numbers.contains(8) && numbers.contains(9)))
					r = false;			
			}
			
			//This loops checks every column for 1-9
			for(int j = 0; j<9; j++)//Rows
			{	
				ArrayList<Integer> numbers = new ArrayList<Integer>();
				
				for(int i = 0; i<9; i++)//Columns
				{
					numbers.add(cellArray[i][j].getValue());								
				}
				
				if(!(numbers.contains(1) && numbers.contains(2) && numbers.contains(3) && numbers.contains(4) && numbers.contains(5) && numbers.contains(6) && numbers.contains(7) && numbers.contains(8) && numbers.contains(9)))
					r = false;			
			}
			
			//This loops checks the boxes
			for(int j = 0; j<3; j++)//Box Rows
			{	
				
				
				for(int i = 0; i<3; i++)//Box Columns
				{
					ArrayList<Integer> numbers = new ArrayList<Integer>();
					
					for(int s = 0; s<3; s++)//Rows within the box
					{
						for(int p = 0; p<3; p++)
						{
							numbers.add(cellArray[i][j].getValue());				
						}
						
						if(!(numbers.contains(1) && numbers.contains(2) && numbers.contains(3) && numbers.contains(4) && numbers.contains(5) && numbers.contains(6) && numbers.contains(7) && numbers.contains(8) && numbers.contains(9)))
							r = false;						
					}																
				}		
			}
		}
		
		
		return r;
	}
	
	
    public void paintComponent(Graphics g)  
    {
        int width = getWidth();            
        int height = getHeight();  
        
        super.paintComponent(g); 

		
		Graphics2D g2 = (Graphics2D) g;
		
		drawGrid(g, g2, cellsize,xoffset,yoffset);
		//Draws Vertical Lines

		
		drawCells(g, g2, xoffset, yoffset, cellsize);
        
    }
    
	public void drawCells(Graphics g, Graphics2D g2, int xoffset, int yoffset, int cellsize)
	{
		g.setFont(bigFont);
		for(int j = 0; j<9; j++)
		{
		
			for(int i = 0; i<9; i++)
			{
//				if(cellArray[j][i].getLocked() == false && colorIsClicked)
//				{
//					g.setColor(colorClicked);
//					g.fillRect(xoffset + (cellsize * i), yoffset + (cellsize * j), cellsize, cellsize);
//					drawGrid(g, g2, cellsize, xoffset, yoffset);
//				}
				if(cellArray[j][i].getLocked() == true)//Dark gray for locked
				{
					g.setColor(Color.gray);
					g.fillRect(xoffset + (cellsize * i), yoffset + (cellsize * j), cellsize, cellsize);
					drawGrid(g, g2, cellsize, xoffset, yoffset);										
				}	
				else if(cellArray[j][i].getLocked() == false){
					if(cellArray[j][i].getString() == " ")//White for unentered
					{
						g.setColor(Color.WHITE);
						g.fillRect(xoffset + (cellsize * i), yoffset + (cellsize * j), cellsize, cellsize);
						drawGrid(g, g2, cellsize, xoffset, yoffset);
					}
					if(cellArray[j][i].getString() != " ") //Lightgray for entered numbers
					{
						g.setColor(Color.lightGray);
						g.fillRect(xoffset + (cellsize * i), yoffset + (cellsize * j), cellsize, cellsize);
						drawGrid(g, g2,cellsize, xoffset,yoffset);			
					}
				}
				
				
				g.setColor(Color.BLACK);				
				g.drawString(cellArray[j][i].getString(), xoffset - 3 + (cellsize/2) + (cellsize * i), yoffset + 5 + (cellsize/2) + (cellsize * j) );
				
				
			}
		}
	}
	
	public void drawGrid(Graphics g, Graphics2D g2, int cellsize, int xoffset, int yoffset)
	{
		g.setColor(Color.BLACK);
		
		//Draws Vertical Lines
		for(int i = 0; i<=9; i++){
			int x = i * cellsize + xoffset;

			g2.setStroke(new BasicStroke(1));			
			
			if((i%3) == 0)
				g2.setStroke(new BasicStroke(3));
				
			g2.drawLine(x, yoffset, x, yoffset + (cellsize * 9));		
		}
		
		//Draws Horizontal Lines
		for(int i = 0; i<=9; i++){
			int y = i * cellsize + yoffset;
			
			g2.setStroke(new BasicStroke(1));	
			
			if((i%3) == 0)
				g2.setStroke(new BasicStroke(3));	
				
			g2.drawLine(xoffset, y, xoffset + (cellsize * 9), y);		
		}	
	}
	
	public void setButtonGridVisible(boolean b)
	{
		buttongrid.setVisible(b);
	}
    
}
