package gridselector;


import javax.swing.JPanel; //imports JFrame library
import javax.swing.JButton; //imports JButton library

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout; //imports GridLayout library
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import cell.Cell;
 
public class GridSelector extends JPanel{
 
		private boolean visible;
		private JButton button[]=new JButton[10];
		private Cell myCell;
	
        JButton[][] grid; //names the grid of buttons
 
        public GridSelector(){ //constructor  	
                setLayout(new GridLayout(3,3)); //set layout
                grid=new JButton[3][3]; //allocate the size of grid
                
                int k = 0;
                for(int y=0; y<3; y++){
                        for(int x=0; x<3; x++){
                        		k++;
                                JButton button = new JButton(Integer.toString(k)); //creates new button   
                                

                                button.setPreferredSize(new Dimension(18, 18));  //Sets the size of the box to be smaller
                                Font customFont = new Font("Courier New", Font.BOLD, 12);

                                button.setFont(customFont);
                                button.setMargin(new Insets(0, 0, 0, 0));
                                button.setBorder(null);
                                button.setBackground(Color.lightGray);
                                
                                button.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e)
                                    {                                      	
                                    	String text = e.getSource().toString();
                                    	int value = 0;
                                    	
                                    	if(text.contains("text=0"))
                                    		value = 0;
                                    	if(text.contains("text=1"))
                                    		value = 1;                                  	
                                    	if(text.contains("text=2"))
                                    		value = 2;
                                    	if(text.contains("text=3"))
                                    		value = 3;
                                    	if(text.contains("text=4"))
                                    		value = 4;
                                    	if(text.contains("text=5"))
                                    		value = 5;
                                    	if(text.contains("text=6"))
                                    		value = 6;
                                    	if(text.contains("text=7"))
                                    		value = 7;
                                    	if(text.contains("text=8"))
                                    		value = 8;
                                    	if(text.contains("text=9"))
                                    		value = 9;
                                 	    myCell.setValue(value);
                                    	setLocation(800,800);
                                    }                                	
                                });                                                                                              
                                add(button); //adds button to grid                                                                                        
                        	}
                        }
        }
        
        public void resetLoc(int x,int y, Cell clickedCell)
        {
        	myCell = clickedCell;
        	setLocation(myCell.getX(), myCell.getY()); //Sets to the location of the cell instead of x,y where clicked
        }
        

        
        
        
}


	