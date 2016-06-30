package de.htwg.se.arimaa.controller;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.arimaa.controller.IArimaaController;


public class ArimaaControllerTest extends TestCase{

	IArimaaController controller;

	@Before
	public void setUp() throws Exception {
		controller = new ArimaaController();
	}

//	@Test
//	void testmoveFigur() {
		// success move
//		Position from = new Position(0, 1);
//		Position to = new Position(0, 2);
//		assertFalse(controller.getMoveCounter() == 4 );
//		assertTrue(controller.moveFigur(1, from, to));
//
//		// same position
//		from = new Position(0, 0);
//		to = new Position(0, 0);
//		assertFalse(controller.moveFigur(1, from, to));
//
//		// no free cell
//		from = new Position(0, 0);
//		to = new Position(1, 0);
//		assertFalse(controller.moveFigur(1, from, to));
//
//		// more than one move in x-axis
//		from = new Position(1, 0);
//		to = new Position(3, 0);
//		assertFalse(controller.moveFigur(1, from, to));
//
//		// more than one move in y-axis
//		from = new Position(0, 2);
//		to = new Position(2, 2);
//		assertFalse(controller.moveFigur(1, from, to));
//
//	}
}
