package de.htwg.se.arimaa.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.arimaa.model.impl.Figure;
import de.htwg.se.arimaa.model.impl.Player;
import de.htwg.se.arimaa.util.position.Position;
import jdk.net.NetworkPermission;

public class PlayerTest {
	IPlayer player;
	List<IFigure> figures;

	@Before
	public void setUp() throws Exception {
		figures = new ArrayList<>();
		figures.add(new Figure(new Position(0, 0), FIGURE_NAME.R));
		figures.add(new Figure(new Position(1, 0), FIGURE_NAME.R));

		player = new Player(PLAYER_NAME.GOLD, figures);
	}

	@Test
	public void testGetName() {
		assertEquals(PLAYER_NAME.GOLD, player.getPlayerName());
	}

	@Test
	public void testMoveFigure() {
		player.moveFigure(new Position(1, 0), new Position(1,1));

		assertEquals(null, player.getFigure(new Position(1, 0)));
		assertEquals(FIGURE_NAME.R, player.getFigure(new Position(1,1)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMoveFigureException() {
		player.moveFigure(new Position(1,1), new Position(1, 0));
	}

	@Test
	public void testGetFigure() {
		assertEquals(FIGURE_NAME.R, player.getFigure(new Position(0, 0)));
		assertEquals(null, player.getFigure(new Position(3, 4)));

		player.disableFigure(new Position(0, 0));
		assertEquals(null, player.getFigure(new Position(0, 0)));
	}

	@Test
	public void testDisableFigure() {
		assertFalse(player.disableFigure(new Position(0, 1)));
		assertTrue(player.disableFigure(new Position(0, 0)));
		assertEquals(null, player.getFigure(new Position(0, 0)));
	}

	@Test
	public void testGetFigures() {
		player.disableFigure(new Position(0,0));
		List<IFigure> figuresList  = player.getFigures();
		IFigure figure = new Figure(new Position(0, 0), FIGURE_NAME.R);
		
		assertFalse(figuresList.contains(figure));
	}
}
