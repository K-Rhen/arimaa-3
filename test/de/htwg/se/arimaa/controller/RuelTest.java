package de.htwg.se.arimaa.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.se.arimaa.arimaa.ArimaaModule;
import de.htwg.se.arimaa.util.position.Position;

public class RuelTest {

	private IArimaaController controller;
	private Injector injector = Guice.createInjector(new ArimaaModule());

	@Before
	public void setUp() throws Exception {
		controller = injector.getInstance(IArimaaController.class);
	}

	@Test
	public void testFromPositionIsEmpty() {
		assertFalse(controller.moveFigure(new Position(0, 5), new Position(0, 6)));
		assertEquals(GameStatus.PRECONDITIONRULES_VIOLATED, controller.getGameStatus());
	}

	@Test
	public void testToPositionIsOccupied() {
		assertFalse(controller.moveFigure(new Position(0, 6), new Position(1, 6)));
		assertEquals(GameStatus.PRECONDITIONRULES_VIOLATED, controller.getGameStatus());
	}

	@Test
	public void testNoMoveRemain() {
		assertTrue(controller.moveFigure(new Position(0, 6), new Position(0, 5)));
		assertTrue(controller.moveFigure(new Position(0, 5), new Position(0, 4)));
		assertTrue(controller.moveFigure(new Position(0, 4), new Position(0, 3)));
		assertTrue(controller.moveFigure(new Position(0, 3), new Position(0, 2)));
		assertFalse(controller.moveFigure(new Position(0, 2), new Position(1, 2)));

		assertEquals(GameStatus.PRECONDITIONRULES_VIOLATED, controller.getGameStatus());
	}

	@Test
	public void testIsHold() {
		// Gold Camel
		assertTrue(controller.moveFigure(new Position(3, 6), new Position(3, 5)));
		assertTrue(controller.moveFigure(new Position(3, 5), new Position(3, 4)));
		controller.changePlayer();
		// Silver Elephant
		assertTrue(controller.moveFigure(new Position(3, 1), new Position(3, 2)));
		assertTrue(controller.moveFigure(new Position(3, 2), new Position(3, 3)));

		controller.changePlayer();
		// Gold camel wants to go right
		assertFalse(controller.moveFigure(new Position(3, 4), new Position(4, 4)));
		assertEquals(GameStatus.PRECONDITIONRULES_VIOLATED, controller.getGameStatus());
		// Gold Cat came to help
		assertTrue(controller.moveFigure(new Position(2, 6), new Position(3, 6)));
		assertTrue(controller.moveFigure(new Position(3, 6), new Position(3, 5)));

		// Gold Camel can no go right
		assertTrue(controller.moveFigure(new Position(3, 4), new Position(4, 4)));
		assertEquals(GameStatus.MOVEFIGURE, controller.getGameStatus());

		controller.changePlayer();
		// Silver Camel go down
		assertTrue(controller.moveFigure(new Position(4, 1), new Position(4, 2)));
		assertTrue(controller.moveFigure(new Position(4, 2), new Position(4, 3)));

		controller.changePlayer();
		// Gold Camel go left
		assertTrue(controller.moveFigure(new Position(4, 4), new Position(5, 4)));
		assertEquals(GameStatus.MOVEFIGURE, controller.getGameStatus());

		controller.changePlayer();
		// Silver Elephant go down
		assertTrue(controller.moveFigure(new Position(3, 3), new Position(3, 4)));

		controller.changePlayer();
		// Gold Camel go left
		assertTrue(controller.moveFigure(new Position(5, 4), new Position(4, 4)));
		// Gold Camel cant go right
		assertFalse(controller.moveFigure(new Position(4, 4), new Position(4, 5)));
		assertEquals(GameStatus.PRECONDITIONRULES_VIOLATED, controller.getGameStatus());
		// Gold Elephants come help
		assertTrue(controller.moveFigure(new Position(4, 6), new Position(4, 5)));
		// Gold Camel no can go right
		assertTrue(controller.moveFigure(new Position(4, 4), new Position(5, 4)));
		assertEquals(GameStatus.MOVEFIGURE, controller.getGameStatus());
	}

	@Test
	public void testIsPulled() {
		// Gold Elephant go up
		assertTrue(controller.moveFigure(new Position(4, 6), new Position(4, 5)));
		assertTrue(controller.moveFigure(new Position(4, 5), new Position(4, 4)));
		controller.changePlayer();
		// Silver Camel go down
		assertTrue(controller.moveFigure(new Position(4, 1), new Position(4, 2)));
		assertTrue(controller.moveFigure(new Position(4, 2), new Position(4, 3)));
		controller.changePlayer();

		// Gold Elephant go right
		assertTrue(controller.moveFigure(new Position(4, 4), new Position(5, 4)));
		//Gold Elephant would pull Silver camel left
		assertFalse(controller.moveFigure(new Position(4, 3), new Position(3, 3)));
		assertEquals(GameStatus.PRECONDITIONRULES_VIOLATED, controller.getGameStatus());
		// Gold Elephant pull Silver camel down
		assertTrue(controller.moveFigure(new Position(4, 3), new Position(4, 4)));
		assertEquals(GameStatus.MOVEFIGURE, controller.getGameStatus());
		assertEquals(2, controller.getRemainingMoves());

		// Gold Elephant go right
		assertTrue(controller.moveFigure(new Position(5, 4), new Position(6, 4)));
		// Gold Elephant pull Silver camel right
		assertTrue(controller.moveFigure(new Position(4, 4), new Position(5, 4)));
		assertEquals(GameStatus.MOVEFIGURE, controller.getGameStatus());
		assertEquals(0, controller.getRemainingMoves());
	}

	@Test
	public void testToPositionPossibleMove() {
		assertTrue(controller.moveFigure(new Position(0, 6), new Position(0, 5)));
		assertEquals(GameStatus.MOVEFIGURE, controller.getGameStatus());

		// move a figure a grater distance than 1
		assertFalse(controller.moveFigure(new Position(0, 5), new Position(0, 2)));
		assertEquals(GameStatus.PRECONDITIONRULES_VIOLATED, controller.getGameStatus());

		// other player figure wants move and Figure is not pulled or pushed
		assertFalse(controller.moveFigure(new Position(0, 1), new Position(0, 2)));
		assertEquals(GameStatus.PRECONDITIONRULES_VIOLATED, controller.getGameStatus());
	}

}
