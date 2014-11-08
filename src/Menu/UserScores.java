package Menu;

import javax.swing.*;

import java.awt.*;

public class UserScores extends JPanel{
	public JLabel message;
	
	public UserScores()
	{
		super();
		
		setSize(640, 480);
		setLayout(null);
		setBackground(Color.LIGHT_GRAY);
		
		message = new JLabel("HIGH SCORES");
		message.setFont(new Font("Arial", Font.BOLD, 24));
		message.setBounds(this.getWidth()/2-75, this.getHeight()/10, 200, 20);
		add(message);
	}
	
}