package tableAccessories;

public class Card {
	private int value;
	private String[] types = {"Ace", "Number", "Jack", "Queen", "King"};
	private String type;
	private String[] suits = {"Clubs","Diamonds","Hearts","Spades"};
	private String suit;
	private int bjValue;
	
	public Card(int value, int suit){
		this.setValue(value);
		this.setSuit(suits[suit]);
		if (value == 1) {
			setType(types[0]);
		}
		else if (value > 1 && value < 11) {
			setType("");
		}
		else if (value == 11) {
			setType(types[2]);
		}
		else if (value == 12) {
			setType(types[3]);
		}
		else {
			setType(types[4]);
		}
	}
	
	public String getCard() {
		if (getValue() > 1 && getValue() < 11) {
			return (getValue() + " of " + getSuit());
		}
		else return (getType() + " of " + getSuit());
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getSuit() {
		return suit;
	}

	public void setSuit(String suit) {
		this.suit = suit;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	public int getBJValue() {
		
		if (type.equalsIgnoreCase(types[0]) ) {
			return 11;
		} else if( type.equalsIgnoreCase(types[2]) || type.equalsIgnoreCase(types[3]) || type.equalsIgnoreCase(types[4]) ) {
			return 10;
		} else {
			return value;
		}
		
		
	}
}
