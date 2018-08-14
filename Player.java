
public class Player {
		
		// players name
	private String name;
	
		// # of cards allowed in players hand
		// w/ an arraylist, wouldn't have to specify 10
	private Card[] hand = new Card[10];
	
		// number of cards in current hand
	private int numCards;

		// Constructor, name of the player
	public Player(String aName) {
		
		this.name = aName;
		
			// set the players hand to empty
		this.emptyHand();
		
	}
		// Resetting players hand to 0
	public void emptyHand() {
		
		for (int c = 0; c < 10; c++) {
			this.hand[c] = null;
		}
		this.numCards = 0;
	}

		// adding a card to players hand
		// whether sum is below or equal to 21
	public boolean addCard(Card aCard) {
		
			// print error if we already have the max number of cards
		if (this.numCards == 10) {
			System.err.printf("%s's hand already has 10 cards; " + 
					"cannot add another card\n", this.name);
			System.exit(1);
		}
		
			// add new card in next slot & increment number of cards counter
		this.hand[this.numCards] = aCard;
		this.numCards++;
		
		return (this.getHandSum() <= 21);
	}
	
		// get sum of cards in players hand
	public int getHandSum() {
		
		int handSum = 0;
		int cardNum;
		int numAces = 0;
		
			// calc each cards contribution to hand sum
		for (int c = 0; c < this.numCards; c++) {
			
				// get the number for the current card
			cardNum = this.hand[c].getNumber();
			
			if (cardNum == 1) { // Ace
				numAces++;
				handSum += 11;
			} else if (cardNum > 10) { // face card
				handSum += 10;
			} else {				// 2-9
				handSum += cardNum;
			}
		}
		
			// if we have aces & our sum is > 21, set same/all of them to value 1
		while (handSum > 21 && numAces > 0) {
			handSum -= 10;
			numAces--;
		}
		
		return handSum;
	}
	
		// print cards in players hand
		// whether the first card is hidden or not
	public void printHand(boolean showFirstCard) {
		
		System.out.printf("%s's cards:\n", this.name);
		for (int c = 0; c < this.numCards; c++) {
			if (c == 0 && !showFirstCard) {
				System.out.println("	[hidden]");
			} else {
				System.out.printf("	%s\n", this.hand[c].toString());
			}
		}
	}
}
