
import java.util.Scanner;

public class GameRunner {

	public static void main(String[] args) {
		
			// initialize
		Scanner sc = new Scanner(System.in);
		Deck theDeck = new Deck(1, true);
		
			// initialize the players objects
		Player me = new Player("Eric");
		Player dealer = new Player("Dealer");
		
		me.addCard(theDeck.dealNextCard());
		dealer.addCard(theDeck.dealNextCard());
		me.addCard(theDeck.dealNextCard());
		dealer.addCard(theDeck.dealNextCard());
		
			// print initial hands
		System.out.println("Cards are dealt\n");
		me.printHand(true);
		dealer.printHand(false);
		System.out.println("\n");
		
			// flags for when each player is finished hitting
		boolean meDone = false;
		boolean dealerDone = false;
		String answer;
		
		while (!meDone || !dealerDone) {
			
				// players turn
			if (!meDone) {
				System.out.print("Hit or Stay? (Enter H or S): ");
				answer = sc.next();
				System.out.println();
				
					// if the player hits
				if (answer.compareToIgnoreCase("H") == 0) {
					
						// add next card in deck & store whether player has busted 
					meDone = !me.addCard(theDeck.dealNextCard());
					me.printHand(true);
					
				} else if(answer.compareToIgnoreCase("S") == 0) {
					meDone = true;
								
				} 
			}
			
				// dealers turn
			if (!dealerDone) {
				if (dealer.getHandSum() < 17) {
					System.out.println("The Dealer hits\n");
					dealerDone = !dealer.addCard(theDeck.dealNextCard());
					dealer.printHand(false);
				} else {
					System.out.println("The Dealer stays\n");
					dealerDone = true;
				}
			}
			
			System.out.println();
		}
		
			// close scanner
		sc.close();
		
			// print final hands
		me.printHand(true);
		dealer.printHand(true);
		
		int mySum = me.getHandSum();
		int dealerSum = dealer.getHandSum();
		
		if (mySum > dealerSum && mySum <= 21 || dealerSum > 21) {
			System.out.println("\nYou win! Your score: " + mySum);
			System.out.println("Dealer lost with a score: " + dealerSum);
		} else if (mySum == dealerSum) {
			System.out.println("\nIt's a wash! Both scored: " + mySum);
		} else {
			System.out.println("\nDealer wins! Dealers score: " + dealerSum);
			System.out.println("You lost with a score: " + mySum);
		}
	}

}
