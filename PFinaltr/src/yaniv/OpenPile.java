package yaniv;

import java.awt.Graphics;
import java.util.Stack;

import graphic.Drawable;

public class OpenPile implements Drawable {
	Stack<Card> openPile;
	/**
	 * xStart - the x to start draw the open pile
	 * yStart - the y to start draw the open pile
	 */
	private static final int xStart = 470;
	private static final int yStart = 250;

	public OpenPile(Stack<Card> openPile) {
		this.openPile = openPile;
	}

	
	
	public Stack<Card> getOpenPile() {
		return openPile;
	}

	public void setOpenPile(Stack<Card> openPile) {
		this.openPile = openPile;
	}

	public OpenPile() {
		this.openPile = new Stack<Card>();
	}
	/**
	 * add a card to the stack
	 * @param card
	 */
	public void addCard(Card card) {
		this.openPile.push(card);
	}
	/**
	 * 
	 * @return the top of the stack and remove it from the stack
	 */
	public Card removeCard() {
		return this.openPile.pop();
	}
	

	public int getxStart() {
		return xStart;
	}

	public int getyStart() {
		return yStart;
	}

	/**
	 * use for the drawable interface
	 */
	@Override
	public void draw(Graphics g) {
		g.drawImage(openPile.peek().getImage(), OpenPile.xStart, OpenPile.yStart, 70, 120, null);
	}
	
	
	
	
	
	
}
