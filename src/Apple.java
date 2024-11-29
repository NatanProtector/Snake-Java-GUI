import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Apple extends JLabel{
	final int PIXEL_SIZE = 40;
	private int X;
	private int Y;
	ImageIcon image;
	public Apple() {
		image = new ImageIcon("src\\Graphics\\apple.png");
		this.setBackground(Color.GREEN);
		this.setOpaque(true);
		this.setIcon(image);
	}
	public int getXLocation() {
		return this.X;
	}
	public int getYLocation() {
		return this.Y;
	}
	public void setnewLocation(int x, int y) {
		this.X = x;
		this.Y = y;
		this.setBounds(this.X*PIXEL_SIZE, this.Y*PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE);
	}
}
