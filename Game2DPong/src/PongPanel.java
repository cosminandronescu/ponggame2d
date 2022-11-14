import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PongPanel extends JPanel implements java.awt.event.ActionListener {
	
	private static final long serialVersionUID = 1L;
	static final int GAME_WIDTH = 700;
	static final int GAME_HEIGHT = 950;
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
	static final int BALL_DIAMETER = 30;
	static final int CONTROL_WIDTH = 90;
	static final int CONTROL_HEIGHT = 20;

	Timer timer;
	Image image;
	Graphics graphics;
	Random random;
	PongControl rectangle;
	PongBall ball;
	PongScore score;

	String paddlePath = "/media/PaddleSound.wav";
	String musicPath = "/media/GameMusic.wav";
	String lostPath = "/media/Lost.wav";
	Media lostSound = new Media();
	Media musicPlay = new Media();
	Media paddleSound = new Media();

	boolean drawn;

	//JPanel creation - here is where the game starts
	public PongPanel(){

		newControl();
		newBall();
		score = new PongScore(GAME_WIDTH, GAME_HEIGHT);
		this.setFocusable(true);
		this.addKeyListener(new ActionListener());
		this.setPreferredSize(SCREEN_SIZE);

		//starts timer to run the game
		timer = new Timer(10, this);
		timer.setInitialDelay(500);
		timer.start();

		musicPlay.Music(musicPath);
	}
	public void paint(Graphics g) {
		image = createImage(getWidth(), getHeight());
		graphics  = image.getGraphics();
		draw(graphics);
		g.drawImage(image, 0, 0, this);
	}

	//location of the ball in the panel
	public void newBall() {
		random = new Random();
		ball = new PongBall((GAME_WIDTH / 2) - (BALL_DIAMETER / 2), (GAME_HEIGHT / 2) - (BALL_DIAMETER / 2), BALL_DIAMETER, BALL_DIAMETER);
	}

	//location of the paddle in the panel
	public void newControl() {
		rectangle = new PongControl((GAME_WIDTH / 2) - (CONTROL_WIDTH / 2), 910, CONTROL_WIDTH, CONTROL_HEIGHT);
	}

	//draws the paddle, ball and the score in the panel
	public void draw(Graphics g) {
		rectangle.draw(g);
		ball.draw(g);
		score.draw(g);
	}

	//updates the location for the paddle and ball
	public void smooth() {
		rectangle.smooth();
		ball.smooth();
	}
	public void panelEdge() {
		//sets game border
		if(rectangle.x <= 0) rectangle.x = 0;
		if(rectangle.x >= (GAME_WIDTH-CONTROL_WIDTH)) 
			rectangle.x = GAME_WIDTH-CONTROL_WIDTH;
		//left border
		if(ball.x <= 0) {
			ball.setX(-ball.xSpeed);
		}
		//right border
		if(ball.x >= GAME_WIDTH - BALL_DIAMETER) {
			ball.setX(-ball.xSpeed);
		}
		//top border
		if(ball.y <= 100) {
			ball.setY(-ball.ySpeed);
		}

		//collision physics between the paddle and ball
		if(ball.intersects(rectangle)) {

			paddleSound.Audio(paddlePath);

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
		else {
			//restarts score if the ball goes past the paddle
			if(ball.y >= GAME_HEIGHT - 39) lostSound.Audio(lostPath);
			if(ball.y >= GAME_HEIGHT - 40) {
				newControl();
				newBall();
				timer.restart();
				score.score = 0;
			}
		}
	}

	//keyboard inputs
	public class ActionListener extends KeyAdapter {

		int check = 0;

		public void keyPressed(KeyEvent e) {

			//paddle control
			rectangle.keyPressed(e);

			//pause game
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

		//stops the paddle from moving after releasing the key
		public void keyReleased(KeyEvent e) {
			rectangle.keyReleased(e);
		}

	}
	//repaints the all the objects
	@Override
	public void actionPerformed(ActionEvent e) {

		smooth();
		panelEdge();
		repaint();

		//resets the timer - it's recommended for long sessions
		int loopslot = 0;
		loopslot++;
		if (loopslot == 20000000) {
			timer.restart();
		}

	}
}
