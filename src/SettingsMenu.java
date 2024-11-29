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
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class SettingsMenu extends JPanel implements ActionListener {
	int selection;
	JLabel label;
	JButton back;
	JRadioButton volume;
	ButtonGroup difficulty;
	JRadioButton easy,normal, hard;
	mainFrame frame;
	ImageIcon selected,notSelected,yesVolume, noVolume;
	AudioPlayer audioPlayer;
	final Color backGroundColor = new Color(170, 125, 45);
	public SettingsMenu(mainFrame frame , int speed) {
		
		try {audioPlayer = new AudioPlayer();}
		catch (LineUnavailableException e) {e.printStackTrace();}
		catch (IOException e) {e.printStackTrace();}
		catch (UnsupportedAudioFileException e) {e.printStackTrace();}
		
		notSelected = new ImageIcon(".\\src\\Graphics\\close.png");
		selected = new ImageIcon(".\\src\\Graphics\\check.png");
		yesVolume = new ImageIcon(".\\src\\Graphics\\volume.png");
		noVolume = new ImageIcon(".\\src\\Graphics\\mute.png");
		label = new JLabel("Select Difficulty");
		label.setFont(new Font("MV Boli",Font.BOLD,25));
		
		this.selection = speed;
		
		easy = new JRadioButton("Easy");
		easy.setPreferredSize(new Dimension(165,50));
		easy.setFont(new Font("MV Boli",Font.BOLD, 25));
		easy.setIconTextGap(20);
		easy.setIcon(notSelected);
		easy.setSelectedIcon(selected);
		easy.setBackground(backGroundColor);
		easy.addActionListener(this);
		easy.setFocusable(false);
		
		normal = new JRadioButton("Normal");
		normal.setPreferredSize(new Dimension(165,50));
		normal.setFont(new Font("MV Boli",Font.BOLD, 25));
		normal.setIconTextGap(20);
		normal.setIcon(notSelected);
		normal.setSelectedIcon(selected);
		normal.setBackground(backGroundColor);
		normal.addActionListener(this);
		normal.setFocusable(false);
		
		hard = new JRadioButton("Hard");
		hard.setFont(new Font("MV Boli",Font.BOLD, 25));
		hard.setIconTextGap(20);
		hard.setPreferredSize(new Dimension(165,50));;
		hard.setIcon(notSelected);
		hard.setSelectedIcon(selected);
		hard.setBackground(backGroundColor);
		hard.addActionListener(this);
		hard.setFocusable(false);

		volume = new JRadioButton();
		volume.setPreferredSize(new Dimension(80,55));
		volume.setIcon(noVolume);
		volume.setSelectedIcon(yesVolume);
		volume.setBackground(backGroundColor);
		volume.setSelected(audioPlayer.getVolumeOnOrOff());
		volume.addActionListener(this);
		volume.setFocusable(false);
		
		setSelection(speed);
		
		difficulty = new ButtonGroup();
		difficulty.add(easy);
		difficulty.add(normal);
		difficulty.add(hard);
		
		this.frame = frame;
		
		back = new JButton("Back");
		back.setPreferredSize(new Dimension(90,55));
		back.setFocusable(false);
		back.addActionListener(this);
		back.setBackground(new Color(150, 105, 25));
		back.setFont(new Font("MV Boli", Font.BOLD,30));
		back.setForeground(Color.BLACK);
		back.setBorder(BorderFactory.createLineBorder(new Color(140, 85, 5),5));

		this.setBorder(BorderFactory.createLineBorder(new Color(150, 105, 25),5));
		this.setBackground(new Color(170, 125, 45));
		this.setBounds(120, 155, 250, 310);
		this.setLayout(new FlowLayout());
		this.add(label);
		this.add(easy);
		this.add(normal);
		this.add(hard);
		this.add(volume);
		this.add(back);
	}
	private void setSelection(int speed) {
		switch(speed) {
		case 2:
			easy.setSelected(true);
			break;
		case 1:
			normal.setSelected(true);
			break;
		case 0:
			hard.setSelected(true);
		}
	}
	public int getDifficulty() {
		return this.selection;
	}
	public void playEffect(int i) {
		this.audioPlayer.playEffect(i);
	}
	public void moveUp() {
		if (this.selection == 0) {
			playEffect(6);
			this.hard.setSelected(false);
			this.normal.setSelected(true);
			this.selection = 1;
		}
		else if (this.selection == 1) {
			playEffect(6);
			this.normal.setSelected(false);
			this.easy.setSelected(true);
			this.selection = 2;
		}
	} 
	public void moveDown() {
		if (this.easy.isSelected()) {
			playEffect(6);
			this.easy.setSelected(false);
			this.normal.setSelected(true);
			this.selection = 1;
		}
		else if (this.normal.isSelected()) {
			playEffect(6);
			this.normal.setSelected(false);
			this.hard.setSelected(true);
			this.selection = 0;
		}
		else if (this.hard.isSelected()) {
			volume.doClick();
		}
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == back) {
			this.frame.returnToMain();
		}
		if (e.getSource() == easy) {
			playEffect(6);
			this.selection = 2;
		}
		if (e.getSource() == normal) {
			playEffect(6);
			this.selection = 1;
		}
		if (e.getSource() == hard) {
			playEffect(6);
			this.selection = 0;
		}
		if (e.getSource() == volume) {
			audioPlayer.turnVolumeOnOff();
		}
	}
}
