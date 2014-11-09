//Programmers <James McConkey, Ethan Smith>

package sudokugame;

import java.util.*;
import java.awt.*;
import java.io.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import playingfield.PlayingField;
import puzzle.Puzzle;
import Menu.MainMenu;
import Menu.UserScores;
import loginscreen.CreateUser;
import loginscreen.LoginScreen;
import loginscreen.User;



public class SudokuGame extends JApplet
{
	private final int WIDTH = 640;
	private final int HEIGHT = 480;
	private final int yoffset = 1;
	private final int xoffset = 30;
	private final int cellsize = 52;
	
	ArrayList<User> userList = new ArrayList<User>();
	
  	public void init()
	{
		this.setSize(WIDTH, HEIGHT);
			
				  
		Puzzle puzzle1 = new Puzzle(xoffset,cellsize,yoffset);
				  
				  
		final JPanel cards = new JPanel(new CardLayout());  
				  
		final LoginScreen card0 = new LoginScreen();
		final CreateUser card1 = new CreateUser();
		final MainMenu card2 = new MainMenu();
		final PlayingField card3 = new PlayingField(puzzle1,xoffset,cellsize,yoffset);
		final UserScores card4 = new UserScores();
		 
				  
		//Each screen will be a different screen, we will switch between these like "cards"
		//In the end our game should have login screen, main menu, game, and scores, for a total
		//of four cards.
		cards.add(card0, "LoginScreen");
		cards.add(card1, "CreateUser");
		cards.add(card2, "MainMenu");
		cards.add(card3, "MainGame");
		cards.add(card4, "UserScores");
				  
		this.add(cards);
				  
		final CardLayout cardLayout = (CardLayout) cards.getLayout();
				  
		//Mouse Listeners for the Login Screen
		card0.login.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				String username = card0.enterUsername.getText();
				char[] password = card0.enterPassword.getPassword();
				System.out.println(username+"\n");
				System.out.println(password+"\n");
								  
				//This is where we need to check if this person is a registered user
								  
								  
								  
					
								  
				cardLayout.show(cards, "MainMenu");
			}
		});
		  
		card0.newUser.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				cardLayout.show(cards, "CreateUser");
			}
		});
		  
		card1.createUser.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				boolean passwordsMatch = true;
				
				//Implement logic to add the user here
				String username = card1.usernameLabel.getText();
				char[] password = card1.newPassword.getPassword();
				char[] confirmPassword = card1.confirmNewPassword.getPassword();
				
				//If the passwords in both fields match then we search the password file to
				//see if that user name has been taken, if it has not been taken then we add that
				//login to our user "database" which for this assignment is going to just be a txt file			  
				
				if(password.length != confirmPassword.length || password.length == 0 || confirmPassword.length == 0)
				{
					passwordsMatch = false;
				}
				else
				{
					for(int i = 0; i<password.length; i++)
					{
						if(password[i] != confirmPassword[i])
						{
							passwordsMatch = false;
							break;
						}
					}
				}
	
				if(passwordsMatch == true)
				{
					//Call function to add username and password to txt file, this function needs to return
					//true if that username and password already exist
					boolean userAlreadyExists = addUser(username, password);
					
					if(userAlreadyExists == true)
					{
					 
					}
					else
					{
					 
					}
					
					
					cardLayout.show(cards, "MainGame");
				}
				else
				{
					String message = "Invalid password or password mismatch. \n\nPlease re-enter your password";				  
					JOptionPane.showMessageDialog(null, message);
				}
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
		  
		card2.userScores.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				cardLayout.show(cards, "UserScores");
			}
		});
		
		  
	//	  card2.loadGame.addMouseListener(new MouseAdapter(){
	//		  public void mousePressed(MouseEvent e){
	//			  cardLayout.show(cards, "LoadGameMenu");
	//		  }
	//	  });
		  
		  
		card3.giveUp.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				cardLayout.show(cards, "MainMenu");
			}
		});
		
		card3.savePuzzle.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				//This is where we need to add in the logic for saving the game.  I think we should
				//only allow users to keep one game saved at a time.
				cardLayout.show(cards, "MainMenu");
			}
		});
		
		card3.checkPuzzle.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				boolean puzzleComplete = checkPuzzle();
				
				if(puzzleComplete == true)
				{
					
					cardLayout.show(cards, "UserScores");
				}
				else
				{
					//Do nothing
				}
			}
		});
				  
		card4.backButton.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				cardLayout.show(cards, "MainMenu");
			}
		});
		
		
		
	   }
  	
  	
  	
  
	public boolean addUser(String username, char[] password)
	{
		
		
		
		
		return true;
	}
	
	public boolean checkPuzzle()
	{
		boolean puzzleComplete = true;
		
		//Do a double for loop here to traverse the puzzle to check if each tile contains a 1-9 for
		//for each column and row with no repeats
		
		//Now check each 3 by 3 subsection of the puzzle for 1-9
		
		
		if(puzzleComplete == true)
		{
			String message = "GOOD JOB! \n\nPUZZLE COMPLETE!";				  
			JOptionPane.showMessageDialog(null, message);
			
			//Add to the user's score here
			
			
			return true;
		}
		else
		{
			String message = "Puzzle is still incomplete. There must be an error in your answer.\n\nGood luck!";
			JOptionPane.showMessageDialog(null, message);
			return false;
		}
	}
	  
	public void readPuzzles()
	{
		
	}
    
  
}





