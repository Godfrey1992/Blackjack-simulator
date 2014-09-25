import java.util.ArrayList;
import java.util.Scanner;



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
	static int numberOfPlayers = 6;
	static Deck deck;
	static Card card;
	static int roundCounter = 1;
	static int counter;
	static Boolean win = false;
	static int largestTotal;
	static int stickCounter;
	static String name;
	
    public static void main(String[] args) {

    	startApp();	
    }

    // Takes user input of the number of player they would like.
	private static void startApp() {
		System.out.println(" ");
		System.out.println("Enter a number of player between 2 and 6");
    	Scanner in = new Scanner(System.in);
    	int playerNum = Integer.parseInt(in.nextLine());
    	
    	if(playerNum >=2 && playerNum <=6){
    		numberOfPlayers = playerNum;
    		beginGame();
    		
    	}else{
    		System.out.println("Please enter the correct number of players");
    		startApp();
    	}
	}

	// Runs the main methods of the application
	private static void beginGame() {
		deck = new Deck();
    	deck.randomizeDeck();
    	
    	System.out.println(" ");
    	System.out.println("===============================");
    	System.out.println("==========Game=begins==========");
    	
    	generatePlayers();
    	firstDeal(deck);
    	showPlayerCardTotal();
    	playGame();
	}

    // Deals 2 cards from the top of the shuffled deck.
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

	// Generates the specified number of players
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
	
	// Shows the total card value of the players hand.
	private static void showPlayerCardTotal(){
		System.out.println(" ");
		System.out.println("--------Calculating-total--------");
		for (int i = 0; i < players.size(); i++){
    		player = players.get(i);
    		System.out.println(player.getName()+" total is "+player.calculateTotal());
    	}
	}
	
	// This method is executed for every round played
	private static void playGame(){
		
		while(win == false){
			System.out.println(" ");
			System.out.println("--------Game-Round-"+ roundCounter +"-------");
			playRound();
			roundCounter++;
			counter = 0;
		}
	}
	
	// Main method for the game logic.
	private static void playRound(){
		while(counter < players.size()){

			Player p = players.get(counter);
			
			if(players.size() == 1){
				p = players.get(0);
				System.out.println(" ");
				System.out.println(p.getName()+" is the last player they **WIN**");
				win = true;
				break;
				
			}else if(players.size() == 0){
				System.out.println("Its a draw all players went bust");
				break;
			}
			
			if(p.gettickStatus() == false){
			
				int playerCardTotal = p.calculateTotal();
				
				if(playerCardTotal < 17){
					System.out.println(p.getName()+" **Hit** with "+p.calculateTotal());
					System.out.print(p.getName()+" recieves: ");
					card = deck.dealCards();
					p.getCards(card);
					counter++;
					System.out.println(" ");
					
				}else if(playerCardTotal >= 17 && playerCardTotal < 21){
					System.out.println(p.getName()+" **Stick** with "+p.calculateTotal());
					p.changeStickStatus();
					stickCounter++;
					counter++;
					System.out.println(" ");
					
				}else if(playerCardTotal > 21){
					System.out.println(p.getName()+" **Bust** with "+p.calculateTotal());
					players.remove(counter);
					System.out.println(p.getName()+" was removed");
					System.out.println(" ");
					
					// check is player is the last player
					if (counter != players.size()){
						playRound();
					}
					counter++;
				
				// If a player hits 21 exactly
				}else if(playerCardTotal == 21){
					System.out.println(p.getName() +" **WINS** with "+p.calculateTotal());
					win = true;
					break;
				}
			
			// If all the player stick
			}else{

				if (stickCounter != players.size()){
					System.out.println(p.getName()+" **Sticks** with "+p.calculateTotal());
					counter++;
					playRound();				
											
				}else if(stickCounter == players.size()){
					System.out.println("All players stick");
					System.out.println("Player with the highest total wins");
					System.out.println(" ");
					
					for(Player player: players){
						if(player.calculateTotal() > largestTotal){
							largestTotal = player.calculateTotal();
							name = player.getName();
						}
					}
					
					System.out.println(name+" **WINS** with: "+largestTotal);
					win = true;
					break;
				}
			}
		}
	}
}
