package Menu;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JPanel{

	public JLabel mainMenu;
	public JLabel startPuzzle;
	public JButton easyButton;
	public JButton mediumButton;
	public JButton hardButton;
	public JButton hardestButton;
	public JButton userScores = new JButton("User Scores");
	public JButton loadGame = new JButton("Load Game");
	
	public MainMenu()
	{
		super();
		
		setSize(640, 480);
		setLayout(null);
		setBackground(Color.LIGHT_GRAY);
		
		//Would be best if we set the JLable size to fit the font exactly...then the equation would 
		//work better for centering
		mainMenu = new JLabel("Main Menu");
		mainMenu.setFont(new Font("Arial", Font.BOLD, 24));
		mainMenu.setBounds(this.getWidth()/2-75, this.getHeight()/10, 150, 20);
		add(mainMenu);
		
		startPuzzle = new JLabel("Start a New Puzzle:");
		startPuzzle.setFont(new Font("Arial", Font.PLAIN, 24));
		startPuzzle.setBounds(this.getWidth()/2-125, this.getHeight()/5, 300, 20);
		add(startPuzzle);
		
		easyButton = new JButton("Easy");
		easyButton.setBackground(new Color(102,255,255));
		easyButton.setBounds(75,150,100,60);
		add(easyButton);
//		easyButton.addMouseListener(new MouseAdapter(){
//			@Override
//			public void mousePressed(MouseEvent e){
//				System.out.println("You pressed the Easy button");
//				buttonPressed = "Easy";
//			}
//		});
		
		
		mediumButton = new JButton("Medium");
//		mediumButton.setBackground(new Color());
		mediumButton.setBounds(200, 150, 100, 60);
		add(mediumButton);
//		mediumButton.addMouseListener(new MouseAdapter(){
//			@Override
//			public void mousePressed(MouseEvent e){
//				System.out.println("You pressed the Medium button");
//				buttonPressed = "Medium";
//			}
//		});
		
		hardButton = new JButton("Hard");
//		hardButton.setBackground(new Color());
		hardButton.setBounds(325, 150, 100, 60);
		add(hardButton);
//		hardButton.addMouseListener(new MouseAdapter(){
//			@Override
//			public void mousePressed(MouseEvent e){
//				System.out.println("You pressed the Hard button");
//				buttonPressed = "Hard";
//			}
//		});
		
		hardestButton = new JButton("Hardest");
//		hardestButton.setBackground(new Color());
		hardestButton.setBounds(450, 150, 100, 60);
		add(hardestButton);
//		hardestButton.addMouseListener(new MouseAdapter(){
//			@Override
//			public void mousePressed(MouseEvent e){
//				System.out.println("You pressed the Hardest button");
//				buttonPressed = "Hardest";
//			}
//		});
		
		userScores = new JButton("User Scores");
//		userScores.setBackground(new Color());
		userScores.setBounds(this.getWidth()/3-75, 400, 150, 40);
		add(userScores);
//		userScores.addMouseListener(new MouseAdapter(){
//			@Override
//			public void mousePressed(MouseEvent e){
//				System.out.println("You pressed the User Scores button");
//				buttonPressed = "UserScores";
//			}
//		});
		
		loadGame = new JButton("Load Game");
//		loadGame.setBackground(new Color());
		loadGame.setBounds(this.getWidth()/3*2 - 75, 400, 150, 40);
		add(loadGame);
//		loadGame.addMouseListener(new MouseAdapter(){
//			@Override
//			public void mousePressed(MouseEvent e){
//				System.out.println("You pressed the Load Game button");
//				buttonPressed = "LoadGame";
//			}
//		});
		
		
				
	}
}
