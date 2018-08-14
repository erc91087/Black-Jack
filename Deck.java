
import java.util.Random;

public class Deck {
	
		// array of cards where top card is the first index
	private Card[] myCards;
		// current number of cards in deck
	private int numCards;
	
		// Constructor w/ a default of one deck & no shuffling
	public Deck() {
		
			// call the other constructor, defining one deck w/o shuffling
		this(1, false);
	}
	
		/* Constructor that defines number of decks
		 * And whether it should be shuffled
		 */
	public Deck(int numDecks, boolean shuffle) {

		
		this.numCards = numDecks * 52;
		this.myCards = new Card[this.numCards];
		
			// int card index
		int c = 0;
		
			// for each deck
		for (int d = 0; d < numDecks; d++) {
			
				// for each suit
			for (int s = 0; s < 4; s++) {
				
					// for each number
				for (int n = 1; n <= 13; n++) {
					
						// add a new card to the deck w/ Card constructor
					this.myCards[c] = new Card(Suit.values()[s], n);
					c++;
				}
			}
		}
			// shuffle, if necessary
		if (shuffle) {
			this.shuffle();
		}
	}

		
		// Shuffle deck by randomly swapping pairs of cards
	public void shuffle() {

		
			// int random number generator
		Random rng = new Random();
		
			// temporary card
		Card temp;
		
		int j;
		for (int i = 0; i < this.numCards; i++) {
			
				// get a random card j to swap i's value with
			j = rng.nextInt(this.numCards);
			
				// do swap
			temp = this.myCards[i];
			this.myCards[i] = this.myCards[j];
			this.myCards[j] = temp;
		}
		
	}

		// Deal next card from top of deck.
	public Card dealNextCard() {
		
			// get top card
		Card top = this.myCards[0];
		
			// shift all subsequent cards to the left by one index
			// could have used an arraylist or linkedlist
		for (int c = 1; c < this.numCards; c++) {
			this.myCards[c-1] = this.myCards[c];
		}
		
		this.myCards[this.numCards-1] = null; // emptying out card in last slot
		
			// decrement number of cards in deck
		this.numCards--;
		
		return top;
	}
	
	public void printDeck(int numToPrint) {
		
		for (int c = 0; c < numToPrint; c++) {
			System.out.printf("% 3d/%d %s\n", c+1, this.numCards, this.myCards[c].toString());
		}
		System.out.printf("\t[%d others]\n", this.numCards - numToPrint);
	}
}
