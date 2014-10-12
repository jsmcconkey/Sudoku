package gridselector;

import javax.swing.JPanel; //imports JFrame library
import javax.swing.JButton; //imports JButton library
import java.awt.GridLayout; //imports GridLayout library
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
 
public class GridSelector extends JPanel{
 
		private boolean visible;
		private JButton button[]=new JButton[10];
		private int x;
		private int y;
	
	
        JButton[][] grid; //names the grid of buttons
 
        public GridSelector(){ //constructor  	

        		x = 0; 
        		y = 0;
                setLayout(new GridLayout(3,3)); //set layout
                grid=new JButton[3][3]; //allocate the size of grid
                int k = 0;
                for(int y=0; y<3; y++){
                        for(int x=0; x<3; x++){
                        		k++;
                                JButton button = new JButton(Integer.toString(k)); //creates new button   
                                
                                button.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e)
                                    {
                                    	setLocation(800,800);
                                    }                                	
                                });                                                                                              
                                add(button); //adds button to grid                                                                                        
                        	}
                        }
        }
        

        
        
        
}


	