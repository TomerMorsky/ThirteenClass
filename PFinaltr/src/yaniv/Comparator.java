package yaniv;

public class Comparator implements java.util.Comparator<Player> {

	//compare between players actually between their sum cards in hand
	@Override
	public int compare(Player o1, Player o2) {
		if (o1.sumCards() < o2.sumCards())
			return -1;
		if(o1.sumCards() == o2.sumCards())
			return 0;
		return 1;
	}
}

