package Menu;

import javax.swing.*;

import java.awt.*;

public class UserScores extends JPanel{
	public JLabel message;
	public JButton backButton;
	public JTable userScores;
	
	public UserScores()
	{
		super();
		
		setSize(640, 480);
		setLayout(null);
		setBackground(Color.black);
		
		message = new JLabel("HIGH SCORES");
		message.setFont(new Font("Arial", Font.BOLD, 24));
		message.setForeground(Color.yellow);
		message.setBounds(this.getWidth()/2-100, this.getHeight()/10, 200, 20);
		add(message);
		
		backButton = new JButton("<== Main Menu");
		backButton.setBackground(Color.LIGHT_GRAY);
		backButton.setBounds(this.getWidth()/20,this.getHeight()/20,150,50);
		add(backButton);
		
		String[] columnNames = {"User", "Score"};
		String[][] topScores = getTopScores();
		userScores = new JTable(topScores, columnNames);
		userScores.setBounds(this.getWidth()/2-125, 100, 400, 200);
		userScores.setRowHeight(20);
		userScores.setBackground(Color.black);
		userScores.setFont(new Font("Arial", Font.BOLD, 15));
		userScores.setForeground(Color.white);
		userScores.setShowGrid(false);
		add(userScores);		
	}
	
	public String[][] getTopScores()
	{
		String [] firstScore = {"Steven McNutts", "20"};
		String [] secondScore = {"Slippery McBalls", "15"};
		String[][] returnData = {firstScore, secondScore};
		
		return returnData;
	}
	
}
