package yaniv;

import java.util.Comparator;

public class ComparatorCards implements Comparator<Card> {
//compare between cards value
	@Override
	public int compare(Card o1, Card o2) {
		if (o1.getNumber() < o2.getNumber())
			return -1;
		if(o1.getNumber() == o2.getNumber())
			return 0;
		return 1;
		
	}

}
