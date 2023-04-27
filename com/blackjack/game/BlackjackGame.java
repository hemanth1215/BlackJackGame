package com.blackjack.game;

import com.blackjack.exception.InvalidCardSuitException;
import com.blackjack.exception.InvalidCardValueException;
import com.blackjack.exception.InvalidDeckPositionException;
import com.blackjack.model.Dealer;
import com.blackjack.model.Deck;
import com.blackjack.model.Player;

import java.util.Scanner;

public class BlackjackGame {
	private Scanner scanner = new Scanner(System.in);
	private int numOfPlayers;
	private Player[] players;
	private Deck deck;
	protected Dealer dealer = new Dealer();

	public void beginGame(){
		String names;
		System.out.println("Welcome to Blackjack!");
		System.out.println("");

		do {
			System.out.print("How many players (1-3)? ");
			numOfPlayers = scanner.nextInt();
		} while (numOfPlayers > 3 || numOfPlayers < 0);

		players = new Player[numOfPlayers];
		deck = new Deck();

		// Get the players name
		for (int i = 0; i < numOfPlayers; i++) {
			System.out.print("What is player " + (i + 1) + "'s name? ");
			names = scanner.next();
			players[i] = new Player();
			players[i].setName(names);
		}
	}

	public void shuffle() throws InvalidDeckPositionException, InvalidCardSuitException, InvalidCardValueException {
		deck.shuffle();
	}

	public void dealCards(){
		for (int j = 0; j < 2; j++) {
			for (int i =0; i < numOfPlayers; i++) {
				if(players[i].getBank() > 0)
				{
				players[i].addCard(deck.nextCard());
				}
			}

			dealer.addCard(deck.nextCard());
		}
	}

	public void checkBlackjack(){
		if (dealer.isBlackjack() ) {
			System.out.println("Dealer has BlackJack!");
			for (int i =0; i < numOfPlayers; i++) {
				if (players[i].getTotal() == 21 ) {
					System.out.println(players[i].getName() + " pushes");
					players[i].push();
				} else {
					System.out.println(players[i].getName() + " loses");
					players[i].bust();
				}	
			}
		} else {
			if (dealer.peek() ) {
				System.out.println("Dealer peeks and does not have a BlackJack");
			}

			for (int i =0; i < numOfPlayers; i++) {
				if (players[i].getTotal() == 21 ) {
					System.out.println(players[i].getName() + " has blackjack!");
					players[i].blackjack();
				}
			}
		}		
	}

	public void hitOrStand() {
		String command;
		char c;
		for (int i = 0; i < numOfPlayers; i++) {
				System.out.println();
				System.out.println(players[i].getName() + " has " + players[i].getHandString());

				do {
					do {
						System.out.print(players[i].getName() + " (H)it or (S)tand? ");
						command = scanner.next();
						c = command.toUpperCase().charAt(0);
					} while ( ! ( c == 'H' || c == 'S' ) );
					if ( c == 'H' ) {
						players[i].addCard(deck.nextCard());
						System.out.println(players[i].getName() + " has " + players[i].getHandString());
					}
				} while (c != 'S' && players[i].getTotal() <= 21 );
		}
	}

	public void dealerPlays() {
		boolean isSomePlayerStillInTheGame = false;
		for (int i =0; i < numOfPlayers && isSomePlayerStillInTheGame == false; i++){
			if (players[i].getTotal() <= 21 ) {
				isSomePlayerStillInTheGame = true;
			}
		}
		if (isSomePlayerStillInTheGame) {
			dealer.dealerPlay(deck);
		}
	}

	public void settleBets() {
		System.out.println();

		for (int i = 0; i < numOfPlayers; i++) {
			if( players[i].getTotal() > 21 ) {
				System.out.println(players[i].getName() + " has busted");
				players[i].bust();
			} else if ( players[i].getTotal() == dealer.calculateTotal() ) {
				System.out.println(players[i].getName() + " has pushed");
				players[i].push();
			} else if ( players[i].getTotal() < dealer.calculateTotal() && dealer.calculateTotal() <= 21 ) {
				System.out.println(players[i].getName() + " has lost");
				players[i].loss();
			} else if (players[i].getTotal() == 21) {
				System.out.println(players[i].getName() + " has won with blackjack!");
				players[i].blackjack();
			} else {
				System.out.println(players[i].getName() + " has won");
				players[i].win();
			}
		}
	}

	public void printStatus() {
		for (int i = 0; i < numOfPlayers; i++) {
			if(players[i].getBank() > 0)
			{
			System.out.println(players[i].getName() + " has " + players[i].getHandString());
			}
		}
		System.out.println("Dealer has " + dealer.getHandString(true, true));
	}

	public void clearHands() {
		for (int i = 0; i < numOfPlayers; i++) {
			players[i].clearHand();
		}
		dealer.clearHand();

	}

	public boolean playAgain() {
		String command;
		char c;
		Boolean playState = true;
		if(forceEnd()) {
			playState = false;	
		}
		else {
			do {
				System.out.println("");
				System.out.print("Do you want to play again (Y)es or (N)o? ");
				command = scanner.next();
				c = command.toUpperCase().charAt(0);
			} while ( ! ( c == 'Y' || c == 'N' ) );
			if(c == 'N')
			{
				playState = false;
			}
		}
		return playState;
	}

	public boolean forceEnd() {
		boolean end = false;
		int endCount = 0;
		
		for (int i = 0; i < numOfPlayers; i++) {
			if(players[i].getBank() == -1)
			{
				endCount++;
			}
		}
		if(endCount == numOfPlayers)
		{
			end = true;
		}
		if(end)
		{
			System.out.println("");
			System.out.println("All players have lost and the game ends.");
		}
		
		return end;
	}
}