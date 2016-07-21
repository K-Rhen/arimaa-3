package de.htwg.se.arimaa.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.se.arimaa.arimaa.ArimaaModule;
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
		controller.create();
		assertEquals(GameStatus.CREATE, controller.getGameStatus());
	}

	@Test
	public void testgetActualPlayer() {
		int ap = controller.getCurrentPlayer();
		assertTrue(ap == 1);
		assertTrue(controller.getNextPlayer() == 2);
		controller.changePlayer();
		assertTrue(controller.getNextPlayer() == 1);

	}

	@Test
	public void testgetMoves() {
		assertEquals(controller.getRemainingMoves(), 4);
	}

	@Test
	public void testarimmaExit() {
		controller.arimaaExit();
		controller.CurrentPitchView();
	}

	@Test
	public void testReduceMove() {
		assertTrue(controller.moveFigure(1, new Position(0,1),new Position(0,2)));
		assertEquals(3, controller.getRemainingMoves());
		assertTrue(controller.moveFigure(1, new Position(0,2),new Position(0,3)));
		assertEquals(2, controller.getRemainingMoves());
		assertTrue(controller.moveFigure(1, new Position(0,3),new Position(0,4)));
		assertEquals(1, controller.getRemainingMoves());
		assertTrue(controller.moveFigure(1, new Position(0,4),new Position(0,5)));
		assertEquals(0, controller.getRemainingMoves());
		System.out.println(controller.CurrentPitchView());
		assertFalse(controller.moveFigure(1, new Position(0,5),new Position(1,5)));
		assertEquals(0, controller.getRemainingMoves());
	}
}
