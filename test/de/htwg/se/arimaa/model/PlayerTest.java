package de.htwg.se.arimaa.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.arimaa.model.Character.CHARAKTER_NAME;
import de.htwg.se.arimaa.util.character.Position;

public class PlayerTest {
	Player player;
	String playerName;
	Position pos;

	@Before
	public void setUp() throws Exception {
		playerName = "Alias";
		pos = new Position (0,0);
		player = new Player(playerName);
	}

	@Test
	public void testsetFigur() {
		player.setFigur(CHARAKTER_NAME.RABBIT, pos);
		assertEquals(CHARAKTER_NAME.RABBIT, player.getFigur(pos));
	}

	@Test
	public void testgetFigur() {
		player.setFigur(CHARAKTER_NAME.RABBIT, pos);
		assertEquals(CHARAKTER_NAME.RABBIT, player.getFigur(pos));
		
		assertEquals(null, player.getFigur(new Position(1,0)));
	}

	@Test
	public void testgetName() {
		assertEquals(playerName, player.getPlayerName());
	}
}