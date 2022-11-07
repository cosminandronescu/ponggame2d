import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;

public class PongControl extends Rectangle {

	private static final long serialVersionUID = 1L;
	int xSpeed;

	PongControl(int x, int y, int CONTROL_WIDTH, int CONTROL_HEIGHT) {

		super(x, y, CONTROL_WIDTH, CONTROL_HEIGHT);
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_A) {
			setX(-10);
			smooth();
		}
		if(e.getKeyCode() == KeyEvent.VK_D) {
			setX(+10);
			smooth();
		}
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_A) {
			setX(0);
			smooth();
		}
		if(e.getKeyCode() == KeyEvent.VK_D) {
			setX(0);
			smooth();
		}
	}
	public void setX(int setX) {
		xSpeed = setX;
	}
	public void smooth() {
		x = x + xSpeed;
	}
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		RenderingHints rh = new RenderingHints(
	             RenderingHints.KEY_ANTIALIASING,
	             RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHints(rh);
		g2.setColor(Color.CYAN);
		g2.fillRect(x, y, width, height);

	}
}
