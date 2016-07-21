package de.htwg.se.arimaa.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.se.arimaa.arimaa.ArimaaModule;


public class ArimaaControllerTest {

	private IArimaaController controller;
	@Before
	public void setUp() throws Exception {
		Injector injector = Guice.createInjector(new ArimaaModule());
		controller = injector.getInstance(IArimaaController.class);
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
	public void testgetMoves(){
		assertEquals(controller.getRemainingMoves(), 4);
	}
	@Test
	public void testgetgamestatus(){
		controller.getGameStatus();
	}
	@Test
	public void testarimmaExit(){
		controller.arimaaExit();
		controller.CurrentPitchView();
	}
	
	@Test
	public void testreducemove(){
		assertTrue(controller.moveFigureByString(1, "a7-a6"));
		assertTrue(controller.moveFigureByString(2, "a2-a3"));

		assertTrue(controller.moveFigureByString(2, "a3-b3"));
	}
}
