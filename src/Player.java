import java.util.ArrayList;

/**
 * Created By Godfrey Oguike 2014
 * Class encapsulates individual player attributes and methods.
 */

public class Player {
	
	String playerName;
	boolean stickStatus = false;
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
			
			}
		
		for (int value: cardVal){
			cardValTotal += value;
		}
		return cardValTotal;
	}
	
	public void changeStickStatus(){
		stickStatus = true;
	}
	
	public boolean gettickStatus(){
		return stickStatus;
	}
}
