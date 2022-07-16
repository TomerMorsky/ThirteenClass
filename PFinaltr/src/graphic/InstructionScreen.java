package graphic;

import java.awt.BorderLayout;
import java.awt.Dimension;
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

public class InstructionScreen extends JPanel {
	Image backround; 
	private JButton backButton;
	
	public InstructionScreen() {
		super();
		setBackround();
		createBackButton();
		add(this.backButton);
		this.setLayout(null);
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(backround, 0, 0, null);//draw the background
	}
	
	/**
	 * set the background image
	 */
	private void setBackround() {
		try{
			backround = ImageIO.read(new File("pictures/instructions.png"));
		}catch(IOException e){
			System.out.println("file not found");
		}
		Dimension size = new Dimension(backround.getWidth(null), backround.getHeight(null));
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setLayout(new BorderLayout());
	}	
	
	private void createBackButton() {
		Image backImage = null;
	
		try {
			backImage = ImageIO.read(new File("pictures/backButton1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		backButton = new JButton();
		backButton.setIcon(new ImageIcon(backImage));
		backButton.setBounds(20, 570, 90, 90);
		backButton.setContentAreaFilled(false);
		backButton.setBorderPainted(false);
		final InstructionScreen thisPanel = this;
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				main.frame.invalidate();
				main.frame.remove(thisPanel);
				main.frame.setContentPane(new OpenScreen());
				main.frame.validate();
				main.frame.setSize(400,400);
				
			}
		});
			
	}
}
