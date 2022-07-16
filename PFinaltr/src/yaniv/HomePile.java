package yaniv;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * this class used to save all the cards in a list. It responsible on the first boot of the cards.
 *
 */
public class HomePile {
	private List<Card> homeCards;

	public HomePile() {
		this.homeCards = new LinkedList<Card>();
		createHomePile();
	}
	/**
	 * the method will create the home pile. will adds 54 cards to the list.This list changes never!
	 * every card in the list has his image so it can be draw.Every card in the list get a shape, color and picture
	 */
	private void createHomePile(){
		char shape = 's';
		char color = 'b' ;
		int number = 1;
		for(int i = 1; i < 53; i++){
			try{
				Image icon = ImageIO.read(new File("pictures/"+i+".png"));
				Card c = new Card(number, shape, color, i, icon);
				homeCards.add(c);
				}catch (IOException e){
					System.out.println("file not found");
				}
			if(shape == 's'){
				shape = 'h';
				color = 'r';
			}
			else if(shape == 'h'){
				shape = 'd';
				color = 'r';
			}
			else if (shape == 'd'){
				shape = 'c';
				color = 'b';
			}else{ 
					shape = 's';
					color = 'b';
			}
			/**
			 * every 4 cards the number of the card changes	
			 */
			
			
			if(i % 4 == 0)
				number ++;
			//Card c = new Card(number, shape, color, i,icon);
			}
		/**
		 * create the jokers- special cards, and add them to the list
		 */
		try{
		Image icon = ImageIO.read(new File("pictures/"+53+".png"));
		Joker j = new Joker('r', 53, icon);
		homeCards.add(j);
		icon  = ImageIO.read(new File("pictures/"+54+".png"));
		j = new Joker('b', 54, icon);
		homeCards.add(j);
		}catch(IOException e){
			System.out.println("file not found");
		}
		
	}
	/**
	 * only get method, avoid changing this list by mistake 
	 * @return the homeCards list 
	 */
	public List<Card> getHomeCards() {
		return homeCards;
	}
	
	
}
