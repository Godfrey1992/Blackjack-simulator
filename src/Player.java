import java.util.ArrayList;


public class Player {
	
	String playerName;
	ArrayList<Card> cards = new ArrayList<Card>();
	
	public Player(String name){
		playerName = name;
	}
	
	public String getName(){
		return playerName;
	}
	
	public void getCards(Card card){
		cards.add(card);
	}
	
	public int calculateTotal(){
		ArrayList<Integer> cardVal = new ArrayList<Integer>();
		int cardValTotal = 0;
		
		for (int i = 0; i < cards.size(); i++){
			Card card = cards.get(i);
			cardVal.add(card.getCardVal());
			
			for (int value: cardVal){
				cardValTotal += value;
			}
		}
		return cardValTotal;
	}
}
