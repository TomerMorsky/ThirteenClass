package yaniv;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.imageio.ImageIO;
import graphic.Drawable;

public class Player implements Drawable{
	private List<Card> cards;
	private Boolean isYaniv;
	private Rectangle currentPlayerRectangle;
	private int ID;
	private List<Integer> tempCardIndex; // list of card that chosen - will draw differently
	private List<Card> tempCards;
	private List<Integer> tempCardSerial;// list of the serial of the cards that chosen
	private String name;
	private int x = 120, y;//place of the draw cards
	private Boolean gameOver = false;
	private int destenationX;
	private int destenationY;
	private List<Card> tempSequenceCards;
	
	/**
	 * create the player. give him 5 mixed cards and set the YANIV call to false.
	 */
	public Player(int id) {
		this.ID = id;
		this.cards = new LinkedList<Card>();
		this.isYaniv = false;
		tempSequenceCards = new ArrayList<Card>();
		tempCardIndex = new ArrayList<Integer>();
		tempCardSerial = new ArrayList<Integer>();
		tempCards = new ArrayList<Card>();
		currentPlayerRectangle = new Rectangle();
		if(id == 0){
			x = 80;
			y = 120;
			destenationX = 80;
			destenationY = 210;
			
			currentPlayerRectangle.setBounds(75, 110, 130, 270);// static rectangle,
			//appear when the current player playing
		}
		if(id == 1){
			x = 385;
			y =400;
			destenationX = this.x + (45 * 2);//to the middle of the cards
			destenationY = 400;
			currentPlayerRectangle.setBounds(380, 395, 260, 130);
		}
			
		if(id == 2){
			y = 300;
			x = 800;
			destenationX = 800;
			destenationY = 210;
			currentPlayerRectangle.setBounds(795, 110, 130, 270);
		}
			
			 
	}
	
		
	public List<Card> getTempSequenceCards() {
		return tempSequenceCards;
	}


	public void setTempSequenceCards(List<Card> tempSequenceCards) {
		this.tempSequenceCards = tempSequenceCards;
	}


	public int getDestenationX() {
		return destenationX;
	}


	public void setDestenationX(int destenationX) {
		this.destenationX = destenationX;
	}


	public int getDestenationY() {
		return destenationY;
	}


	public void setDestenationY(int destenationY) {
		this.destenationY = destenationY;
	}


	public Boolean getGameOver() {
		return gameOver;
	}

	public void setGameOver(Boolean gameOver) {
		this.gameOver = gameOver;
	}


	public Rectangle getCurrentPlayerRectangle() {
		return currentPlayerRectangle;
	}

	public void setCurrentPlayerRectangle(Rectangle currentPlayerRectangle) {
		this.currentPlayerRectangle = currentPlayerRectangle;
	}

	public List<Card> getTempCards() {
		return tempCards;
	}

	public void setTempCards(List<Card> tempCards) {
		this.tempCards = tempCards;
	}

	public List<Integer> getTempCardSerial() {
		return tempCardSerial;
	}

	public void setTempCardSerial(List<Integer> tempCardSerial) {
		this.tempCardSerial = tempCardSerial;
	}

	public List<Integer> getTempCardIndex() {
		return tempCardIndex;
	}

	public void setTempCardIndex(List<Integer> tempCardIndex) {
		this.tempCardIndex = tempCardIndex;
	}

	public List<Card> getCards() {
		return cards;
	}
	
	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
	
	public Boolean getIsYaniv() {
		return isYaniv;
	}
	
	public void setIsYaniv(Boolean isYaniv) {
		this.isYaniv = isYaniv;
	}
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	/**
	 * an event will be on that property. it will listen when it changes to true, 
	 * then it will check other players cards and announce who is the winner
	 */
	public void YanivCall() {
		this.isYaniv = true;
	}
	
	/**
	 * 
	 * @return the sum of the cards of the player
	 */
	public int sumCards() {
		int sum = 0;
		for(int i = 0; i < cards.size(); i++){
			if(this.cards.get(i).getNumber() > 10){
				sum += 10;
			}else{
				sum += this.cards.get(i).getNumber();
			}
		}
		return sum;
	}
	
	public void addCard(Card card){
		this.cards.add(card);
	}
	/**
	 * remove a card from the card list according to the serial of the card
	 * if serial not found do nothing
	 * @param serialCardNumber
	 */
	public Card removeCard(int serialCardNumber) {
		for(int i = 0; i < this.cards.size(); i++){
			if(this.cards.get(i).getSerial() == serialCardNumber)
				return this.cards.remove(i);
		}
		return null;
	}
	
	
	
	
	
	 /**
     * Converts a given BufferedImage into an Image
     * 
     * @param bimage The BufferedImage to be converted
     * @return The converted Image
     */
    public static Image toImage(BufferedImage bimage){
        // Casting is enough to convert from BufferedImage to Image
        Image img = (Image) bimage;
        return img;
    }
	/**
     * Creates an empty image with transparency
     * 
     * @param width The width of required image
     * @param height The height of required image
     * @return The created image
     */
    public static Image getEmptyImage(int width, int height){
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        return toImage(img);
    }
    /**
     * Converts a given Image into a BufferedImage
     * 
     * @param img The Image to be converted
     * @return The converted BufferedImage
     */
    public static BufferedImage toBufferedImage(Image img){
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }
        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();
        // Return the buffered image
        return bimage;
    }
    /**
     * Rotates an image. Actually rotates a new copy of the image.
     * 
     * @param img The image to be rotated
     * @param angle The angle in degrees
     * @return The rotated image
     */
	 public Image rotate(Image img, double angle){
	        double sin = Math.abs(Math.sin(Math.toRadians(angle))), cos = Math.abs(Math.cos(Math.toRadians(angle)));
	        int w = img.getWidth(null), h = img.getHeight(null);
	        int neww = (int) Math.floor(w * cos + h * sin), newh = (int) Math.floor(h
	                * cos + w * sin);
	        BufferedImage bimg = toBufferedImage(getEmptyImage(neww, newh));
	        Graphics2D g = bimg.createGraphics();
	        g.translate((neww - w) / 2, (newh - h) / 2);
	        g.rotate(Math.toRadians(angle), w / 2, h / 2);
	        g.drawRenderedImage(toBufferedImage(img), null);
	        g.dispose();
	        return toImage(bimg);
	    }
	
	 private int findIndexInSerialCardIndex(int serialNumberCard){
		int indexOfCardsInList;
		for(indexOfCardsInList = 0; indexOfCardsInList < tempCardSerial.size(); indexOfCardsInList++){
			if(tempCardSerial.get(indexOfCardsInList) == serialNumberCard)
				return indexOfCardsInList;
		}
		return -1;
	}
	 private int findIndexIntempCardIndex(int cardHandIndex) {
		 int indexOfCardsInList;
		for(indexOfCardsInList = 0; indexOfCardsInList < tempCardIndex.size(); indexOfCardsInList++){
			if(tempCardIndex.get(indexOfCardsInList) == cardHandIndex)
				return indexOfCardsInList;
		}
		return -1;
	}
	 
	 public int findIndexInCardsList(Card card, List<Card> list) {
		 int indexOfCardsInList;
		for(indexOfCardsInList = 0; indexOfCardsInList < list.size(); indexOfCardsInList++){
			if(list.get(indexOfCardsInList).getSerial() == card.getSerial())
				return indexOfCardsInList;
		}
		return -1;
	}
	 /**
	  * check if the index that given appear in the list if it does return true
	  * @param index
	  * @return
	  */
	 public boolean isIncludeIntempCardIndex(int index){
		 for(int i = 0; i < tempCardIndex.size(); i++){
			 if(tempCardIndex.get(i) == index)
				 return true;
		 }
		 return false;
	 }
	 /**
	  * check if the card that send is appear in hand
	  * @param card
	  * @return true is does or false if not 
	  */
	 public boolean isIncludeInHandCards(Card card){
		 for(int i = 0; i < this.cards.size(); i++){
			 if(cards.get(i).getNumber() == card.getNumber())
				 return true;
		 }
		 return false;
	 }
	 
	
	/**
	 * draw all the card in cards list
	 */
	@Override
	public void draw(Graphics g) {
		int xStart = this.x;
		int yStart = this.y;
		Image temp, backCard = null;
		try {
			backCard = ImageIO.read(new File("pictures/gray_back.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		/**
		 * if != 1 so the players at the sides of the screen, else draw the cards of the player
		 */
		SortHand();//sort cards before drawing
		if (this.ID == 2){
			for(int i = 0; i < this.cards.size(); i++, yStart -= 45){
				if (this.gameOver) {
					temp = rotate(cards.get(i).getImage(), 90);//work - open cards
				}else {
					temp = rotate(backCard, 90);
				}
				g.drawImage(temp, xStart, yStart, 120, 70, null);
				
			}
			
		}else if(this.ID == 0){
			for(int i = 0; i < this.cards.size(); i++, yStart += 45){
				if (this.gameOver) {
					temp = rotate(cards.get(i).getImage(), 90);//work - open cards
				}else {
					temp = rotate(backCard, 90);
				}
				g.drawImage(temp, xStart, yStart, 120, 70, null);
			}
		}
		else{
			for(int i = 0; i < this.cards.size(); i++, xStart += 45){
				/**
				 * 
				 */
				if(isIncludeIntempCardIndex(i)){
					g.drawImage(cards.get(i).getImage(), xStart, 380, 70, 120, null);
				}else
				g.drawImage(cards.get(i).getImage(), xStart, yStart, 70, 120, null);
			}
			
		}
	}	
	/**
	 * method that sort the cards in hand according to serial
	 */
	private void SortHand(){
		Collections.sort(cards, new SortCards());
	}
	
	private void SortCardList(List<Card> tempList){
		Collections.sort(tempList, new SortCards());
	}
	//return the highest card in hand
	public Card findGradestCard() {
		Card maxCard = Collections.max(this.cards,new ComparatorCards());
		return maxCard;
	}

	//works good
	public List<List<Card>> findAllGrowingSequenceNew () {
		List<Card> grow = new LinkedList<Card>();
		int tempIndex = -1;
		List<List<Card>> cardsSequences = new LinkedList<List<Card>>();
		grow.add(this.cards.get(0));
		for (int i = 0; i < this.cards.size() - 1; i++) {
			if ((tempIndex = findNextCardSequence(this.cards, i + 1, cards.get(i))) != -1) {
				grow.add(cards.get(tempIndex));
			} else {
				cardsSequences.add(grow);
				grow = new LinkedList<Card>();
				grow.add(cards.get(i + 1));
			}
		}
		cardsSequences.add(grow);
		return cardsSequences;
	}
	
	/**
	 * only for same numbers
	 * @param HandCards
	 * @return a list of all the sequences in hand (only of same number). every list in the list of lists will include a sequence of cards
	 */
	public List<List<Card>> findAllSequence (List<Card> HandCards) {
		int checkNumber;
		List<List<Card>> cardsSequences = new LinkedList<List<Card>>();
		List<Card> cardsLowSequence = new LinkedList<Card>();
		cardsLowSequence.add(HandCards.get(0));
		for (int i = 0; i < HandCards.size() - 1 ; i++){
			checkNumber = HandCards.get(i).getNumber();
			if (HandCards.get(i + 1).getNumber() == checkNumber) {
				cardsLowSequence.add(HandCards.get(i + 1));
			} else {
				cardsSequences.add(cardsLowSequence);
				cardsLowSequence = new LinkedList<Card>();
				cardsLowSequence.add(HandCards.get(i + 1));
			}
		}
		cardsSequences.add(cardsLowSequence);// needs to add the last one because not added
		return cardsSequences;
		
	}
	/**
	 * 
	 * @return a list of cards which is the longest sequence
	 */
	public List<Card> getLongestSequence() {
		List<List<Card>> cardsSequences = findAllSequence(this.cards);//if there is 2 sequences with the same length
		List<List<Card>> cardsGrowingSequences = findAllGrowingSequenceNew();
		//it will take the one with the bigger number
		List<Card> longestSequence = Collections.max(cardsSequences,new CompareSequence());
		cardsGrowingSequences.add(longestSequence);// add to the list of list 
		//the biggest sum list from the regular sequence
		longestSequence = Collections.max(cardsGrowingSequences,new CompareSequence());//then call to collections.max
		//to get again the biggest sum now.
		if (longestSequence.size() < 2) {
			longestSequence.remove(0);
			//before choosing the biggest card, need to check if the card in the open pile is better to take
			longestSequence.add(findGradestCard());// if the biggest sequence is only 1 card 
			//so prefer to throw the biggest card
		}
		/*
		 * if there is a sequence of 2 jokers they will not be thrown. Instead, send a new cards in hand without the 
		 * jokers. if there is only one joker he won't be thrown!If there is only 2 joker say yaniv!
		 */
		
		if (findGradestCard().getNumber() > sumCardsList(longestSequence)) {
			// if the biggest card amount is bigger than the sequence amount, prefer to throw the biggest card
			longestSequence.clear();
			longestSequence.add(findGradestCard());
		}
		return longestSequence;
	}
	
	public List<Card> takeTheSameCardInOpenPile(Card card) {
		List<Card> longestSequence = this.getLongestSequence();
		/**
		 * if the sequence to throw is the same number like in the pile, prefer to save the sequence,
		 * throw the second card prefers to throw and take the card from the open, at the following course will throw
		 * the new sequence.
		 */
		if(longestSequence.get(0).getNumber() == card.getNumber() && this.cards.size() > 1) {
			List<Card> tempCardsInHand = new LinkedList<Card>();
			for(int i = 0; i < this.cards.size(); i++) {
				tempCardsInHand.add(this.cards.get(i));
			}
			return createNewCardsSequencesWitoutGetList(tempCardsInHand, longestSequence);
			/** work but not if there is joker in hand
			for(int i = 0; i < this.cards.size() - longestSequence.size(); i ++) {
				tempCardsInHand.add(this.cards.get(i));
			}**/
		}
		 return longestSequence;
	}
	
	public List<Card> createNewCardsSequencesWitoutGetList (List<Card> cardsSequences,List<Card> CardsToRemoveFromSequence) {
		Collections.sort(CardsToRemoveFromSequence, new SortCards());
		List<Card> tempCardsInHand = new LinkedList<Card>();
		for (int i = 0; i < cardsSequences.size(); i++) {
			tempCardsInHand.add(cardsSequences.get(i));
		}
		tempCardsInHand.removeAll(CardsToRemoveFromSequence);
		tempCardsInHand = Collections.max(findAllSequence(tempCardsInHand), new CompareSequence()); 
		return tempCardsInHand;
	}
	
	/**
	 * calculate the points in hand in a current time.
	 * @return the sum of the points in hand
	 */
	public int getPointsInHand() {
		int sum = 0;
		for(int i = 0; i < this.cards.size(); i++) {
			if (this.cards.get(i).getNumber() > 10) {
				sum += 10;
			} else {
				sum += this.cards.get(i).getNumber();
			}
		}
		return sum;
	}
	
	private int sumCardsList(List<Card> list) {
		int sum = 0;
		for(int i = 0; i < list.size(); i++) {
			sum += list.get(i).getNumber();
		}
		return sum;
	}
	
	
	/**
	 * check if regular sequence can be throw
	 * @param list
	 * @return
	 */
	public boolean isCanBeThrowen(List<Card> list) {
		if(!isJokerInList(list)){
			SortCardList(list);
			if(list.size() <= 1){
				return true;
			} else if (list.get(0).getNumber() == list.get(1).getNumber()) {
				for(int i = 1; i < list.size(); i++) {
					if(list.get(i).getNumber() != list.get(i - 1).getNumber()) {
						return false;
					}
				}
				return true;
			} else if (list.get(1).getNumber() == (list.get(0).getNumber() + 1) && (list.get(1).getShape() == list.get(0).getShape()) && 
					list.size() > 2) {
				for (int i = 1; i < list.size(); i ++) {
					if (list.get(i).getNumber() != list.get(i - 1).getNumber() + 1 || list.get(i).getShape() != list.get(i - 1).getShape()) {
						return false;
					}
				}
				return true;
			} else {
				return false;
			}
		} else {
			if (isCanBeThrowen(getListWithoutJoker(list))) {
				return true;
				// else will be onle for sequence that need to be complete by joker (2 4 5 | 2 3)
			} else {
				SortCardList(list);
				int countJoker = countJoker(list); 
				List<Card> tmpList = getListWithoutJoker(list);
				if (tmpList.size() >= 2) {
					for (int i = 0; i < tmpList.size() - 1; i++) {
						if(tmpList.get(i).getNumber() == tmpList.get(i + 1).getNumber() - 1 
								&& tmpList.get(i).getShape() == tmpList.get(i + 1).getShape()){
							continue;
						} else if (tmpList.get(i).getNumber() == tmpList.get(i + 1).getNumber() - 2 
								&& tmpList.get(i).getShape() == tmpList.get(i + 1).getShape() && countJoker >= 1){
							countJoker --;
							continue;
						}else if (tmpList.get(i).getNumber() == tmpList.get(i + 1).getNumber() - 2 
								&& tmpList.get(i).getShape() == tmpList.get(i + 1).getShape() && countJoker >= 2) {
							countJoker --;
							continue;
						}else {
							return false;
						}
					}
					return true;
				}
				
			}
		}
		return false;
	}
	
	public boolean isJokerInList(List<Card> list) {
		for(int i = 0; i < list.size();i++) {
			if(list.get(i).getNumber() == 0) {
				return true;
			}
		}
		return false;
	}
	
	 public void chooseUP(Card card, int id, int index) {
			 tempSequenceCards.add(card);
			 tempCards.add(card);
			 tempCardIndex.add(index);
			 tempCardSerial.add(cards.get(index).getSerial());// adds the serial instead of the index
	}
	 public void chooseDOWN(Card card, int id, int index) {
			 if(findIndexIntempCardIndex(index) != -1 && findIndexInSerialCardIndex(card.getSerial()) != -1){
				tempCardIndex.remove(findIndexIntempCardIndex(index));	
				tempCardSerial.remove(findIndexInSerialCardIndex(card.getSerial()));
				tempCards.remove(findIndexInCardsList(card,tempCards));
				tempSequenceCards.remove(findIndexInCardsList(card,tempSequenceCards));
			 }else{
				 System.out.println("not found");
			 }
		}
	 private List<Card> getListWithoutJoker(List<Card> list) {
		 List<Card> noJoker = new LinkedList<Card>();
		 for (int i = 0; i < list.size(); i ++) {
			 if (list.get(i).getNumber() != 0) {
				 noJoker.add(list.get(i));
			 }
		 }
		 return noJoker;
		 
	 }
	 /**
	  * 
	  * @return number of joker in list
	  */
	 private int countJoker(List<Card> list) {
		int jokerCount = 0;
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getNumber() == 0) {
				jokerCount ++;
			}
		}
		return jokerCount;
	}
	 /**
	  * 
	  * @param list
	  * @param index from that index the method will look for the next card in the sequence
	  * @return the index of the next card in sequence. If there is not such a card, the method return -1.
	  */
	 private int findNextCardSequence(List <Card> list, int index, Card temp) {
		 for (int i = index; i < list.size(); i++) {
			 if(list.get(i).getNumber() - 1 == temp.getNumber() && list.get(i).getShape() == temp.getShape()) {
				 return i;
			 }
		 }
		 return -1;
	 }
	 public boolean isCompleteSequence(Card card) {
		// boolean isComplete = false;
		 if (isIncludeInHandCards(card)) {
			 return true;
		 }
		 List<List<Card>> growingSequence = findAllGrowingSequenceNew();
		 for (int i = 0; i < growingSequence.size(); i++) {
			 if (growingSequence.get(i).size() > 1) {
				 if (growingSequence.get(i).get(0).getNumber() == card.getNumber() + 1 && 
						 growingSequence.get(i).get(0).getShape() == card.getShape() || 
						 growingSequence.get(i).get(growingSequence.get(i).size() - 1).getNumber() == card.getNumber() - 1
						 && growingSequence.get(i).get(growingSequence.get(i).size() - 1).getShape() == card.getShape() ){
					 return true;
				 }
			 } else {
				 return false;
			 }
		 }
		 return false;
	 }
	
}
