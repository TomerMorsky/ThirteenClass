package graphic;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public abstract class DrawableCard {
	protected Image image;// image of the card
	
	public DrawableCard(Image image) {
		super();
		this.image = image;
	}
	
	public Image getImage() {
		return image;
	}

	public void draw(Graphics g, int x, int y, Image backCard, Boolean isAvalible) {
		if (isAvalible) {
			Image temp = rotate(image, 90);
			g.drawImage(temp, x, y, 120, 70, null);
		} else {
			backCard = rotate(backCard, 90);
			g.drawImage(backCard, x, y, 120, 70, null);
		}

	}
	
	//overload - use for draw player card 
	public void draw(Graphics g, int x, int y) {
		g.drawImage(this.image, x, y, 70, 120, null);
	}
	

	/**
	 * Rotates an image. Actually rotates a new copy of the image.
	 * 
	 * @param img   The image to be rotated
	 * @param angle The angle in degrees
	 * @return The rotated image
	 */
	private Image rotate(Image img, double angle) {
		double sin = Math.abs(Math.sin(Math.toRadians(angle))), cos = Math.abs(Math.cos(Math.toRadians(angle)));
		int w = img.getWidth(null), h = img.getHeight(null);
		int neww = (int) Math.floor(w * cos + h * sin), newh = (int) Math.floor(h * cos + w * sin);
		BufferedImage bimg = toBufferedImage(getEmptyImage(neww, newh));
		Graphics2D g = bimg.createGraphics();
		g.translate((neww - w) / 2, (newh - h) / 2);
		g.rotate(Math.toRadians(angle), w / 2, h / 2);
		g.drawRenderedImage(toBufferedImage(img), null);
		g.dispose();
		return toImage(bimg);
	}

	/**
	 * Converts a given BufferedImage into an Image
	 * 
	 * @param bimage The BufferedImage to be converted
	 * @return The converted Image
	 */
	private static Image toImage(BufferedImage bimage) {
		// Casting is enough to convert from BufferedImage to Image
		Image img = (Image) bimage;
		return img;
	}

	/**
	 * Creates an empty image with transparency
	 * 
	 * @param width  The width of required image
	 * @param height The height of required image
	 * @return The created image
	 */
	private static Image getEmptyImage(int width, int height) {
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		return toImage(img);
	}

	/**
	 * Converts a given Image into a BufferedImage
	 * 
	 * @param img The Image to be converted
	 * @return The converted BufferedImage
	 */
	private static BufferedImage toBufferedImage(Image img) {
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

}
