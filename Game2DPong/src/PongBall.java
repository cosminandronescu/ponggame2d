import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.Random;

public class PongBall extends Rectangle {

	private static final long serialVersionUID = 1L;
	Random random;
	int xSpeed;
	int ySpeed;
	int speed = 1;
	
	PongBall(int x, int y, int width, int height) {
		
		super(x, y, width, height);
		random = new Random();
		
		int randomX = random.nextInt(2);
		if(randomX == 0) randomX--;
		setX(randomX * speed);
		
		int randomY = random.nextInt(2);
		if(randomY == 0) randomY--;
		setY(randomY * speed);
	}

	public void setX(int randomX) {
		xSpeed = randomX;
		
	}
	public void setY(int randomY) {
		ySpeed = randomY;
		
	}
	public void smooth() {
		x = x + xSpeed;
		y = y + ySpeed;
	}
	public void draw (Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		RenderingHints rh = new RenderingHints(
	             RenderingHints.KEY_ANTIALIASING,
	             RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHints(rh);
		g2.setColor(Color.CYAN);
		g2.fillOval(x, y, height, width);

	}
}
