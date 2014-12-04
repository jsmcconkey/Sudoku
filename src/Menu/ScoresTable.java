/*
**Programmer <James McConkey and Ethan Smith>
*/

package Menu;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.ArrayList;

public class ScoresTable extends JPanel {
	public JLabel message;
	public JButton backButton;
	static String[] columnNames = {"User", "Score"};
	static String[][] topScores = null;
	public JTable scoreTable;
	public Image background;
	
	//Constructor
	public ScoresTable(ArrayList<UserScore> scoreList,Image b) {
		super();
		
		background = b;
		
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
		
		scoreTable = new JTable(topScores, columnNames);
		scoreTable.setBounds(640/2-125, 100, 400, 200);
		scoreTable.setRowHeight(20);
		scoreTable.setOpaque(false);
		((DefaultTableCellRenderer)scoreTable.getDefaultRenderer(Object.class)).setOpaque(false);
		scoreTable.setFont(new Font("Arial", Font.BOLD, 15));
		scoreTable.setForeground(Color.white);
		scoreTable.setShowGrid(false);
		scoreTable.setVisible(true);
		add(scoreTable);
	}
	
	//Methods
	public void setScores(ArrayList<UserScore> scoreList) {		
		for(int i = 0; i<10; i++) {
			scoreTable.setValueAt(scoreList.get(i).getName(), i, 0);
			scoreTable.setValueAt(String.valueOf(scoreList.get(i).getValue()), i, 1);
		}
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(background, 0, 0, null);
	}
}