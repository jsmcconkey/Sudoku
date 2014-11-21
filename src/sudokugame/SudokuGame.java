//Programmers <James McConkey, Ethan Smith>

/*
 * Checked legitimacy of the puzzle input reader. 
 * Code looks good and does it's job. 
 * Nice work guys. -Chris
 */
package sudokugame;

import java.util.*;
import java.awt.*;
import java.io.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import cell.Cell;
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
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

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
	
	private String userParentDirectory = null;
	
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
				String stringPassword = new String(password);
								  
				//This is where we need to check if this person is a registered user
				boolean isExistingLogin = checkLogin(username, stringPassword);
				
				if(isExistingLogin == true)
				{
					String message = "Welcome back " + username + "!";				  
					JOptionPane.showMessageDialog(null, message);
					cardLayout.show(cards, "MainMenu");
				}
				else
				{
//					String message = "Your username or password are incorrect. \n\n Please reenter your username and password";				  
//					JOptionPane.showMessageDialog(null, message);
				}
					
			}
		});
				  
		card0.createLogin.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				cardLayout.show(cards, "CreateUser");
			}
		});
		  
		card1.createUser.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				boolean passwordsMatch = true;
				boolean usernameIsAcceptable = true;
				
				//Implement logic to add the user here
				String username = card1.newUsername.getText();
				char[] password = card1.newPassword.getPassword();
				char[] confirmPassword = card1.confirmNewPassword.getPassword();
				
				if(username.contains("/") || username.contains("\\") || username.contains(".")){
					usernameIsAcceptable = false;
				}
				
				//If the passwords in both fields match then we search the password file to
				//see if that user name has been taken, if it has not been taken then we add that
				//login to our user "database" which for this assignment is going to just be a txt file			  
				
				if(password.length != confirmPassword.length || password.length == 0 || confirmPassword.length == 0 ||
						password.length < 5 || password.length > 10)
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
					String newPassword = new String(password);
					boolean userAlreadyExists = addUser(username, newPassword);
					
					if(userAlreadyExists == true)
					{
						String message = "The username " + username + " is already registered. \n\nPlease enter a different username.";				  
						JOptionPane.showMessageDialog(null, message);
					}
					else if(usernameIsAcceptable == false)
					{
						String message = "That username contains unacceptable characters.  Please change your username.";				  
						JOptionPane.showMessageDialog(null, message);
					}
					else
					{
						String message = username + " let's play Sudoku!\n\nSelect your difficulty level.";				  
						JOptionPane.showMessageDialog(null, message);
						cardLayout.show(cards, "MainMenu");
					}
				}
				else
				{
					String message = "Invalid password or password mismatch. \n\nPassword must be between 5 and 10 characters long. \n\nPlease re-enter your password";				  
					JOptionPane.showMessageDialog(null, message);
				}
			}
		});
		  
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
		
		card2.expertButton.addMouseListener(new MouseAdapter(){
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
		
		  
		card2.loadGame.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				boolean gameExists = loadGame();
				if(gameExists){
					cardLayout.show(cards, "MainGame");
				}
				else{
					String message = "You currently do not have a saved game.  \n\nPlease select a difficulty.";				  
					JOptionPane.showMessageDialog(null, message);
				}
			}
		});
		
		card2.logout.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				cardLayout.show(cards, "LoginScreen");
			}
		});
		  
		  
		card3.giveUp.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){		
				boolean yesno = giveUp();
				
				if(yesno == true)
				{
					cardLayout.show(cards, "UserScores");				
					Reload();
				}
			}
		});
		
//		card3.backToGridSelector.addMouseListener(new MouseAdapter(){
//			public void mousePressed(MouseEvent e){
//				
//			}
//		});
//		
//		card3.clearColor.addMouseListener(new MouseAdapter(){
//			public void mousePressed(MouseEvent e){
//				
//			}
//		});
//		
//		card3.blueButton.addMouseListener(new MouseAdapter(){
//			public void mousePressed(MouseEvent e){
//				
//			}
//		});
//		
//		card3.greenButton.addMouseListener(new MouseAdapter(){
//			public void mousePressed(MouseEvent e){
//				
//			}
//		});
//		
//		card3.redButton.addMouseListener(new MouseAdapter(){
//			public void mousePressed(MouseEvent e){
//				
//			}
//		});
//		
//		card3.yellowButton.addMouseListener(new MouseAdapter(){
//			public void mousePressed(MouseEvent e){
//				
//			}
//		});
		
		card3.savePuzzle.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				activeField.setButtonGridVisible(false);
				saveGame();
				String message = "Your game has been saved!\nTo load your game select Load Game from the main menu.";				  
				JOptionPane.showMessageDialog(null, message);	
				
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
			}
		});
				  
		card4.backButton.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				cardLayout.show(cards, "MainMenu");
			}
		});
		
		
		
	   }
  	
  	
  	public boolean checkLogin(String username, String password)
  	{
	  	String path = null;
	  	String fileUsername = null;
	  	String filePassword = null;
	  	
	  	try
	  	{
	  		if(OSDetector.isWindows())
	  		{
	  			path = getClass().getClassLoader().getResource(".").getPath();
	  			path = path.substring(0, path.length()-4);
	  			userParentDirectory = path + "data\\\\Users\\\\" + username;
	  			path = path + "data\\\\Users\\\\" + username + "\\\\userinfo.txt";
	  		}
	  		else if(OSDetector.isLinux() || OSDetector.isMac())
	  		{
			  	path = getClass().getClassLoader().getResource(".").getPath();
			  	path = path.substring(0, path.length()-4) ;
			  	userParentDirectory = path + "data/Users/" + username;
			  	path = path + "data/Users/" + username + "/userinfo.txt";
			}
	  	} catch (Exception e)
	  	{
	  		e.printStackTrace(System.err);
	  	}
  		
	  	File possibleUser = new File(userParentDirectory);
	  	
	  	if(possibleUser.exists())
	  	{
	  		//Get the password from that txt file
		    File file = new File(path);
		    FileInputStream fis = null;
		    BufferedInputStream bis = null;
		    DataInputStream dis = null;
		    
		    try {
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				dis = new DataInputStream(bis);
								
				fileUsername = dis.readLine();				
				filePassword = dis.readLine();
				
		  		fis.close();
		  		bis.close();
		  		dis.close();
		  		
		    } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  		
	  		System.out.println("That is a user, now check the password.");
	  		if(password.equals(filePassword))
	  		{
	  			return true;
	  		}
	  		else
	  		{
				String message = "That password does not match our records.  \n\nPlease reenter your password.";				  
				JOptionPane.showMessageDialog(null, message);
	  			return false;
	  		}
	  	}
	  	else
	  	{
			String message = "Your username is not found. \n\nPlease enter a valid username or create a new user.";				  
			JOptionPane.showMessageDialog(null, message);
	  		return false;
	  	}  	
	}
  
	public boolean addUser(String username, String password)
	{
	  	String path = null;
	  	
	  	try
	  	{
	  		if(OSDetector.isWindows())
	  		{
	  			path = getClass().getClassLoader().getResource(".").getPath();
	  			path = path.substring(0, path.length()-4);
	  			userParentDirectory = path + "data\\\\Users\\\\" + username;
	  			path = path + "data\\\\Users\\\\" + username + "\\\\userinfo.txt";
	  		}
	  		else if(OSDetector.isLinux() || OSDetector.isMac())
	  		{
			  	path = getClass().getClassLoader().getResource(".").getPath();
			  	path = path.substring(0, path.length()-4) ;
			  	userParentDirectory = path + "data/Users/" + username;
			  	path = path + "data/Users/" + username + "/userinfo.txt";
			}
	  	} catch (Exception e)
	  	{
	  		e.printStackTrace(System.err);
	  	}
	  	
	  	System.out.println(userParentDirectory);
	  	System.out.println(path);
	  	
	  	
	  	
	  	File newUser = new File(userParentDirectory);
	  	
	  	if(newUser.exists())
	  	{
	  		System.out.println("That user already exists");
	  		return true;
	  	}
	  	else
	  	{
	  		try {
	  			File userDirectory = new File(userParentDirectory);
	  			userDirectory.mkdir();
	  			File userInfo = new File(userParentDirectory, ("userinfo.txt"));
	  			
	  			try {
					userInfo.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				PrintWriter writer = new PrintWriter(path);//, "UTF-8)");
				writer.println(username);
				writer.println(password);
				writer.println("0");
				writer.println("#");
				writer.println("##");
				writer.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  		
	  		System.out.println("That user does not exist");
	  		
	  		return false;
	  	}		
	}
	
	public void saveGame()
	{
		File savedGame = null;
		PrintWriter writer = null;
		
	  	try
	  	{
	  		if(OSDetector.isWindows())
	  		{
	  			savedGame = new File(userParentDirectory, ("\\\\savedgame.txt"));
	  			writer = new PrintWriter(userParentDirectory + "\\\\savedgame.txt");
	  		}
	  		else if(OSDetector.isLinux() || OSDetector.isMac())
	  		{
	  			savedGame = new File(userParentDirectory, ("/savedgame.txt"));
	  			writer = new PrintWriter(userParentDirectory + "/savedgame.txt");
			}
	  	} catch (Exception e)
	  	{
	  		e.printStackTrace(System.err);
	  	}
		
		try
		{
			System.out.println(userParentDirectory);
  			savedGame.createNewFile();
  			
  			Cell[][] arrayToSave = activeField.getArray();
  			

			//Here you have the full array of cells. Now you just have to parse through this array and cell.getValue() to get all the values you want to write.
			//You will also need to write whether or not the cell has been "locked" into the grid (the preset cells are all locked)
			for(int i = 0; i < arrayToSave.length; i++)
			{
				for(int j = 0; j < arrayToSave[i].length; j++)
				{
					writer.println(i + "," + j + "," + arrayToSave[i][j].getValue() + "," + arrayToSave[i][j].getLocked());
					System.out.println(i + "," + j + "," + arrayToSave[i][j].getValue() + "," + arrayToSave[i][j].getLocked());
				}
			}
			
			writer.close();
			
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public boolean loadGame()
	{
		Puzzle savedPuzzle = new Puzzle(xoffset,cellsize,yoffset);
		File file = null;
		String currentLine = null;
	    FileInputStream fis = null;
	    BufferedInputStream bis = null;
	    DataInputStream dis = null;
	    		
	  	try
	  	{
	  		if(OSDetector.isWindows())
	  		{
	  		    file = new File(userParentDirectory + "\\\\savedgame.txt");
	  		}
	  		else if(OSDetector.isLinux() || OSDetector.isMac())
	  		{
	  		    file = new File(userParentDirectory + "/savedgame.txt");
			}
	  	} catch (Exception e)
	  	{
	  		e.printStackTrace(System.err);
	  	}
	  	
	  	if(file.exists())
	  	{
		    try {
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				dis = new DataInputStream(bis);
				
				while((currentLine = dis.readLine()) != null)
				{
					System.out.println("Current line: " + currentLine);
					
					String tokens[] = currentLine.split(",");
					
		    		int row = Integer.parseInt(tokens[0]);
		    		int column = Integer.parseInt(tokens[1]);
		    		int value = Integer.parseInt(tokens[2]);
		    		String locked = tokens[3];
		    		

					boolean b = true;
					if(locked.equals("false"))
					{
						b = false;
					}
					
					savedPuzzle.setCell(row, column, value, false, b);
					
					System.out.println(row);
				}
		    } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    activeField.setGrid(savedPuzzle);
		    return true;
	  	}
	  	else{
			return false;
	  	}
	}
	
	public boolean checkPuzzle()
	{
		boolean puzzleComplete = activeField.checkVictory();
		int points = activeField.checkScore();
		int mod = 1;
		
		if(activeField.getDifficulty().equals("medium"))
			mod = 2;		
		else if(activeField.getDifficulty().equals("hard"))
			mod = 4;	
		else if(activeField.getDifficulty().equals("expert"))
			mod = 7;			

		
		
		if(puzzleComplete == true)
		{	
			//Add to the user's score here
			points = (points*mod);
			
			
			String message = "Congratulations! you have successfully completed the puzzle for a full " + points + " points!";				  
			JOptionPane.showMessageDialog(null, message);
			
			return true;
		}
		else
		{
			String message = "Puzzle is still incomplete. If you would like to claim partial points, please press Give Up.\n\nGood luck!";
			JOptionPane.showMessageDialog(null, message);
			return false;
		}
	}
	
	public boolean giveUp()
	{
		boolean puzzleComplete = activeField.checkVictory();
		int points = activeField.checkScore();
		boolean r = true;
		int mod = 1;
		
		if(activeField.getDifficulty().equals("medium"))
			mod = 2;		
		else if(activeField.getDifficulty().equals("hard"))
			mod = 4;	
		else if(activeField.getDifficulty().equals("expert"))
			mod = 7;			

		
		
		
		
		if(puzzleComplete == true)
		{

			//Add to the user's score here
			points = (points*mod);
			
			
			String message = "No need to give up! Your puzzle is completely correct. You have been awarded a full "+ points+" points!";				  
			JOptionPane.showMessageDialog(null, message);
	
		}
		else
		{
			String message = "You have given up on an incomplete Puzzle. You have been rewarded "+points+" points for your effort.";
			JOptionPane.showMessageDialog(null, message);
			
			//Add to the user's score here	
		}
		
		//R is going to be used for a "are you sure" type of option
		return r;
		
	}
	
	
	
	  public void Reload()
	  {		
		    activeField.setButtonGridVisible(false);
			EasyList.clear();
			EasyList = new ArrayList<Puzzle>();
			MediumList.clear();
			MediumList = new ArrayList<Puzzle>();
			HardList.clear();
			HardList = new ArrayList<Puzzle>();
			ExpertList.clear();
			ExpertList = new ArrayList<Puzzle>();
			UserList.clear();
			UserList = new ArrayList<User>();
			readPuzzles();
		  
		  
		  
		  
		  
	  }
	  
	  public void readPuzzles() 
	  {
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
			  			path = path + "data\\\\Puzzles\\\\" + puzzleName;
			  		}
			  		else if(OSDetector.isLinux() || OSDetector.isMac())
			  		{
					  	path = getClass().getClassLoader().getResource(".").getPath();
					  	path = path.substring(0, path.length()-4);
					  	path = path + "data/Puzzles/" + puzzleName;
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
		    	  
		    	  System.out.println("-------------------------------------------------\nLOADING PUZZLE: "+puzzleName+"\n-------------------------------------------------");
		    	  
			      // dis.available() returns 0 if the file does not have more lines.
			      while (dis.available() != 0) {
	
			    	  
					String s = dis.readLine();
			    	
					if(s.contains("/*"))
					{
						if(s.contains("correct"))
						{
							finished = true;
				    		System.out.println("-------------------------------------------------\nLOADING SOLUTIONS FOR: "+ puzzleName + "\n-------------------------------------------------");
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
			    		thisPuzzle.setCell(row, column, value,finished,true); 
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
	  
	  
	  
	  public void addPoints(int num)
	  {
		  
		  
		  
	  }
}





