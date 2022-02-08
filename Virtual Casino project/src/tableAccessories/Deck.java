package tableAccessories;
import java.util.*;

public class Deck{
	public int deckSize;
	int currentSize;
	ArrayList<Card> cList = new ArrayList<Card>();
	ArrayList<Card> discard = new ArrayList<Card>();
	
	public Deck() {
		deckSize = 52;
		currentSize = deckSize;
		int l = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 1; j <= 13; j++) {
				cList.add(new Card(j, i));
				l++;
			}
		}
	}
	
	public Card Draw(){
		Random rand = new Random();
		int dInt = rand.nextInt(currentSize);
		Card dCard = cList.get(dInt);
		discard.add(dCard);
		cList.remove(dInt);
		currentSize --;
		return dCard;
	}
	
	public void resetDeck() {
		for (int i = 0; i < discard.size(); i++) {
			cList.add(discard.get(i));
			currentSize++;
		}
	}
}
