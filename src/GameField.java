import java.awt.Color;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;

public class GameField extends JPanel{
	int currentDirection = 0;
	int previousDirection = 0;
	SnakeHead head;
	Apple apple;
	Snake frame;
	DisplayPanel display;
	AudioPlayer audioPlayer;
	public GameField(DisplayPanel panel, Snake frame) {
		this.frame = frame;
		
		this.display = panel;
		
		try { this.audioPlayer = new AudioPlayer();}
		catch (LineUnavailableException e) {e.printStackTrace();}
		catch (IOException e) {e.printStackTrace();}
		catch (UnsupportedAudioFileException e) {e.printStackTrace();}
		
		this.setLayout(null);
		this.setBackground(Color.GREEN);
		
		head = new SnakeHead(6,6);
		head.addNewBody(new SnakeBody(6,7,0));
		head.addNewBody(new SnakeBody(6,8,0));
		head.addNewBody(new SnakeBody(6,9,0));
		
		this.add(head);
		this.add(head.getNext());
		this.add(head.getNext().getNext());
		this.add(head.getNext().getNext().getNext());
		
		apple = new Apple();
		this.add(apple);
		this.generateApple();
	}
	public boolean checkvalidDirection(int dir) {
		if (this.previousDirection == dir)
			return true;
		if ((this.previousDirection+dir)%2 == 1)
			return true;
		return false;
	}
	private int getOppositeDirection(int i) {
		int opposite = i + 2;
		return opposite>3? opposite - 4 : opposite;
	}
	public void move() {
		if (this.previousDirection != this.currentDirection && 
			getOppositeDirection(this.previousDirection) != this.currentDirection)
			this.audioPlayer.playEffect(2);
		if (checkvalidDirection(this.currentDirection))
			this.previousDirection = this.currentDirection;
		this.head.move(this.previousDirection);
		if (checkEaten()) {
			this.audioPlayer.playEffect(4);
			this.display.addAppleToCount();
			this.display.updateHighScore();
			generateApple();
			SnakeBody tail = head.getTail();
			tail.addNewBody(new SnakeBody(-1,-1, 0));
			this.add(tail.getNext());
		}
		else if (checkLose())
			defeat();
			
	}
	public void setCurrentDirection(int dir) {
		this.currentDirection = dir;
	}
	private void generateApple() {
		boolean legalLocation = false;
		int x= 1;
		int y= 1;
		while (!legalLocation) {
			x = (int)(Math.random()*15);
			y = (int)(Math.random()*13);
			if (!head.isLocatedAt(x,y)) {
				legalLocation = true;
				this.apple.setnewLocation(x,y);
			}
		}
	}
	private boolean checkEaten() {
		return ((this.head.getXLocation() == this.apple.getXLocation()) &&
				(this.head.getYLocation() == this.apple.getYLocation()));
	}
	private boolean checkLose() {
		return (this.head.getXLocation() < 0  || 
				this.head.getXLocation() > 14 ||
				this.head.getYLocation() < 0  ||
				this.head.getYLocation() > 12 ||
				this.head.getNext().isLocatedAt(this.head.getXLocation(), this.head.getYLocation()));
	}
	private void defeat(){
		audioPlayer.playEffect(3);
		this.frame.defeat(display.numOfApplesEaten);
	}
}
