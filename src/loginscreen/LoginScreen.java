package loginscreen;

import javax.swing.*;
import java.awt.*;

public class LoginScreen extends JPanel{

	public JLabel introMessage;
	public JLabel username;
	public JLabel password;
	public JTextField enterUsername;
	public JPasswordField enterPassword;
	public JButton newUser;
	public JButton login;
	
	public LoginScreen()
	{
		super();
		
		setSize(640, 480);
		setLayout(null);
		setBackground(Color.LIGHT_GRAY);
		
		introMessage = new JLabel("Let's Play Sudoku!");
		introMessage.setFont(new Font("Arial", Font.BOLD, 24));
		introMessage.setBounds(this.getWidth()/2-100, this.getHeight()/10, 300, 25);
		add(introMessage);
		
		username = new JLabel("Username:");
		username.setFont(new Font("Arial", Font.BOLD, 15));
		username.setBounds(this.getWidth()/2-100, this.getHeight()/3, 100, 20);
		add(username);
		
		password = new JLabel("Password:");
		password.setFont(new Font("Arial", Font.BOLD, 15));
		password.setBounds(this.getWidth()/2-100, this.getHeight()/3+30, 100, 20);
		add(password);
		
		enterUsername = new JTextField(15);
		enterUsername.setEditable(true);
		enterUsername.setBounds(this.getWidth()/2, this.getHeight()/3, 100, 20);
		add(enterUsername);
		
		enterPassword = new JPasswordField(15);
		enterPassword.setEditable(true);
		enterPassword.setBounds(this.getWidth()/2, this.getHeight()/3+30, 100, 20);
		add(enterPassword);
		
		login = new JButton("Login");
		login.setBackground(new Color(102,255,255));
		login.setBounds(this.getWidth()/2+password.getWidth()+10,this.getHeight()/3,100,60);
		add(login);
		
		newUser = new JButton("Create Login");
		newUser.setBackground(new Color(102,255,255));
		newUser.setBounds(this.getWidth()/2,this.getHeight()-150,150,60);
		add(newUser);
	}
}
