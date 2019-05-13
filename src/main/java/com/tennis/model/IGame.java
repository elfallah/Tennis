package com.tennis.model;

public interface IGame {
	
	public Player winner();
	
	public Player loser();
	
	public Results check();

	public Enum<GAME_RESULT> getCurrentResult();
	
	public enum GAME_RESULT {
		DEUCE,
		ADVANTAGE,
		WON,
		IN_PROGRESS,
		TIEBREAK
	}
}
