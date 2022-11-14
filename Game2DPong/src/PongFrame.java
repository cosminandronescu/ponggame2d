import java.awt.*;
import javax.swing.JFrame;


public class PongFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	PongPanel panel = new PongPanel();
	
	//JFrame creation
	PongFrame(){
		panel = new PongPanel();
		this.add(panel);
		this.setTitle("Pong Game 2D");
		this.setResizable(false);
		this.setBackground(Color.BLACK);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
	}
}


