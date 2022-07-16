package graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import game.main;
import yaniv.Game;

public class OpenScreen extends JPanel{
	JButton startButton, instructionsButton, gobletButton;
	Image startButtonImag, instructionsButtonImag, gobletImage;
	JTextField enterNameTextField;
	Image backround;
	public OpenScreen() {
		setLayout(null);
		try {
			startButtonImag = ImageIO.read(new File("pictures/btStart2.png"));
			instructionsButtonImag = ImageIO.read(new File("pictures/btInstructions.png"));
			gobletImage = ImageIO.read(new File("pictures/goblet.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		createStartbutton();
		createInstructionButton();
		createGobletImageButton();
		createJtextFieldName();
		add(startButton);
		add(instructionsButton);
		add(gobletButton);
		add(enterNameTextField);
		setBackround();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backround, 0, 0, null);//draw the background
	}


	private void createStartbutton() {
		startButton = new JButton();
		startButton.setIcon(new ImageIcon(startButtonImag));
		startButton.setBounds(112, 100, 186, 73);
		startButton.setContentAreaFilled(false);
		startButton.setBorderPainted(false);
		startButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				main.game.setPlayerName(enterNameTextField.getText());
				main.frame.setContentPane(new GamePanel(main.game.getPlayers(), main.game.getOpenPile(), main.game.getPile(), main.game));
				main.frame.validate();
				main.frame.setSize(1000, 600);
				main.frame.setLocationRelativeTo(null);
			}
		});
	}
	
	private void createInstructionButton() {
		instructionsButton = new JButton();
		instructionsButton.setIcon(new ImageIcon(instructionsButtonImag));
		instructionsButton.setBounds(325, 310, 55, 59);
		instructionsButton.setContentAreaFilled(false);
		instructionsButton.setBorderPainted(false);
		instructionsButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				main.frame.setContentPane(new InstructionScreen());
				main.frame.validate();
				main.frame.setSize(1280,720);
				
			}
		});
	}
	/**
	 * create the goblet button, when pushed go to the Max Score screen
	 */
	private void createGobletImageButton() {
		gobletButton = new JButton();
		gobletButton.setIcon(new ImageIcon(gobletImage));
		gobletButton.setBounds(0, 315, 57, 47);
		gobletButton.setContentAreaFilled(false);
		gobletButton.setBorderPainted(false);
		gobletButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				main.frame.setContentPane(new MaxScores());
				main.frame.validate();
				main.frame.setSize(500,600);
			}
		});
	}
	/**
	 * set the background image
	 */
	private void setBackround() {
		try{
			backround = ImageIO.read(new File("pictures/option2.jpg"));
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
	
	private void createJtextFieldName() {
		enterNameTextField = new JTextField(" your name:");
		enterNameTextField.setOpaque(false);
		enterNameTextField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				
			}
			@Override
			public void focusGained(FocusEvent e) {
				enterNameTextField.setText("");
			}
		});
		enterNameTextField.setBounds(95,230, 200,30);  
	} 
	
}
