package com.blackjack.model;

public class Dealer {

	private Hand hand = new Hand();

	public boolean isBlackjack(){
		if (hand.calculateTotal() == 21){
			return true;
		} else {
			return false;
		}
	}

	public void dealerPlay(Deck deck){
		System.out.println();
		while (hand.calculateTotal() <= 16) {
			System.out.println("Dealer has " + hand.calculateTotal()+ " and hits");
			hand.addCard(deck.nextCard());
			System.out.println("Dealer " + this.getHandString(true, false));
		} 
		if ( hand.calculateTotal() > 21) {
			System.out.println("Dealer busts. " + this.getHandString(true, false));
		} else {
			System.out.println("Dealer stands. " + this.getHandString(true, false));
		}
	}

	public void addCard(Card card) {
		hand.addCard(card);
	}

	public String getHandString(boolean isDealer, boolean hideHoleCard ) {
		String str = "Cards:" + hand.toString(isDealer, hideHoleCard);
		return str;
	}

	public int calculateTotal() {
		return hand.calculateTotal();
	}

	public void clearHand() {
		hand.clearHand();
	}

	public boolean peek() {
		return hand.dealerPeek();
	}
}