package de.htwg.se.arimaa.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.arimaa.model.impl.FIGURE_NAME;
import de.htwg.se.arimaa.model.impl.Figure;
import de.htwg.se.arimaa.model.impl.Player;
import de.htwg.se.arimaa.util.character.Position;

public class PlayerTest {
	IPlayer player;
	String playerName;
	Position pos;

	ArrayList<IFigure> figures;
	@Before
	public void setUp() throws Exception {
		playerName = "Alias";
		pos = new Position (0,0);
		
		figures = new ArrayList<>();
		figures.add(new Figure(new Position(0, 0), FIGURE_NAME.R));
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
		assertTrue(player.moveFigure(start, end));	
	}
	
	@Test
	public void testsetFigureChangePositonFalse() {
		Position start = new Position(0,1);
		Position end = new Position(0,1);
		assertFalse(player.moveFigure(start, end));	
	}
	@Test
	public void testgetFigures(){
		assertEquals( figures, player.getFigures());
	}
	@Test
	public void testgetFigure(){
		assertEquals(FIGURE_NAME.R, player.getFigure(new Position(0, 0)) );
		assertEquals(player.getFigure(new Position(3, 4)), null);
	}
	
	@Test
	public void testdeleteFigure(){
		assertTrue(player.deleteFigure(new Position(0, 0)));
		assertFalse(player.deleteFigure(new Position(0, 0)));
	}
}
