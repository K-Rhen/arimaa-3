package de.htwg.se.arimaa.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.arimaa.model.impl.CHARAKTER_NAME;
import de.htwg.se.arimaa.model.impl.Character;
import de.htwg.se.arimaa.model.impl.Player;
import de.htwg.se.arimaa.util.character.Position;

public class PlayerTest {
	IPlayer player;
	String playerName;
	Position pos;

	ArrayList<ICharacter> figures;
	@Before
	public void setUp() throws Exception {
		playerName = "Alias";
		pos = new Position (0,0);
		
		figures = new ArrayList<>();
		figures.add(new Character(new Position(0, 0), CHARAKTER_NAME.R));
		player = new Player(playerName, figures);
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
		Position start = new Position(0,1);
		Position end = new Position(0,1);
		assertFalse(player.setFigureChangePositon(start, end));	
	}
	@Test
	public void testgetFigures(){
		assertEquals( figures, player.getFigures());
	}
	@Test
	public void testgetFigure(){
		assertEquals(CHARAKTER_NAME.R, player.getFigur(new Position(0, 0)) );
		assertEquals(player.getFigur(new Position(3, 4)), null);
	}
	
	@Test
	public void testdeleteFigure(){
		assertTrue(player.deleteFigure(new Position(0, 0)));
		assertFalse(player.deleteFigure(new Position(0, 0)));
	}
}
