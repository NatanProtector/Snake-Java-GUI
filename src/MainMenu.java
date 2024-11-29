import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenu extends JPanel implements ActionListener{
	
	JLabel title;
	JButton play;
	JButton settings;
	JButton leaderBoard;
	JButton quit;
	mainFrame frame;
	ImageIcon image = new ImageIcon("src\\Graphics\\apple.png");
	JLabel selecter;
	public MainMenu(mainFrame frame)  {
		
		this.frame = frame;
		
		selecter = new JLabel(image);
		
		
		play = new JButton("Play");
		play.setPreferredSize(new Dimension(130,55));
		play.setFocusable(false);
		play.addActionListener(this);
		play.setBackground(new Color(150, 105, 25));
		play.setFont(new Font("MV Boli", Font.BOLD,30));
		play.setForeground(Color.BLACK);
		play.setBorder(BorderFactory.createLineBorder(new Color(140, 85, 5),5));
		settings = new JButton("Settings");
		settings.setPreferredSize(new Dimension(130,55));
		settings.setFocusable(false);
		settings.addActionListener(this);
		settings.setBackground(new Color(150, 105, 25));
		settings.setFont(new Font("MV Boli", Font.BOLD,20));
		settings.setForeground(Color.BLACK);
		settings.setBorder(BorderFactory.createLineBorder(new Color(140, 85, 5),5));
		leaderBoard = new JButton("LeaderBoard");
		leaderBoard.setPreferredSize(new Dimension(130,55));
		leaderBoard.setFocusable(false);
		leaderBoard.addActionListener(this);
		leaderBoard.setBackground(new Color(150, 105, 25));
		leaderBoard.setFont(new Font("MV Boli", Font.BOLD,15));
		leaderBoard.setForeground(Color.BLACK);
		leaderBoard.setBorder(BorderFactory.createLineBorder(new Color(140, 85, 5),5));
		quit = new JButton("Quit");
		quit.setPreferredSize(new Dimension(130,55));
		quit.setFocusable(false);
		quit.addActionListener(this);
		quit.setBackground(new Color(150, 105, 25));
		quit.setFont(new Font("MV Boli", Font.BOLD,30));
		quit.setForeground(Color.BLACK);
		quit.setBorder(BorderFactory.createLineBorder(new Color(140, 85, 5),5));
		
		this.setLayout(new FlowLayout(FlowLayout.CENTER,10,15));
		this.setBorder(BorderFactory.createLineBorder(new Color(150, 105, 25),5));
		this.setBackground(new Color(170, 125, 45));
		this.setBounds(120, 155, 250, 310);
		this.add(play);
		this.add(settings);
		this.add(leaderBoard);
		this.add(quit);	
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == play) {
			frame.startGame();
		}
		if (e.getSource() == settings) {
			frame.openSettings();
			frame.moveSelectorTo(1);
		}
		if (e.getSource() == leaderBoard) {
			frame.openLeaderBoard();
			frame.moveSelectorTo(2);
		}
		if (e.getSource() == quit)
			System.exit(0);
	}
}
