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
	public JButton userScores = new JButton("User Scores");
	public JButton loadGame = new JButton("Load Game");
	public Color customColor = new Color(153, 76, 0);
	
	public MainMenu()
	{
		super();
		
		setSize(640, 480);
		setLayout(null);
		setBackground(new Color(153,76,0));
		
		//Would be best if we set the JLable size to fit the font exactly...then the equation would 
		//work better for centering
		mainMenu = new JLabel("Welcome to Poodoku!");
		mainMenu.setFont(new Font("Arial", Font.BOLD, 24));
		mainMenu.setForeground(new Color(102,51,0));
		mainMenu.setBounds(this.getWidth()/2-75, this.getHeight()/10, 300, 20);
		add(mainMenu);
		
		startPuzzle = new JLabel("Start a New Puzzle:");
		startPuzzle.setFont(new Font("Arial", Font.PLAIN, 24));
		startPuzzle.setBounds(this.getWidth()/2-125, this.getHeight()/5, 300, 20);
		add(startPuzzle);
		
		easyButton = new JButton("Easy");
		easyButton.setBackground(new Color(204,102,0));
		easyButton.setBounds(75,150,100,60);
		add(easyButton);
		
		mediumButton = new JButton("Medium");
		mediumButton.setBackground(new Color(102,51,0));
		mediumButton.setBounds(200, 150, 100, 60);
		add(mediumButton);

		hardButton = new JButton("Hard");
		hardButton.setBackground(new Color(75,40,0));
		hardButton.setBounds(325, 150, 100, 60);
		add(hardButton);
	
		expertButton = new JButton("Expert");
		expertButton.setBackground(new Color(60,30,0));
		expertButton.setForeground(Color.white);
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
				
	}
}
