import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PongPanel extends JPanel implements Runnable, java.awt.event.ActionListener {


	private static final long serialVersionUID = 1L;
	static final int GAME_WIDTH = 600;
	static final int GAME_HEIGHT = 800;
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
	static final int BALL_DIAMETER = 20;
	static final int CONTROL_WIDTH = 80;
	static final int CONTROL_HEIGHT = 20;
	Timer timer;
	Thread thread;
	Image image;
	Graphics graphics;
	Random random;
	PongControl rectangle;
	PongBall ball;
	PongScore score;

	boolean drawn;

	public PongPanel(){

		newControl();
		newBall();
		score = new PongScore(GAME_WIDTH, GAME_HEIGHT);
		this.setFocusable(true);
		this.addKeyListener(new ActionListener());
		this.setPreferredSize(SCREEN_SIZE);

		timer = new Timer(10, this);
		timer.setInitialDelay(500);
		timer.start();


	}


	public void newBall() {

		random = new Random();
		ball = new PongBall((GAME_WIDTH / 2) - (BALL_DIAMETER / 2), (GAME_HEIGHT / 2) - (BALL_DIAMETER / 2), BALL_DIAMETER, BALL_DIAMETER);

	}

	public void newControl() {
		rectangle = new PongControl((GAME_WIDTH / 2) - (CONTROL_WIDTH / 2), 760, CONTROL_WIDTH, CONTROL_HEIGHT);


	}
	public void paint(Graphics g) {

		image = createImage(getWidth(), getHeight());
		graphics  = image.getGraphics();
		draw(graphics);
		g.drawImage(image, 0, 0, this);

	}
	public void draw(Graphics g) {

		rectangle.draw(g);
		ball.draw(g);
		score.draw(g);
	}

	public void smooth() {
		rectangle.smooth();
		ball.smooth();

	}
	public void panelEdge() {

		if(rectangle.x <= 0) rectangle.x = 0;
		if(rectangle.x >= (GAME_WIDTH-CONTROL_WIDTH)) 
			rectangle.x = GAME_WIDTH-CONTROL_WIDTH;


		if(ball.x <= 0) {
			ball.setX(-ball.xSpeed);
		}
		if(ball.x >= GAME_WIDTH - BALL_DIAMETER) {
			ball.setX(-ball.xSpeed);
		}
		if(ball.y <= 100) {
			ball.setY(-ball.ySpeed);

		}

		if(ball.intersects(rectangle)) {

			if (ball.ySpeed > 0) {
				score.score++;
			}
			ball.ySpeed = (-ball.ySpeed);

			ball.ySpeed--;


			if(ball.xSpeed > 0 && score.score%2==0) ball.xSpeed++;
			else if (score.score%2==0) ball.xSpeed--;

			ball.setX(ball.xSpeed);
			ball.setY(ball.ySpeed);
		}

		if(ball.y > GAME_HEIGHT - 40) {
			newControl();
			newBall();
			timer.restart();
			score.score = 0;
		}

	}

	public void unpause() {
		timer.start();
	}

	public class ActionListener extends KeyAdapter {

		int check = 0;

		public void keyPressed(KeyEvent e) {
			rectangle.keyPressed(e);
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {

				if (check == 0) {
					timer.stop();
					check++;
				}
				else {
					timer.start();
					check = 0;
				}
			}

		}
		public void keyReleased(KeyEvent e) {
			rectangle.keyReleased(e);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		smooth();
		panelEdge();
		repaint();

		int loopslot = 0;
		loopslot++;
		if (loopslot == 10000000) {
			timer.restart();
		}

	}
	public void run() { 
		// TODO Auto-generated method stub

	}

}
