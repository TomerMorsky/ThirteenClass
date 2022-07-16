package yaniv;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import graphic.Drawable;
import graphic.DrawableCard;

public class Card extends DrawableCard {
	private int number; // 0 for joker 11 for Jack 12 for queen 13 for king
	private char shape;
	private char color;
	private int serial;// between 1-54 needed a chart that explain the serials numbers.
	//all the mixed cards will be in cards list and the identification depend on serial
	
	// wanted to have the image from the homePile according to serial
	public Card(int number, char shape, char color, int serial, Image image) {
		super(image);
		this.number = number;
		this.shape = shape;
		this.color = color;
		this.serial = serial;	
	}
	
	public int getSerial() {
		return serial;
	}
	public void setSerial(int serial) {
		this.serial = serial;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public char getShape() {
		return shape;
	}
	public void setShape(char shape) {
		this.shape = shape;
	}
	public char getColor() {
		return color;
	}
	public void setColor(char color) {
		this.color = color;
	}

	/**
	 * equals method, uses mainly for removeAll in Player class
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Card) {
			Card temp = (Card)obj;
			if (temp.serial == serial) {
				return true;
			}
		}
		return false;
	}
	
}
