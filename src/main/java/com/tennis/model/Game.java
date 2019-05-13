package com.tennis.model;

import com.tennis.model.Results.TYPE;

import lombok.Data;

@Data
public class Game implements IGame {

	private Player player1;
	private Player player2;
	private GAME_RESULT currentResult;

	public Game(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
	}

	public Player winner() {
		return (player1.getScoreGame() > player2.getScoreGame()) ? player1 : player2;
	}

	public Player loser() {
		return (player1.getScoreGame() > player2.getScoreGame()) ? player2 : player1;
	}
	

	@Override
	public Results check() {
		if (player1.getScoreGame() >= 4 && player1.getScoreGame() >= player2.getScoreGame() + 2) {
			currentResult = GAME_RESULT.WON;
		} else if (player2.getScoreGame() >= 4 && player2.getScoreGame() >= player1.getScoreGame() + 2) {
			currentResult = GAME_RESULT.WON;			
		} else if (player1.getScoreGame() >= 4 && player2.getScoreGame() >= 4)  {
			if (player1.getScoreGame() == player2.getScoreGame()) {
				currentResult = GAME_RESULT.DEUCE;
			} else if (Math.abs(player1.getScoreGame() - player2.getScoreGame()) == 1) {
				currentResult = GAME_RESULT.ADVANTAGE;

			}
		}
		else
			currentResult = GAME_RESULT.IN_PROGRESS;
		
		return new Results(TYPE.GAME, this);
	}
	

	
}
