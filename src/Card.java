

/**
 * Created By Godfrey Oguike 2014
 * This is the domain class that represents a card in a card game.
 */
public class Card {
	
	String cardSuit;
	String cardType;
	int cardValue;
	
	public Card(String suit, String type, int value){
		
		cardSuit = suit;
		cardType = type;
		cardValue = value;	
	}
	
	public String getSuit(){
		return cardSuit;
	}
	
	public String getType(){
		return cardType;
	}
	
	public int getCardVal(){
		return cardValue;
	}
	
}
