import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class mainFrame  extends JFrame implements KeyListener{
	AudioPlayer audioPlayer;
	SettingsMenu settingsMenu;
	MainMenu menuPanel;
	LeaderBoard leaderBoard;
	private boolean settingsOn = false;
	private boolean leaderBoardOn = false;
	private JPanel title;
	private JLabel titleLabel;
	
	private ImageIcon icon = new ImageIcon("src\\Graphics\\head_up.png");
	private ImageIcon verticalDeco = new ImageIcon("src\\Graphics\\body_vertical.png");
	private ImageIcon topRightDeco = new ImageIcon("src\\Graphics\\body_topright.png");
	private ImageIcon horizontalDeco = new ImageIcon("src\\Graphics\\body_horizontal.png");
	private ImageIcon bottomLeftDeco = new ImageIcon("src\\Graphics\\body_bottomleft.png");
	private ImageIcon topLeftDeco = new ImageIcon("src\\Graphics\\body_topleft.png");
	private ImageIcon bottomRightDeco = new ImageIcon("src\\Graphics\\body_bottomright.png");
	private ImageIcon headDeco = new ImageIcon("src\\Graphics\\head_down.png");
	private ImageIcon appleDeco = new ImageIcon("src\\Graphics\\apple.png");
	
	private JLabel deco1;
	private JLabel deco2;
	private JLabel deco3;
	private JLabel deco4;
	private JLabel deco5;
	private JLabel deco6;
	private JLabel deco7;
	private JLabel deco8;
	private JLabel deco9;
	private JLabel deco10;
	private JLabel deco11;
	private JLabel deco12;
	private JLabel deco13;
	private JLabel deco14;
	private JLabel deco15;
	
	ImageIcon image = new ImageIcon("src\\Graphics\\apple.png");
	JLabel selector;
	int selectorLocation = 0;
	boolean selectorActive = true;
	
	public mainFrame(int difficulty) {
		try {audioPlayer = new AudioPlayer();}
		catch (LineUnavailableException e) {e.printStackTrace();}
		catch (IOException e) {e.printStackTrace();}
		catch (UnsupportedAudioFileException e) {e.printStackTrace();}
		
		selector = new JLabel(image);
		selector.setBounds(135, 175, 40, 40);
		
		menuPanel = new MainMenu(this);
		
		settingsMenu = new SettingsMenu(this,difficulty);
		
		leaderBoard = new LeaderBoard(this);
		
		titleLabel = new JLabel("Natan's Snake");
		titleLabel.setFont(new Font("MV Boli", Font.BOLD, 50));
		
		title = new JPanel();
		title.setBackground(new Color(170, 125, 45));
		title.setBounds(20, 20, 445, 100);
		title.setBorder(BorderFactory.createLineBorder(new Color(150, 105, 25),5));
		title.add(titleLabel);
		
		deco1 = new JLabel(verticalDeco);
		deco1.setBounds(40, -20, 40, 40);
		deco2 = new JLabel(verticalDeco);
		deco2.setBounds(40, 120, 40, 40);
		deco3 = new JLabel(verticalDeco);
		deco3.setBounds(40, 160, 40, 40);
		deco4 = new JLabel(topRightDeco);
		deco4.setBounds(40, 200, 40, 40);
		deco5 = new JLabel(horizontalDeco);
		deco5.setBounds(80, 200, 40, 40);
		deco6 = new JLabel(horizontalDeco);
		deco6.setBounds(370, 200, 40, 40);
		deco7 = new JLabel(bottomLeftDeco);
		deco7.setBounds(410, 200, 40, 40);
		deco8 = new JLabel(verticalDeco);
		deco8.setBounds(410, 240, 40, 40);
		deco9 = new JLabel(topLeftDeco);
		deco9.setBounds(410, 280, 40, 40);
		deco10 = new JLabel(horizontalDeco);
		deco10.setBounds(370, 280, 40, 40);
		deco11 = new JLabel(horizontalDeco);
		deco11.setBounds(80, 280, 40, 40);
		deco12 = new JLabel(bottomRightDeco);
		deco12.setBounds(40, 280, 40, 40);
		deco13 = new JLabel(verticalDeco);
		deco13.setBounds(40, 320, 40, 40);
		deco14 = new JLabel(headDeco);
		deco14.setBounds(40, 360, 40, 40);
		deco15 = new JLabel(appleDeco);
		deco15.setBounds(40, 440, 40, 40);
		
		this.addKeyListener(this);
		this.setIconImage(icon.getImage());
		this.setTitle("Natan's Snake");
		this.setLayout(null);
		this.add(deco1);
		this.add(deco2);
		this.add(deco3);
		this.add(deco4);
		this.add(deco5);
		this.add(deco6);
		this.add(deco7);
		this.add(deco8);
		this.add(deco9);
		this.add(deco10);
		this.add(deco11);
		this.add(deco12);
		this.add(deco13);
		this.add(deco14);
		this.add(deco15);
		this.add(selector);
		this.add(title);
		this.add(menuPanel);
		this.getContentPane().setBackground(Color.GREEN);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocation(400,150);
		this.setSize(500,550);
		this.setResizable(false);
		this.setVisible(true);
	}
	public void startGame() {
		playEffect(5);
		this.dispose();
		new Snake(this.settingsMenu.getDifficulty(), this.leaderBoard);
	}
	public void openSettings() {
		playEffect(1);
		this.settingsOn = true;
		this.selectorActive = false;
		this.remove(selector);
		this.remove(menuPanel);
		this.add(settingsMenu);
		this.pack();
		this.setSize(500,550);
	}
	public void returnToMain() {
		playEffect(1);
		this.settingsOn = false;
		this.leaderBoardOn = false;
		this.selectorActive = true;
		this.remove(settingsMenu);
		this.remove(leaderBoard);
		this.add(selector);
		
		this.add(menuPanel);
		this.pack();
		this.setSize(500,550);
	}
	public void openLeaderBoard() {
		playEffect(1);
		this.leaderBoardOn = true;
		this.selectorActive = false;
		this.remove(selector);
		this.remove(menuPanel);
		this.add(leaderBoard);
		this.pack();
		this.setSize(500, 550);
	}
	private void back() {
		if (this.leaderBoardOn || this.settingsOn)
			returnToMain();
		else
			System.exit(0);
	}
	public void playEffect(int i) {
		this.audioPlayer.playEffect(i);
	}
	private void moveSelector(int direction) {
		int newLocation = this.selectorLocation + direction;
		if (newLocation<0 || newLocation>3)
			return;
		playEffect(1);
		moveSelectorTo(newLocation);
	}
	public void moveSelectorTo(int i) {
		this.selectorLocation = i;
		this.selector.setBounds(135, 175 + this.selectorLocation*72, 40, 40);
	}
	private void select() {
		switch (this.selectorLocation) {
		case 0: startGame();
		break;
		case 1: openSettings();
		break;
		case 2: openLeaderBoard();
		break;
		case 3: System.exit(0);
		}
	}
	public void keyTyped(KeyEvent e) {
		
	}
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 27)
			back();
		if ((e.getKeyChar() == 'w' || e.getKeyCode() == 38) && this.selectorActive)
			moveSelector(-1);
		if ((e.getKeyChar() == 's' || e.getKeyCode() == 40) && this.selectorActive)
			moveSelector(1);
		if (e.getKeyCode() == 10) {
			if (selectorActive) 
				select();
			else
				back();
		}
		if ((e.getKeyChar() == 'w' || e.getKeyCode() == 38) && this.settingsOn) {
			this.settingsMenu.moveUp();
		} 
		if ((e.getKeyChar() == 's' || e.getKeyCode() == 40) && this.settingsOn) {
			this.settingsMenu.moveDown();
		}
	}
	public void keyReleased(KeyEvent e) {
		
	}	
}
