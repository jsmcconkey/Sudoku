package sudokugame;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import playingfield.PlayingField;
import Menu.MainMenu;


public class SudokuGame extends JApplet
{
  private final int WIDTH = 640;
  private final int HEIGHT = 480;

  public void init()
   {
	  this.setSize(WIDTH, HEIGHT);
	  
	  final JPanel cards = new JPanel(new CardLayout());
	  MainMenu card1 = new MainMenu();
	  PlayingField card2 = new PlayingField();
	  
	  cards.add(card1, "MainMenu");
	  cards.add(card2, "MainGame");
	  
	  this.add(cards);
	  
	  final CardLayout cardLayout = (CardLayout) cards.getLayout();
	  
	  card1.easyButton.addMouseListener(new MouseAdapter(){
		  public void mousePressed(MouseEvent e){
			  cardLayout.show(cards, "MainGame");
		  }
	  });
	  
//	  this.setSize(WIDTH, HEIGHT);
//	  
//	  MainMenu menuFrame = new MainMenu();
//	  this.getContentPane().add(menuFrame);
//	  //this.setVisible(true);
//	  menuFrame.setBounds(0, 0, WIDTH, HEIGHT);
//	  menuFrame.setVisible(true);
	  
	  
//	  do{
//		  if(menuFrame.buttonPressed.equals("Easy"))
//		  {
//			  System.out.println("First if statement in init");
//			  menuFrame.setVisible(false);
//		  }
//		  
//		  
//	  }while(menuFrame.buttonPressed.equals("None"));
	  
	  //Once the person clicks a button then do appropriate action and change the view menu to false...
	  
//	  PlayingField field = new PlayingField();
//	  this.getContentPane().add(field);
//	  field.setVisible(true);


   }
    
  
}





