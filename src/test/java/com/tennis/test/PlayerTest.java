package com.tennis.test;

import static org.hamcrest.Matchers.*;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.stream.IntStream;

import org.junit.Test;

import com.tennis.model.Player;

public class PlayerTest {

	@Test
	public void pointsCanBeAddedToEachPlayer() {
		Player player1 = new Player("player1");
		Player player2 = new Player("player2");
		IntStream.rangeClosed(1, 3).forEach((Integer) -> {
			player1.winPoint();
		});
		IntStream.rangeClosed(1, 4).forEach((Integer) -> {
			player2.winPoint();
		});
		assertThat(player1, hasProperty("scoreGame", is(3)));
		assertThat(player2, hasProperty("scoreGame", is(4)));
	}

}
