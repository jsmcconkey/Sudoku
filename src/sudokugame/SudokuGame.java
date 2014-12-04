/*
 * Programmers <James McConkey and Ethan Smith>
 * 12/5/2014
 */
package sudokugame;

import java.util.*;
import java.awt.*;
import java.io.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.imageio.ImageIO;
import javax.swing.*;
import cell.Cell;
import playingfield.PlayingField;
import puzzle.Puzzle;
import Menu.MainMenu;
import Menu.UserScore;
import Menu.ScoresTable;
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

public class SudokuGame extends JApplet {
	//Data
	private final int WIDTH = 640;
	private final int HEIGHT = 480;
	private final int yoffset = 1;
	private final int xoffset = 30;
	private final int cellsize = 52;
	private final int AMNT_PUZZLES = 9;
	private String userParentDirectory = null;
	private String userScoresParentDir = null;
	private String username = null;
	private Random rn;
	private PlayingField activeField;
	private ScoresTable activeScoreCard;
	
	//Stores all of the puzzles and the users
	private ArrayList<Puzzle> EasyList;
	private ArrayList<Puzzle> MediumList;
	private ArrayList<Puzzle> HardList;
	private ArrayList<Puzzle> ExpertList;
	private ArrayList<UserScore> ScoreList;
	private ArrayList<User> UserList;
	
	//Background Images
	private Image titleBackground;
	private Image gameBackground;
	private Image scoresBackground;
	private Image createBackground;
	private Image menuBackground;
	
	//Main initialization function
	public void init() {
		this.setSize(WIDTH, HEIGHT);
		
		//Initialize the Lists to be used for the game
		EasyList = new ArrayList<Puzzle>();
		MediumList = new ArrayList<Puzzle>();
		HardList = new ArrayList<Puzzle>();
		ExpertList = new ArrayList<Puzzle>();
		UserList = new ArrayList<User>();
		ScoreList = new ArrayList<UserScore>();
		
		//Load all of the data using the file I/O system
		readPuzzles();
		readScores();
		loadImages();
		
		//Random number to be used for puzzle selection
		rn = new Random();
				
		//Sudoku PlayingField and HighScore  cards
		activeField = new PlayingField(xoffset,cellsize,yoffset,gameBackground);
		activeScoreCard = new ScoresTable(ScoreList,scoresBackground);
		
		
		//The transitions in the game will be handled using a CardLayout.
		final JPanel cards = new JPanel(new CardLayout());  
		final LoginScreen card0 = new LoginScreen(titleBackground);
		final CreateUser card1 = new CreateUser(createBackground);
		final MainMenu card2 = new MainMenu(menuBackground);
		final PlayingField card3 = activeField;
		final ScoresTable card4 = activeScoreCard;
		
		cards.add(card0, "LoginScreen");
		cards.add(card1, "CreateUser");
		cards.add(card2, "MainMenu");
		cards.add(card3, "MainGame");
		cards.add(card4, "UserScores");
		
		this.add(cards);
		final CardLayout cardLayout = (CardLayout) cards.getLayout();
		
		
		/*
		 * Mouse Listeners that involve card transitions are found below because
		 * card transitions must take place in this area of the game.
		 */
		
		//Login Screen Action Listeners******************
		card0.login.addMouseListener(new MouseAdapter() { 
			public void mousePressed(MouseEvent e){
				username = card0.enterUsername.getText();
				char[] password = card0.enterPassword.getPassword();
				String stringPassword = new String(password);
								  
				//This is where we need to check if this person is a registered user
				boolean isExistingLogin = checkLogin(username, stringPassword);
				
				if(isExistingLogin == true) {
					String message = "Welcome back " + username + "!";				  
					JOptionPane.showMessageDialog(null, message);
					cardLayout.show(cards, "MainMenu");
				}
				else {
//					String message = "Your username or password are incorrect. \n\n Please reenter your username and password";				  
//					JOptionPane.showMessageDialog(null, message);
				}
					
			}
		});
				  
		card0.createLogin.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e){
				cardLayout.show(cards, "CreateUser");
			}
		});
		
		
		//Create User Screen Action Listeners*****************
		card1.backButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				cardLayout.show(cards, "LoginScreen");
			}
		});
		
		card1.createUser.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e){
				boolean passwordsMatch = true;
				boolean usernameIsAcceptable = true;
				
				//Implement logic to add the user here
				username = card1.newUsername.getText();
				char[] password = card1.newPassword.getPassword();
				char[] confirmPassword = card1.confirmNewPassword.getPassword();
				
				if(username.contains("/") || username.contains("\\") || username.contains(".")){
					usernameIsAcceptable = false;
				}
				
				//If the passwords in both fields match then we search the password file to
				//see if that user name has been taken, if it has not been taken then we add that
				//login to our user "database" which for this assignment is going to just be a txt file			  
				
				if(password.length != confirmPassword.length || password.length == 0 || confirmPassword.length == 0 ||
						password.length < 5 || password.length > 10) {
					passwordsMatch = false;
				}
				else {
					for(int i = 0; i<password.length; i++) {
						if(password[i] != confirmPassword[i]) {
							passwordsMatch = false;
							break;
						}
					}
				}
	
				if(passwordsMatch == true) {
					//Call function to add username and password to txt file, this function needs to return
					//true if that username and password already exist
					String newPassword = new String(password);
					boolean userAlreadyExists = addUser(username, newPassword);
					
					if(userAlreadyExists == true) {
						String message = "The username " + username + " is already registered. \n\nPlease enter a different username.";				  
						JOptionPane.showMessageDialog(null, message);
					}
					else if(usernameIsAcceptable == false) {
						String message = "That username contains unacceptable characters.  Please change your username.";				  
						JOptionPane.showMessageDialog(null, message);
					}
					else {
						String message = username + " let's play Sudoku!\n\nSelect your difficulty level.";				  
						JOptionPane.showMessageDialog(null, message);
						cardLayout.show(cards, "MainMenu");
					}
				}
				else {
					String message = "Invalid password or password mismatch. \n\nPassword must be between 5 and 10 characters long. \n\nPlease re-enter your password";				  
					JOptionPane.showMessageDialog(null, message);
				}
			}
		});
		
		//Main Menu Screen action listeners*******************S
		card2.easyButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				int amount = EasyList.size();

				if(amount == 0) {
					String message = "There are no easy mazes in the data folder!";				  
					JOptionPane.showMessageDialog(null, message);					
				}
				else if(amount > 0) {
					int rand = rn.nextInt(amount);					
					activeField.setGrid(EasyList.get(rand));
					cardLayout.show(cards, "MainGame");									
				}
			}
		});
		  
		card2.mediumButton.addMouseListener(new MouseAdapter() { 
			public void mousePressed(MouseEvent e) {
				int amount = MediumList.size();
				
				if(amount == 0) {
					String message = "There are no medium mazes in the data folder!";				  
					JOptionPane.showMessageDialog(null, message);					
				}
				else if(amount > 0) {
					int rand = rn.nextInt(amount);					
					activeField.setGrid(MediumList.get(rand));
					cardLayout.show(cards, "MainGame");									
				}
			}
		});
		  
		card2.hardButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				int amount = HardList.size();
				
				if(amount == 0) {
					String message = "There are no hard mazes in the data folder!";				  
					JOptionPane.showMessageDialog(null, message);					
				}
				else if(amount > 0) {
					int rand = rn.nextInt(amount);					
					activeField.setGrid(HardList.get(rand));
					cardLayout.show(cards, "MainGame");									
				}
			}
		});
		
		card2.expertButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				int amount = ExpertList.size();
				
				if(amount == 0) {
					String message = "There are no expert mazes in the data folder!";				  
					JOptionPane.showMessageDialog(null, message);					
				}
				else if(amount > 0) {
					int rand = rn.nextInt(amount);					
					activeField.setGrid(ExpertList.get(rand));
					cardLayout.show(cards, "MainGame");									
				}
			}
		});
		  
		card2.userScores.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e){
				readScores();
				cardLayout.show(cards, "UserScores");
			}
		});
				  
		card2.loadGame.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				boolean gameExists = loadGame();
				if(gameExists) {
					cardLayout.show(cards, "MainGame");
				}
				else {
					String message = "You currently do not have a saved game.  \n\nPlease select a difficulty.";				  
					JOptionPane.showMessageDialog(null, message);
				}
			}
		});
		
		card2.logout.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				cardLayout.show(cards, "LoginScreen");
			}
		});
		  
		//Main Game Action Listeners**********************
		card3.giveUp.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {		
				boolean yesno = giveUp();
				
				if(yesno == true) {
					readScores();
					activeScoreCard.setScores(ScoreList);
					cardLayout.show(cards, "UserScores");				
					Reload();
				}
			}
		});
		
		card3.savePuzzle.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				activeField.setButtonGridVisible(false);
				saveGame();
				Reload();
				String message = "Your game has been saved!\nTo load your game select Load Game from the main menu.";				  
				JOptionPane.showMessageDialog(null, message);	
				
				cardLayout.show(cards, "MainMenu");
			}
		});
		
		card3.checkPuzzle.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				boolean puzzleComplete = checkPuzzle();		
				if(puzzleComplete == true) {	
					readScores();
					activeScoreCard.setScores(ScoreList);
					cardLayout.show(cards, "UserScores");	
					Reload();
				}
			}
		});
		
		//High Scores Screen Action Listeners*****************
		card4.backButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				cardLayout.show(cards, "MainMenu");
			}
		});
	}
  	
  	//checkLogin checks to the I/O system if the login exists and if the password is correct
  	public boolean checkLogin(String username, String password) {
	  	String path = null;
	  	String fileUsername = null;
	  	String filePassword = null;
	  	
	  	try {
	  		if(OSDetector.isWindows()) {
	  			path = getClass().getClassLoader().getResource(".").getPath();
	  			path = path.substring(0, path.length()-4);
	  			userParentDirectory = path + "data\\\\Users\\\\" + username;
	  			path = path + "data\\\\Users\\\\" + username + "\\\\userinfo.txt";
	  		}
	  		else if(OSDetector.isLinux() || OSDetector.isMac()) {
			  	path = getClass().getClassLoader().getResource(".").getPath();
			  	path = path.substring(0, path.length()-4) ;
			  	userParentDirectory = path + "data/Users/" + username;
			  	path = path + "data/Users/" + username + "/userinfo.txt";
			}
	  	} catch (Exception e) {
	  		e.printStackTrace(System.err);
	  	}
  		
	  	File possibleUser = new File(userParentDirectory);
	  	
	  	if(possibleUser.exists()) {
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
	  		
	  		if(password.equals(filePassword)) {
	  			return true;
	  		}
	  		else {
				String message = "That password does not match our records.  \n\nPlease reenter your password.";				  
				JOptionPane.showMessageDialog(null, message);
	  			return false;
	  		}
	  	}
	  	else {
			String message = "Your username is not found. \n\nPlease enter a valid username or create a new user.";				  
			JOptionPane.showMessageDialog(null, message);
	  		return false;
	  	}  	
	}
  	
  	//addUser creates a new folder for the user and stores information about the user such as
  	//their username and password.
	public boolean addUser(String username, String password) {
	  	String path = null;
	  	
	  	try {
	  		if(OSDetector.isWindows()) {
	  			path = getClass().getClassLoader().getResource(".").getPath();
	  			path = path.substring(0, path.length()-4);
	  			userParentDirectory = path + "data\\\\Users\\\\" + username;
	  			path = path + "data\\\\Users\\\\" + username + "\\\\userinfo.txt";
	  		}
	  		else if(OSDetector.isLinux() || OSDetector.isMac()) {
			  	path = getClass().getClassLoader().getResource(".").getPath();
			  	path = path.substring(0, path.length()-4) ;
			  	userParentDirectory = path + "data/Users/" + username;
			  	path = path + "data/Users/" + username + "/userinfo.txt";
			}
	  	} catch (Exception e) {
	  		e.printStackTrace(System.err);
	  	}
	  	
	  	File newUser = new File(userParentDirectory);
	  	
	  	if(newUser.exists()) {
	  		return true;
	  	}
	  	else {
	  		try {
	  			File userDirectory = new File(userParentDirectory);
	  			userDirectory.mkdir();
	  			File userInfo = new File(userParentDirectory, ("userinfo.txt"));
	  			
	  			try {
					userInfo.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				PrintWriter writer = new PrintWriter(path);//, "UTF-8)");
				writer.println(username);
				writer.println(password);
				writer.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	  		
	  		return false;
	  	}		
	}
	
	//saveGame saves each value in the user's puzzle to a file in the user's directory.
	public void saveGame() {
		File savedGame = null;
		PrintWriter writer = null;
		
	  	try {
	  		if(OSDetector.isWindows()) {
	  			savedGame = new File(userParentDirectory, ("\\\\savedgame.txt"));
	  			writer = new PrintWriter(userParentDirectory + "\\\\savedgame.txt");
	  		}
	  		else if(OSDetector.isLinux() || OSDetector.isMac()) {
	  			savedGame = new File(userParentDirectory, ("/savedgame.txt"));
	  			writer = new PrintWriter(userParentDirectory + "/savedgame.txt");
			}
	  	} catch (Exception e) {
	  		e.printStackTrace(System.err);
	  	}
		
		try {
  			savedGame.createNewFile();
  			
  			Cell[][] arrayToSave = activeField.getArray();
  			Cell[][] answersToSave = activeField.getAnswerArray();
  
			//Here you have the full array of cells. Now you just have to parse through this array and cell.getValue() to get all the values you want to write.
			//You will also need to write whether or not the cell has been "locked" into the grid (the preset cells are all locked)
			writer.println(activeField.getDifficulty()); 			
  			
			for(int i = 0; i < arrayToSave.length; i++) {
				for(int j = 0; j < arrayToSave[i].length; j++) {
					String C = "white";
					if(arrayToSave[i][j].getColor() == Color.white)
						C = "white";
					if(arrayToSave[i][j].getColor() == Color.white)
						C = "white";
					if(arrayToSave[i][j].getColor() == Color.white)
						C = "white";
					
					writer.println(i + "," + j + "," + arrayToSave[i][j].getValue() + "," + arrayToSave[i][j].getLocked() +","+ C);
				}
			}
			
			writer.println("/*correct answers");
			
			for(int i = 0; i < answersToSave.length; i++) {
				for(int j = 0; j < answersToSave[i].length; j++) {
					writer.println(i + "," + j + "," + answersToSave[i][j].getValue() + "," + answersToSave[i][j].getLocked());
				}
			}			
			
			writer.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//loadGame goes through and loads the game that is saved in the user's profile.
	//Currently the user can only save one game
	public boolean loadGame() {
		Puzzle savedPuzzle = new Puzzle(xoffset,cellsize,yoffset);
		File file = null;
		String currentLine = null;
	    FileInputStream fis = null;
	    BufferedInputStream bis = null;
	    DataInputStream dis = null;
  		boolean finished = false;
  		
	  	try {
	  		if(OSDetector.isWindows()) {
	  		    file = new File(userParentDirectory + "\\\\savedgame.txt");
	  		}
	  		else if(OSDetector.isLinux() || OSDetector.isMac()) {
	  		    file = new File(userParentDirectory + "/savedgame.txt");
			}
	  	} catch (Exception e) {
	  		e.printStackTrace(System.err);
	  	}
	  	
	  	if(file.exists()) { 	  	
		    try {
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				dis = new DataInputStream(bis);
				
				while((currentLine = dis.readLine()) != null) {			
					if(currentLine.contains("/*")) {
						if(currentLine.contains("correct")) {
							finished = true;
						}
					}	
					else if(currentLine.equals("easy") || currentLine.equals("medium") || currentLine.equals("hard") || currentLine.equals("expert")) {
			    		savedPuzzle.setDifficulty(currentLine);
			    	}
					else {
						String tokens[] = currentLine.split(",");
						
			    		int row = Integer.parseInt(tokens[0]);
			    		int column = Integer.parseInt(tokens[1]);
			    		int value = Integer.parseInt(tokens[2]);
			    		String locked = tokens[3];
			    		
						boolean b = true;
						if(locked.equals("false")) {
							b = false;
						}
						
						savedPuzzle.setCell(row, column, value, finished, b);
					}
				}
		    } catch (IOException e) {
				e.printStackTrace();
			}
		    
		    activeField.setGrid(savedPuzzle);
		    return true;
	  	}
	  	else {
			return false;
	  	}
	}
	
	//checkPuzzle checks the current Sudoku puzzle for completion and displays
	//the appropriate message
	public boolean checkPuzzle() {
		boolean puzzleComplete = activeField.checkVictory();
		int points = activeField.checkScore();
		int mod = 1;
		
		if(activeField.getDifficulty().equals("medium")) {
			mod = 2;		
		}
		else if(activeField.getDifficulty().equals("hard")) {
			mod = 4;	
		}
		else if(activeField.getDifficulty().equals("expert")) {
			mod = 7;			
		}

		if(puzzleComplete == true) {	
			//Add to the user's score here
			points = (points*mod);
			updateUserScores(points);
			String message = "Congratulations! you have successfully completed the puzzle for a full " + points + " points!";				  
			JOptionPane.showMessageDialog(null, message);
			return true;
		}
		else {
			String message = "Puzzle is still incomplete. If you would like to claim partial points, please press Give Up.\n\nGood luck!";
			JOptionPane.showMessageDialog(null, message);
			return false;
		}
	}
	
	//The giveUp function gives the user the option to confirm or cancel whether or not
	//they would like to give up.  If they confirm then they will be rewarded for the spaces
	//that they have correct.
	public boolean giveUp() {
		boolean puzzleComplete = activeField.checkVictory();
		int points = activeField.checkScore();

		boolean r = true;	
		int mod = 1;
		
		if(activeField.getDifficulty().equals("medium")) {
			mod = 2;		
		}
		else if(activeField.getDifficulty().equals("hard")) {
			mod = 4;	
		}
		else if(activeField.getDifficulty().equals("expert")) {
			mod = 7;			
		}
		
		if(puzzleComplete == true) {
			points = (points*mod);
			String message = "No need to give up! Your puzzle is completely correct. You have been awarded a full "+ points+" points!";				  
			JOptionPane.showMessageDialog(null, message);
			updateUserScores(points);
		}
		else {
			String cancelMessage = "Are you sure that you would like to give up?\nYou will be rewarded the number of spaces\nthat you have correct.";

			int code = JOptionPane.showConfirmDialog(null, cancelMessage, "GIVE UP?!", JOptionPane.YES_NO_OPTION);

			if (code == JOptionPane.NO_OPTION) {
				r = false;
			} 
			else {
				String message = "You have given up on an incomplete Puzzle. You have been rewarded "+points+" points for your effort.";
				JOptionPane.showMessageDialog(null, message);
				updateUserScores(points);
				r = true;
			} 
		}
		    
		return r;	
	}
	
	//Reloads all of the puzzles into the appropriate data structures
	public void Reload() {		
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
	  
	  public void readPuzzles() {
		  	String path = null;

		  	for(int puzzNum = 1; puzzNum <= AMNT_PUZZLES; puzzNum++) {
			  	String puzzleName = "puzzle"+puzzNum+".txt";
			  	
			  	try {
			  		if(OSDetector.isWindows()) {
			  			path = getClass().getClassLoader().getResource(".").getPath();
			  			path = path.substring(0, path.length()-4);
			  			path = path + "data\\\\Puzzles\\\\" + puzzleName;
			  		}
			  		else if(OSDetector.isLinux() || OSDetector.isMac()) {
					  	path = getClass().getClassLoader().getResource(".").getPath();
					  	path = path.substring(0, path.length()-4);
					  	path = path + "data/Puzzles/" + puzzleName;
					}
			  	} catch (Exception e) {
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
		    	  
			      // dis.available() returns 0 if the file does not have more lines.
			      while (dis.available() != 0) {			    	  
					String s = dis.readLine();
			    	
					if(s.contains("/*")) {
						if(s.contains("correct")) {
							finished = true;
						}
						//System.out.println("Comment Line");	
					}			
					else if(s.equals("easy") || s.equals("medium") || s.equals("hard") || s.equals("expert")) {
			    		thisPuzzle.setDifficulty(s);
			    	}
			    	else if(s.length() == 5) {
			    		String tokens[] = s.split(",");

			    		if(tokens.length != 3) {
			    			break;
			    		}
			    					    		
			    		int row = Integer.parseInt(tokens[0]) -1;
			    		int column = Integer.parseInt(tokens[1]) -1;
			    		int value = Integer.parseInt(tokens[2]);
			    		thisPuzzle.setCell(row, column, value,finished,true); 
			    	}
			    	else {
			    		System.out.println("Line Unread, cannot understand format!");	
			    	}
			      }
	
			      // dispose all the resources after using them.
			      fis.close();
			      bis.close();
			      dis.close();
			      
			      if(thisPuzzle.checkReal() == true) {
			    	  if(thisPuzzle.getDifficulty().equals("easy")) {
			    		  EasyList.add(thisPuzzle);
			    	  }
			    	  else if(thisPuzzle.getDifficulty().equals("medium")) {
			    		  MediumList.add(thisPuzzle);
			    	  }
			    	  else if(thisPuzzle.getDifficulty().equals("hard")) {
			    		  HardList.add(thisPuzzle);
			    	  }
			    	  else if(thisPuzzle.getDifficulty().equals("expert")) {
			    		  ExpertList.add(thisPuzzle);
			    	  }
			      }
	
			    } catch (FileNotFoundException e) {
			      e.printStackTrace();
			    } catch (IOException e) {
			      e.printStackTrace();
			    }
			  }
		}
	  
	  
	  	//readScores reads all of the scores into the scoreList data structure
	  	public void readScores() {
	  		ScoreList.clear();
	  		//Rewrites Scorelist every time you call this function. We don't want repeats
			String path = null;
					
		  	try {
		  		if(OSDetector.isWindows()) {
		  			path = getClass().getClassLoader().getResource(".").getPath();
		  			path = path.substring(0, path.length()-4);
		  			userScoresParentDir = path + "data";
		  			path = path + "data\\\\topscores.txt";
		  		}
		  		else if(OSDetector.isLinux() || OSDetector.isMac()) {
				  	path = getClass().getClassLoader().getResource(".").getPath();
				  	path = path.substring(0, path.length()-4) ;
				  	userScoresParentDir = path + "data";
				  	path = path + "data/topscores.txt";
				}
		  	} catch (Exception e) {
		  		e.printStackTrace(System.err);
		  	}
	
		  	//Search the file for the user, if they don't exist then add them
		  	String currentLine = null;
		  	File file = new File(path);
		    FileInputStream fis = null;
		    BufferedInputStream bis = null;
		    DataInputStream dis = null;
		    if(file.exists() == false) {
		    	File newUserScoresFile = new File(userScoresParentDir, ("topscores.txt"));
		    	
		    	try {
					newUserScoresFile.createNewFile();
					PrintWriter writer = new PrintWriter(path);
					writer.println("High Scores:");
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }	  		
		    else try {
			      fis = new FileInputStream(file);
	
			      // Here BufferedInputStream is added for fast reading.
			      bis = new BufferedInputStream(fis);
			      dis = new DataInputStream(bis);
			      
			      while (dis.available() != 0) {    	  
						String s = dis.readLine();
						String tokens[] = s.split(",");
				
						if(tokens.length == 2) {		
							int v = Integer.parseInt(tokens[1]);
						
							UserScore thisScore = new UserScore(tokens[0], v);
							ScoreList.add(thisScore);
					    }
			      }
			    	
				    // dispose all the resources after using them.
				    fis.close();
				    bis.close();
				    dis.close();	      
		    }
	
			catch (FileNotFoundException e) {
				e.printStackTrace();
			} 
		    catch (IOException e) {
		    	e.printStackTrace();
		    }
		    
		    sortScores(ScoreList);
		}
	  
	   //writeScores writes the scores to topscores.txt in the data file
	   public void writeScores() {
			String path = null;
			sortScores(ScoreList);
		  	try {
		  		if(OSDetector.isWindows()) {
		  			path = getClass().getClassLoader().getResource(".").getPath();
		  			path = path.substring(0, path.length()-4);
		  			userScoresParentDir = path + "data";
		  			path = path + "data\\\\topscores.txt";
		  		}
		  		else if(OSDetector.isLinux() || OSDetector.isMac()) {
				  	path = getClass().getClassLoader().getResource(".").getPath();
				  	path = path.substring(0, path.length()-4) ;
				  	userScoresParentDir = path + "data";
				  	path = path + "data/topscores.txt";
				}
		  	} catch (Exception e) {
		  		e.printStackTrace(System.err);
		  	}
				
		  	//Search the file for the user, if they don't exist then add them
		  	String currentLine = null;
		  	File file = new File(path);
		    FileInputStream fis = null;
		    BufferedInputStream bis = null;
		    DataInputStream dis = null;
		    if(file.exists() == false) {
		    	File newUserScoresFile = new File(userScoresParentDir, ("topscores.txt"));
		    	
		    	try {
					newUserScoresFile.createNewFile();
					PrintWriter writer = new PrintWriter(path);
									
					for(int i = 0; i<ScoreList.size(); i++) {
						writer.println(ScoreList.get(i).getName() + "," + ScoreList.get(i).getValue());
					}
						
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }	  		
		    else try {
				PrintWriter writer = new PrintWriter(path);
				
				for(int i = 0; i<ScoreList.size(); i++) {
					writer.println(ScoreList.get(i).getName() + "," + ScoreList.get(i).getValue());
				}
					
				writer.close();	    	
		    }
	
			catch (FileNotFoundException e) {
				e.printStackTrace();
			} 
		    catch (IOException e) {
		    	e.printStackTrace();
		    }
		}	  	
  	
	   	//updateUserScores updates the users score in the topscores.txt file
		public void updateUserScores(int points) {	
			boolean found = false;
			for(int i = 0; i<ScoreList.size(); i++) {
				if(username.equals(ScoreList.get(i).getName())) {
						found = true;
						ScoreList.get(i).addPoints(points);		
				}
			}	
			if(found == false) {
				UserScore thisScore = new UserScore(username, points);	
				ScoreList.add(thisScore);
			}
			
//			printList();
			writeScores();		
		}
		
	//loadImages loads the background images to be used for the backgrounds in the game.
	public void loadImages() {
	  	String path = null;
	  	
	  	try {
	  		if(OSDetector.isWindows()) {
	  			path = getClass().getClassLoader().getResource(".").getPath();
	  			path = path.substring(0, path.length()-4);
	  			path = path + "data\\\\Graphics\\\\";
	  		}
	  		else if(OSDetector.isLinux() || OSDetector.isMac()) {
			  	path = getClass().getClassLoader().getResource(".").getPath();
			  	path = path.substring(0, path.length()-4);
			  	path = path + "data/Graphics/";
			}
	  	} catch (Exception e) {
	  		e.printStackTrace(System.err);
	  	}	
	
		try {
			titleBackground = ImageIO.read(new File(path + "blue.jpg"));
			menuBackground = ImageIO.read(new File(path + "blue.jpg"));
			createBackground = ImageIO.read(new File(path + "blue.jpg"));
			scoresBackground = ImageIO.read(new File(path + "darkgray.jpg"));
			gameBackground = ImageIO.read(new File(path + "blue.jpg"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	  	
	}
	
	//sortScores uses bubble sort to put the scores in the scoreList in descending order.
	public void sortScores(ArrayList<UserScore> scoreList) {
		for(int i = 0; i<scoreList.size(); i++) {
			int iPositionValue = scoreList.get(i).getValue();
			int indexOfLargestValueLeftOnList = i;
			
			for(int j = i; j<scoreList.size(); j++) {
				int jPositionValue = scoreList.get(j).getValue();
				if(iPositionValue < jPositionValue) {
					iPositionValue = jPositionValue;
					indexOfLargestValueLeftOnList = j;
				}
			}
			if(i == indexOfLargestValueLeftOnList){
				
			}
			else {
				UserScore tempIUser = new UserScore(scoreList.get(i).getName(), scoreList.get(i).getValue());
				scoreList.set(i, scoreList.get(indexOfLargestValueLeftOnList));
				scoreList.set(indexOfLargestValueLeftOnList, tempIUser);
			}
		}		
	}
	
//	printList is a print debug function
//	public void printList() {			
//		for(int i = 0; i<ScoreList.size(); i++) {
//			System.out.println("Printing: "+ScoreList.get(i).getName() + ": " + ScoreList.get(i).getValue());
//		}	
//	}
}