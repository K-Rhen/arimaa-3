package de.htwg.se.arimaa.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.se.arimaa.arimaa.ArimaaModule;
import de.htwg.se.arimaa.controller.impl.PLAYER_NAME;
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
	public void testgetCurrentPlayer() {
		assertEquals(PLAYER_NAME.GOLD, controller.getCurrentPlayer());
	}

	@Test
	public void testgetRemainingMoves() {
		assertEquals(controller.getRemainingMoves(), 4);
	}
	
	@Test
	public void testChangePlayer(){
		controller.changePlayer();
		assertEquals(PLAYER_NAME.SILVER, controller.getCurrentPlayer());
		controller.changePlayer();
		assertEquals(PLAYER_NAME.GOLD, controller.getCurrentPlayer());
	}

	@Test
	public void testArimmaExit() {
		controller.arimaaExit();
		controller.CurrentPitchView();
	}

	@Test
	public void testMoveFigure(){
		System.out.println(controller.CurrentPitchView());
		assertTrue(controller.moveFigure(PLAYER_NAME.GOLD, new Position(0,6),new Position(0,5)));
		System.out.println(controller.CurrentPitchView());
	}
	
	@Test
	public void testReduceMove() {
//		System.out.println(controller.CurrentPitchView());
//		assertTrue(controller.moveFigure(PLAYER_NAME.GOLD, new Position(0,6),new Position(0,5)));
//		System.out.println(controller.CurrentPitchView());
//		assertEquals(3, controller.getRemainingMoves());
//		assertTrue(controller.moveFigure(PLAYER_NAME.GOLD, new Position(0,5),new Position(0,4)));
//		assertEquals(2, controller.getRemainingMoves());
//		assertTrue(controller.moveFigure(PLAYER_NAME.GOLD, new Position(0,4),new Position(0,3)));
//		assertEquals(1, controller.getRemainingMoves());
//		assertTrue(controller.moveFigure(PLAYER_NAME.GOLD, new Position(0,3),new Position(0,2)));
//		assertEquals(0, controller.getRemainingMoves());
//		
//		assertFalse(controller.moveFigure(PLAYER_NAME.GOLD, new Position(0,2),new Position(1,2)));
//		assertEquals(0, controller.getRemainingMoves());
	}
}
