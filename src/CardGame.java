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
	static int roundCounter = 1;
	static int i;
	static boolean win = false;
	
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
		System.out.println("--------Calculating-total--------");
		for (int i = 0; i < players.size(); i++){
    		player = players.get(i);
    		System.out.println(player.getName()+" total is "+player.calculateTotal());
    	}
	}
	
	private static void playGame(){
			int s =0;
		while(s < 5){
			System.out.println(" ");
			System.out.println("--------Game-Round-"+ roundCounter +"-------");
			playRound();
			roundCounter++;
			s++;
			i = 0;
			if(win == true){
				break;
			}
		}
		System.out.println("ITS A DRAW");
	}
	
	private static void playRound(){
		while(i < players.size()){
			Player p = players.get(i);
			
			if(p.gettickStatus() == false){
			
				int playerCardTotal = p.calculateTotal();
				
				if(playerCardTotal < 17){
					System.out.println(p.getName()+" **Hit** with "+p.calculateTotal());
					System.out.print(p.getName()+" recieves: ");
					card = deck.dealCards();
					p.getCards(card);
					i++;
					System.out.println(" ");
					
				}else if(playerCardTotal >= 17 && playerCardTotal < 21){
					System.out.println(p.getName()+" **Stick** with "+p.calculateTotal());
					p.changeStickStatus();
					i++;
					System.out.println(" ");
					
				}else if(playerCardTotal > 21){
					System.out.println(p.getName()+" **Bust** with "+p.calculateTotal());
					
					players.remove(i);
					System.out.println(p.getName()+" was removed");
					System.out.println(" ");
					
					// check is player is the last player
					if (i != players.size()){
						playRound();
					}
					i++;
				
				// If a player hits 21 exactly
				}else if(playerCardTotal == 21){
					System.out.println(p.getName() +" **WINS** with "+p.calculateTotal());
					win = true;
					break;
					
				}else{
					
				}
			
			// If all the player stick
			}else{
				int largestTotal = 0;
				String playerName = null;
				for (int i = 0; i < players.size(); i++){
					p = players.get(i);
					if(p.calculateTotal() > largestTotal){
							largestTotal = p.calculateTotal();
							playerName = p.getName();
						}
					}
				System.out.println(playerName +" **WINS** with "+largestTotal);
				win = true;
				break;
			}
		}
	}
}
