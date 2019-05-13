package com.tennis.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.tennis.model.Player;
import com.tennis.model.Referee;
import com.tennis.model.Game;
import static org.hamcrest.Matchers.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GameTest {
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(GameTest.class);

	Player player1;
	Player player2;
	Game game;
	Referee referee;

	@Before
	public void beforeGameTest() {
		player1 = new Player("player1");
		player2 = new Player("player2");
		game = new Game(player1, player2);
		referee = new Referee();
	}

	@Test
	// The score is: 0-0
	public void CaseA() {
		Game game = new Game(player1, player2);
		assertEquals(game.getPlayer1().getScoreTranslated(), 0);
		assertEquals(game.getPlayer2().getScoreTranslated(), 0);
	}

	@Test
	// The score is: 15-0
	public void CaseB() {
		referee.winPoint(game, player1);
		assertEquals(game.getPlayer1().getScoreTranslated(), 15);
		assertEquals(game.getPlayer2().getScoreTranslated(), 0);
		assertEquals(game.getCurrentResult(), Game.GAME_RESULT.IN_PROGRESS);
	}

	@Test
	// The score is: 30-0
	public void CaseC() {
		referee.winPoint(game, player1);
		referee.winPoint(game, player1);
		assertEquals(game.getPlayer1().getScoreTranslated(), 30);
		assertEquals(game.getPlayer2().getScoreTranslated(), 0);
		assertEquals(game.getCurrentResult(), Game.GAME_RESULT.IN_PROGRESS);
	}

	@Test
	// The score is: 40-0
	public void caseD() {
		IntStream.rangeClosed(1, 3).forEach((Integer) -> {
			referee.winPoint(game, player1);
		});
		assertEquals(game.getPlayer1().getScoreTranslated(), 40);
		assertEquals(game.getPlayer2().getScoreTranslated(), 0);
		assertEquals(game.getCurrentResult(), Game.GAME_RESULT.IN_PROGRESS);
	}

	@Test
	// If the 2 players reach the score 40, the DEUCE rule is activated
	public void caseE() {
		log.info("Players reach the score 40, DEUCE rule is activated");
		IntStream.rangeClosed(1, 4).forEach((Integer) -> {
			referee.winPoint(game, player1);
			referee.winPoint(game, player2);
		});
		assertEquals(game.getCurrentResult(), Game.GAME_RESULT.DEUCE);
	}

	@Test
	// If the score is DEUCE , the player who win the point take the ADVANTAGE
	public void caseF() {
		log.info("Players reach the score 40, DEUCE rule is activated");
		IntStream.rangeClosed(1, 4).forEach((Integer) -> {
			referee.winPoint(game, player1);
			referee.winPoint(game, player2);
		});
		assertEquals(game.getCurrentResult(), Game.GAME_RESULT.DEUCE);
		referee.winPoint(game, player1);
		assertEquals(game.getCurrentResult(), Game.GAME_RESULT.ADVANTAGE);
		
	}

	@Test
	public void caseG() {
		IntStream.rangeClosed(1, 4).forEach((Integer) -> {
			referee.winPoint(game, player1);
			referee.winPoint(game, player2);
		});
		assertEquals(game.getCurrentResult(), Game.GAME_RESULT.DEUCE);
		referee.winPoint(game, player1);
		assertEquals(game.getCurrentResult(), Game.GAME_RESULT.ADVANTAGE);
		referee.winPoint(game, player2);
		assertEquals(game.getCurrentResult(), Game.GAME_RESULT.DEUCE);
		referee.winPoint(game, player1);
		assertEquals(game.getCurrentResult(), Game.GAME_RESULT.ADVANTAGE);
		referee.winPoint(game, player1);
		assertEquals(game.getCurrentResult(), Game.GAME_RESULT.WON);
		assertEquals(game.winner(), player1);
	}

}
