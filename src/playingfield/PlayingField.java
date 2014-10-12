package playingfield;

import javax.swing.*;

import java.awt.Font;

import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
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
import java.util.ArrayList;
import java.util.Random;



public class PlayingField extends JPanel

	{
	
	public Cell[][] cellArray = new Cell[9][9];
	public Random rand = new Random();
	public String cellAt;
	public Font bigFont = new Font("Serif", Font.BOLD, 16);
	public GridSelector buttongrid = new GridSelector();
	
	public PlayingField(){
		    super();
		    setBackground(Color.WHITE);	
		    add(buttongrid);
		    buttongrid.setLocation(800,800);
		    addMouseListener(new MouseAdapter() {
		        @Override
		        public void mousePressed(MouseEvent e) 
		        {
		            System.out.println("X: " + e.getX() + " Y: " + e.getY());
		            checkCells(e.getX(),e.getY());
		            repaint();
		        }
		    });
		    
		    int offset = 25;
		    int cellsize = 45;
		    
			for(int j = 0; j<9; j++)
				for(int i = 0; i<9; i++)
				{
					boolean l;
					if((rand.nextInt(3)) == 0)
						l = true;
					else
						l = false;
					
					cellArray[j][i] = new Cell(rand.nextInt(9),l, offset + (cellsize * i), offset + (cellsize * j), cellsize);		
				}
	}
	
	public void checkCells(int x, int y)
	{

		for(int j = 0; j<9; j++)
			for(int i = 0; i<9; i++)
				if(cellArray[j][i].checkClick(x, y) == true)
					buttongrid.resetLoc(x, y, cellArray[j][i]);
	}
	
	public boolean checkVictory()
	{
		boolean r = true;
		
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
		return r;
	}
	
	
    public void paintComponent(Graphics g)  
    {
        int width = getWidth();            
        int height = getHeight();  
        
        super.paintComponent(g); 

		int offset = 25;
		int cellsize = 45;
		
		Graphics2D g2 = (Graphics2D) g;
		
		drawGrid(g, g2, cellsize,offset);
		//Draws Vertical Lines

		
		drawCells(g, g2, offset, cellsize);
        
    }
    
	public void drawCells(Graphics g, Graphics2D g2, int offset, int cellsize)
	{
		g.setFont(bigFont);
		for(int j = 0; j<9; j++)
		{
		
			for(int i = 0; i<9; i++)
			{
				if(cellArray[j][i].getLocked() == true)//Dark gray for locked
				{
					g.setColor(Color.GRAY);
					g.fillRect(offset + (cellsize * i), offset + (cellsize * j), cellsize, cellsize);
					drawGrid(g, g2, cellsize, offset);										
				}	
				else if(cellArray[j][i].getLocked() == false){
					if(cellArray[j][i].getString() == " ")//White for unentered
					{
						g.setColor(Color.WHITE);
						g.fillRect(offset + (cellsize * i), offset + (cellsize * j), cellsize, cellsize);
						drawGrid(g, g2, cellsize, offset);
					}
					if(cellArray[j][i].getString() != " ") //Lightgray for entered numbers
					{
						g.setColor(Color.lightGray);
						g.fillRect(offset + (cellsize * i), offset + (cellsize * j), cellsize, cellsize);
						drawGrid(g, g2,cellsize, offset);			
					}
				}
				
				
				g.setColor(Color.BLACK);				
				g.drawString(cellArray[j][i].getString(), offset - 3 + (cellsize/2) + (cellsize * i), offset + 5 + (cellsize/2) + (cellsize * j) );
				
				
			}
		}
	}
	
	public void drawGrid(Graphics g, Graphics2D g2, int cellsize, int offset)
	{
		g.setColor(Color.BLACK);
		
		for(int i = 0; i<=9; i++){
			int x = i * cellsize + offset;

			g2.setStroke(new BasicStroke(1));			
			
			if((i%3) == 0)
				g2.setStroke(new BasicStroke(3));
				
			g2.drawLine(x, offset, x, offset + (cellsize * 9));		
		}
		
		//Draws Horizontal Lines
		for(int i = 0; i<=9; i++){
			int y = i * cellsize + offset;
			
			g2.setStroke(new BasicStroke(1));	
			
			if((i%3) == 0)
				g2.setStroke(new BasicStroke(3));	
				
			g2.drawLine(offset, y, offset + (cellsize * 9), y);		
		}	
		
	
	}
	
    
}
