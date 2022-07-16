package game;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import graphic.OpenScreen;
import yaniv.Game;

public class main {
	public static JFrame frame;
	public static Game game;

	public static void main(String[] args) {

		frame = new JFrame("open screen");
		game = new Game();
		OpenScreen panel = new OpenScreen();
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		frame.setVisible(true);
		frame.setLocation(400, 250);
		frame.setResizable(false);
		try {
			frame.setIconImage(ImageIO.read(new File("pictures/yanivImage.jpg")));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
