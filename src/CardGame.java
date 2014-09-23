import java.util.ArrayList;



/**
 * Created By Godfrey Oguike 2014
 * Class that handles the playing of a card game from a simple command line interface,
 * and echoes back a step-by-step description of the game to the console.
 */
public class CardGame {

    /**
     * Main. Plays a card game from a command line interface.
     * @param args the arguments to the game
     */
	
	static ArrayList<Player> players = new ArrayList<Player>();
	static Player player;
	static int numberOfPlayers = 3;
	static Deck deck;
	static Card card;
	
    public static void main(String[] args) {

    	deck = new Deck();
    	deck.randomizeDeck();
    	
    	System.out.println(" ");
    	System.out.println("===============================");
    	System.out.println("==========Game=begins==========");
    	
    	generatePlayers();
    	firstDeal(deck);
    	//deck.showDeck();
    	showPlayerCardTotal();
    	playGame();
    	
    }

	private static void firstDeal(Deck deck) {
		System.out.println(" ");
		System.out.println("--------Dealing-cards--------");
		for (int i = 0; i < 2; i++){
			for(Player p: players){
				System.out.print(p.getName()+" gets - ");
		    	p.getCards(deck.dealCards());
		    }
		}	
	}

	private static void generatePlayers() {
		System.out.println(" ");
		System.out.println("--------Players-joining--------");
		for (int i = 0; i < numberOfPlayers; i++){
    		player = new Player("Player "+i);
    		players.add(player);
    		player = players.get(i);
    		System.out.println("Player joined: "+player.getName());
    	}
	}
	
	private static void showPlayerCardTotal(){
		System.out.println(" ");
		System.out.println("--------Calc-total--------");
		for (int i = 0; i < players.size(); i++){
    		player = players.get(i);
    		System.out.println(player.getName()+" total is "+player.calculateTotal());
    	}
	}
	
	private static void playGame(){
		int roundCounter = 1;
		System.out.println(" ");
		System.out.println("--------Game-Round-"+ roundCounter +"-------");
		
		for(Player p: players){
			int playerCardTotal = p.calculateTotal();
			
			if(playerCardTotal < 17){
				System.out.println(p.getName()+" **Hit** with "+p.calculateTotal());
				System.out.print(p.getName()+" recieves: ");
				card = deck.dealCards();
				p.getCards(card);
				System.out.println(" ");
				
			}else if(playerCardTotal >= 17 && playerCardTotal < 21){
				System.out.print(p.getName()+" **Stick** with "+p.calculateTotal());
				System.out.println(" ");
				
			}else if (playerCardTotal > 21){
				System.out.println(p.getName()+" **Bust** with "+p.calculateTotal());
				System.out.println(p.getName()+" was removed");
				//players.remove(p);
				System.out.println(" ");
			}
		}
	}
}
