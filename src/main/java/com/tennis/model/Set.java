package com.tennis.model;

import com.tennis.model.Results.TYPE;

import lombok.Data;

@Data
public class Set implements IGame {
	
	private Game game;
	private TieBreak tieBreak;
	private GAME_RESULT currentResult; 
	
	public Set(Game game) {
		this.game = game;
	}

	public Player setWinner() {
		return (game.getPlayer1().getScoreSet() > game.getPlayer2().getScoreSet()) ? game.getPlayer1() : game.getPlayer2();
	}
	
	public Player setLoser() {
		return (game.getPlayer1().getScoreSet() > game.getPlayer2().getScoreSet()) ? game.getPlayer2() : game.getPlayer1();
	}
	
	@Override
	public Player winner() {
		if (setWinner().getScoreSet() == 6 && setLoser().getScoreSet() <= 4)
			return setWinner();
		
		else if (setWinner().getScoreSet() == 7) 
			return setWinner();
		
		else
			return null;
	}

	@Override
	public Player loser() {
		if (game.winner().getScoreSet() == 6 && game.loser().getScoreSet() <= 4)
			return game.loser();
		
		else if (game.winner().getScoreSet() == 7) 
			return game.loser();
		
		else
			return null;
	}

	@Override
	public Results check() {
		if ( winner() != null  ) {
			currentResult = GAME_RESULT.WON;
			
		}
		else {
			currentResult = GAME_RESULT.IN_PROGRESS;
			
			if (game.winner().getScoreSet() == 6 && game.loser().getScoreSet() == 6 && !game.winner().isTieBreak() ) {
				
				currentResult = GAME_RESULT.TIEBREAK;
				
				game.winner().setTieBreak(true);
				game.loser().setTieBreak(true);
				
				tieBreak = new TieBreak(this);
				
			} 
			
		}
		
		return new Results(TYPE.SET, this);
	}
	

}
