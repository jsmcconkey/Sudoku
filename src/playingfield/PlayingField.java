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
import java.awt.BorderLayout; 
import java.util.Random;



public class PlayingField extends JPanel {
	
	public Cell[][] cellArray = new Cell[9][9];
	public Random rand = new Random();
	public String cellAt;
	public Font bigFont = new Font("Serif", Font.BOLD, 16);
	public PlayingField(){
		    super();
		    setBackground(Color.WHITE);	
		    
		    
		    
			for(int j = 0; j<9; j++)
				for(int i = 0; i<9; i++)
				{
					boolean l;
					if((rand.nextInt(3)) == 0)
						l = true;
					else
						l = false;
					
					System.out.print(l);
					cellArray[j][i] = new Cell(rand.nextInt(9),l);		
				}
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
