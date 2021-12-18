package yaniv;

import java.util.Comparator;
import java.util.List;

public class CompareSequence implements Comparator<List<Card>>{
//Compare between the summary of the lists
	@Override
	public int compare(List<Card> o1, List<Card> o2) {
		return sumList(o1) - sumList(o2);
		
	}
	private int sumList(List<Card> list) {
		int sum = 0;
		for(int i = 0; i < list.size(); i++) {
			sum += list.get(i).getNumber();
		}
		return sum;
	}

}
