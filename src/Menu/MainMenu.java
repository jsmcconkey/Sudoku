package Menu;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JPanel{

	public JLabel mainMenu;
	public JLabel startPuzzle;
	public JButton easyButton;
	public JButton mediumButton;
	public JButton hardButton;
	public JButton expertButton;
	public JButton userScores;
	public JButton loadGame;
	public Color customColor = new Color(102, 178, 255);
	public JButton logout;
	
	public MainMenu()
	{
		super();
		
		setSize(640, 480);
		setLayout(null);
		setBackground(new Color(224,224,224));
		
		//Would be best if we set the JLable size to fit the font exactly...then the equation would 
		//work better for centering
		mainMenu = new JLabel("Main Menu");
		mainMenu.setFont(new Font("Arial", Font.BOLD, 24));
//		mainMenu.setForeground(new Color(102,51,0));
		mainMenu.setBounds(this.getWidth()/2-75, this.getHeight()/10, 300, 20);
		add(mainMenu);
		
		startPuzzle = new JLabel("Start a New Puzzle:");
		startPuzzle.setFont(new Font("Arial", Font.PLAIN, 24));
		startPuzzle.setBounds(this.getWidth()/2-125, this.getHeight()/5, 300, 20);
		add(startPuzzle);
		
		easyButton = new JButton("Easy");
		easyButton.setBackground(new Color(153,255,255));
		easyButton.setBounds(75,150,100,60);
		add(easyButton);
		
		mediumButton = new JButton("Medium");
		mediumButton.setBackground(new Color(102,255,255));
		mediumButton.setBounds(200, 150, 100, 60);
		add(mediumButton);

		hardButton = new JButton("Hard");
		hardButton.setBackground(new Color(0,255,255));
		hardButton.setBounds(325, 150, 100, 60);
		add(hardButton);
	
		expertButton = new JButton("Expert");
		expertButton.setBackground(new Color(0,204,204));
//		expertButton.setForeground(Color.white);
		expertButton.setBounds(450, 150, 100, 60);
		add(expertButton);
		
		userScores = new JButton("User Scores");
		userScores.setBackground(customColor);
		userScores.setBounds(this.getWidth()/3-75, 400, 150, 40);
		add(userScores);
		
		loadGame = new JButton("Load Last Save");
		loadGame.setBackground(customColor);
		loadGame.setBounds(this.getWidth()/3*2 - 75, 400, 150, 40);
		add(loadGame);
				
		logout = new JButton("Logout");
		logout.setBackground(customColor);
		logout.setBounds(this.getWidth()-110, 10, 100, 40);
		add(logout);
	}
}
