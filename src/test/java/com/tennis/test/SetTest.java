package com.tennis.test;

import static org.junit.Assert.assertEquals;

import java.util.stream.IntStream;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.tennis.model.Game;
import com.tennis.model.IGame.GAME_RESULT;
import com.tennis.model.Player;
import com.tennis.model.Referee;
import com.tennis.model.Set;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SetTest {
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(SetTest.class);

	private Player player1;
	private Player player2;
	private Game game;
	private Set set;
	private Referee referee;

	@Before
	public void beforeGameTest() {
		player1 = new Player("player1");
		player2 = new Player("player2");
		game = new Game(player1, player2);
		set = new Set(game);
		referee = new Referee();

	}

	@Test
	// The set starts with a score of 0 Game for each player
	public void CaseA() {
		set.check();
		assertEquals(set.getCurrentResult(), GAME_RESULT.IN_PROGRESS);
	}

	@Test
	// the Set score changes to 4:
	public void CaseB() {
		IntStream.rangeClosed(1, 4).forEach((Integer) -> {
			referee.winSet(set, player1);
		});
		assertEquals(set.getCurrentResult(), GAME_RESULT.IN_PROGRESS);
		assertEquals(set.getGame().getPlayer1().getScoreSet(), 4);
	}

	@Test
	// If a player reach the Set score of 6 and the other player has a Set score of
	// 4 or lower, the player win the Set
	public void CaseC() {
		log.info("player1 reaches the Set score of 6 and player2 has a Set score of 4");
		IntStream.rangeClosed(1, 6).forEach(i -> {
			if ( i <= 2 )
				referee.winSet(set, player1);
			else {
				referee.winSet(set, player1);
				referee.winSet(set, player2);
			}
		});
		assertEquals(set.getCurrentResult(), GAME_RESULT.WON);
		assertEquals(set.winner(), player1);
	}

	@Test
	// If a player wins a Game and reach the Set score of 6 and the other player has
	// a Set score of 5, a new Game must be played and the first player who reach
	// the score of 7 wins the match
	public void caseD() {
		IntStream.rangeClosed(1, 6).forEach(i -> {
			if ( i == 1 )
				referee.winSet(set, player1);
			else {
				referee.winSet(set, player1);
				referee.winSet(set, player2);
			}
		});
		referee.winSet(set, player1);
		assertEquals(set.winner(), player1);
	}
}
