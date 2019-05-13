package com.tennis.model;

import java.util.Arrays;
import java.util.List;

public class Player {
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Player.class);

	public static final List<Integer> pointsTranslated = Arrays.asList(0, 15, 30, 40);

	private int scoreGame;
	private int scoreSet;
	private boolean isTieBreak;
	private int scoreTieBreak;
	private String name;

	public Player(String name) {
		this.name = name;
	}

	public int getScoreGame() {
		return scoreGame;
	}

	public int getScoreSet() {
		return scoreSet;
	}

	public String getName() {
		return name;
	}
	
	
	public boolean isTieBreak() {
		return isTieBreak;
	}

	public void setTieBreak(boolean isTieBreak) {
		this.isTieBreak = isTieBreak;
	}

	public int getScoreTieBreak() {
		return scoreTieBreak;
	}

	public void winPoint() {
		if (isTieBreak) {
			this.scoreTieBreak = this.scoreTieBreak + 1;
			log.info("{} : TieBreak score {}" ,getName(), scoreTieBreak);
			
		} else {
			this.scoreGame = this.scoreGame + 1;
			log.info("{} : {}" ,getName(), getScoreTranslated());
			
		}
	}

	public void winSet() {
		this.scoreSet = this.scoreSet + 1;
		log.info("{} : Set {}" ,getName(), scoreSet);
	}
	

	public int getScoreTranslated() {
		int max = pointsTranslated.size();
		return scoreGame < max ? pointsTranslated.get(scoreGame) : pointsTranslated.get(max - 1);
	}

}
