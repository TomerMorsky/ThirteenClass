package yaniv;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Card {
	private int number; // 0 for joker 11 for Jack 12 for queen 13 for king
	private char shape;
	private char color;
	private Image image;// image of the card
	private int serial;// between 1-54 needed a chart that explain the serials numbers.
	//all the mixed cards will be in cards list and the identification depend on serial
	
	// wanted to have the image from the homePile according to serial
	public Card(int number, char shape, char color, int serial, Image image) {
		this.number = number;
		this.shape = shape;
		this.color = color;
		this.serial = serial;
		this.image = image;// uses for draw the cards
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

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
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
	//overload - use for draw player card 
	public void draw(Graphics g, int x, int y) {
		g.drawImage(this.image, x, y, 70, 120, null);
	}
	
	//overload - use for draw player card if isAvalible is true - draw the card regular (see details) else draw his back. 
	public void draw(Graphics g, int x, int y, Image backCard, Boolean isAvalible) {
		if (isAvalible) {
			Image temp = rotate(image, 90);
			g.drawImage(temp, x, y, 120, 70, null);
		}else {
			backCard = rotate(backCard, 90);
			g.drawImage(backCard, x, y, 120, 70, null);
		}
		
	}
	
}
