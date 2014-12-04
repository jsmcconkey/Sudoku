/*
**Programmer <James McConkey and Ethan Smith>
*/

package loginscreen;

import javax.swing.*;

import java.awt.*;

public class CreateUser extends JPanel {
	public JLabel message;
	public JLabel usernameLabel;
	public JLabel passwordLabel;
	public JLabel confirmPasswordLabel;
	public JTextField newUsername;
	public JPasswordField newPassword;
	public JPasswordField confirmNewPassword;
	public JButton createUser;
	public JButton backButton;
	public Image background;
	
	//Constructor
	public CreateUser(Image b) {
		super();
		
		background = b;
		
		setSize(640, 480);
		setLayout(null);
		setBackground(new Color(224,224,224));
		
		message = new JLabel("Please enter a new username and password:");
		message.setFont(new Font("Arial", Font.BOLD, 22));
		message.setBounds(this.getWidth()/15, this.getHeight()/10, 600, 125);
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
		passwordLabel.setBounds(this.getWidth()/2-200, this.getHeight()/3+60, 160, 20);
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
		
		backButton = new JButton("<== Login Screen");
		backButton.setBackground(new Color(102,255,255));
		backButton.setBounds(this.getWidth()/20,this.getHeight()/20,175,50);
		add(backButton);
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(background, 0, 0, null);
	}
}
