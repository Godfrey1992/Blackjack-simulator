import java.util.ArrayList;
import java.util.Collections;

/**
 * Created By Godfrey Oguike 2014
 * This is the class that represents a deck of cards in a card game.
 */

public class Deck {
	static ArrayList<Card> deck = new ArrayList<Card>();
	
	String[] suits = new String[] {"Hearts", "Diamonds", "Clubs", "Spades"};
	String[] types = new String[] {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
	int[] values = new int[] {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};
	int suitCounter;
	int deckCounter = 0;
	Card card;
	
	
	public Deck(){
		System.out.println("--------Generating-Deck--------");
		for (suitCounter = 0; suitCounter < suits.length; suitCounter++){
			
			for (int i = 0; i < types.length; i++){
				card = new Card(suits[suitCounter], types[i], values[i]);
				deck.add(card);
				card = deck.get(deckCounter);
				System.out.println("Added to Deck: "+card.getType()+" of "+card.getSuit()+" value = "+card.getCardVal());
				deckCounter++;
			}
			System.out.println("  ");
		}
	}
	
	public void randomizeDeck(){
		Collections.shuffle(deck);
		System.out.println(" ");
		System.out.println("--------Randomizing-Deck--------");
		showDeck();
	}

	public void showDeck() {
		System.out.println(" ");
		System.out.println("--------New-Deck--------");
		for (int i = 0; i < deck.size(); i++){
			card = deck.get(i);
			System.out.println(card.getType()+" of "+card.getSuit()+" value = "+card.getCardVal());
		}
	}
	
	public Card dealCards(){
		card = deck.get(0);
		deck.remove(0);
		System.out.println(card.getType()+" of "+card.getSuit());
		return card;
	}	
}
