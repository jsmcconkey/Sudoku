package loginscreen;

import javax.swing.*;

import java.awt.*;

public class CreateUser extends JPanel{
	public JLabel message;
	public JLabel usernameLabel;
	public JLabel passwordLabel;
	public JLabel confirmPasswordLabel;
	public JTextField newUsername;
	public JPasswordField newPassword;
	public JPasswordField confirmNewPassword;
	public JButton createUser;
	
	public CreateUser()
	{
		super();
		
		setSize(640, 480);
		setLayout(null);
		setBackground(Color.LIGHT_GRAY);
		
		message = new JLabel("Please enter a new username and password:");
		message.setFont(new Font("Arial", Font.BOLD, 24));
		message.setBounds(this.getWidth()/15, this.getHeight()/10, 600, 20);
		add(message);
		
		usernameLabel = new JLabel("New Username:");
		usernameLabel.setFont(new Font("Arial", Font.BOLD, 15));
		usernameLabel.setBounds(this.getWidth()/2-200, this.getHeight()/3, 150, 20);
		add(usernameLabel);
		
		passwordLabel = new JLabel("New Password:");
		passwordLabel.setFont(new Font("Arial", Font.BOLD, 15));
		passwordLabel.setBounds(this.getWidth()/2-200, this.getHeight()/3+30, 150, 20);
		add(passwordLabel);
		
		passwordLabel = new JLabel("Confirm Password:");
		passwordLabel.setFont(new Font("Arial", Font.BOLD, 15));
		passwordLabel.setBounds(this.getWidth()/2-200, this.getHeight()/3+60, 150, 20);
		add(passwordLabel);
		
		newUsername = new JTextField(15);
		newUsername.setEditable(true);
		newUsername.setBounds(this.getWidth()/2, this.getHeight()/3, 100, 20);
		add(newUsername);
		
		newPassword = new JPasswordField(15);
		newPassword.setEditable(true);
		newPassword.setBounds(this.getWidth()/2, this.getHeight()/3+30, 100, 20);
		add(newPassword);
		
		confirmNewPassword = new JPasswordField(15);
		confirmNewPassword.setEditable(true);
		confirmNewPassword.setBounds(this.getWidth()/2, this.getHeight()/3+60, 100, 20);
		add(confirmNewPassword);
		
		createUser = new JButton("Create User");
		createUser.setBackground(new Color(102,255,255));
		createUser.setBounds(this.getWidth()-200,158,125,85);
		add(createUser);
	}
	
	
}
