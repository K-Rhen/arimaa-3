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

	}
}
