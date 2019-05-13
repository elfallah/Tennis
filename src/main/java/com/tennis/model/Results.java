package com.tennis.model;

import lombok.Data;

@Data
public class Results {
	
	private TYPE type;
	private IGame game;
	
	public Results(TYPE type, IGame game) {
		super();
		this.type = type;
		this.game = game;
	}
	
	public String toString() {
		return type.name() + " " + game.getCurrentResult().name() + (game.getCurrentResult().name().equals("WON") ? (" by " + game.winner().getName()) : "") ;
	}
	
	public enum TYPE {
		GAME,
		SET,
		TIEBREAK
	}
	
}
