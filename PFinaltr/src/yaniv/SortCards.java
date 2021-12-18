package yaniv;

import java.util.Comparator;

public class SortCards implements Comparator<Card> {
/**
 * used for sort the cards in hand so in the screen it appear in the correct order(from small to big - left to right)
 * The method return the difference between the two cards.It used in collection sort in Player class.
 */
	@Override
	public int compare(Card o1, Card o2) {
		return o1.getSerial() - o2.getSerial();
	}

}
