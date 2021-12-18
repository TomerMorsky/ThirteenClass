package graphic;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import game.main;
import yaniv.Game;
import yaniv.Stats;

public class WinningScreen extends JPanel {
	Image youWin, resume, leave;
	JButton btResume, btLeaveGame;
	String name;
	int maxScore;
	private Image backround;
	public WinningScreen(String playerName, int maxPoints) {
		name = playerName;
		maxScore = maxPoints;
		setLayout(null);
		main.frame.setBackground(new Color(255, 255, 240));
		try {
			youWin = ImageIO.read(new File("pictures/youWin.jpg"));
			resume = ImageIO.read(new File("pictures/continueBt.png"));
			leave = ImageIO.read(new File("pictures/leavegameBt.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		createResumebutton();
		createLeavebutton();
		add(btResume);
		add(btLeaveGame);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(backround, 0, 0, null);//draw the background
		g.drawImage(youWin, 360, 50, 500, 499, null);
	}
	
	 private void createResumebutton() {
		  btResume = new JButton();
		  btResume.setIcon(new ImageIcon(resume));
		  btResume.setBounds(100, 600, 217, 79);
		  btResume.setContentAreaFilled(false);
		  btResume.setBorderPainted(false);
		  btResume.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					main.game.newGame();
					main.frame.setSize(1000, 600);
					main.frame.setContentPane(new GamePanel(main.game.getPlayers(), main.game.getOpenPile(), main.game.getPile(), main.game));
					main.frame.validate();
				}
			});
		}
	  private void createLeavebutton() {
		  btLeaveGame = new JButton();
		  btLeaveGame.setIcon(new ImageIcon(leave));
		  btLeaveGame.setBounds(900, 600, 213, 81);
		  btLeaveGame.setContentAreaFilled(false);
		  btLeaveGame.setBorderPainted(false);
		  btLeaveGame.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						Stats stats = new Stats();
						stats.updateStats(main.game.getPlayerName(), maxScore);
						System.out.println("write to file!!!!!");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					main.game = new Game();
					GamePanel.isRun = false;
					main.frame.setBackground(null);
					main.frame.setSize(400,400);
					main.frame.setContentPane(new OpenScreen());
					main.frame.validate();
				}
			});
		}
	  
}
