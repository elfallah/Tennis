package com.tennis.model;

import com.tennis.model.Results.TYPE;

import lombok.Data;

@Data
public class TieBreak implements IGame {

	private Set set;
	private GAME_RESULT currentResult; 
	
	public TieBreak(Set set) {
		this.set = set;
	}
	
	@Override
	public Player winner() {
		if (tieBreakWinner().getScoreTieBreak() == 7
				&& Math.abs(set.getGame().getPlayer1().getScoreTieBreak() - set.getGame().getPlayer2().getScoreTieBreak()) >= 2) {
			return tieBreakWinner();
		}
		return null;
	}

	@Override
	public Player loser() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Player tieBreakWinner() {
		return (set.getGame().getPlayer1().getScoreTieBreak() > set.getGame().getPlayer2().getScoreTieBreak()) ? set.getGame().getPlayer1() : set.getGame().getPlayer2();
	}
	
//	public Player tieBreakLoser() {
//		return (set.getGame().getPlayer1().getScoreTieBreak() > set.getGame().getPlayer2().getScoreTieBreak()) ? set.getGame().getPlayer2() : set.getGame().getPlayer1();
//	}
	
	@Override
	public Results check() {
		if (set.getGame().getPlayer1().getScoreSet() == 6 && set.getGame().getPlayer2().getScoreSet() == 6) {
		
			currentResult = GAME_RESULT.TIEBREAK;
			
			set.getGame().getPlayer1().setTieBreak(true);
			set.getGame().getPlayer2().setTieBreak(true);
			
			if (tieBreakWinner().getScoreTieBreak() == 7
					&& Math.abs(set.getGame().getPlayer1().getScoreTieBreak() - set.getGame().getPlayer2().getScoreTieBreak()) >= 2) {
				currentResult = GAME_RESULT.WON;
			}
		}
		return new Results(TYPE.TIEBREAK, this);
	}

}
