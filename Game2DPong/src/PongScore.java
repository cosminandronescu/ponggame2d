import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

public class PongScore extends Rectangle {

	private static final long serialVersionUID = 1L;
	static int GAME_WIDTH;
	static int GAME_HEIGHT;
	int score;

	PongScore(int GAME_WIDTH, int GAME_HEIGHT) {

		PongScore.GAME_WIDTH = GAME_WIDTH;
		PongScore.GAME_HEIGHT = GAME_HEIGHT;
	}

	public void draw(Graphics g) {

		Graphics2D g2d = (Graphics2D)g;
		RenderingHints rh = new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHints(rh);
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, 600, 100);
		g2d.setColor(Color.CYAN);
		g2d.fillRect(0, 90, 600, 10);


		Graphics2D g2t = (Graphics2D)g;
		RenderingHints rth = new RenderingHints(
				RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		g2d.setColor(Color.CYAN);
		g2d.fillRect(0, 14, 160, 30);
		g2t.setRenderingHints(rth);
		g2t.setColor(Color.BLACK);
		g2t.setFont(new Font("Arial", Font.BOLD, 30));
		g2t.drawString("SCORE " + String.valueOf(score), 10, 40);

		g2t.setColor(Color.CYAN);
		g2t.setFont(new Font("Arial", Font.BOLD, 20));
		g2t.drawString("[ESC] to pause/unpause", 10, 80);		

	}

}
