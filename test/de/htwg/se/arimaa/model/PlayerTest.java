package de.htwg.se.arimaa.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.arimaa.util.character.Position;

public class PlayerTest {
	Player player;
	String playerName;
	Position pos;

	@Before
	public void setUp() throws Exception {
		playerName = "Alias";
		pos = new Position (0,0);
		
		ArrayList<Character> figures = new ArrayList<>();
		figures.add(new Character(new Position(0, 0), CHARAKTER_NAME.R));
		player = new Player(playerName,figures);
	}

	@Test
	public void testsetFigur() {
		player.setFigur(CHARAKTER_NAME.R, pos);
		assertEquals(CHARAKTER_NAME.R, player.getFigur(pos));
	}

	@Test
	public void testgetFigur() {
		player.setFigur(CHARAKTER_NAME.R, pos);
		assertEquals(CHARAKTER_NAME.R, player.getFigur(pos));
		
		assertEquals(null, player.getFigur(new Position(1,0)));
	}

	@Test
	public void testgetName() {
		assertEquals(playerName, player.getPlayerName());
	}
	
	@Test
	public void testsetFigureChangePositonTrue() {
		Position start = new Position(0,0);
		Position end = new Position(0,1);
		assertTrue(player.setFigureChangePositon(start, end));	
	}
	
	@Test
	public void testsetFigureChangePositonFalse() {
		Position start = new Position(0,5);
		Position end = new Position(0,1);
		assertFalse(player.setFigureChangePositon(start, end));	
	}
}
