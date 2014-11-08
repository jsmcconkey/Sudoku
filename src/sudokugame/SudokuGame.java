package sudokugame;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import playingfield.PlayingField;
import Menu.MainMenu;
import loginscreen.CreateUser;
import loginscreen.LoginScreen;


public class SudokuGame extends JApplet
{
  private final int WIDTH = 640;
  private final int HEIGHT = 480;

  public void init()
   {
	  this.setSize(WIDTH, HEIGHT);
	  
	  final JPanel cards = new JPanel(new CardLayout());
	  
	  LoginScreen card0 = new LoginScreen();
	  CreateUser card1 = new CreateUser();
	  MainMenu card2 = new MainMenu();
	  PlayingField card3 = new PlayingField();
	  
	  
	  //Each screen will be a different screen, we will switch between these like "cards"
	  //In the end our game should have login screen, main menu, game, and scores, for a total
	  //of four cards.
	  cards.add(card0, "LoginScreen");
	  cards.add(card1, "CreateUser");
	  cards.add(card2, "MainMenu");
	  cards.add(card3, "MainGame");
//	  cards.add(card3, "UserScores");
	  
	  this.add(cards);
	  
	  final CardLayout cardLayout = (CardLayout) cards.getLayout();
	  
	  //Mouse Listeners for the Login Screen
	  card0.login.addMouseListener(new MouseAdapter(){
		  public void mousePressed(MouseEvent e){
			  cardLayout.show(cards, "MainMenu");
		  }
	  });
	  
	  card0.newUser.addMouseListener(new MouseAdapter(){
		  public void mousePressed(MouseEvent e){
			  cardLayout.show(cards, "CreateUser");
		  }
	  });	  
	  
	  //Each of these will also need to implement the logic that makes up the difficulty
	  card2.easyButton.addMouseListener(new MouseAdapter(){
		  public void mousePressed(MouseEvent e){
			  cardLayout.show(cards, "MainGame");
		  }
	  });
	  card2.mediumButton.addMouseListener(new MouseAdapter(){
		  public void mousePressed(MouseEvent e){
			  cardLayout.show(cards, "MainGame");
		  }
	  });
	  card2.hardButton.addMouseListener(new MouseAdapter(){
		  public void mousePressed(MouseEvent e){
			  cardLayout.show(cards, "MainGame");
		  }
	  });
	  card2.hardestButton.addMouseListener(new MouseAdapter(){
		  public void mousePressed(MouseEvent e){
			  cardLayout.show(cards, "MainGame");
		  }
	  });
//	  card2.scoreButton.addMouseListener(new MouseAdapter(){
//		  public void mousePressed(MouseEvent e){
//			  cardLayout.show(cards, "ScorePage");
//		  }
//	  });
//	  card2.loadGame.addMouseListener(new MouseAdapter(){
//		  public void mousePressed(MouseEvent e){
//			  cardLayout.show(cards, "LoadGameMenu");
//		  }
//	  });
	  

   }
    
  
}





