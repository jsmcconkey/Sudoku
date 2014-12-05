/*
 * Programmers <James McConkey and Ethan Smith>
 */

package playingfield;

import java.awt.Font;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import puzzle.Puzzle;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import cell.Cell;
import gridselector.GridSelector;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;


public class PlayingField extends JPanel {	
	//Main Variables
	public Cell[][] cellArray;
	public Cell[][] answerArray;
	public String cellAt;
	public Font bigFont = new Font("Serif", Font.BOLD, 16);
	public GridSelector buttongrid = new GridSelector();
	public boolean flag = false;
	public boolean hasAnswer = false;
	public String diff;
	public final int MAX_ALPHA = 255;
	public final int TRANS_ALPHA = 100;
	
	//Buttons
	public JButton giveUp;
	public JButton savePuzzle;
	public JButton checkPuzzle;
	
	//Offsets
	public int xoffset;
	public int yoffset;
	public int cellsize;

	//Color Note Taking System
	public JButton blueButton;
	public JButton greenButton;
	public JButton redButton;
	public JButton yellowButton;
	public JButton clearColor;
	public JButton backToGridSelector;
	public boolean colorIsClicked;
	public Color colorClicked;
	
	//Images
	public Image background;

	public JButton finishGame = new JButton("Finish Game");
	
	public PlayingField(int x, int c, int y, Image b) {
	    super();		
	    
	    background = b;
	    		    	
	    this.setLayout(null); //Allows us to put the button added where we want to...does not start it in a default spot
	            
	    xoffset = x;
	    yoffset = y;
	    cellsize = c;
	    
	    //Colors
	    final Color blueColor = new Color(102,178,255,MAX_ALPHA);
	    final Color greenColor = new Color(153,255,153,MAX_ALPHA);
	    final Color redColor = new Color(255,204,204,MAX_ALPHA);
	    final Color yellowColor = new Color(255,255,204,MAX_ALPHA);
	    
	    
	    //Buttons
	    add(buttongrid);
	    buttongrid.setVisible(false);
	    buttongrid.setBounds(0, 0, 54, 54); //Makes it so that the buttongrid does not show up until first click...
	    
	    giveUp = new JButton("Give Up");
	    giveUp.setBounds(510, 5, 120, 50);
	    giveUp.setBackground(new Color(147,245,245));
	    add(giveUp);
	    
	    savePuzzle = new JButton("Save Puzzle");
	    savePuzzle.setBounds(510, 310, 120, 50);
	    savePuzzle.setBackground(new Color(147,245,245));
	    add(savePuzzle);
	    
	    checkPuzzle = new JButton("Check");
	    checkPuzzle.setBounds(510, 370, 120, 100);
	    checkPuzzle.setBackground(new Color(147,245,245));
	    add(checkPuzzle); 
	                
	    blueButton = new JButton("B");
	    blueButton.setBounds(510, 165, 55, 40);
	    blueButton.setBackground(blueColor);
	    blueButton.setForeground(blueColor.darker());
	    add(blueButton);
	    
	    greenButton = new JButton("G");
	    greenButton.setBounds(575, 165, 55, 40);
	    greenButton.setBackground(greenColor);
	    greenButton.setForeground(greenColor.darker());
	    add(greenButton);
	    
	    redButton = new JButton("R");
	    redButton.setBounds(510, 210, 55, 40);
	    redButton.setBackground(redColor);
	    redButton.setForeground(redColor.darker());
	    add(redButton);
	    
	    yellowButton = new JButton("Y");
	    yellowButton.setBounds(575, 210, 55, 40);
	    yellowButton.setBackground(yellowColor);
	    yellowButton.setForeground(yellowColor.darker());
	    add(yellowButton);
	    
	    clearColor = new JButton("W");
	    clearColor.setBounds(510, 120, 120, 40);
	    clearColor.setBackground(Color.white);
	    clearColor.setForeground(Color.white.darker().darker());
	    add(clearColor);
	    
	    backToGridSelector = new JButton("Selector");
	    backToGridSelector.setBounds(510, 75, 120, 40);
	    backToGridSelector.setBackground(Color.LIGHT_GRAY);
	    add(backToGridSelector);
	    
	    addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent e) {
	            //System.out.println("Mouse Pressed - X: " + e.getX() + " Y: " + e.getY());
	            checkCells(e.getX(),e.getY());
	            repaint();
	            
	        }
	    });
	    
		backToGridSelector.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				colorIsClicked = false;
				checkCells(e.getX(), e.getY());
				repaint();
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
				colorClicked = blueColor;
				buttongrid.setVisible(false);
			}
		});
		
		greenButton.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				colorIsClicked = true;
				colorClicked = greenColor;
				buttongrid.setVisible(false);
			}
		});
		
		redButton.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				colorIsClicked = true;
				colorClicked = redColor;
				buttongrid.setVisible(false);
			}
		});
		
		yellowButton.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				colorIsClicked = true;
				colorClicked = yellowColor;
				buttongrid.setVisible(false);
			}
		});
	}
	
	public void setGrid(Puzzle grid) {
	    //Main Setup 
		cellArray = grid.getArray();
		diff = grid.getDifficulty();
		
		if(grid.hasAnswer() == true)
		{
			answerArray = grid.getAnswerArray();
			hasAnswer = true;
		}		
	}
	
	public Cell[][] getArray() {
		return cellArray;
	}
	
	public Cell[][] getAnswerArray() {
		return answerArray;
	}
	
	public void checkCells(int x, int y) {
		//This function runs through every cell every time the user clicks, in order to tell when one has been clicked on
		for(int j = 0; j<9; j++) {
			for(int i = 0; i<9; i++) {
				if(cellArray[j][i].checkClick(x, y) == true && colorIsClicked == false) {
					buttongrid.resetLoc(x, y, cellArray[j][i]);
					buttongrid.setVisible(true);	
				}
				else if(cellArray[j][i].checkClick(x, y) == true && colorIsClicked == true) {
					buttongrid.setVisible(false);
					cellArray[j][i].setColor(colorClicked);
				}
			}
		}	
	}
	
	
	public int checkScore() {
		//This function checks for the victory condition when the user presses the finish button
		int points = 0;
		
		//If the board already has a stored answer
		if(hasAnswer==true) {			
			for(int j = 0; j<9; j++)
				for(int i = 0; i<9; i++) {
					int v1 = answerArray[j][i].getValue();
					int v2 = cellArray[j][i].getValue();
					
					if((v1 == v2) && (cellArray[j][i].getLocked() ==false)) {
						points++;
					}
				}		
		}
		else {
			points = -1;
		}
		
		return points;
	}
	
	public boolean checkVictory() {
		//This function checks for the victory condition when the user presses the finish button
		boolean r = false;
		
		//If the board already has a stored answer
		if(hasAnswer==true) {
			boolean match = true;
			
			for(int j = 0; j<9; j++) {
				for(int i = 0; i<9; i++) {
					int v1 = answerArray[j][i].getValue();
					int v2 = cellArray[j][i].getValue();
					
					if(v1 != v2) {
						match = false;
					}
					
					r = match;
				}
			}					
		}
		
		return r;
	}
	
	
    public void paintComponent(Graphics g) {
    	g.drawImage(background, 0, 0, null);
    	
        int width = getWidth();            
        int height = getHeight();  
		
		Graphics2D g2 = (Graphics2D) g;
		
		drawGrid(g, g2, cellsize,xoffset,yoffset);
		//Draws Vertical Lines

		System.out.println("Painting");
		drawCells(g, g2, xoffset, yoffset, cellsize);
        
    }
    
	public void drawCells(Graphics g, Graphics2D g2, int xoffset, int yoffset, int cellsize) {
		g.setFont(bigFont);
		for(int j = 0; j<9; j++) {
			for(int i = 0; i<9; i++) {	
				if(cellArray[j][i].getLocked() == true) {
					g.setColor(Color.LIGHT_GRAY);
					g.fillRect(xoffset + (cellsize * i), yoffset + (cellsize * j), cellsize, cellsize);
					drawGrid(g, g2, cellsize, xoffset, yoffset);										
				}	
				else if(cellArray[j][i].getLocked() == false) {
					Color cellColor = cellArray[j][i].getColor();
					g.setColor(cellColor);
					g.fillRect(xoffset + (cellsize * i), yoffset + (cellsize * j), cellsize, cellsize);
					drawGrid(g, g2, cellsize, xoffset, yoffset);
				}
				
				g.setColor(Color.BLACK);				
				g.drawString(cellArray[j][i].getString(), xoffset - 3 + (cellsize/2) + (cellsize * i), yoffset + 5 + (cellsize/2) + (cellsize * j) );
			}
		}
	}
	
	
	
	
	public void drawGrid(Graphics g, Graphics2D g2, int cellsize, int xoffset, int yoffset) {
		g.setColor(Color.BLACK);
		
		//Draws Vertical Lines
		for(int i = 0; i<=9; i++) {
			int x = i * cellsize + xoffset;

			g2.setStroke(new BasicStroke(1));			
			
			if((i%3) == 0) {
				g2.setStroke(new BasicStroke(3));
			}
				
			g2.drawLine(x, yoffset, x, yoffset + (cellsize * 9));		
		}
		
		//Draws Horizontal Lines
		for(int i = 0; i<=9; i++) {
			int y = i * cellsize + yoffset;
			
			g2.setStroke(new BasicStroke(1));	
			
			if((i%3) == 0) {
				g2.setStroke(new BasicStroke(3));	
			}
				
			g2.drawLine(xoffset, y, xoffset + (cellsize * 9), y);		
		}	
	}
	
	public void setButtonGridVisible(boolean b) {
		buttongrid.setVisible(b);
	}
    
	public String getDifficulty() {
		return diff;
	}
}