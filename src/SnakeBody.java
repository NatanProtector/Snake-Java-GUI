import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class SnakeBody  extends JLabel {
	final int NORTH = 0, EAST = 1, SOUTH = 2, WEST = 3;
	final int PIXEL_SIZE = 40;
	private int X;
	private int Y;
	int currentDirection;
	private SnakeBody nextBodyPart;
	ImageIcon bottomLeft = new ImageIcon("src\\Graphics\\body_bottomleft.png");
	ImageIcon bottomRight = new ImageIcon("src\\Graphics\\body_bottomright.png");
	ImageIcon horizontal = new ImageIcon("src\\Graphics\\body_horizontal.png");
	ImageIcon vertical = new ImageIcon("src\\Graphics\\body_vertical.png");
	ImageIcon topLeft = new ImageIcon("src\\Graphics\\body_topleft.png");
	ImageIcon topRight = new ImageIcon("src\\Graphics\\body_topright.png");
	ImageIcon tailUp = new ImageIcon("src\\Graphics\\tail_up.png");
	ImageIcon tailDown = new ImageIcon("src\\Graphics\\tail_down.png");
	ImageIcon tailRight = new ImageIcon("src\\Graphics\\tail_right.png");
	ImageIcon tailLeft = new ImageIcon("src\\Graphics\\tail_left.png");
	
	public SnakeBody(int x,int y, int direction) {
		this.X = x;
		this.Y = y;
		this.currentDirection = direction;
		this.nextBodyPart = null;
		this.setBounds(this.X*PIXEL_SIZE, this.Y*PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE);
		this.setBackground(Color.GREEN);
		this.setOpaque(true);
		
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
	public void move(int x, int y, int direction) {
		if (this.getNext() != null)
			this.getNext().move(this.X, this.Y, this.currentDirection);
		setImage(this.currentDirection, direction);
		this.currentDirection = direction;
		this.X = x;
		this.Y = y;
		this.setLocation(
				this.X*PIXEL_SIZE,
				this.Y*PIXEL_SIZE);
	}
	private void setImage(int currentDir, int nextDir) {
		if (this.nextBodyPart == null)
			setTailImage(nextDir);
		else 
			this.setDirectionImage(currentDir, nextDir);
	}
	private void setDirectionImage(int currentDir, int nextDir) {
				
		if (currentDir == nextDir) {
			 if (currentDir%2==0)
				 this.setIcon(vertical);
			 else
				 this.setIcon(horizontal);
		}
		else if ((currentDir == SOUTH && nextDir == EAST) || (currentDir == WEST && nextDir == NORTH )) {
			this.setIcon(topRight);
 		}
		else if ((currentDir == NORTH && nextDir == WEST) || (currentDir == EAST && nextDir == SOUTH )) {
			this.setIcon(bottomLeft);
 		}
		else if ((currentDir == WEST && nextDir == SOUTH) || (currentDir == NORTH && nextDir == EAST )) {
			this.setIcon(bottomRight);
 		}
		else if ((currentDir == EAST && nextDir == NORTH) || (currentDir == SOUTH && nextDir == WEST )) {
			this.setIcon(topLeft);
 		}
			
	}
	private void setTailImage(int nextDir) {
		switch (nextDir) {
		case NORTH:
			this.setIcon(tailDown);
			break;
		case EAST:
			this.setIcon(tailLeft);
			break;
		case SOUTH:
			this.setIcon(tailUp);
			break;
		case WEST:
			this.setIcon(tailRight);
		}		
	}
	public boolean isLocatedAt(int x, int y) {
		if (this.X == x && this.Y == y)
			return true;
		if (this.getNext() == null)
			return false;
		return this.getNext().isLocatedAt(x,y);
	}
}