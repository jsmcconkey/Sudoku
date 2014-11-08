package sudokugame;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import playingfield.PlayingField;
import puzzle.Puzzle;
import Menu.MainMenu;


public class SudokuGame extends JApplet
{
  private final int WIDTH = 640;
  private final int HEIGHT = 480;
  private final int yoffset = 1;
  private final int xoffset = 83;
  private final int cellsize = 52;

  public void init()
   {
	  this.setSize(WIDTH, HEIGHT);
	  
	  final JPanel cards = new JPanel(new CardLayout());
	  MainMenu card1 = new MainMenu();
	  Puzzle puzzle1 = new Puzzle(xoffset,cellsize,yoffset);
	  PlayingField card2 = new PlayingField(puzzle1,xoffset,cellsize,yoffset);
	  
	  //Each screen will be a different screen, we will switch between these like "cards"
	  //In the end our game should have login screen, main menu, game, and scores, for a total
	  //of four cards.
	  cards.add(card1, "MainMenu");
	  cards.add(card2, "MainGame");
	  
	  this.add(cards);
	  
	  final CardLayout cardLayout = (CardLayout) cards.getLayout();
	  
	  //Each of these will also need to implement the logic that makes up the difficulty
	  card1.easyButton.addMouseListener(new MouseAdapter(){
		  public void mousePressed(MouseEvent e){
			  cardLayout.show(cards, "MainGame");
		  }
	  });
	  card1.mediumButton.addMouseListener(new MouseAdapter(){
		  public void mousePressed(MouseEvent e){
			  cardLayout.show(cards, "MainGame");
		  }
	  });
	  card1.hardButton.addMouseListener(new MouseAdapter(){
		  public void mousePressed(MouseEvent e){
			  cardLayout.show(cards, "MainGame");
		  }
	  });
	  card1.hardestButton.addMouseListener(new MouseAdapter(){
		  public void mousePressed(MouseEvent e){
			  cardLayout.show(cards, "MainGame");
		  }
	  });
//	  card1.scoreButton.addMouseListener(new MouseAdapter(){
//		  public void mousePressed(MouseEvent e){
//			  cardLayout.show(cards, "ScorePage");
//		  }
//	  });
//	  card1.loadGame.addMouseListener(new MouseAdapter(){
//		  public void mousePressed(MouseEvent e){
//			  cardLayout.show(cards, "LoadGameMenu");
//		  }
//	  });
	  

   }
  
 public void readPuzzles()
 {
	 
	 
	 
	 
	 
 }
    
  
}





