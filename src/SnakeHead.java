import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class SnakeHead extends JLabel {
	final int NORTH = 0, EAST = 1, SOUTH = 2, WEST = 3;
	private int currentDirection = 0;
	final int PIXEL_SIZE = 40;
	private int X;
	private int Y;
	private ImageIcon headNorth;
	private ImageIcon headEast;
	private ImageIcon headSouth;
	private ImageIcon headWest;
	private SnakeBody nextBodyPart;
	public SnakeHead(int x, int y) {
		this.X = x;
		this.Y = y;
		nextBodyPart = null;
		
		headNorth = new ImageIcon("src\\Graphics\\head_up.png");
		headEast = new ImageIcon("src\\Graphics\\head_right.png");
		headSouth = new ImageIcon("src\\Graphics\\head_down.png");
		headWest = new ImageIcon("src\\Graphics\\head_left.png");
		
		this.setBounds(this.X*PIXEL_SIZE, this.Y*PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE);
		this.setIcon(headNorth);
		this.setBackground(Color.GREEN);
		this.setOpaque(true);
	}
	public int getXLocation() {
		return this.X;
	}
	public int getYLocation() {
		return this.Y;
	}
	public int getDirection() {
		return this.currentDirection;
	}
	private void moveInDirectoin(int xDirection, int yDirection, int direction) {
		this.getNext().move(this.X, this.Y, direction);
		this.currentDirection = direction;
		this.X += xDirection;
		this.Y += yDirection;
		this.setLocation(
				this.X*PIXEL_SIZE,
				this.Y*PIXEL_SIZE);
	}
	private void setNewImage(int dir) {
		switch(dir) {
		case NORTH:
			this.setIcon(headNorth);
			break;
		case EAST:
			this.setIcon(headEast);
			break;
		case SOUTH:
			this.setIcon(headSouth);
			break;
		case WEST:
			this.setIcon(headWest);
		}
	}
	public void move(int direction) {
		switch (direction) {
			case NORTH:
				setNewImage(NORTH);
				moveInDirectoin(0, -1, NORTH);
				break;
			case EAST:
				setNewImage(EAST);
				moveInDirectoin(1, 0, EAST);
				break;
			case SOUTH:
				setNewImage(SOUTH);
				moveInDirectoin(0, 1, SOUTH);
				break;
			case WEST:
				setNewImage(WEST);
				moveInDirectoin(-1, 0, WEST);
		}
	}
	
	public void addNewBody(SnakeBody body) {
		if (this.getNext() == null)
			this.nextBodyPart = body;
		else
			this.getNext().addNewBody(body);
	}
	public SnakeBody getNext() {
		return this.nextBodyPart;
	}
	public boolean isLocatedAt(int x, int y) {
		if (this.X == x && this.Y == y)
			return true;
		return this.getNext().isLocatedAt(x,y);
	}
	public SnakeBody getTail() {
		return getThisTail(this.getNext());
	}
	private SnakeBody getThisTail(SnakeBody next) {
		SnakeBody nextBody = next.getNext();
		if (nextBody == null)
			return next;
		return getThisTail(nextBody);
	}
}
