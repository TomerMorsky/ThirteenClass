package graphic;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import game.main;
import yaniv.Card;
import yaniv.Game;
import yaniv.OpenPile;
import yaniv.Pile;
import yaniv.Player;

 
public class GamePanel extends JPanel {
	private static final int speed_Of_Card_Animation = 1; // recommended 1. anyway not above 3!!
	private List<Drawable> objectsToDraw;
	Image backround;
	Image yanivImage;
	JButton yanivButton;
	Game gameTemp;
	JPanel p;
	static int minToCallYaniv = 9; // the minimal sum to call yaniv
	private JTextArea p1Points, p2Points, p3Points;
	private int sourceX;
	private int sourceY;
	private Card currentCard;
	private boolean isImageRotated;
	private int destinationX = 100;
	private int destinationY = 100;
	private Image backCard = null;
	private boolean seeDetails = false;
	private static List<JTextArea> pointsList = new ArrayList<JTextArea>();
	private static List<Integer> pointsInInt = new ArrayList<Integer>();
	public static Boolean isRun = false;
	private static GamePanel instance = null;
	/**
	 * get a list of players that their cards will draw
	 * @param players
	 */
	public GamePanel(List<Player> players,OpenPile openPile,Pile pile, Game game){
		instance = this;
		 if (!isRun) {
			createPlayersPointsJtexts();
			isRun = true;
		} else {
			updateJtextsSecond();
		}
		setBackround();
		p = this;
		setLayout(null);
		gameTemp = game;
		objectsToDraw = new ArrayList<Drawable>();
		for(int i = 0; i < players.size(); i++){
			objectsToDraw.add(players.get(i));			
		}
		objectsToDraw.add(openPile);
		objectsToDraw.add(pile);
		createYanivButton();
		add(this.yanivButton);
		this.yanivButton.setEnabled(false);// at first the button can not be clicked - works v 
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(final MouseEvent e) {
				Thread resolveClicked = new Thread(new Runnable() {
					public void run() {
						gameTemp.whatClicked(e.getX(), e.getY(), p);
					}
				});
				resolveClicked.start();
				
			}
		});
		try {
			backCard  = ImageIO.read(new File("pictures/gray_back.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(backround, 0, 0, null);//draw the background
		Rectangle playerGreenRecangle = gameTemp.getPlayers().get(gameTemp.getCurrentPlayer()).getCurrentPlayerRectangle();
		g.setColor(Color.GREEN);
		((Graphics2D)g).setStroke(new BasicStroke(4));
		if(!main.game.getPlayers().get(0).getGameOver()) {
			g.drawRect(playerGreenRecangle.x, playerGreenRecangle.y, playerGreenRecangle.width, playerGreenRecangle.height);
		}
		for(Drawable drawable : objectsToDraw){
			drawable.draw(g);
		}
		if(gameTemp.getCurrentPlayer() != 1) {
			this.yanivButton.setEnabled(false);
		}else {
			// only if the sum is lower than 9 can click the yaniv 
			if(gameTemp.getPlayers().get(1).sumCards() < minToCallYaniv) {
				this.yanivButton.setEnabled(true);
			}
			
		}
		if (currentCard != null) {
			if (isImageRotated) {
				currentCard.draw(g, sourceX, sourceY, backCard, seeDetails);// if "see details" true so you can see the card if not you will see his back
			}else {
				currentCard.draw(g, sourceX, sourceY);

			}
		}
	}
	
	public boolean isSeeDetails() {
		return seeDetails;
	}

	public void setSeeDetails(boolean seeDetails) {
		this.seeDetails = seeDetails;
	}

	/**
	 * set the background image
	 */
	private void setBackround() {
		try{
			backround = ImageIO.read(new File("pictures/backroundblue.jpg"));
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
	
	private void createYanivButton() {
		try {
			 this.yanivImage = ImageIO.read(new File("pictures/yanivENA.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.yanivButton = new JButton();
		this.yanivButton.setIcon(new ImageIcon(this.yanivImage));
		this.yanivButton.setBounds(700, 450, 116, 77);
		yanivButton.setContentAreaFilled(false);
		yanivButton.setBorderPainted(false);
		this.yanivButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameTemp.yanivButton();
				
			}
		});
		
	}
	/**
	 * get first values of the points
	 */
	private void createPlayersPointsJtexts() {
		p1Points = new JTextArea();
		p1Points.setText("0");
		p1Points.setFont(new Font("Arial Black", Font.BOLD, 22));
		p1Points.setBounds(80, 80, 60, 40);
		p1Points.setEditable(false);
		p1Points.setOpaque(false);
		
		p2Points = new JTextArea();
		p2Points.setText("0");
		p2Points.setFont(new Font("Arial Black", Font.BOLD, 22));
		p2Points.setBounds(300, 500, 60, 40);
		p2Points.setEditable(false);
		p2Points.setOpaque(false);

		
		p3Points = new JTextArea();
		p3Points.setText("0");
		p3Points.setFont(new Font("Arial Black", Font.BOLD, 22));
		p3Points.setBounds(900, 80, 60, 40);
		p3Points.setEditable(false);
		p3Points.setOpaque(false);

		
		p1Points.setVisible(true);
		p2Points.setVisible(true);
		p3Points.setVisible(true); 
		
		pointsList.add(p1Points);
		pointsList.add(p2Points);
		pointsList.add(p3Points);
		
		for (int i = 0; i <pointsList.size(); i++) {
			pointsInInt.add(0);
		}
		
		add(p1Points);
		add(p2Points);
		add(p3Points);
		
	}
	/**
	 * There is another method for restore the previous values.
	 */
	private void updateJtextsSecond() {
		p1Points = new JTextArea();
		p1Points.setText(String.valueOf(pointsInInt.get(0)));
		p1Points.setFont(new Font("Arial Black", Font.BOLD, 22));
		p1Points.setBounds(80, 80, 60, 40);
		p1Points.setEditable(false);
		p1Points.setOpaque(false);
		
		p2Points = new JTextArea();
		p2Points.setText(String.valueOf(pointsInInt.get(1)));
		p2Points.setFont(new Font("Arial Black", Font.BOLD, 22));
		p2Points.setBounds(300, 500, 60, 40);
		p2Points.setEditable(false);
		p2Points.setOpaque(false);
	
		p3Points = new JTextArea();
		p3Points.setText(String.valueOf(pointsInInt.get(2)));
		p3Points.setFont(new Font("Arial Black", Font.BOLD, 22));
		p3Points.setBounds(900, 80, 60, 40);
		p3Points.setEditable(false);
		p3Points.setOpaque(false);
		
		p1Points.setVisible(true);
		p2Points.setVisible(true);
		p3Points.setVisible(true); 
		
		pointsList.add(p1Points);
		pointsList.add(p2Points);
		pointsList.add(p3Points);
		
		add(p1Points);
		add(p2Points);
		add(p3Points);
	}
	
	public static void updatePoints(List<Integer> points) {
		for (int i = 0; i < points.size(); i++) {
			pointsList.get(i).setText(String.valueOf(points.get(i)));
			pointsInInt.set(i, points.get(i));
		}
		GamePanel.getIntance().repaint();
	}
	public static GamePanel getIntance() {
		return instance;
	}
	//of pile
	public void setDestinationAndSource(int sorX, int sorY, int desX, int desY, Card card) {
		//setDestination();
		System.out.println();
		sourceX = sorX;//place of the pile
		sourceY = sorY;//place of the pile
		destinationX = desX;
		destinationY = desY;
		currentCard = card;
		if (main.game.getCurrentPlayer() == 2 || main.game.getCurrentPlayer() == 0) {
			isImageRotated = true;
		}
		else
			isImageRotated = false;
	}

	public void setDestinationThrow(int index) {
		int currentPlayer = main.game.getCurrentPlayer();
		setSourceThrow(index);
		destinationX = 470;
		destinationY = 100;
		currentCard = main.game.getPlayers().get(currentPlayer).getCards().get(index);
		isImageRotated = false;
	}
	
	private void setSourceThrow(int index) {	
		if (main.game.getCurrentPlayer() == 0) {
			sourceY = 120 + 45 * index;
			sourceX = 80;
		}
		else if (main.game.getCurrentPlayer() == 1) {
			sourceY = 400;
			sourceX = 385 + 45 * index;
		}
		else if (main.game.getCurrentPlayer() == 2) {
			sourceY = 300 - 45 * index;
			sourceX = 800;
		}
	}
	

	public void setCurrrentCard() {
		currentCard = null;
	}
	public boolean isNotInDestionation() {
		if (sourceX != destinationX || sourceY != destinationY)
		{
			if (sourceY < destinationY)
				sourceY += speed_Of_Card_Animation;
			else if (sourceY > destinationY)
				sourceY -= speed_Of_Card_Animation;
			if (sourceX < destinationX)
				sourceX += speed_Of_Card_Animation;
			else if (sourceX > destinationX)
				sourceX -= speed_Of_Card_Animation;
		}
		return (sourceX != destinationX || sourceY != destinationY);// works always but speed must be lower than 5 
	}

	public Card getCurrentCard() {
		return currentCard;
	}
	
}
