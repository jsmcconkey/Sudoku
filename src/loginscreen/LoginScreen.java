package loginscreen;

import javax.swing.*;
import java.awt.*;

public class LoginScreen extends JPanel{

	public JLabel introMessage;
	public JLabel username;
	public JLabel password;
	public JLabel notAUser;
	public JTextField enterUsername;
	public JPasswordField enterPassword;
	public JButton createLogin;
	public JButton login;
	
	public LoginScreen()
	{
		super();
		
		setSize(640, 480);
		setLayout(null);
		setBackground(new Color(224,224,224));
		
		introMessage = new JLabel("Let's Play Sudoku!");
		introMessage.setFont(new Font("Arial", Font.BOLD, 24));
		introMessage.setBounds(this.getWidth()/2-250/2, this.getHeight()/10, 250, 25);
		add(introMessage);
		
		username = new JLabel("Username:");
		username.setFont(new Font("Arial", Font.BOLD, 18));
		username.setBounds(this.getWidth()/2-150, this.getHeight()/3, 150, 30);
		add(username);
		
		password = new JLabel("Password:");
		password.setFont(new Font("Arial", Font.BOLD, 18));
		password.setBounds(this.getWidth()/2-150, this.getHeight()/3+40, 150, 30);
		add(password);
		
		enterUsername = new JTextField(15);
		enterUsername.setEditable(true);
		enterUsername.setBounds(this.getWidth()/2-25, this.getHeight()/3, 150, 30);
		add(enterUsername);
		
		enterPassword = new JPasswordField(15);
		enterPassword.setEditable(true);
		enterPassword.setBounds(this.getWidth()/2-25, this.getHeight()/3+40, 150, 30);
		add(enterPassword);
		
		login = new JButton("Login");
		login.setBackground(new Color(102,255,255));
		login.setBounds(this.getWidth()/2+password.getWidth()+10, enterUsername.getY(), 125, enterPassword.getY()+enterPassword.getHeight()-enterUsername.getY());
		add(login);
		
		notAUser = new JLabel("Need an account?");
		notAUser.setFont(new Font("Arial", Font.ITALIC, 15));
		notAUser.setBounds(enterPassword.getX(), enterPassword.getY()+175, 150, 20);
		add(notAUser);
		
		createLogin = new JButton("Create Login");
		createLogin.setBackground(new Color(102,255,255));
		createLogin.setBounds(enterPassword.getX(),enterPassword.getY()+200,enterPassword.getWidth(),60);
		add(createLogin);
	}
}
