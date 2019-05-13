package com.tennis.model;

import com.tennis.model.IGame.GAME_RESULT;

public class Referee {
	
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Referee.class);
	
	private Results resuls;

	public Results winPoint(IGame game, Player player) {
		player.winPoint();
		return check(game);
	}
	
	public Results winSet(IGame game, Player player) {
		player.winSet();
		return check(game);
	}
	
	public void winTieBreakPoint(IGame game, Player player) {
		check(game);
		player.winPoint();
		check(game);
		
	}
	
	private Results check(IGame game) {
		if ( resuls != null && resuls.getGame().getCurrentResult() == GAME_RESULT.WON ) {
			log.info("{} already ended", resuls.getType() );
			return resuls;
		}
		else {
			resuls = game.check();
			if ( resuls.getType() != Results.TYPE.TIEBREAK)
				log.info(resuls.toString());
			return resuls;
		}
	}
	
}
