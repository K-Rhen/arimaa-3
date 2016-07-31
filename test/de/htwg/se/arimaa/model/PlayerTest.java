package de.htwg.se.arimaa.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.arimaa.model.impl.Figure;
import de.htwg.se.arimaa.model.impl.Player;
import de.htwg.se.arimaa.util.position.Position;

public class PlayerTest {
	IPlayer player;
	ArrayList<IFigure> figures;

	@Before
	public void setUp() throws Exception {
		figures = new ArrayList<>();
		figures.add(new Figure(new Position(0, 0), FIGURE_NAME.R));

		player = new Player(PLAYER_NAME.GOLD, figures);
	}

	@Test
	public void testGetName() {
		assertEquals(PLAYER_NAME.GOLD, player.getPlayerName());
	}

	@Test
	public void testMoveFigure() {
		Position start = new Position(0, 0);
		Position end = new Position(0, 1);
		player.moveFigure(start, end);
		assertEquals(null, player.getFigure(start));
		assertEquals(FIGURE_NAME.R, player.getFigure(end));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testMoveFigureException(){
		Position start = new Position(0, 1);
		Position end = new Position(0, 0);
		
		player.moveFigure(start, end);
	}

	@Test
	public void testGetFigure() {
		assertEquals(FIGURE_NAME.R, player.getFigure(new Position(0, 0)));
		assertEquals(player.getFigure(new Position(3, 4)), null);
	}

	@Test
	public void testDeleteFigure() {
		figures.add(new Figure(new Position(1, 1), FIGURE_NAME.R));

		assertTrue(player.deleteFigure(new Position(1, 1)));
		assertFalse(player.deleteFigure(new Position(1, 1)));
	}
	
	@Test
	public void testGetFigures(){
		assertEquals(figures, player.getFigures());
	}
}
