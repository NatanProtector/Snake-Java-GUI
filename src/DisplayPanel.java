import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DisplayPanel extends JPanel implements ActionListener{
	private JButton back;
	private JLabel applesEaten;
	private JLabel loseLabel;
	int numOfApplesEaten;
	Snake frame;
	private ImageIcon apple = new ImageIcon("src\\Graphics\\apple.png");
	private ImageIcon firstTrophy = new ImageIcon("src\\Graphics\\gold-cup.png");
	private ImageIcon secondTrophy = new ImageIcon("src\\Graphics\\silver-cup.png");
	private ImageIcon thirdTrophy = new ImageIcon("src\\Graphics\\bronze-cup.png");
	private boolean highScoreAchived = false;
	private int[] scoreList;
	private JLabel scoreToBeat;
	AudioPlayer audioPlayer;
	public DisplayPanel(Snake frame) {
		try {
			this.audioPlayer = new AudioPlayer();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
		this.frame = frame;
		back = new JButton("Back");
		back.setPreferredSize(new Dimension(90,50));
		back.setFocusable(false);
		back.addActionListener(this);
		back.setBackground(new Color(150, 105, 25));
		back.setFont(new Font("MV Boli", Font.BOLD,30));
		back.setForeground(Color.BLACK);
		back.setBorder(BorderFactory.createLineBorder(new Color(140, 85, 5),5));
		
		numOfApplesEaten = 0;
		applesEaten = new JLabel("eaten: 0");
		applesEaten.setFont(new Font("MV Boli", Font.BOLD , 25));
		applesEaten.setHorizontalTextPosition(JLabel.RIGHT);
		applesEaten.setIcon(apple);
		applesEaten.setOpaque(true);;
		applesEaten.setBackground(new Color(170, 125, 45));
		
		loseLabel = new JLabel("");
		loseLabel.setForeground(Color.RED);
		loseLabel.setFont(new Font("MV Boli",Font.BOLD,25));
		
		scoreList = frame.leaderBoard.getScoresToPass();
		scoreToBeat = new JLabel(scoreList[0]+"");
		scoreToBeat.setFont(new Font("MV Boli",Font.BOLD,25));
		scoreToBeat.setIcon(thirdTrophy);
		
		this.setLayout(new FlowLayout(FlowLayout.TRAILING ,50,0));
		this.setBackground(new Color(170, 125, 45));
		this.setPreferredSize(new Dimension(600,50));
		this.add(applesEaten);
		this.add(scoreToBeat);
		this.add(loseLabel);
		this.add(back);
	}
	
	public int getApplesEaten() {
		return this.numOfApplesEaten;
	}
	
	public void addAppleToCount() {
		this.numOfApplesEaten++;
		applesEaten.setText("eaten: " + this.numOfApplesEaten);
	}
	public void defeat() {
		this.remove(this.applesEaten);
		this.remove(this.scoreToBeat);
		displayLoseOrHighScore();
		this.repaint();
	}
	private void displayLoseOrHighScore() {
		if (this.highScoreAchived) {
			this.loseLabel.setForeground(Color.yellow);
			this.loseLabel.setText("NEW HIGH SCORE!");
		} else
			this.loseLabel.setText("YOU  HAVE  LOST!");
	}
	public void updateHighScore() {
		if (this.numOfApplesEaten == this.scoreList[0]) {
			this.highScoreAchived = true;
			this.scoreToBeat.setIcon(this.secondTrophy);
			this.scoreToBeat.setText(this.scoreList[1]+"");
		}
		else if (this.numOfApplesEaten == this.scoreList[1]) {
			this.scoreToBeat.setIcon(this.firstTrophy);
			this.scoreToBeat.setText(this.scoreList[2]+"");
		}
		else if (this.numOfApplesEaten >= this.scoreList[2]) {
			this.scoreToBeat.setText(this.numOfApplesEaten +"");
		}
			
	}
	public void back() {
		this.audioPlayer.playEffect(1);
		int previousSpeed = frame.getSpeed();
		this.frame.tick.cancel();
		this.frame.dispose();
		new mainFrame(previousSpeed);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == back)
			back();
	}
}
