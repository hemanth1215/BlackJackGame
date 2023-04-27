package com.blackjack;

import com.blackjack.game.BlackjackGame;

public class BlackjackApplication {

	public static void main(String[] args) throws Exception {

		BlackjackGame mygame = new BlackjackGame();
		boolean endGame = false;

		mygame.beginGame();
		do {
			mygame.shuffle();
			mygame.dealCards();
			mygame.printStatus();
			mygame.checkBlackjack();
			mygame.hitOrStand();
			mygame.dealerPlays();
			mygame.settleBets();
			mygame.clearHands();
			endGame = mygame.playAgain();
		} while (endGame);
		System.out.println("Thank you for playing!");
	}
}