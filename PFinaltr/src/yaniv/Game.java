package yaniv;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;
import game.main;
import graphic.GamePanel;
import graphic.LoseScreen;
import graphic.WinningScreen;

public class Game  {
	private static final int speed_Of_card_Take_animatoin = 1;//Recommend between 1 to 5 
	private static final int Cards_In_Hand = 5;
	private static final int division = 30;
	private static final int lenghtOfCard = 120;
	private static final int final_Value_fix_Rectangle_Location = 35;
	private static final int Number_Of_Players = 3;
	private static final int amount_TO_Say_YANIV = 8;
	final int CardsInHand = 5;
	private List<Player> players;
	private OpenPile openPile;
	private Pile pile;
	private List<Rectangle> cardsLocationAsRectangle;
	private Rectangle pileLocationAsRectangle;
	private Rectangle openPileLocationAsRectangle;
	private int currentPlayer = 1;// every change will change + 3 and after method %3	
	GamePanel gamePanel;//change from jpanel
	private List<Card> cardsSequences = new LinkedList<Card>();
	private static List<Integer> points = new ArrayList<Integer>(); ;
	private static Boolean isRun = false;
	private int currentWinnerID = -1;
	private String PlayerName;
	private int gameCounter = 1;//count how many games the player play before he exit the game use for calculate his points for max points 
	private boolean isInDestionation;
	public Game() {
		this.players = new LinkedList<Player>();
		this.openPile = new OpenPile();
		this.cardsLocationAsRectangle = new LinkedList<Rectangle>();
		this.pile = new Pile();
		createPlayers(Number_Of_Players);// create the players and put them in the players array
		scatterCards();//scatter the cards
		openPile.addCard(pile.removeCard());// after scatter the cards pull one card from pile and put in open pile like opening card
		createRectangleLocations();
		createPileRectangleLocations();
		createOpenPileRectangleLocations();
		if (!isRun) {
			for(int i = 0; i < this.players.size(); i ++)
				points.add(0);
			isRun = true;
		}
		
		
	}
	

	public String getPlayerName() {
		return PlayerName;
	}

	public void setPlayerName(String playerName) {
		PlayerName = playerName;
	}	
	
	public int getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public OpenPile getOpenPile() {
		return openPile;
	}

	public void setOpenPile(OpenPile openPile) {
		this.openPile = openPile;
	}

	public Pile getPile() {
		return pile;
	}

	public void setPile(Pile pile) {
		this.pile = pile;
	}

	public int getGameCounter() {
		return gameCounter;
	}

	public void setGameCounter(int gameCounter) {
		this.gameCounter = gameCounter;
	}

	/**
	 * when someone wins that method called. It check who is the winner and play the yaniv sound of a winning
	 */
	public void yanivButton() {
		Player winner = Collections.min(players,new Comparator());
		// check if the count works well. the player with the lowest sum is the winner
		this.currentWinnerID = winner.getID();
		try{
			File  url = new File("sounds/yaniv.wav");
			Clip clip = AudioSystem.getClip();
			AudioInputStream ais = AudioSystem.getAudioInputStream(url);
			clip.open(ais);
			clip.start();
		}catch(LineUnavailableException | UnsupportedAudioFileException | IOException murle){
			System.out.println(murle);
			System.out.println("exeption");
		}
		gameFinish();
		return;
		
	}
	
	/**
	 * get number of players and create players (according to numberOfPlayers)
	 * @param numberOfPlayers
	 */
	private void createPlayers(int numberOfPlayers){
		for(int i = 0; i < numberOfPlayers; i ++) {
			players.add(new Player(i));
		}
		players.get(0).setName("computer 0");
		players.get(1).setName("you");
		players.get(2).setName("computer 2");
	}
	
	/**
	 * scatter the cards to the players in players array. give every player 5 cards
	 */
	public void scatterCards(){
		for(int i = 0; i < this.players.size() ; i++){
			for(int j = 0; j < Cards_In_Hand; j++)
			players.get(i).addCard(pile.removeCard());
		}
	}



	/**
	 * Responsible to create the cardsLocationAsRectangle for player 1 (the user),
	 *  uses for recognise which card has pressed
	 */
	
	public void createRectangleLocations() {
		/**
		 * run according to the cards size and create the rectangle location list
		 */
		List<Rectangle> cardsLocationAsRectangleTemp = new LinkedList<Rectangle>();
		int x = players.get(1).getX();
		for(int i = 0; i < players.get(1).getCards().size() - 1; i++, x += 45){
			Rectangle location = new Rectangle(x, players.get(1).getY() - final_Value_fix_Rectangle_Location, 45, 120);
			cardsLocationAsRectangleTemp.add(location);
		}
		Rectangle location = new Rectangle(x, players.get(1).getY() - final_Value_fix_Rectangle_Location, 70, 120);
		cardsLocationAsRectangleTemp.add(location);
		this.cardsLocationAsRectangle = cardsLocationAsRectangleTemp;
	}
	
	/**
	 * Responsible to create the PileLocationAsRectangle for the pile
	 * use to recognise if pile pressed
	 */
	public void createPileRectangleLocations() {
		// 11 is the offset from the first card that draw to the last one. 70 is the    and 120 is the     
		Rectangle locationPile = new Rectangle(Pile.getxStart(), Pile.getyStart(), 70 + 11, 120 + 11);
		this.pileLocationAsRectangle = locationPile;
	}
	 
	/**
	 * Responsible to create the OpenPileLocationAsRectangle for the pile
	 * use to recognize if pile pressed
	 */
	public void createOpenPileRectangleLocations() {
		Rectangle locationPile = new Rectangle(openPile.getxStart(), openPile.getyStart(), 70 + 11, 120);
		this.openPileLocationAsRectangle = locationPile;
	}
	
	/**
	 * a part of the AI. this method is responsible on the turns of the computer
	 * the method computerPlays is the AI itself 
	 */
	private void computersPlay() {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 2; i++){
					if (currentPlayer == 0 || currentPlayer == 2){
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if(players.get(0).getGameOver()) {
							continue;//change
						}
						computerPlays();
						gamePanel.repaint();
						currentPlayer++;
						currentPlayer = currentPlayer % 3;
					}
				}
			}
		});
		thread.start();
	}
	
	/**
	 * responsible to clear all the lists that involved in graphic
	 */
	public void removeCardsFromHand(){
		for(int i = 0; i < players.get(1).getTempCardSerial().size(); i++){
			openPile.addCard(players.get(1).removeCard(players.get(1).getTempCardSerial().get(i)));
			createRectangleLocations();
		}
		/**
		 * start sound of pull a card
		 */
		try{
			File  url = new File("sounds/draw.wav");
			Clip clip = AudioSystem.getClip();
			AudioInputStream ais = AudioSystem.getAudioInputStream(url);
			clip.open(ais);
			clip.start();
		}catch(LineUnavailableException | UnsupportedAudioFileException | IOException murle){
			System.out.println(murle);
		}
		
		players.get(1).getTempCardSerial().clear();
		players.get(1).getTempCardIndex().clear();
		players.get(1).getTempCards().clear();
		players.get(1).getTempSequenceCards().clear();
		
	}
	
public void cardClicked3(int x, int y) {
		
		int cardClickedNumber = -1;
		for(int i = 0; i < cardsLocationAsRectangle.size() && cardClickedNumber < 0; i++){
			if(x > cardsLocationAsRectangle.get(i).getX() &&  x < (cardsLocationAsRectangle.get(i).getX() + cardsLocationAsRectangle.get(i).getWidth()) 
					&& y > cardsLocationAsRectangle.get(i).getY() + division && y < cardsLocationAsRectangle.get(i).getY() + lenghtOfCard + division ){
			if(players.get(1).isIncludeIntempCardIndex(i)) {
					players.get(1).chooseDOWN(players.get(1).getCards().get(i), 1,i);
					cardClickedNumber = players.get(1).getCards().get(i).getSerial();
				}else {
					players.get(1).chooseUP(players.get(1).getCards().get(i), 1,i);
				}
			}
		}
	}

	/**
	 * call the correct method according to y 
	 * @param x
	 * @param y
	 * @param p 
	 */
	public void whatClicked(int x, int y, JPanel p) {
		players.get(1).findAllGrowingSequenceNew();
		if(!players.get(0).getGameOver()) {
			gamePanel = (GamePanel) p;
			if(currentPlayer == 1){
				if (y > 400){
					cardClicked3(x, y);
				}else if (y >= 250 && !players.get(1).getTempCardSerial().isEmpty()){
					openPileClickedNew(x, y);	
				}else if(!players.get(1).getTempCardSerial().isEmpty()){
					pileClickedNew(x, y);
				}
			}
			gamePanel.repaint();
		}
		
			
	}
	
	private void pileFinished() {
		Card tempCard;
		tempCard = this.openPile.getOpenPile().pop();
		while(!this.openPile.getOpenPile().isEmpty()){
			this.pile.addCard(this.openPile.removeCard());
		}
		this.openPile.getOpenPile().push(tempCard);	
		this.pile.shuffelPile();
	}
	
	/**
	 * clear all the lists that saved the selected cards, means that all the cards turn down.
	 * @param player
	 */
	private void turnDownTheCards(Player player) {
		players.get(1).getTempCardSerial().clear();
		players.get(1).getTempCardIndex().clear();
		players.get(1).getTempCards().clear();
		players.get(1).getTempSequenceCards().clear();
	}
	public void computerPlays(){
		/**
		 * first check if can call yaniv. If does - call yanivButton and end the round
		 * if not - 
		 */
		if(this.players.get(currentPlayer).sumCards() <= amount_TO_Say_YANIV) {
			yanivButton();
			return; // do not keep the function After call to yanivButton.
		}
		this.cardsSequences = players.get(currentPlayer).getLongestSequence();
			/**
			 * if the card in the open pile appear in the cards in hand - take it
			 * need to check if the there is a sequence with the same number - if in hand there is 2,4,4,5,5 
			 * the computer will prefer to throw the 5,5 but if he take the card from the open he will have 
			 * 5,5,5 which is better.will call a special method that create the cardSequence again but that 
			 * time the sequence to throw will be the 4,4.
			 */
			if(players.get(currentPlayer).isCompleteSequence(openPile.getOpenPile().peek())) {
				this.cardsSequences = players.get(currentPlayer).takeTheSameCardInOpenPile(openPile.getOpenPile().peek());
				moveCardFromOpenPile();
				Card cardToAdd = openPile.getOpenPile().pop();
				for (int i = 0; i < this.cardsSequences.size(); i++) {
					openPile.addCard(this.cardsSequences.get(i));
					players.get(currentPlayer).removeCard(this.cardsSequences.get(i).getSerial());
				}
				players.get(currentPlayer).addCard(cardToAdd);
			} else if (openPile.getOpenPile().peek().getNumber() <= 5 && openPile.getOpenPile().peek().getNumber() < 
					players.get(currentPlayer).getCards().get(0).getNumber() ||
					players.get(currentPlayer).findGradestCard().getNumber() >=  openPile.getOpenPile().peek().getNumber()
					&& openPile.getOpenPile().peek().getNumber() <= 5){
					moveCardFromOpenPile();
					Card cardToAdd = openPile.getOpenPile().pop();
					for (int i = 0; i < this.cardsSequences.size(); i++) {
						openPile.addCard(this.cardsSequences.get(i));
						players.get(currentPlayer).removeCard(this.cardsSequences.get(i).getSerial());
				}
				players.get(currentPlayer).addCard(cardToAdd);
			}else{
				for (int i = 0; i < this.cardsSequences.size(); i++) {
					openPile.addCard(this.cardsSequences.get(i));
					players.get(currentPlayer).removeCard(this.cardsSequences.get(i).getSerial());
				}
				moveCardFromStock();
				players.get(currentPlayer).addCard(pile.removeCard());
			}
			
		
		// the sound will be after all the checks 
		try{
			File  url = new File("sounds/draw.wav");
			Clip clip = AudioSystem.getClip();
			AudioInputStream ais = AudioSystem.getAudioInputStream(url);
			clip.open(ais);
			clip.start();
		}catch(LineUnavailableException | UnsupportedAudioFileException | IOException murle){
			System.out.println(murle);
		}
		if(this.pile.getPileCards().isEmpty()){
			pileFinished();// fill the pile again 
		}
	}
	
	// methods for computer:
	/**
	 * After yaniv button called - come here, update the points of each player for the current play 
	 * show the cards of the players for 4 seconds and move the player to the correct screen.
	 */
	public void gameFinish() {
		for(int i = 0; i < players.size(); i++) {
			players.get(i).setGameOver(true);
		}
		updatePoints();
		gamePanel.repaint();
		//if someone wanted to throw a card, and leave it up but decided to call yaniv 
		for (int i = 0; i < players.size(); i++) {
			turnDownTheCards(players.get(i));
		}
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				if (currentWinnerID != 1) {
					main.frame.setSize(1280,720);
					main.frame.setContentPane(new LoseScreen(players.get(currentWinnerID).getName(),points.get(currentWinnerID)));
					main.frame.validate();
					main.frame.repaint();
					PlayerName = "computer";
				} else{
					main.frame.setSize(1280,720);
					main.frame.setContentPane(new WinningScreen(players.get(currentWinnerID).getName(),points.get(currentWinnerID)));
					main.frame.validate();
					main.frame.repaint();
				}
				
			}
		}, 4000);
		
		//update the new points
	}
	
	/**
	 * update the points. the winner does not get points. 
	 */
	public void updatePoints() {
		for(int i = 0; i < points.size(); i++) {
			Integer temp = points.get(i);
				temp += this.players.get(i).getPointsInHand();
			points.set(i, temp);// set the new points
			GamePanel.updatePoints(points);
		}
	}
	
	public void newGame() {
		for(int i = 0; i < this.players.size(); i++) {
			while (!this.players.get(i).getCards().isEmpty()) {
				this.players.get(i).removeCard(this.players.get(i).getCards().get(0).getSerial());
			}
		}
		for( int i = 0; i < players.size(); i++) {
			players.get(i).setGameOver(false);
		}
		this.currentWinnerID = -1;
		this.openPile = new OpenPile();
		this.pile = new Pile();
		scatterCards();//scatter the cards
		openPile.addCard(pile.removeCard());// after scatter the cards pull one card from pile and put in open pile like opening card
		createRectangleLocations();
		createPileRectangleLocations();
		createOpenPileRectangleLocations();
		this.currentPlayer = 1; 
		this.gameCounter ++;
		refreshCurrentRectanglePlayer();
		
	}
	private void moveCardFromStock() {
		gamePanel.setSeeDetails(false);
        gamePanel.setDestinationAndSource(Pile.getxStart(), Pile.getyStart(), players.get(currentPlayer).getDestenationX(), players.get(currentPlayer).getDestenationY(), pile.getTop());
        isInDestionation = gamePanel.isNotInDestionation();
        while (isInDestionation) {
        	isInDestionation = gamePanel.isNotInDestionation();
        	gamePanel.repaint();
           try {
                Thread.sleep(speed_Of_card_Take_animatoin);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        gamePanel.setCurrrentCard();
    }
	private void moveCardFromOpenPile() {
		gamePanel.setSeeDetails(true);
        gamePanel.setDestinationAndSource(openPile.getxStart(), openPile.getyStart(),
        		players.get(currentPlayer).getDestenationX(), players.get(currentPlayer).getDestenationY(), openPile.getOpenPile().peek());
        isInDestionation = gamePanel.isNotInDestionation();
        while (isInDestionation) {
        	isInDestionation = gamePanel.isNotInDestionation();
        	gamePanel.repaint();
           try {
                Thread.sleep(speed_Of_card_Take_animatoin);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        gamePanel.setCurrrentCard();
    }
	
	private void refreshCurrentRectanglePlayer() {
		for (int i = 0; i < players.size(); i++) {
			Rectangle tempPlayerRectangle = players.get(i).getCurrentPlayerRectangle();
			if (i == 0) {;
				tempPlayerRectangle.setBounds(tempPlayerRectangle.x, tempPlayerRectangle.y, tempPlayerRectangle.width, 45 * players.get(i).getCards().size() + 40);
			} else if (i == 1){
				tempPlayerRectangle.setBounds(tempPlayerRectangle.x, tempPlayerRectangle.y, 45 * players.get(i).getCards().size() + 35, tempPlayerRectangle.height);
			} else { // i == 2
				tempPlayerRectangle.setBounds(tempPlayerRectangle.x, 340 - 45 * players.get(i).getCards().size(), tempPlayerRectangle.width, 45 * players.get(i).getCards().size() + 40);
			}
			players.get(i).setCurrentPlayerRectangle(tempPlayerRectangle);
		}
	}
	
	private void removeCardsFromHandToPile() {
		removeCardsFromHand();
		gamePanel.repaint();
		refreshCurrentRectanglePlayer();
		currentPlayer ++;
		currentPlayer = currentPlayer % 3;
		computersPlay();
		
	} 
	public void openPileClickedNew(int x, int y) {
		if(x > openPileLocationAsRectangle.getX() &&  x < (openPileLocationAsRectangle.getX() + openPileLocationAsRectangle.width ) 
				&& y > openPileLocationAsRectangle.getY() && y < (openPileLocationAsRectangle.getY() + openPileLocationAsRectangle.height )){
			createRectangleLocations();
			if(players.get(1).isCanBeThrowen(players.get(1).getTempSequenceCards())){
				moveCardFromOpenPile();
				this.players.get(1).addCard(this.openPile.removeCard());
				 removeCardsFromHandToPile();
			}else{
				turnDownTheCards(this.players.get(1));
			}
		}
	}
	
	/**
	 * remove the card from the pile and add it to the player
	 * @param x of click
	 * @param y of click
	 */
	public void pileClickedNew(int x , int y) {
		if(x > pileLocationAsRectangle.getX() &&  x < (pileLocationAsRectangle.getX() + pileLocationAsRectangle.width) 
				&& y > pileLocationAsRectangle.getY() && y < (pileLocationAsRectangle.getY() + pileLocationAsRectangle.height)){
			if(this.pile.getPileCards().isEmpty()){
				pileFinished();// fill the pile again 
			}
			createRectangleLocations();
			if(players.get(1).isCanBeThrowen(players.get(1).getTempSequenceCards())){
				moveCardFromStock();// first draw the animation and then remove from pile. if first remove card
				//from pile and only then draw the animation, the card is going to be drawn is not the correct one
				this.players.get(1).addCard(this.pile.removeCard());
			     removeCardsFromHandToPile();
				
			}else{
				turnDownTheCards(this.players.get(1));
			}
			if(this.pile.getPileCards().isEmpty()){
				pileFinished();// fill the pile again 
			}
			
		}
	}
}
