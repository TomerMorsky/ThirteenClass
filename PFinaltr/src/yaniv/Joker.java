package yaniv;


import java.awt.Image;
/**
 * class for joker card- special card. in addiction to a regular card it has two more attributes this is because joker can be every card 
 * @author Tomer
 *
 */
public class Joker extends Card {
	private int possible1;
	private int possible2;
	public Joker(char color, int serial, Image image) {
		super(0,'j', color, serial, image);// j for joker
		possible1 = -1;
		possible2 = -1;
	}
	public int getPossible1() {
		return possible1;
	}
	public void setPossible1(int possible1) {
		this.possible1 = possible1;
	}
	public int getPossible2() {
		return possible2;
	}
	public void setPossible2(int possible2) {
		this.possible2 = possible2;
	}
	

}
