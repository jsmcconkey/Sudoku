package Menu;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;

import loginscreen.User;

public class UserScores extends JPanel{
	public JLabel message;
	public JButton backButton;
	static String[] columnNames = {"User", "Score"};
	static String[][] topScores = null;
	public JTable userScores;
	
	public UserScores(ArrayList<UserScore> scoreList)
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
	
		initializeScores(scoreList);
	}

	public void initializeScores(ArrayList<UserScore> scoreList) {		
		topScores = null;
		userScores = null;

		for(int i = 0; i<scoreList.size(); i++)
		{
			int iPositionValue = scoreList.get(i).getValue();
			int indexOfLargestValueLeftOnList = i;
			
			for(int j = i; j<scoreList.size(); j++)
			{
				int jPositionValue = scoreList.get(j).getValue();
				if(iPositionValue < jPositionValue)
				{
					iPositionValue = jPositionValue;
					indexOfLargestValueLeftOnList = j;
				}
			}
			if(i == indexOfLargestValueLeftOnList){
				
			}
			else{
				UserScore tempIUser = new UserScore(scoreList.get(i).getName(), scoreList.get(i).getValue());
				scoreList.set(i, scoreList.get(indexOfLargestValueLeftOnList));
				scoreList.set(indexOfLargestValueLeftOnList, tempIUser);
			}

		}

		
		String [] firstScore = {scoreList.get(0).getName(), String.valueOf(scoreList.get(0).getValue())};
		String [] secondScore = {scoreList.get(1).getName(), String.valueOf(scoreList.get(1).getValue())};
		String [] thirdScore = {scoreList.get(2).getName(), String.valueOf(scoreList.get(2).getValue())};
		String [] fourthScore = {scoreList.get(3).getName(), String.valueOf(scoreList.get(3).getValue())};
		String [] fifthScore = {scoreList.get(4).getName(), String.valueOf(scoreList.get(4).getValue())};
		String [] sixthScore = {scoreList.get(5).getName(), String.valueOf(scoreList.get(5).getValue())};
		String [] seventhScore = {scoreList.get(6).getName(), String.valueOf(scoreList.get(6).getValue())};
		String [] eigthScore = {scoreList.get(7).getName(), String.valueOf(scoreList.get(7).getValue())};
		String [] ninthScore = {scoreList.get(8).getName(), String.valueOf(scoreList.get(8).getValue())};
		String [] tenthScore = {scoreList.get(9).getName(), String.valueOf(scoreList.get(9).getValue())};

		String[][] returnData = {firstScore, secondScore, thirdScore,
				fourthScore, fifthScore, sixthScore, seventhScore, 
				eigthScore, ninthScore, tenthScore};
		
		topScores = returnData;
		
		userScores = new JTable(topScores, columnNames);
		userScores.setBounds(640/2-125, 100, 400, 200);
		userScores.setRowHeight(20);
		userScores.setBackground(Color.black);
		userScores.setFont(new Font("Arial", Font.BOLD, 15));
		userScores.setForeground(Color.white);
		userScores.setShowGrid(false);
		userScores.setVisible(true);
		add(userScores);
	}
	
	public void setScores(ArrayList<UserScore> scoreList) {		
		topScores = null;
		userScores = null;

		for(int i = 0; i<scoreList.size(); i++)
		{
			int iPositionValue = scoreList.get(i).getValue();
			int indexOfLargestValueLeftOnList = i;
			
			for(int j = i; j<scoreList.size(); j++)
			{
				int jPositionValue = scoreList.get(j).getValue();
				if(iPositionValue < jPositionValue)
				{
					iPositionValue = jPositionValue;
					indexOfLargestValueLeftOnList = j;
				}
			}
			if(i == indexOfLargestValueLeftOnList){
				
			}
			else{
				UserScore tempIUser = new UserScore(scoreList.get(i).getName(), scoreList.get(i).getValue());
				scoreList.set(i, scoreList.get(indexOfLargestValueLeftOnList));
				scoreList.set(indexOfLargestValueLeftOnList, tempIUser);
			}

		}
	
		for(int i = 0; i<10; i++)
		{
			userScores.setValueAt(scoreList.get(i).getName(), i, 0);
			userScores.setValueAt(String.valueOf(scoreList.get(i).getValue()), i, 1);
		}
	}
	
}
