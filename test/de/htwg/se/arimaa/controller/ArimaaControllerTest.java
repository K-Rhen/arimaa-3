package de.htwg.se.arimaa.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class ArimaaControllerTest {

	private IArimaaController controller;
	@Before
	public void setUp() throws Exception {
		controller = IArimaaControllerFactory.getInstance();
	}

	@Test
	public void testgetActualPlayer() {
		int ap = controller.getActualPlayer();
		assertTrue(ap == 1);
		assertTrue(controller.getNextPlayer() == 2);
		controller.changePlayer();
		assertTrue(controller.getNextPlayer() == 1);

	}
	@Test
	public void testgetMoves(){
		assertEquals(controller.getMoveCounter(), 4);
	}
	@Test
	public void testgetgamestatus(){
		controller.getGameStatus();
	}
	@Test
	public void testarimmaExit(){
		controller.arimaaExit();
		controller.ShowPitch();
	}
	
	@Test
	public void testreducemove(){
		assertTrue(controller.moveFigureByString(1, "a7-a6"));
		assertTrue(controller.moveFigureByString(2, "a2-a3"));

		assertTrue(controller.moveFigureByString(2, "a3-b3"));
	}
}
