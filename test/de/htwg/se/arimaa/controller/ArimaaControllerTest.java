package de.htwg.se.arimaa.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.se.arimaa.arimaa.ArimaaModule;
import de.htwg.se.arimaa.model.FIGURE_NAME;
import de.htwg.se.arimaa.model.IPitch;
import de.htwg.se.arimaa.model.PLAYER_NAME;
import de.htwg.se.arimaa.model.impl.Pitch;
import de.htwg.se.arimaa.util.position.Position;

public class ArimaaControllerTest {

	private IArimaaController controller;
	private Injector injector = Guice.createInjector(new ArimaaModule());

	@Before
	public void setUp() throws Exception {
		controller = injector.getInstance(IArimaaController.class);
	}

	@Test
	public void testCreate() {
		controller.createNewGame();
		assertEquals(GameStatus.CREATE, controller.getGameStatus());
	}

	@Test
	public void testGetCurrentPlayer() {
		assertEquals(PLAYER_NAME.GOLD, controller.getCurrentPlayerName());
	}

	@Test
	public void testGetRemainingMoves() {
		assertEquals(controller.getRemainingMoves(), 4);
	}

	@Test
	public void testChangePlayer() {
		controller.changePlayer();
		assertEquals(PLAYER_NAME.SILVER, controller.getCurrentPlayerName());
		controller.changePlayer();
		assertEquals(PLAYER_NAME.GOLD, controller.getCurrentPlayerName());
	}

	@Test
	public void testGetStatusText() {
		assertEquals("", controller.getStatusText());
	}

	@Test
	public void testArimmaExit() {
		controller.quitGame();
		controller.currentPitchView();
	}

	@Test
	public void testReduceMove() {
		assertTrue(controller.moveFigure(new Position(0, 6), new Position(0, 5)));
		assertEquals(3, controller.getRemainingMoves());
		assertTrue(controller.moveFigure(new Position(0, 5), new Position(0, 4)));
		assertEquals(2, controller.getRemainingMoves());
		assertTrue(controller.moveFigure(new Position(0, 4), new Position(0, 3)));
		assertEquals(1, controller.getRemainingMoves());
		assertTrue(controller.moveFigure(new Position(0, 3), new Position(0, 2)));
		assertEquals(0, controller.getRemainingMoves());

		// false, because no moves remain
		assertFalse(controller.moveFigure(new Position(0, 2), new Position(1, 2)));
		assertEquals(0, controller.getRemainingMoves());
	}

	@Test
	public void testMoveFigure() {
		// move gold figure
		assertTrue(controller.moveFigure(new Position(0, 6), new Position(0, 5)));
		assertFalse(controller.moveFigure(new Position(0, 6), new Position(0, 5)));

		controller.changePlayer();

		// move silver figure
		assertTrue(controller.moveFigure(new Position(0, 1), new Position(0, 2)));
		assertFalse(controller.moveFigure(new Position(0, 1), new Position(0, 2)));
	}

	@Test
	public void testGetFigureNamebyPosition() {
		assertEquals(FIGURE_NAME.R, controller.getFigureNamebyPosition(new Position(0, 6)));
		assertEquals(FIGURE_NAME.R, controller.getFigureNamebyPosition(new Position(0, 1)));

		assertEquals(null, controller.getFigureNamebyPosition(new Position(0, 3)));
	}

	@Test
	public void testGetPlayerNamebyPosition() {
		assertEquals(PLAYER_NAME.GOLD, controller.getPlayerNamebyPosition(new Position(0, 6)));
		assertEquals(PLAYER_NAME.SILVER, controller.getPlayerNamebyPosition(new Position(0, 1)));
		
		assertEquals(null, controller.getPlayerNamebyPosition(new Position(0, 2)));
	}

}
