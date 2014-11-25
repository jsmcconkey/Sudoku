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
		setBackground(new Color(224,224,224));
		
		message = new JLabel("HIGH SCORES");
		message.setFont(new Font("Arial", Font.BOLD, 24));
		message.setBounds(this.getWidth()/2-75, this.getHeight()/10, 200, 20);
		add(message);
		
		backButton = new JButton("<== Main Menu");
		backButton.setBackground(new Color(102,255,255));
		backButton.setBounds(this.getWidth()/20,this.getHeight()/20,150,50);
		add(backButton);
		
		String[] columnNames = {"User", "Score"};
		String[][] topScores = getTopScores();
		userScores = new JTable(topScores, columnNames);
		userScores.setPreferredScrollableViewportSize(new Dimension(200,200));
		userScores.setBounds(this.getWidth()/2-200, 50, 400, 200);
		userScores.setRowHeight(20);
		JScrollPane scrollPane = new JScrollPane(userScores);
		userScores.setBounds(this.getWidth()/2-200, 50, 400, 200);
		userScores.setFillsViewportHeight(true);
		userScores.setVisible(true);
		scrollPane.setVisible(true);

		add(scrollPane);
		
	}
	
	public String[][] getTopScores()
	{
		String [] firstScore = {"Steven McNutts", "20"};
		String [] secondScore = {"Slippery McBalls", "15"};
		String[][] returnData = {firstScore, secondScore};
		
		return returnData;
	}
	
}
