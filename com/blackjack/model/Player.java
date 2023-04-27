package com.blackjack.model;

public class Player {
	private int bank;
	private int bet;
	private String name;
	private Hand hand;

	public Player() {
		bank = 100;
		hand = new Hand();
	}

	public int getBank() {
		return bank;
	}

	public void bust() {
		bank -= bet;
		bet = 0;
	}

	public void win() {
		bank += bet;
		bet = 0;
	}

	public void loss() {
		bank -= bet;
		bet = 0;
	}

	public void removeFromGame() {
		bank = -1;
	}

	public void resetBank() {
		bank = 0;
	}

	public void blackjack() {
		bank += bet * 1.5;
		bet = 0;
	}

	public void push() {
		bet = 0;
	}

	public void setBet(int newBet) {
		bet = newBet;
	}

	public void setName(String name1){
		name = name1;
	}

	public String getName() {
		return name;
	}

	public int getTotal() {
		return hand.calculateTotal();
	}

	public int getBet(){
		return this.bet;
	}

	public void addCard(Card card) {
		hand.addCard(card);

	}

	public String getHandString() {
		String str = "Cards:" + hand.toString();

		return str;
	}

	public void clearHand() {
		hand.clearHand();
	}
		
}