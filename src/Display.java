import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display extends Canvas{

	private static final long serialVersionUID = 8274011568777903027L;

	public Display(int width, int height, String name, Game game){
		JFrame frame = new JFrame(name);
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		game.start();
	}
}
