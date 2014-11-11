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
import osdetector.OSDetector;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

public class SudokuGame extends JApplet
{
	private final int WIDTH = 640;
	private final int HEIGHT = 480;
	private final int yoffset = 1;
	private final int xoffset = 30;
	private final int cellsize = 52;
	private final int AMNT_PUZZLES = 9;
	private Random rn;
	
	private PlayingField activeField;
	
	//Stores all of the puzzles and the users
	private ArrayList<Puzzle> EasyList;
	private ArrayList<Puzzle> MediumList;
	private ArrayList<Puzzle> HardList;
	private ArrayList<Puzzle> ExpertList;
	
	private ArrayList<User> UserList;
	
  	public void init()
	{
		this.setSize(WIDTH, HEIGHT);
		
		EasyList = new ArrayList<Puzzle>();
		MediumList = new ArrayList<Puzzle>();
		HardList = new ArrayList<Puzzle>();
		ExpertList = new ArrayList<Puzzle>();
		UserList = new ArrayList<User>();
		readPuzzles();
		
		rn = new Random();
		
		
		
		activeField = new PlayingField(xoffset,cellsize,yoffset);

				  
				  
		final JPanel cards = new JPanel(new CardLayout());  
				  
		final LoginScreen card0 = new LoginScreen();
		final CreateUser card1 = new CreateUser();
		final MainMenu card2 = new MainMenu();
		final PlayingField card3 = activeField;
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
				int amount = EasyList.size();
				System.out.println("Size of EasyList: "+ amount);
				
				if(amount == 0)
				{
					String message = "There are no easy mazes in the data folder!";				  
					JOptionPane.showMessageDialog(null, message);					
				}
				else if(amount > 0)
				{
					int rand = rn.nextInt(amount);					
					activeField.setGrid(EasyList.get(rand));
					cardLayout.show(cards, "MainGame");									
				}
			}
		});
		  
		card2.mediumButton.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				int amount = MediumList.size();
				System.out.println("Size of MediumList: "+ amount);
				
				if(amount == 0)
				{
					String message = "There are no medium mazes in the data folder!";				  
					JOptionPane.showMessageDialog(null, message);					
				}
				else if(amount > 0)
				{
					int rand = rn.nextInt(amount);					
					activeField.setGrid(MediumList.get(rand));
					cardLayout.show(cards, "MainGame");									
				}
			}
		});
		  
		card2.hardButton.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				int amount = HardList.size();
				System.out.println("Size of HardList: "+ amount);
				
				if(amount == 0)
				{
					String message = "There are no hard mazes in the data folder!";				  
					JOptionPane.showMessageDialog(null, message);					
				}
				else if(amount > 0)
				{
					int rand = rn.nextInt(amount);					
					activeField.setGrid(HardList.get(rand));
					cardLayout.show(cards, "MainGame");									
				}
			}
		});
		
		card2.hardestButton.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				int amount = ExpertList.size();
				System.out.println("Size of ExpertList: "+ amount);
				
				if(amount == 0)
				{
					String message = "There are no expert mazes in the data folder!";				  
					JOptionPane.showMessageDialog(null, message);					
				}
				else if(amount > 0)
				{
					int rand = rn.nextInt(amount);					
					activeField.setGrid(ExpertList.get(rand));
					cardLayout.show(cards, "MainGame");									
				}
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
		boolean puzzleComplete = activeField.checkVictory();

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
	  
	  public void readPuzzles() {
		  	String path = null;
		  	
		  	
		  	for(int puzzNum = 1; puzzNum <= AMNT_PUZZLES; puzzNum++)
		  	{
			  	String puzzleName = "puzzle"+puzzNum+".txt";
			  	
			  	try
			  	{
			  		if(OSDetector.isWindows())
			  		{
			  			path = getClass().getClassLoader().getResource(".").getPath();
			  			path = path.substring(0, path.length()-4);
			  			path = path + "data\\\\" + puzzleName;
			  		}
			  		else if(OSDetector.isLinux() || OSDetector.isMac())
			  		{
					  	path = getClass().getClassLoader().getResource(".").getPath();
					  	path = path.substring(0, path.length()-4);
					  	path = path + "data\\\\" + puzzleName;
					}
			  	} catch (Exception e)
			  	{
			  		e.printStackTrace(System.err);
			  	}
			  	
			  	
			  	
			    File file = new File(path);
			    FileInputStream fis = null;
			    BufferedInputStream bis = null;
			    DataInputStream dis = null;
	
			    try {
			      fis = new FileInputStream(file);
	
			      // Here BufferedInputStream is added for fast reading.
			      bis = new BufferedInputStream(fis);
			      dis = new DataInputStream(bis);
			      
			      //Sets up new Puzzle
		    	  Puzzle thisPuzzle = new Puzzle(xoffset,cellsize,yoffset);
		    	  boolean finished = false;
		    	  
		    	  System.out.println("-----------------------\nLOADING PUZZLE: "+puzzleName+"\n----------------------");
		    	  
			      // dis.available() returns 0 if the file does not have more lines.
			      while (dis.available() != 0) {
	
			    	  
					String s = dis.readLine();
			    	
					if(s.contains("/*"))
					{
						if(s.contains("correct"))
						{
							finished = true;
				    		System.out.println("-----------------------\nLOADING SOLUTIONS FOR: "+ puzzleName + "\n----------------------");
						}
						System.out.println("Comment Line");	
					}			
					else if(s.equals("easy") || s.equals("medium") || s.equals("hard") || s.equals("expert"))
			    	{
			    		thisPuzzle.setDifficulty(s);
				        System.out.println(s);
			    	}
			    	else if(s.length() == 5)
			    	{
			    		String tokens[] = s.split(",");
			    		//System.out.println("Splitting "+s);		    		
			    		
			    		if(tokens.length != 3)
			    			break;
			    		
			    		int row = Integer.parseInt(tokens[0]) -1;
			    		int column = Integer.parseInt(tokens[1]) -1;
			    		int value = Integer.parseInt(tokens[2]);
			    		thisPuzzle.setCell(row, column, value,finished); 
				        System.out.println(s);
			    	}
			    	else
			    	{
			    		//System.out.println("Line Unread, cannot understand format!");	
			    	}
			    		
			    	
	
			      }
	
			      // dispose all the resources after using them.
			      fis.close();
			      bis.close();
			      dis.close();
			      
			      if(thisPuzzle.checkReal() == true)
			      {
			    	  if(thisPuzzle.getDifficulty().equals("easy"))
			    		  EasyList.add(thisPuzzle);
			    	  else if(thisPuzzle.getDifficulty().equals("medium"))
			    		  MediumList.add(thisPuzzle);
			    	  else if(thisPuzzle.getDifficulty().equals("hard"))
			    		  HardList.add(thisPuzzle);
			    	  else if(thisPuzzle.getDifficulty().equals("expert"))
			    		  ExpertList.add(thisPuzzle);
			      }
	
			    } catch (FileNotFoundException e) {
			      e.printStackTrace();
			    } catch (IOException e) {
			      e.printStackTrace();
			    }
			  }
	
	    
	  }
}





