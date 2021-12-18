package graphic;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.JTextArea;

import game.main;
import yaniv.Stats;

public class MaxScores extends JPanel {
	private Stats stats;
	Image backround;
	JButton backButton;
	JTextArea maxPoints, championName, allTimesWinner;
	String name;
	int points;
	public MaxScores() {
		try {
			stats = new Stats();
			points = stats.getMaxScore();
			name = stats.getNameOfWinner();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/**
		 *  try {
			this.stats = new Stats();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("error while trying to make new stst");
		}
		 */
		
		  
		
		setBackround();
		createBackButton();
		createJtextEreaName();
		createJtextEreawinnersOfAllTimes();
		add(backButton);
		createJtextEreaPoints();
		this.setLayout(null);
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
		backButton.setBounds(20, 460, 90, 90);
		backButton.setContentAreaFilled(false);
		backButton.setBorderPainted(false);
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("back clicked");
				main.frame.setContentPane(new OpenScreen());
				main.frame.validate();
				main.frame.setSize(400,400);
				
			}
		});
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
			backround = ImageIO.read(new File("pictures/noteBook.jpg"));
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
	
	public void createJtextEreaPoints() {
		maxPoints = new JTextArea();
		maxPoints.setText("Points: " + String.valueOf(points));
		maxPoints.setFont(new Font("Arial Black", Font.BOLD, 22));
		maxPoints.setBounds(75, 130, 300, 30);
		maxPoints.setEditable(false);
		maxPoints.setOpaque(false);
		add(maxPoints);
	}
	
	public void createJtextEreaName() {
		championName = new JTextArea();
		championName.setText("The champion is: " + name);
		championName.setFont(new Font("Arial Black", Font.BOLD, 22));
		championName.setBounds(75, 100, 600, 30);
		championName.setEditable(false);
		championName.setOpaque(false);
		add(championName);
	}
	
	public void createJtextEreawinnersOfAllTimes() {
		allTimesWinner = new JTextArea();
		allTimesWinner.setText("Winners Of All Times:");
		allTimesWinner.setFont(new Font("Arial Black", Font.BOLD, 22));
		allTimesWinner.setBounds(75, 70, 300, 30);
		allTimesWinner.setEditable(false);
		allTimesWinner.setOpaque(false);
		add(allTimesWinner);
	}
}
