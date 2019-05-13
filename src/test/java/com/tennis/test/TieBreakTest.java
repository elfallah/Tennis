package com.tennis.test;

import static org.junit.Assert.assertEquals;

import java.util.stream.IntStream;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.tennis.model.Game;
import com.tennis.model.Player;
import com.tennis.model.Referee;
import com.tennis.model.Set;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TieBreakTest {
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(TieBreakTest.class);
	
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
	// After the score of set is 6 for each player, Player 1 wins 1 point
	public void CaseA() {
		// 2 players reach the score of 6 sets
		IntStream.rangeClosed(1, 6).forEach((Integer) -> {
			referee.winSet(set, player1);
			referee.winSet(set, player2);
		});
		
		// Player 1 wins 1 point
		referee.winPoint(game, player1);
		// Expected Tie Break score=1
		int result = player1.getScoreTieBreak();
		Assertions.assertThat(result).isEqualTo(1);
	}

	@Test
	// The Tie-Break ends as soon as a player gets a least 7 points and 2 points
	// more than his opponent
	public void CaseOne() {
		// 2 players reach the score of 6 sets
		IntStream.rangeClosed(1, 6).forEach((Integer) -> {
			referee.winSet(set, player1);
			referee.winSet(set, player2);
		});
		
		// the Tie-Break rule is activated
		// Player 1 wins 7 point
		// Player 2 wins 5 point
		
		IntStream.rangeClosed(1, 7).forEach(i -> {
			if ( i <= 2 ) 
				referee.winPoint(game, player1);
			else {
				referee.winTieBreakPoint(set.getTieBreak(), player1);
				referee.winTieBreakPoint(game, player2);
			}
		});
		
		assertEquals(set.getTieBreak().winner(), player1);
		int result = player1.getScoreTieBreak();
		Assertions.assertThat(result).isEqualTo(7);
	}

}
