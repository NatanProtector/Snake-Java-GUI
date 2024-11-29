import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class LeaderBoard extends JPanel implements ActionListener{
	JButton back;
	BufferedWriter writer;
	BufferedReader reader;
	mainFrame frame;
	
	String firstPlace;
	String firstApples;
	String secondPlace;
	String secondApples;
	String thirdPlace;
	String thirdApples;
	
	JLabel firstLabel;
	JLabel secondLabel;
	JLabel thirdLabel;
	
	ImageIcon firstTropy;
	ImageIcon secondTrophy;
	ImageIcon thirdTrophy;

	public LeaderBoard(mainFrame frame) {

		this.frame = frame;

		firstTropy = new ImageIcon("src\\Graphics\\gold-cup.png");
		secondTrophy = new ImageIcon("src\\Graphics\\silver-cup.png");
		thirdTrophy = new ImageIcon("src\\Graphics\\bronze-cup.png");
		
		read();
		
		this.firstLabel = new JLabel();
		this.firstLabel.setFont(new Font("MV Boli", Font.BOLD,30));
		this.firstLabel.setIcon(firstTropy);
		this.secondLabel = new JLabel();
		this.secondLabel.setFont(new Font("MV Boli", Font.BOLD,30));
		this.secondLabel.setIcon(secondTrophy);
		this.thirdLabel = new JLabel();
		this.thirdLabel.setFont(new Font("MV Boli", Font.BOLD,30));
		this.thirdLabel.setIcon(thirdTrophy);
		
		updateLabels();
		
		this.back = new JButton("Back");
		this.back.setPreferredSize(new Dimension(130,55));
		this.back.setFocusable(false);
		this.back.addActionListener(this);
		this.back.setBackground(new Color(150, 105, 25));
		this.back.setFont(new Font("MV Boli", Font.BOLD,30));
		this.back.setForeground(Color.BLACK);
		this.back.setBorder(BorderFactory.createLineBorder(new Color(140, 85, 5),5));
		
		this.setBorder(BorderFactory.createLineBorder(new Color(150, 105, 25),5));
		this.setBackground(new Color(170, 125, 45));
		this.setBounds(120, 155, 250, 310);
		this.setLayout(new FlowLayout(FlowLayout.CENTER,0,20));
		this.add(firstLabel);
		this.add(secondLabel);
		this.add(thirdLabel);
		this.add(back);
		
	}
	private int getScore (int i) {
		switch (i) {
		case 1: return Integer.parseInt(this.firstApples);
		case 2: return Integer.parseInt(this.secondApples);
		case 3: return Integer.parseInt(this.thirdApples);
		}
		return -1;
	}
	private void updateLabels() {
		this.firstLabel.setText(this.firstPlace + " " + this.firstApples);
		this.secondLabel.setText(this.secondPlace + " " + this.secondApples);
		this.thirdLabel.setText(this.thirdPlace + " " + this.thirdApples);
	}
	private void write() {
		try {
			writer = new BufferedWriter(new FileWriter("LeaderBoard.txt"));
			writer.write(this.firstPlace + "\n" +
						this.firstApples + "\n" +
						this.secondPlace + "\n" +
						this.secondApples + "\n" +
						this.thirdPlace + "\n" +
						this.thirdApples);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void read() {
		try {
			reader = new BufferedReader(new FileReader("LeaderBoard.txt"));
			this.firstPlace = reader.readLine();
			this.firstApples = reader.readLine();
			this.secondPlace = reader.readLine();
			this.secondApples = reader.readLine();
			this.thirdPlace = reader.readLine();
			this.thirdApples = reader.readLine();
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void checkHighScore(int numOfApplesEaten) {
		if (numOfApplesEaten>Integer.parseInt(this.firstApples)) {
			frame.audioPlayer.playEffect(9);
			this.thirdPlace = this.secondPlace;
			this.thirdApples = this.secondApples;
			this.secondPlace = this.firstPlace;
			this.secondApples = this.firstApples;
			this.firstPlace = getName(0);
			this.firstApples = numOfApplesEaten+"";
		}
		else if (numOfApplesEaten>Integer.parseInt(this.secondApples) &&
				numOfApplesEaten < getScore(1)) {
			frame.audioPlayer.playEffect(9);
			this.thirdPlace = this.secondPlace;
			this.thirdApples = this.secondApples;
			this.secondPlace = getName(1);
			this.secondApples = numOfApplesEaten+"";
		}
		else if (numOfApplesEaten>getScore(3) &&
				numOfApplesEaten < Integer.parseInt(this.secondApples)) {
			frame.audioPlayer.playEffect(9);
			this.thirdPlace = getName(2);
			this.thirdApples = numOfApplesEaten+"";
		}
		this.updateLabels();
		this.write();
	}
	private String getName(int place) {
		boolean legalName = false;
		String name = "";
		while (!legalName) {
			name = JOptionPane.showInputDialog("New High Score!\nWhat is your name? ");
			legalName = legalName(name);	
		}
		while (name.length() != 5) {
			name += " ";
		}
		return name;
	}
	private boolean legalName(String name) {
		if (name == null || name.length()>5 || name.length() == 0) {
			frame.audioPlayer.playEffect(8);
			JOptionPane.showMessageDialog(null, "Invalid Name\nname must be 5 characters long and greater then 0", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		for (int i = 0; i<name.length() ; i++) {
			if (name.charAt(i) == '\\' || Character.isDigit(name.charAt(i))) {
				frame.audioPlayer.playEffect(8);
				JOptionPane.showMessageDialog(null, "Invalid Name\nname cannot contain \\", "Error", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		return true;
	}
	public int[] getScoresToPass() {
		int[] list = {
				Integer.parseInt(this.thirdApples)+1,
				Integer.parseInt(this.secondApples)+1,
				Integer.parseInt(this.firstApples)+1,
		};
		return list;
	}
	public void playEffect(int i) {
		 frame.audioPlayer.playEffect(i);
	 }
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == back) {
			this.frame.returnToMain();
		}
	}
}
