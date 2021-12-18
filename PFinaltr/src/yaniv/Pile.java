package yaniv;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.imageio.ImageIO;

import graphic.Drawable;

public class Pile implements Drawable {
	/**
	 * xStart - the x to start draw the open pile
	 * yStart - the y to start draw the open pile
	 * icon - the picture to draw
	 * pile cards - the pile itself
	 * homePile - the packet of the cards
	 */
	private static final int X_start = 470;
	private static final int Y_start = 100;
	private static  Image icon;
	private Queue<Card> pileCards;
	private HomePile homePile;
	
	/**
	 * build he pile, shuffle the cards 
	 */
	public Pile() {
		this.pileCards = new LinkedList<Card>();
		homePile = new HomePile();
		firstShuffelPile();
		createImageOfPile();
	}
	
	public static int getxStart() {
		return X_start;
	}

	public static int getyStart() {
		return Y_start;
	}

	public void addCard(Card card){
		this.pileCards.add(card);
	}
	public Queue<Card> getPileCards() {
		return pileCards;
	}
	
	public Card getTop() {
		return this.pileCards.peek();
	}
	/**
	 * 
	 * @return the top of the queue and remove it from the queue
	 */
	public Card removeCard(){
		return this.pileCards.remove();
	}
	/**
	 * does not destroy the list, copy to another list and shuffle the copied list, then copy the shuffled list to the pileCards
	 * now the pileCards include a queue of shuffled cards 
	 * Shuffle all the cards and put them in the pileCards
	 * @param pileCards include the  cards 
	 */
	public void firstShuffelPile(){
		List<Card> tempCards = new LinkedList<Card>();
		for(int i = 0; i < this.homePile.getHomeCards().size(); i++){
			tempCards.add(this.homePile.getHomeCards().get(i));
		}
		Collections.shuffle(tempCards);
		for(int i = 0; i < tempCards.size(); i++){
			this.pileCards.add(tempCards.get(i));
		}
		
	}
	
	/**
	 * the same as firstShuffelPile but is shuffle the pile itself and not a copy of the pile
	 */
	public void shuffelPile() {
		List<Card> tempCards = new LinkedList<Card>();
		for(int i = 0; i < this.pileCards.size(); i++){
			tempCards.add(this.pileCards.remove());
		}
		Collections.shuffle(tempCards);
		for(int i = 0; i < tempCards.size(); i++){
			this.pileCards.add(tempCards.get(i));
		}
	}
	
	/**
	 * load the icon to draw to the attribute
	 */
	private void createImageOfPile() {
		try{
			 Pile.icon = ImageIO.read(new File("pictures/gray_back.png"));
		}catch(IOException e){
			System.out.println("file not found");
		}
	}
	
	/**
	 * use for the drawable interface
	 */
	@Override
	public void draw(Graphics g) {
		
			int i;
			for(i = 1; i < 10; i += 2){
				g.drawImage(Pile.icon, X_start + i , Y_start + i , 70, 120, null);
			}
			
			
	}
}
