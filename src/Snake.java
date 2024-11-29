import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Snake extends JFrame implements KeyListener {
	private final int NORTH = 0, EAST = 1, SOUTH = 2, WEST = 3;
	private int speed;
	DisplayPanel display;
	GameField game;
	Timer clock;
	TimerTask tick;
	LeaderBoard leaderBoard;
	public Snake(int difficulty, LeaderBoard leaderBoard) {
		
		this.leaderBoard = leaderBoard;
		
		this.speed = difficulty;
		
		display = new DisplayPanel(this);
		
		game = new GameField(display, this);
		game.addKeyListener(this);
		setNewClock(difficulty);
		
		ImageIcon icon  = new ImageIcon("src\\Graphics\\head_up.png");
		this.setIconImage(icon.getImage());
		this.setTitle("Natan's Snake");
		this.addKeyListener(this);
		this.setLayout(new BorderLayout());
		this.add(display, BorderLayout.NORTH);
		this.add(game,BorderLayout.CENTER);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(new Dimension(614,610));
		this.setLocation(380,70);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public void setNewClock(int speed) {
		this.clock = new Timer();
		this.tick = new TimerTask() {
			public void run() {
				game.move();
			}
		};
		this.clock.scheduleAtFixedRate(tick, 0 ,150 + speed*150);
	}
	

	
	private void setNewDirection(int dir) {
		game.setCurrentDirection(dir);
	}
	public void defeat(int applesEaten) {
		this.display.defeat();
		this.leaderBoard.checkHighScore(applesEaten);
		this.clock.cancel();
		this.tick.cancel();
	}
	
	public int getSpeed() {
		return this.speed;
	}	
	
	public void keyTyped(KeyEvent e) {
		
	}
	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == 'w' || e.getKeyCode() == 38)
			setNewDirection(NORTH);
		if (e.getKeyChar() == 'a' || e.getKeyCode() == 37)
			setNewDirection(WEST);
		if (e.getKeyChar() == 's' || e.getKeyCode() == 40)
			setNewDirection(SOUTH);
		if (e.getKeyChar() == 'd'  || e.getKeyCode() == 39)
			setNewDirection(EAST);
		if (e.getKeyCode() == 27)
			display.back();
	}
	
	public void keyReleased(KeyEvent e) {}
}
