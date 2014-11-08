package loginscreen;

import javax.swing.*;

import java.awt.*;

public class CreateUser extends JPanel{
	public JLabel message;
	protected String userName;
	protected String password;
	
	public CreateUser()
	{
		super();
		
		message = new JLabel("Please enter a username and password");
		message.setFont(new Font("Arial", Font.BOLD, 24));
		message.setBounds(this.getWidth()/2-75, this.getHeight()/10, 300, 20);
		add(message);
		
	}
	
	
}
